package com.dah.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dah.domain.Area;
import com.dah.domain.Article;
import com.dah.domain.Review;
import com.dah.domain.ReviewerProfile;
import com.dah.domain.SubmissionEvent;
import com.dah.domain.User;
import com.dah.domain.article.SubmittedState;
import com.dah.enums.ReviewOutcome;
import com.dah.enums.ReviewStatus;
import com.dah.observer.EventPublisher;
import com.dah.observer.events.ArticleConcludedEvent;
import com.dah.observer.events.ReviewAttentionEvent;
import com.dah.records.ArticleForReviewDTO;
import com.dah.records.ReviewData;
import com.dah.records.SubmitArticleData;
import com.dah.repository.ArticleRepository;
import com.dah.repository.ReviewRepository;
import com.dah.workflow.ReviewWorkflowContext;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArticleReviewService {

  private final ArticleRepository articleRepository;
  private final ReviewRepository reviewRepository;
  private final CommitteeService committeeService;
  private final EventService eventService;
  private final AccountService accountService;
  private final ReviewWorkflowContext workflowContext;
  private final EventPublisher eventPublisher;

  public Article submitArticle(SubmitArticleData data) {
    SubmissionEvent event = eventService.getCurrentEvent();

    if (!event.isSubmissionOpen()) {
      throw new IllegalStateException("Período de submissão encerrado.");
    }

    List<User> authors = resolveAuthors(data.authorIds());
    List<Area> areas = committeeService.findAreasByIds(data.areaIds());

    Article article = new Article(null, data.title(), data.abstractText(), authors, areas,
        new SubmittedState());

    return articleRepository.save(article);
  }

  public List<Article> listMyArticles() {
    return articleRepository.findByAuthor(accountService.getCurrentUser());
  }

  public List<ArticleForReviewDTO> listArticlesForReview(ReviewerProfile reviewer) {
    return reviewRepository.findByReviewer(reviewer).stream().map(review -> {
      Article article = review.getArticle();
      List<String> areas = article.getAreas().stream().map(a -> a.getName()).toList();

      return new ArticleForReviewDTO(article.getId(), article.getTitle(), article.getAbstractText(), areas);
    }).toList();
  }

  public List<Review> listAssignedReviews(ReviewerProfile reviewer) {
    return reviewRepository.findByReviewer(reviewer);
  }

  public void submitReview(ReviewData data) {
    Optional<Review> found = reviewRepository.findById(data.reviewId());

    if (found.isEmpty()) {
      throw new IllegalArgumentException(
          String.format("Review de id \"%d\" não existe para receber veredito!", data.reviewId()));
    }

    SubmissionEvent event = eventService.getCurrentEvent();

    if (LocalDate.now().isAfter(event.getReviewDeadline())) {
      throw new IllegalArgumentException("Prazo de revisão já passou!");
    }

    Review oldReview = found.get();

    Review newReview = new Review(oldReview.getId(), oldReview.getArticle(), oldReview.getReviewer(),
        data.contribution(),
        data.criticism(), data.verdict(), ReviewStatus.SUBMITTED);

    reviewRepository.save(newReview);

    List<Review> reviews = reviewRepository.findByArticle(oldReview.getArticle());

    ReviewOutcome reviewOutcome = workflowContext.getReviewPolicy().conclude(reviews);

    if (reviewOutcome == ReviewOutcome.ACCEPTED || reviewOutcome == ReviewOutcome.REJECTED) {
      eventPublisher.publish(new ArticleConcludedEvent(oldReview.getArticle(), reviewOutcome, reviews, event));
      return;
    }

    if (reviewOutcome == ReviewOutcome.ATTENTION) {
      eventPublisher.publish(new ReviewAttentionEvent(oldReview.getArticle(), reviews));
      return;
    }
  }

  private List<User> resolveAuthors(List<Integer> coauthorIds) {
    List<User> authors = new ArrayList<>();
    authors.add(accountService.getCurrentUser());

    if (coauthorIds != null) {
      for (Integer coauthorId : coauthorIds) {
        User coauthor = accountService.findUserById(coauthorId);
        if (!authors.contains(coauthor)) {
          authors.add(coauthor);
        }
      }
    }

    return authors;
  }
}

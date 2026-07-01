package com.dah.service;

import java.util.ArrayList;
import java.util.List;

import com.dah.domain.Area;
import com.dah.domain.Article;
import com.dah.domain.SubmissionEvent;
import com.dah.domain.User;
import com.dah.domain.article.SubmittedState;
import com.dah.observer.EventPublisher;
import com.dah.records.SubmitArticleData;
import com.dah.repository.ArticleRepository;
import com.dah.repository.ReviewRepository;
import com.dah.workflow.ReviewWorkflowContext;

public class ArticleReviewService {

    private final ArticleRepository articleRepository;
    private final ReviewRepository reviewRepository;
    private final CommitteeService committeeService;
    private final EventService eventService;
    private final AccountService accountService;
    private final ReviewWorkflowContext workflowContext;
    private final EventPublisher eventPublisher;

    public ArticleReviewService(ArticleRepository articleRepository, ReviewRepository reviewRepository,
            CommitteeService committeeService, EventService eventService, AccountService accountService,
            ReviewWorkflowContext workflowContext, EventPublisher eventPublisher) {
        this.articleRepository = articleRepository;
        this.reviewRepository = reviewRepository;
        this.committeeService = committeeService;
        this.eventService = eventService;
        this.accountService = accountService;
        this.workflowContext = workflowContext;
        this.eventPublisher = eventPublisher;
    }

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

    public List<Article> listMyArticles() {
        return articleRepository.findByAuthor(accountService.getCurrentUser());
    }
}

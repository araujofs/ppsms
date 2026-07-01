package com.dah.service;

import java.util.ArrayList;
import java.util.List;

import com.dah.domain.Article;
import com.dah.domain.Review;
import com.dah.domain.ReviewerProfile;
import com.dah.observer.EventPublisher;
import com.dah.observer.events.ArticleAssignedEvent;
import com.dah.repository.ArticleRepository;
import com.dah.repository.ReviewRepository;
import com.dah.repository.ReviewerRepository;
import com.dah.workflow.ReviewWorkflowContext;
import com.dah.workflow.distribution.DistributionStrategy;

public class DistributionService {

    private final ArticleRepository articleRepository;
    private final ReviewerRepository reviewerRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewWorkflowContext workflowContext;
    private final EventPublisher eventPublisher;

    public DistributionService(ArticleRepository articleRepository, ReviewerRepository reviewerRepository,
            ReviewRepository reviewRepository, ReviewWorkflowContext workflowContext,
            EventPublisher eventPublisher) {
        this.articleRepository = articleRepository;
        this.reviewerRepository = reviewerRepository;
        this.reviewRepository = reviewRepository;
        this.workflowContext = workflowContext;
        this.eventPublisher = eventPublisher;
    }

    public void distributeArticles() {
        List<Article> articlesToDistribute = findArticlesAwaitingDistribution();

        if (articlesToDistribute.isEmpty()) {
            return;
        }

        List<ReviewerProfile> reviewers = reviewerRepository.findAll();

        DistributionStrategy strategy = workflowContext.getDistributionStrategy();
        List<Review> reviews = strategy.distribute(articlesToDistribute, reviewers);

        reviewRepository.saveAll(reviews);

        for (Article article : articlesToDistribute) {
            article.startReview();
            articleRepository.save(article);
        }

        for (Review review : reviews) {
            eventPublisher.publish(new ArticleAssignedEvent(review));
        }
    }

    private List<Article> findArticlesAwaitingDistribution() {
        List<Article> pending = articleRepository.findPending();
        List<Article> awaitingDistribution = new ArrayList<>();

        for (Article article : pending) {
            if (reviewRepository.findByArticle(article).isEmpty()) {
                awaitingDistribution.add(article);
            }
        }

        return awaitingDistribution;
    }
}
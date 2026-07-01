package com.dah.service;

import com.dah.domain.Article;
import com.dah.domain.Review;
import com.dah.domain.SubmissionEvent;
import com.dah.records.CoordinatorDashboard;
import com.dah.records.PendingDetails;
import com.dah.records.StartEventData;
import com.dah.workflow.ReviewWorkflowContext;
import com.dah.workflow.factory.OneReviewerWorkflowFactory;
import com.dah.workflow.factory.ReviewWorkflowFactory;
import com.dah.workflow.factory.ThreeReviewerWorkflowFactory;
import com.dah.workflow.factory.TwoReviewerWorkflowFactory;
import com.dah.repository.ResettableRepository;
import com.dah.repository.SubmissionEventRepository;
import com.dah.repository.ArticleRepository;
import com.dah.repository.ReviewRepository;
import com.dah.repository.ReviewerRepository;


import java.util.ArrayList;
import java.util.List;

public class EventService {

    private final ReviewWorkflowContext workflowContext;

    private final SubmissionEventRepository eventRepository;
    private final List<ResettableRepository> eventScopedRepositories;

    private final ArticleRepository articleRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewerRepository reviewerRepository;


    public EventService(ReviewWorkflowContext workflowContext, SubmissionEventRepository eventRepository,
            List<ResettableRepository> eventScopedRepositories,
            ArticleRepository articleRepository, ReviewRepository reviewRepository,
            ReviewerRepository reviewerRepository) {
        this.workflowContext = workflowContext;
        this.eventRepository = eventRepository;
        this.eventScopedRepositories = eventScopedRepositories;
    }

    public SubmissionEvent startEvent(StartEventData data) {
        SubmissionEvent event = new SubmissionEvent(
                null,
                data.name(),
                data.city(),
                data.submissionDeadline(),
                data.start(),
                data.end(),
                data.category(),
                data.reviewersPerArticle());

        ReviewWorkflowFactory factory = selectFactory(data.reviewersPerArticle());
        workflowContext.configure(factory);

        eventScopedRepositories.forEach(ResettableRepository::deleteAll);

        return eventRepository.saveCurrent(event);
    }

    private ReviewWorkflowFactory selectFactory(int reviewersPerArticle) {
        return switch (reviewersPerArticle) {
            case 1 -> new OneReviewerWorkflowFactory();
            case 2 -> new TwoReviewerWorkflowFactory();
            case 3 -> new ThreeReviewerWorkflowFactory();
            default -> throw new IllegalArgumentException(
                    "Número de revisores por artigo inválido: " + reviewersPerArticle + ". Use 1, 2 ou 3.");
        };
    }

    public SubmissionEvent getCurrentEvent() {
        return eventRepository.findCurrent()
                .orElseThrow(() -> new IllegalStateException("Nenhum evento foi iniciado ainda."));
    }

    public CoordinatorDashboard getDashboard() {
        SubmissionEvent event = getCurrentEvent();

        List<Article> allArticles = articleRepository.findAll();
        List<Article> pendingArticles = articleRepository.findPending();
        List<Article> finishedArticles = articleRepository.findFinished();

        List<PendingDetails> pendingDetails = new ArrayList<>();

        for (Article article : pendingArticles) {
            List<Review> reviews = reviewRepository.findByArticle(article);

            for (Review review : reviews) {
                if (!review.isSubmitted()) {
                    pendingDetails.add(new PendingDetails(
                            article.getTitle(),
                            review.getReviewer().getResearcher().getEmail(),
                            event.getReviewDeadline()));
                }
            }
        }

        return new CoordinatorDashboard(
                allArticles.size(),
                reviewerRepository.findAll().size(),
                finishedArticles.size(),
                pendingArticles.size(),
                pendingDetails);
    }

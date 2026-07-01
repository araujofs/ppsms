package com.dah.service;

import com.dah.domain.SubmissionEvent;
import com.dah.records.StartEventData;
import com.dah.workflow.ReviewWorkflowContext;
import com.dah.workflow.factory.OneReviewerWorkflowFactory;
import com.dah.workflow.factory.ReviewWorkflowFactory;
import com.dah.workflow.factory.ThreeReviewerWorkflowFactory;
import com.dah.workflow.factory.TwoReviewerWorkflowFactory;


public class EventService {

    
    private final ReviewWorkflowContext workflowContext;

    // TODO (Heitor): quando as interfaces de repositório existirem, coloque aqui.
    // private final SubmissionEventRepository eventRepository;
    // private final List<ResettableRepository> eventScopedRepositories;

    public EventService(ReviewWorkflowContext workflowContext) {
        this.workflowContext = workflowContext;
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

        // 3) TODO (Heitor): limpar os dados do evento anterior (ResettableRepository)
        //    e salvar este evento no SubmissionEventRepository.
        //    Ex.: eventScopedRepositories.forEach(ResettableRepository::deleteAll);
        //         return eventRepository.saveCurrent(event);

        return event;
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

    // TODO (Heitor + integração): precisa do SubmissionEventRepository.
    // public SubmissionEvent getCurrentEvent() { ... }

    // TODO (integração): precisa dos repositórios de artigo/revisão para montar os números.
    // public CoordinatorDashboard getDashboard() { ... }
}

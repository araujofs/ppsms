package com.dah.workflow;

import com.dah.workflow.distribution.DistributionStrategy;
import com.dah.workflow.factory.ReviewWorkflowFactory;
import com.dah.workflow.review.ReviewPolicy;

public class ReviewWorkflowContext {

    private DistributionStrategy distributionStrategy;
    private ReviewPolicy reviewPolicy;

    public void configure(ReviewWorkflowFactory factory) {
        this.distributionStrategy = factory.createDistributionStrategy();
        this.reviewPolicy = factory.createReviewPolicy();
    }

    public DistributionStrategy getDistributionStrategy() {
        if (distributionStrategy == null) {
        throw new IllegalStateException("Configure a estratégia de distribuição!.");
        }
        return distributionStrategy;
    }

    public ReviewPolicy getReviewPolicy() {
        if (reviewPolicy == null) {
            throw new IllegalStateException("Configure a política de revisão!");
        }
        return reviewPolicy;
    }
}

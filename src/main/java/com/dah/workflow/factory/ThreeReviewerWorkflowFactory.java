package com.dah.workflow.factory;

import com.dah.workflow.distribution.DistributionStrategy;
import com.dah.workflow.distribution.strategies.ThreeReviewerDistributionStrategy;
import com.dah.workflow.review.ReviewPolicy;
import com.dah.workflow.review.strategies.ThreeReviewerPolicy;

public class ThreeReviewerWorkflowFactory implements ReviewWorkflowFactory {
    @Override
    public DistributionStrategy createDistributionStrategy() {
        return new ThreeReviewerDistributionStrategy();
    }

    @Override
    public ReviewPolicy createReviewPolicy() {
        return new ThreeReviewerPolicy();
    }
}

package com.dah.workflow.factory;

import com.dah.workflow.distribution.DistributionStrategy;
import com.dah.workflow.distribution.strategies.OneReviewerDistributionStrategy;
import com.dah.workflow.review.ReviewPolicy;
import com.dah.workflow.review.strategies.OneReviewerPolicy;

public class OneReviewerWorkflowFactory implements ReviewWorkflowFactory {
    @Override
    public DistributionStrategy createDistributionStrategy() {
        return new OneReviewerDistributionStrategy();
    }

    @Override
    public ReviewPolicy createReviewPolicy() {
        return new OneReviewerPolicy();
    }
}

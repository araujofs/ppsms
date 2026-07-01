package com.dah.workflow.factory;

import com.dah.workflow.distribution.DistributionStrategy;
import com.dah.workflow.distribution.strategies.TwoReviewerDistributionStrategy;
import com.dah.workflow.review.ReviewPolicy;
import com.dah.workflow.review.strategies.TwoReviewerPolicy;

public class TwoReviewerWorkflowFactory implements ReviewWorkflowFactory {
    @Override
    public DistributionStrategy createDistributionStrategy() {
        return new TwoReviewerDistributionStrategy();
    }

    @Override
    public ReviewPolicy createReviewPolicy() {
        return new TwoReviewerPolicy();
    }
}

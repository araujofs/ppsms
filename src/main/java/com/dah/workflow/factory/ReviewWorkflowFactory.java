package com.dah.workflow.factory;

import com.dah.workflow.distribution.DistributionStrategy;
import com.dah.workflow.review.ReviewPolicy;

public interface ReviewWorkflowFactory {
    DistributionStrategy createDistributionStrategy();
    ReviewPolicy createReviewPolicy();
}

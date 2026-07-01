package com.dah.workflow.distribution.strategies;
import com.dah.workflow.distribution.BalancedDistributionStrategy;

public class OneReviewerDistributionStrategy extends BalancedDistributionStrategy {
    @Override
    protected int reviewersPerArticle() {
        return 1;
    }
}
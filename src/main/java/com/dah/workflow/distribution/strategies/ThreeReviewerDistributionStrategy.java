package com.dah.workflow.distribution.strategies;
import com.dah.workflow.distribution.BalancedDistributionStrategy;

public class ThreeReviewerDistributionStrategy extends BalancedDistributionStrategy {
    @Override
    protected int reviewersPerArticle() {
        return 3;
    }

}

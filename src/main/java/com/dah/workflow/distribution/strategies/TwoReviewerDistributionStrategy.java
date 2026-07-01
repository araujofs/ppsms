package com.dah.workflow.distribution.strategies;
import com.dah.workflow.distribution.BalancedDistributionStrategy;

public class TwoReviewerDistributionStrategy extends BalancedDistributionStrategy {
    @Override
    protected int reviewersPerArticle() {
        return 2;
    }
    
}

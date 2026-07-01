package com.dah.workflow.review.strategies;

import java.util.List;

import com.dah.domain.Review;
import com.dah.enums.ReviewOutcome;
import com.dah.enums.Verdict;
import com.dah.workflow.review.ReviewPolicy;

public class TwoReviewerPolicy implements ReviewPolicy{
    @Override
    public int reviewersPerArticle() {
        return 2;
    }

    @Override
    public boolean isComplete(List<Review> reviews) {
        return reviews.size() == 2 && reviews.get(0).isSubmitted() && reviews.get(1).isSubmitted();
    }

    @Override
    public ReviewOutcome conclude(List<Review> reviews) {
        if (!isComplete(reviews)) {
            return ReviewOutcome.INCOMPLETE;
        }

        Verdict verdict1 = reviews.get(0).getVerdict();
        Verdict verdict2 = reviews.get(1).getVerdict();

        boolean bothAccepted = (verdict1 == Verdict.ACCEPTED || verdict1 == Verdict.WEAK_ACCEPTED)
        && (verdict2 == Verdict.ACCEPTED || verdict2 == Verdict.WEAK_ACCEPTED);

        boolean bothRejected = (verdict1 == Verdict.REJECTED || verdict1 == Verdict.WEAK_REJECT)
            && (verdict2 == Verdict.REJECTED || verdict2 == Verdict.WEAK_REJECT);

        if (bothAccepted) {
        return ReviewOutcome.ACCEPTED;
        }

        if (bothRejected) {
        return ReviewOutcome.REJECTED;
        }

        return ReviewOutcome.ATTENTION;
    
    }
}

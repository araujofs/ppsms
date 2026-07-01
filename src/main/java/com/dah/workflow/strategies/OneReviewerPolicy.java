package com.dah.workflow.strategies;

import com.dah.workflow.ReviewPolicy;
import com.dah.domain.Review;
import com.dah.enums.Verdict;
import com.dah.enums.ReviewOutcome;
import java.util.List;

public class OneReviewerPolicy implements ReviewPolicy {
    @Override
    public int reviewersPerArticle() {
        return 1;
    }

    @Override
    public boolean isComplete(List<Review> reviews) {
        return reviews.size() == 1 && reviews.get(0).isSubmitted();
    }

    @Override
    public ReviewOutcome conclude(List<Review> reviews) {
        if (!isComplete(reviews)) {
            return ReviewOutcome.INCOMPLETE;
        }

        Verdict verdict = reviews.get(0).getVerdict();
        boolean accepted = verdict == Verdict.ACCEPTED || verdict == Verdict.WEAK_ACCEPTED;

        return accepted ? ReviewOutcome.ACCEPTED : ReviewOutcome.REJECTED;
    }
}

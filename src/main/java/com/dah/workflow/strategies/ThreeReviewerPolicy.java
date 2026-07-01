package com.dah.workflow.strategies;

import java.util.List;

import com.dah.domain.Review;
import com.dah.enums.ReviewOutcome;
import com.dah.enums.Verdict;
import com.dah.workflow.ReviewPolicy;

public class ThreeReviewerPolicy implements ReviewPolicy {
    @Override
    public int reviewersPerArticle() {
        return 3;
    }

    @Override
    public boolean isComplete(List<Review> reviews) {
    return reviews.size() == 3 && reviews.stream().allMatch(Review::isSubmitted);
    }

    @Override
    public ReviewOutcome conclude(List<Review> reviews) {
    if (!isComplete(reviews)) {
        return ReviewOutcome.INCOMPLETE;
    }

    int acceptCount = 0;

    for (Review review : reviews) {
        Verdict verdict = review.getVerdict();

        if (verdict == Verdict.ACCEPTED || verdict == Verdict.WEAK_ACCEPTED) {
        acceptCount++;
        }
    }

    return acceptCount >= 2 ? ReviewOutcome.ACCEPTED : ReviewOutcome.REJECTED;
    }
}

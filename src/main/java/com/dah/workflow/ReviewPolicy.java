package com.dah.workflow;

import com.dah.domain.Review;
import com.dah.enums.ReviewOutcome;
import java.util.List;


public interface ReviewPolicy {
    int reviewersPerArticle();
    boolean isComplete(List<Review> reviews);
    ReviewOutcome conclude(List<Review> reviews);
}

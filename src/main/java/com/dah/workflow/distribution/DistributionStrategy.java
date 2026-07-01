package com.dah.workflow.distribution;

import java.util.List;

import com.dah.domain.Article;
import com.dah.domain.Review;
import com.dah.domain.ReviewerProfile;

public interface DistributionStrategy {
  List<Review> distribute(List<Article> articles, List<ReviewerProfile> reviewers);
}
package com.dah.workflow.distribution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dah.domain.Area;
import com.dah.domain.Article;
import com.dah.domain.Review;
import com.dah.domain.ReviewerProfile;

public abstract class BalancedDistributionStrategy implements DistributionStrategy {

    protected abstract int reviewersPerArticle();

    @Override
    public List<Review> distribute(List<Article> articles, List<ReviewerProfile> reviewers) {
        List<Review> reviews = new ArrayList<>();
        Map<ReviewerProfile, Integer> load = new HashMap<>();

        for (ReviewerProfile reviewer : reviewers) {
            load.put(reviewer, 0);
        }

        for (Article article : articles) {
            List<ReviewerProfile> selected = selectReviewers(article, reviewers, load);

            for (ReviewerProfile reviewer : selected) {
                Review review = new Review();
                review.setArticle(article);
                review.setReviewer(reviewer);

                reviews.add(review);
                load.put(reviewer, load.get(reviewer) + 1);
            }
        }

        return reviews;
    }

    private List<ReviewerProfile> selectReviewers(Article article, List<ReviewerProfile> reviewers,
            Map<ReviewerProfile, Integer> load) {

        List<ReviewerProfile> eligible = new ArrayList<>();

        for (ReviewerProfile reviewer : reviewers) {
            if (!article.getAuthors().contains(reviewer.getResearcher())) {
                eligible.add(reviewer);
            }
        }

        eligible.sort(
                Comparator.<ReviewerProfile>comparingInt(reviewer -> calculateCompatibility(article, reviewer))
                        .reversed()
                        .thenComparingInt(reviewer -> calculateReviewerLoad(reviewer, load)));

        int limit = Math.min(reviewersPerArticle(), eligible.size());

        return eligible.subList(0, limit);
    }

    private int calculateCompatibility(Article article, ReviewerProfile reviewer) {
        if (!reviewer.hasDefinedExpertiseAreas()) {
            return 0;
        }

        int compatibility = 0;

        for (Area area : article.getAreas()) {
            if (reviewer.hasExpertiseIn(area)) {
                compatibility++;
            }
        }

        return compatibility;
    }

    private int calculateReviewerLoad(ReviewerProfile reviewer, Map<ReviewerProfile, Integer> load) {
        return load.get(reviewer);
    }
}
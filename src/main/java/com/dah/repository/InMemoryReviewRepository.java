package com.dah.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.dah.domain.Review;
import com.dah.domain.Article;
import com.dah.domain.ReviewerProfile;

public class InMemoryReviewRepository implements ReviewRepository {
    private List<Review> reviews = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Review> findAll() {
        return new ArrayList<>(reviews);
    }

    @Override
    public List<Review> findByArticle(Article article) {
        return reviews.stream()
                .filter(r -> Objects.equals(r.getArticle(), article))
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> findByReviewer(ReviewerProfile reviewer) {
        return reviews.stream()
                .filter(r -> Objects.equals(r.getReviewer(), reviewer))
                .collect(Collectors.toList());
    }

    @Override
    public Review save(Review review) {
        if (review.getId() == null) {
            review.setId(nextId++);
            reviews.add(review);
        } else {
            reviews.removeIf(r -> Objects.equals(r.getId(), review.getId()));
            reviews.add(review);
        }
        return review;
    }

    @Override
    public void saveAll(List<Review> reviewsToSave) {
        for (Review review : reviewsToSave) {
            save(review);
        }
    }

    @Override
    public void deleteAll() {
        reviews.clear();
        nextId = 1;
    }
}
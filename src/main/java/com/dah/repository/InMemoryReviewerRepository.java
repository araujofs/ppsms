package com.dah.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryReviewerRepository implements ReviewerRepository {
    private List<ReviewerProfile> reviewers = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<ReviewerProfile> findAll() {
        return new ArrayList<>(reviewers);
    }

    @Override
    public Optional<ReviewerProfile> findByUser(User user) {
        return reviewers.stream()
                .filter(r -> r.getUser().equals(user))
                .findFirst();
    }

    @Override
    public ReviewerProfile save(ReviewerProfile reviewer) {
        if (reviewer.getId() == null) {
            reviewer.setId(nextId++);
            reviewers.add(reviewer);
        } else {
            reviewers.removeIf(r -> r.getId().equals(reviewer.getId()));
            reviewers.add(reviewer);
        }
        return reviewer;
    }

    @Override
    public void deleteAll() {
        reviewers.clear();
        nextId = 1;
    }
}

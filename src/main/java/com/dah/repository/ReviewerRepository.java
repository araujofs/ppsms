package com.dah.repository;

import java.util.List;
import java.util.Optional;

public interface ReviewerRepository extends ResettableRepository {

    List<ReviewerProfile> findAll();

    Optional<ReviewerProfile> findByUser(User user);

    ReviewerProfile save(ReviewerProfile reviewer);
    
    @Override
    void deleteAll();
}

package com.dah.repository;

import java.util.List;
import java.util.Optional;
import com.dah.domain.ReviewerProfile;
import com.dah.domain.User;

public interface ReviewerRepository extends ResettableRepository {

    List<ReviewerProfile> findAll();

    Optional<ReviewerProfile> findByUser(User user);

    ReviewerProfile save(ReviewerProfile reviewer);
    
    @Override
    void deleteAll();
}

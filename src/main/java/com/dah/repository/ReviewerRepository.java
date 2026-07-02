package com.dah.repository;

import com.dah.domain.ReviewerProfile;
import com.dah.domain.User;
import java.util.List;
import java.util.Optional;

public interface ReviewerRepository extends ResettableRepository {

    List<ReviewerProfile> findAll();

    Optional<ReviewerProfile> findByUser(User user);

    ReviewerProfile save(ReviewerProfile reviewer);
    
    @Override
    void deleteAll();
}

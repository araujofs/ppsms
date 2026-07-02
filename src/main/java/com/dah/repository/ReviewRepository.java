package com.dah.repository;

import com.dah.domain.Article;
import com.dah.domain.Review;
import com.dah.domain.ReviewerProfile;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends ResettableRepository {

    Optional<Review> findById(Integer id);

    List<Review> findAll();

    List<Review> findByArticle(Article article);

    List<Review> findByReviewer(ReviewerProfile reviewer);

    Review save(Review review);

    void saveAll(List<Review> reviews);
    
    @Override
    void deleteAll();
}

package com.dah.repository;

import java.util.List;

public interface ReviewRepository extends ResettableRepository {

    List<Review> findAll();

    List<Review> findByArticle(Article article);

    List<Review> findByReviewer(ReviewerProfile reviewer);

    Review save(Review review);

    void saveAll(List<Review> reviews);
    
    @Override
    void deleteAll();
}
package com.dah.repository;

import java.util.List;

public interface ArticleRepository extends ResettableRepository {

    List<Article> findAll();

    List<Article> findByAuthor(User author);

    List<Article> findPending();

    List<Article> findFinished();

    Article save(Article article);

    @Override
    void deleteAll();
}

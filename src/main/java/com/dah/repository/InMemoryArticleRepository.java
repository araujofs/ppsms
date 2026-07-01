package com.dah.repository;

import com.dah.domain.Article;
import com.dah.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InMemoryArticleRepository implements ArticleRepository {
    private List<Article> articles = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(articles);
    }

    @Override
    public List<Article> findByAuthor(User author) {
        return articles.stream()
                .filter(a -> Objects.equals(a.getAuthor(), author))
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> findPending() {
        return articles.stream()
                .filter(a -> a.getState() != null && a.getState().isPending())
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> findFinished() {
        return articles.stream()
                .filter(a -> a.getState() != null && a.getState().isFinished())
                .collect(Collectors.toList());
    }

    @Override
    public Article save(Article article) {
        if (article.getId() == null) {
            article.setId(nextId++);
            articles.add(article);
        } else {
            articles.removeIf(a -> Objects.equals(a.getId(), article.getId()));
            articles.add(article);
        }
        return article;
    }

    @Override
    public void deleteAll() {
        articles.clear();
        nextId = 1;
    }
}
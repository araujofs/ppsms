package com.dah.repository;

import java.util.ArrayList;
import java.util.List;
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
                .filter(a -> a.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> findPending() {
        return articles.stream()
                .filter(a -> a.getStatus().equals("Submetido") || a.getStatus().equals("Revisão"))
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> findFinished() {
        return articles.stream()
                .filter(a -> a.getStatus().equals("Aceito") || a.getStatus().equals("Rejeitado"))
                .collect(Collectors.toList());
    }

    @Override
    public Article save(Article article) {
        if (article.getId() == null) {
            article.setId(nextId++);
            articles.add(article);
        } else {
            articles.removeIf(a -> a.getId().equals(article.getId()));
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
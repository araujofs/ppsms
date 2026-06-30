package com.dah.domain.article;

import com.dah.domain.Article;

public interface ArticleState {
  void  startReview(Article article);
  void  accept(Article article);
  void  reject(Article article);
  void  markAttention(Article article);
  boolean  isPending();
  boolean  isFinished();
  String getName();
}

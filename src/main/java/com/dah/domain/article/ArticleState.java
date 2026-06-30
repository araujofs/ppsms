package com.dah.domain.article;

import com.dah.domain.Article;

public interface ArticleState {
  public void  startReview(Article article);
  public void  accept(Article article);
  public void  reject(Article article);
  public void  markAttention(Article article);
  public boolean  isPending();
  public boolean  isFinished();
  public String getName();
}

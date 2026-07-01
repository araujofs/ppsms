package com.dah.domain.article;

import com.dah.domain.Article;

public class RejectedState implements ArticleState {

  @Override
  public void startReview(Article article) {
    throw new UnsupportedOperationException("Artigo já foi rejeitado!");
  }

  @Override
  public void accept(Article article) {
    throw new UnsupportedOperationException("Artigo já foi rejeitado!");
  }

  @Override
  public void reject(Article article) {
    throw new UnsupportedOperationException("Artigo já foi rejeitado!");
  }

  @Override
  public void markAttention(Article article) {
    throw new UnsupportedOperationException("Artigo já foi rejeitado!");
  }

  @Override
  public boolean isPending() {
    return false;
  }

  @Override
  public boolean isFinished() {
    return !isPending();
  }

  @Override
  public String getName() {
    return "Rejeitado";
  }
    
}

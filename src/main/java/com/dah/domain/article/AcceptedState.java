package com.dah.domain.article;

import com.dah.domain.Article;

public class AcceptedState implements ArticleState {

  @Override
  public void startReview(Article article) {
    throw new UnsupportedOperationException("Artigo já foi aceito!");
  }

  @Override
  public void accept(Article article) {
    throw new UnsupportedOperationException("Artigo já foi aceito!");
  }

  @Override
  public void reject(Article article) {
    throw new UnsupportedOperationException("Artigo já foi aceito!");
  }

  @Override
  public void markAttention(Article article) {
    throw new UnsupportedOperationException("Artigo já foi aceito!");
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
    return "Aceito";
  }

}

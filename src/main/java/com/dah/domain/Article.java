package com.dah.domain;

import java.util.List;

import com.dah.domain.article.ArticleState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Article {
  private Integer id;
  private String title;
  private String abstractText;
  private List<User> authors;
  private List<Area> areas;
  private ArticleState state;

  public void startReview() {
    state.startReview(this);
  }

  public void accept() {
    state.accept(this);
  }

  public void reject() {
    state.reject(this);
  }

  public void markAttention() {
    state.markAttention(this);
  }

  public boolean isPending() {
    return state.isPending();
  }

  public boolean isFinished() {
    return state.isFinished();
  }
}

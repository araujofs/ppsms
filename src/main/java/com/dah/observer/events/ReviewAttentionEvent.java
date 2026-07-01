package com.dah.observer.events;

import java.util.List;

import com.dah.domain.Article;
import com.dah.domain.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReviewAttentionEvent implements DomainEvent {
  private Article article;
  private List<Review> reviews;
}

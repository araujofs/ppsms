package com.dah.observer.events;

import com.dah.domain.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleAssignedEvent implements DomainEvent {
  private Review review;
}

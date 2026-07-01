package com.dah.observer.events;

import java.util.List;

import com.dah.domain.Article;
import com.dah.domain.Review;
import com.dah.domain.SubmissionEvent;
import com.dah.enums.ReviewOutcome;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleConcludedEvent implements DomainEvent {
  private Article article;
  private ReviewOutcome outcome;
  private List<Review> reviews;
  private SubmissionEvent event;
}

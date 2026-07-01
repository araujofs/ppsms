package com.dah.domain;

import com.dah.enums.ReviewStatus;
import com.dah.enums.Verdict;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {
  private Integer id;
  private Article article;
  private ReviewerProfile reviewer;
  private String contribution;
  private String criticism;
  private Verdict verdict;
  private ReviewStatus status = ReviewStatus.ASSIGNED;
    
  public void submit(String contribution, String criticism, Verdict verdict) {
    this.contribution = contribution;
    this.criticism = criticism;
    this.verdict = verdict;
    
    this.status = ReviewStatus.SUBMITTED;
  }

  public boolean isSubmitted() {
    return status == ReviewStatus.SUBMITTED;
  }
}

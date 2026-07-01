package com.dah.domain;

import java.time.LocalDate;

import com.dah.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SubmissionEvent {
  private Integer id; 
  private String name;
  private String city;
  private LocalDate submissionDeadline;
  private LocalDate start;
  private LocalDate end;
  private Category category;
  private int reviewersPerArticle;

  public boolean isSubmissionOpen() {
    LocalDate today = LocalDate.now();

    return today.isBefore(submissionDeadline) || today.isEqual(submissionDeadline);
  }

  public LocalDate getReviewDeadline() {
    return end;
  }
}

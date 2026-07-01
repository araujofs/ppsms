package com.dah.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewerProfile {
  private Integer id;
  private User researcher;
  private List<Area> expertiseAreas = null;

  public boolean hasExpertiseIn(Area area) {
    return expertiseAreas.contains(area);
  }

  public boolean hasDefinedExpertiseAreas() {
    return expertiseAreas != null;
  }
}

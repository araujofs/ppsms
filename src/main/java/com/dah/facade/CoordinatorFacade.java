package com.dah.facade;

import com.dah.domain.Area;
import com.dah.domain.ReviewerProfile;
import com.dah.domain.SubmissionEvent;
import com.dah.records.CoordinatorDashboard;
import com.dah.records.StartEventData;
import com.dah.service.CommitteeService;
import com.dah.service.DistributionService;
import com.dah.service.EventService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CoordinatorFacade implements CoordinatorActions {

  private final EventService eventService;
  private final CommitteeService committeeService;
  private DistributionService distributionService;

  @Override
  public SubmissionEvent startEvent(StartEventData data) {
    return eventService.startEvent(data);
  }

  @Override
  public Area registerArea(String name) {
    return committeeService.registerArea(name);
  }

  @Override
  public ReviewerProfile inviteReviewer(Integer userId) {
    return committeeService.inviteResearcher(userId);
  }

  @Override
  public void distributeArticles() {
    distributionService.distributeArticles();
  }

  @Override
  public CoordinatorDashboard getDashboard() {
    return eventService.getDashboard();
  }
}

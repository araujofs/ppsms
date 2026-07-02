package com.dah.facade;

import com.dah.domain.Area;
import com.dah.domain.ReviewerProfile;
import com.dah.domain.SubmissionEvent;
import com.dah.records.CoordinatorDashboard;
import com.dah.records.StartEventData;

public interface CoordinatorActions {
  public SubmissionEvent startEvent(StartEventData data);
  public Area registerArea(String name);
  public ReviewerProfile inviteReviewer(Integer userId);
  public void distributeArticles();
  public CoordinatorDashboard getDashboard();
}

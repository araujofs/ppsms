package com.dah.facade;

import com.dah.domain.Area;
import com.dah.domain.ReviewerProfile;
import com.dah.domain.SubmissionEvent;
import com.dah.exceptions.UnauthorizedOperationException;
import com.dah.records.CoordinatorDashboard;
import com.dah.records.StartEventData;
import com.dah.service.AccountService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CoordinatorAuthorizationProxy implements CoordinatorActions {

  private final CoordinatorActions target;
  private final AccountService accountService;

  @Override
  public SubmissionEvent startEvent(StartEventData data) {
    if (accountService.isCurrentUserCoordinator()) {
      throw new UnauthorizedOperationException("Usuário não pode começar novo evento pois não é coordenador!");
    }

    return target.startEvent(data);
  }

  @Override
  public Area registerArea(String name) {
    if (accountService.isCurrentUserCoordinator()) {
      throw new UnauthorizedOperationException("Usuário não pode criar nova área temática pois não é coordenador!");
    }

    return target.registerArea(name);
  }

  @Override
  public ReviewerProfile inviteReviewer(Integer userId) {
    if (accountService.isCurrentUserCoordinator()) {
      throw new UnauthorizedOperationException("Usuário não pode convidar novo revisor pois não é coordenador!");
    }

    return target.inviteReviewer(userId);
  }

  @Override
  public void distributeArticles() {
    if (accountService.isCurrentUserCoordinator()) {
      throw new UnauthorizedOperationException("Usuário não pode distribuir artigos pois não é coordenador!");
    }

    target.distributeArticles();
  }

  @Override
  public CoordinatorDashboard getDashboard() {
    if (accountService.isCurrentUserCoordinator()) {
      throw new UnauthorizedOperationException("Usuário não pode visualizar a dashboard pois não é coordenador!");
    }

    return target.getDashboard();
  }

}

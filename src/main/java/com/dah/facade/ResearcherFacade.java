package com.dah.facade;

import java.util.List;

import com.dah.domain.Area;
import com.dah.domain.Article;
import com.dah.domain.ReviewerProfile;
import com.dah.records.ArticleForReviewDTO;
import com.dah.records.DefineReviewerAreasData;
import com.dah.records.ReviewData;
import com.dah.records.SubmitArticleData;
import com.dah.service.AccountService;
import com.dah.service.CommitteeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResearcherFacade implements ResearcherActions {

  // private final ArticleReviewService articleReviewService;
  private final CommitteeService committeeService;
  private final AccountService accountService;

  @Override
  public Article submitArticle(SubmitArticleData data) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'submitArticle'");
  }

  @Override
  public List<Article> listMyArticles() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'listMyArticles'");
  }

  @Override
  public List<ArticleForReviewDTO> listAssignedArticles() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'listAssignedArticles'");
  }

  @Override
  public void submitReview(ReviewData data) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'submitReview'");
  }

  @Override
  public boolean needsExpertiseAreas() {
    return !committeeService.findReviewerByUser(accountService.getCurrentUser()).hasDefinedExpertiseAreas();
  }

  @Override
  public ReviewerProfile defineExpertiseAreas(DefineReviewerAreasData data) {
    return committeeService.defineExpertiseAreas(accountService.getCurrentUser(), data.areaIds());
  }

  @Override
  public List<Area> listAvailableAreas() {
    return committeeService.listAreas();
  }
}

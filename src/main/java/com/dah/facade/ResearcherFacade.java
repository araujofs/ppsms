package com.dah.facade;

import java.util.List;

import com.dah.domain.Area;
import com.dah.domain.Article;
import com.dah.domain.ReviewerProfile;
import com.dah.exceptions.UnauthorizedOperationException;
import com.dah.records.ArticleForReviewDTO;
import com.dah.records.DefineReviewerAreasData;
import com.dah.records.ReviewData;
import com.dah.records.SubmitArticleData;
import com.dah.service.AccountService;
import com.dah.service.ArticleReviewService;
import com.dah.service.CommitteeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResearcherFacade implements ResearcherActions {

  private final ArticleReviewService articleReviewService;
  private final CommitteeService committeeService;
  private final AccountService accountService;

  @Override
  public Article submitArticle(SubmitArticleData data) {
    return articleReviewService.submitArticle(data);
  }

  @Override
  public List<Article> listMyArticles() {
    return articleReviewService.listMyArticles();
  }

  @Override
  public List<ArticleForReviewDTO> listAssignedArticles() {
    ReviewerProfile reviewer = committeeService.findReviewerByUser(accountService.getCurrentUser());

    if (reviewer == null) {
      throw new UnauthorizedOperationException("Usuário não é revisor e por isso não pode visualizar artigos assinalados a ele!");
    }

    return articleReviewService.listArticlesForReview(reviewer);
  }

  @Override
  public void submitReview(ReviewData data) {
    articleReviewService.submitReview(data);
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

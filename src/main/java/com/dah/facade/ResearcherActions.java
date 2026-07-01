package com.dah.facade;

import java.util.List;

import com.dah.domain.Area;
import com.dah.domain.Article;
import com.dah.domain.ReviewerProfile;
import com.dah.records.ArticleForReviewDTO;
import com.dah.records.DefineReviewerAreasData;
import com.dah.records.ReviewData;
import com.dah.records.SubmitArticleData;

public interface ResearcherActions {
  public Article submitArticle(SubmitArticleData data);
  public List<Article> listMyArticles();
  public List<ArticleForReviewDTO> listAssignedArticles();
  public void submitReview(ReviewData data);
  public boolean needsExpertiseAreas();
  public ReviewerProfile defineExpertiseAreas(DefineReviewerAreasData data);
  public List<Area> listAvailableAreas();
}

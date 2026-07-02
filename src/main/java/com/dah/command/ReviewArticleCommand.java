package com.dah.command;

import com.dah.facade.ResearcherActions;
import com.dah.records.ReviewData;

public class ReviewArticleCommand implements Command<ReviewData> {

    private final ResearcherActions researcherActions;

    public ReviewArticleCommand(ResearcherActions researcherActions) {
        this.researcherActions = researcherActions;
    }

    @Override
    public void execute(ReviewData data) {
        researcherActions.submitReview(data);
    }
}
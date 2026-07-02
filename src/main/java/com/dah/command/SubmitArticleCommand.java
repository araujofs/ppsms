package com.dah.command;

import com.dah.facade.ResearcherActions;
import com.dah.records.SubmitArticleData;

public class SubmitArticleCommand implements Command<SubmitArticleData> {

    private final ResearcherActions researcherActions;

    public SubmitArticleCommand(ResearcherActions researcherActions) {
        this.researcherActions = researcherActions;
    }

    @Override
    public void execute(SubmitArticleData data) {
        researcherActions.submitArticle(data);
    }
}
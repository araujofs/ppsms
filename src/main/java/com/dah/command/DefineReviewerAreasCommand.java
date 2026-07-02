package com.dah.command;

import com.dah.facade.ResearcherActions;
import com.dah.records.DefineReviewerAreasData;

public class DefineReviewerAreasCommand implements Command<DefineReviewerAreasData> {

    private final ResearcherActions researcherActions;

    public DefineReviewerAreasCommand(ResearcherActions researcherActions) {
        this.researcherActions = researcherActions;
    }

    @Override
    public void execute(DefineReviewerAreasData data) {
        researcherActions.defineExpertiseAreas(data);
    }
}
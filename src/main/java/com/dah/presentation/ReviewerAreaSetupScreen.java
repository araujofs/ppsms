package com.dah.presentation;

import com.dah.command.DefineReviewerAreasCommand;
import com.dah.facade.ResearcherActions;
import com.dah.presentation.input.InputReader;
import com.dah.presentation.view.ConsoleView;
import com.dah.records.DefineReviewerAreasData;

public class ReviewerAreaSetupScreen {

    private final InputReader inputReader;
    private final ConsoleView consoleView;
    private final ResearcherActions researcherActions;
    private final DefineReviewerAreasCommand defineReviewerAreasCommand;

    public ReviewerAreaSetupScreen(InputReader inputReader, 
                                   ConsoleView consoleView,
                                   ResearcherActions researcherActions,
                                   DefineReviewerAreasCommand defineReviewerAreasCommand) {
        this.inputReader = inputReader;
        this.consoleView = consoleView;
        this.researcherActions = researcherActions;
        this.defineReviewerAreasCommand = defineReviewerAreasCommand;
    }

    public void show() {
        consoleView.showMessage("Bem-vindo! Para prosseguir, selecione suas áreas de expertise.");
        
        var areas = researcherActions.listAvailableAreas();
        
        consoleView.showAreas(areas);

        DefineReviewerAreasData data = inputReader.readReviewerAreasData(areas);

        defineReviewerAreasCommand.execute(data);
        
        consoleView.showMessage("Áreas de expertise configuradas com sucesso!");
    }
}
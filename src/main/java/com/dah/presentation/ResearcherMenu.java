package com.dah.presentation;

import com.dah.command.*;
import com.dah.presentation.input.InputReader;
import com.dah.presentation.view.ConsoleView;
import com.dah.records.ReviewData;
import com.dah.records.SubmitArticleData;

public class ResearcherMenu {

    private final InputReader inputReader;
    private final ConsoleView consoleView;
    private final SubmitArticleCommand submitArticleCommand;
    private final ReviewArticleCommand reviewArticleCommand;

    public ResearcherMenu(InputReader inputReader, 
                          ConsoleView consoleView,
                          SubmitArticleCommand submitArticleCommand,
                          ReviewArticleCommand reviewArticleCommand) {
        this.inputReader = inputReader;
        this.consoleView = consoleView;
        this.submitArticleCommand = submitArticleCommand;
        this.reviewArticleCommand = reviewArticleCommand;
    }

    public void show() {
        boolean running = true;
        while (running) {
            consoleView.displayResearcherOptions();
            int option = inputReader.readInt("Escolha uma opção: ");

            switch (option) {
                case 1 -> {
                    SubmitArticleData data = inputReader.readSubmitArticleData();
                    submitArticleCommand.execute(data);
                }
                case 2 -> {
                    ReviewData data = inputReader.readReviewData();
                    reviewArticleCommand.execute(data);
                }
                case 0 -> running = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
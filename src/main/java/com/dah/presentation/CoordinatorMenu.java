package com.dah.presentation;

import com.dah.command.*;
import com.dah.presentation.input.InputReader;
import com.dah.presentation.view.ConsoleView;
import com.dah.records.StartEventData;

public class CoordinatorMenu {

    private final InputReader inputReader;
    private final ConsoleView consoleView;
    private final StartEventCommand startEventCommand;
    private final RegisterAreaCommand registerAreaCommand;
    private final InviteReviewerCommand inviteReviewerCommand;
    private final DistributeArticlesCommand distributeArticlesCommand;

    public CoordinatorMenu(InputReader inputReader, 
                           ConsoleView consoleView,
                           StartEventCommand startEventCommand,
                           RegisterAreaCommand registerAreaCommand,
                           InviteReviewerCommand inviteReviewerCommand,
                           DistributeArticlesCommand distributeArticlesCommand) {
        this.inputReader = inputReader;
        this.consoleView = consoleView;
        this.startEventCommand = startEventCommand;
        this.registerAreaCommand = registerAreaCommand;
        this.inviteReviewerCommand = inviteReviewerCommand;
        this.distributeArticlesCommand = distributeArticlesCommand;
    }

    public void show() {
        boolean running = true;
        while (running) {
            consoleView.displayCoordinatorOptions();
            int option = inputReader.readInt("Escolha uma opção: ");

            switch (option) {
                case 1 -> {
                    StartEventData data = inputReader.readStartEventData();
                    startEventCommand.execute(data);
                }
                case 2 -> {
                    String areaName = inputReader.readString("Nome da área: ");
                    registerAreaCommand.execute(areaName);
                }
                case 3 -> {
                    Integer userId = inputReader.readInt("ID do pesquisador: ");
                    inviteReviewerCommand.execute(userId);
                }
                case 4 -> {
                    distributeArticlesCommand.execute();
                }
                case 0 -> running = false;
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
package com.dah.command;

import com.dah.facade.CoordinatorActions;

public class DistributeArticlesCommand implements NoDataCommand {

    private final CoordinatorActions coordinatorActions;

    public DistributeArticlesCommand(CoordinatorActions coordinatorActions) {
        this.coordinatorActions = coordinatorActions;
    }

    @Override
    public void execute() {
        coordinatorActions.distributeArticles();
    }
}
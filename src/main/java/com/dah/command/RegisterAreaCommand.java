package com.dah.command;

import com.dah.facade.CoordinatorActions;

public class RegisterAreaCommand implements Command<String> {

    private final CoordinatorActions coordinatorActions;

    public RegisterAreaCommand(CoordinatorActions coordinatorActions) {
        this.coordinatorActions = coordinatorActions;
    }

    @Override
    public void execute(String areaName) {
        coordinatorActions.registerArea(areaName);
    }
}
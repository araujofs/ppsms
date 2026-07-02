package com.dah.command;

import com.dah.facade.CoordinatorActions;
import com.dah.records.StartEventData;

public class StartEventCommand implements Command<StartEventData> {

    private final CoordinatorActions coordinatorActions;

    public StartEventCommand(CoordinatorActions coordinatorActions) {
        this.coordinatorActions = coordinatorActions;
    }

    @Override
    public void execute(StartEventData data) {
        coordinatorActions.startEvent(data);
    }
}
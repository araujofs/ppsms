package com.dah.command;

import com.dah.facade.CoordinatorActions;

public class InviteReviewerCommand implements Command<Integer> {

    private final CoordinatorActions coordinatorActions;

    public InviteReviewerCommand(CoordinatorActions coordinatorActions) {
        this.coordinatorActions = coordinatorActions;
    }

    @Override
    public void execute(Integer userId) {
        coordinatorActions.inviteReviewer(userId);
    }
}
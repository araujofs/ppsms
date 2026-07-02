package com.dah.presentation;

import com.dah.domain.User;
import com.dah.enums.Role;
import com.dah.facade.ResearcherActions;
import com.dah.service.AccountService;

public class LoginFlowController {

    private final AccountService accountService;
    private final ResearcherActions researcherActions;
    private final ReviewerAreaSetupScreen reviewerAreaSetupScreen;
    private final MainMenu mainMenu;

    public LoginFlowController(AccountService accountService, 
                               ResearcherActions researcherActions,
                               ReviewerAreaSetupScreen reviewerAreaSetupScreen, 
                               MainMenu mainMenu) {
        this.accountService = accountService;
        this.researcherActions = researcherActions;
        this.reviewerAreaSetupScreen = reviewerAreaSetupScreen;
        this.mainMenu = mainMenu;
    }

    public void afterLogin() {
        User currentUser = accountService.getCurrentUser();

        if (currentUser.hasRole(Role.RESEARCHER) && researcherActions.needsExpertiseAreas()) {
            reviewerAreaSetupScreen.show();
        }

        mainMenu.show();
    }
}
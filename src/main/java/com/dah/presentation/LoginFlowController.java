package com.dah.presentation;

import com.dah.domain.User;
import com.dah.enums.Role;
import com.dah.facade.ResearcherActions;
import com.dah.service.AccountService;

/**
 * Controla o fluxo após a autenticação.
 * Decide para onde o usuário deve ser redirecionado com base em seus perfis
 * e na necessidade de configuração inicial (ex: áreas de revisor).
 */
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

        // Regra de negócio: Se o usuário é revisor e ainda não definiu suas áreas,
        // ele é forçado a passar pelo fluxo de configuração de áreas antes do menu principal.
        if (currentUser.hasRole(Role.RESEARCHER) && researcherActions.needsExpertiseAreas()) {
            reviewerAreaSetupScreen.show();
        }

        // Fluxo padrão para o menu principal
        mainMenu.show();
    }
}
package com.dah.presentation;

import com.dah.domain.User;
import com.dah.enums.Role;
import com.dah.service.AccountService;

public class MainMenu {

    private final AccountService accountService;
    private final CoordinatorMenu coordinatorMenu;
    private final ResearcherMenu researcherMenu;

    public MainMenu(AccountService accountService, 
                    CoordinatorMenu coordinatorMenu, 
                    ResearcherMenu researcherMenu) {
        this.accountService = accountService;
        this.coordinatorMenu = coordinatorMenu;
        this.researcherMenu = researcherMenu;
    }

    public void start() {
        User user = accountService.getCurrentUser();
        
        if (user == null) {
            System.out.println("Nenhum usuário logado.");
            return;
        }

        if (user.hasRole(Role.COORDINATOR)) {
            coordinatorMenu.show();
        } else if (user.hasRole(Role.RESEARCHER)) {
            researcherMenu.show();
        } else {
            System.out.println("Usuário sem permissões de acesso aos menus.");
        }
    }
}
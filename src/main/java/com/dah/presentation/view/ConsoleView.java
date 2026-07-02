package com.dah.presentation.view;

import com.dah.domain.Area;
import com.dah.records.ArticleForReviewDTO;
import com.dah.records.CoordinatorDashboard;
import java.util.List;

public class ConsoleView {

    public void showMessage(String message) {
        System.out.println("\n[INFO] " + message);
    }

    public void showError(Exception error) {
        System.err.println("\n[ERRO] " + error.getMessage());
    }

    public void showAreas(List<Area> areas) {
        System.out.println("\n--- Áreas de Expertise Disponíveis ---");
        areas.forEach(area -> System.out.println(area.getId() + " - " + area.getName()));
    }

    public void showDashboard(CoordinatorDashboard dashboard) {
        System.out.println("\n--- Dashboard do Coordenador ---");
        System.out.println("Artigos pendentes: " + dashboard.pendingArticles());
        System.out.println("Revisores ativos: " + dashboard.reviewers());
    }

    public void showArticlesForReview(List<ArticleForReviewDTO> articles) {
        System.out.println("\n--- Artigos para Revisão ---");
        if (articles.isEmpty()) {
            System.out.println("Nenhum artigo pendente.");
        } else {
            articles.forEach(a -> System.out.println("ID: " + a.id() + " | Título: " + a.title()));
        }
    }

    public void displayCoordinatorOptions() {
        System.out.println("\n1. Iniciar Evento | 2. Registrar Área | 3. Convidar Revisor | 4. Distribuir Artigos | 0. Sair");
    }

    public void displayResearcherOptions() {
        System.out.println("\n1. Submeter Artigo | 2. Avaliar Artigo | 0. Sair");
    }
}
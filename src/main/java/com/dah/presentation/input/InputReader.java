package com.dah.presentation.input;

import com.dah.domain.Area;
import com.dah.enums.Category;
import com.dah.enums.Verdict;
import com.dah.records.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputReader {
    private final Scanner scanner;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public InputReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite um número.");
            return readInt(prompt);
        }
    }

    public RegisterUserData readRegisterUserData() {
        String name = readString("Nome: ");
        String email = readString("Email: ");
        String password = readString("Senha: ");
        String institution = readString("Instituição: ");
        return new RegisterUserData(name, email, password, institution);
    }

    public String readAreaName() {
        return readString("Nome da nova área temática: ");
    }

    public Integer readReviewerId() {
        return readInt("ID do usuário a ser convidado como revisor: ");
    }

    public StartEventData readStartEventData() {
        String name = readString("Nome do evento: ");
        String city = readString("Cidade: ");
        
        LocalDate submissionDeadline = LocalDate.parse(readString("Data limite de submissão (dd/MM/yyyy): "), dateFormatter);
        LocalDate start = LocalDate.parse(readString("Data de início (dd/MM/yyyy): "), dateFormatter);
        LocalDate end = LocalDate.parse(readString("Data de término (dd/MM/yyyy): "), dateFormatter);
        
        Category category = null;
        while (category == null) {
            try {
                System.out.print("Categoria (FULL_PAPER, SHORT_PAPER, DEMO): ");
                category = Category.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Categoria inválida.");
            }
        }
        
        int reviewersPerArticle = readInt("Quantidade de revisores por artigo: ");
        
        return new StartEventData(name, city, submissionDeadline, start, end, category, reviewersPerArticle);
    }

    public SubmitArticleData readSubmitArticleData() {
        String title = readString("Título do artigo: ");
        String abstractText = readString("Resumo (Abstract): ");
        
        System.out.print("IDs de coautores (separados por vírgula): ");
        String coauthorsInput = scanner.nextLine();
        List<Integer> coauthors = coauthorsInput.isBlank() ? List.of() : 
            Stream.of(coauthorsInput.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
        
        System.out.print("IDs de áreas (separados por vírgula): ");
        String areasInput = scanner.nextLine();
        List<Integer> areas = areasInput.isBlank() ? List.of() : 
            Stream.of(areasInput.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
        
        return new SubmitArticleData(title, abstractText, coauthors, areas); 
    }

    public ReviewData readReviewData() {
        int articleId = readInt("ID do artigo para avaliar: ");
        String contribution = readString("Contribuição: ");
        String criticism = readString("Crítica: ");
        
        Verdict verdict = null;
        while (verdict == null) {
            System.out.print("Veredito (REJECTED, WEAK_REJECT, WEAK_ACCEPTED, ACCEPTED): ");
            try {
                verdict = Verdict.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Opção inválida.");
            }
        }
        return new ReviewData(articleId, contribution, criticism, verdict);
    }

    public DefineReviewerAreasData readReviewerAreasData(List<Area> availableAreas) {
        System.out.println("Áreas disponíveis: " + availableAreas);
        String input = readString("Selecione os IDs das áreas (separados por vírgula): ");
        
        List<Integer> ids = Stream.of(input.split(","))
                                  .map(String::trim)
                                  .map(Integer::parseInt)
                                  .collect(Collectors.toList());
                                  
        return new DefineReviewerAreasData(ids); 
    }
}
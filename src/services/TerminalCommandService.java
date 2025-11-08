package services;

import commands.Command;

import java.util.Scanner;

public class TerminalCommandService {

    // Deklarerar ApplicationService
    private final ApplicationService service;

    public TerminalCommandService(ApplicationService service) {
        // Konstruktor för ApplicationService
        this.service = service;
    }

    public void start() throws Exception {
        // Deklarerar scanner
        Scanner scan = new Scanner(System.in);

        // Utskrift till användaren
        System.out.println("=== DEV ALBERT TOPALLAJ ===");
        System.out.println("=== PERSONAL FINANCE APP ===");
        System.out.println("Välkommen! Välj ett kommando: ");

        while (true) { // While-loop medan man är inne i applikationen

            // Kontobalansen utskriven
            System.out.println("\nKONTOBALANS: " + service.getTransactionService().getBalance());

            // For-loopen för alla kommandon
            for (Command command : service.getCommands()) {
                System.out.println(" " + command.getName() + " - " + command.getDescription());
            }

            // Hake för att det ska se bra ut och där användaren kan skriva in kommando
            System.out.print("> ");

            // Scannar input från användaren för kommando
            String input = scan.nextLine();

            try { // Try catch för felhantering

                // Från ApplicationService aktivera kommandot baserat på inputen från användaren
                service.executeCommand(input);
            } catch (Exception e) {
                // Om inte det funkar så skrivs felmeddelandet ut
                System.out.println("Ett fel uppstod " + e.getMessage());
            }
        }
    }
}

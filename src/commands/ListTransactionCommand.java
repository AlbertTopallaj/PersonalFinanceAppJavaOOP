package commands;

import models.Transaction;
import enums.TransactionType;
import services.ITransactionFilter;
import services.ITransactionService;

import java.time.*;
import java.util.List;
import java.util.Scanner;

public class ListTransactionCommand extends Command { // Ärver från Command

    private final ITransactionFilter transactionFilter;

    public ListTransactionCommand(ITransactionService transactionService, ITransactionFilter transactionFilter) { // Konstruktor för klassen
        // Namnet samt beskrivningen av kommandot sätts, samt så används transactionService
        super("lista", "Få en lista av alla transaktioner", transactionService);
        this.transactionFilter = transactionFilter;
    }

    @Override
    // Execute metoden för kommandot
    public void execute() throws Exception {

        // Deklarerar scanner
        Scanner scan = new Scanner(System.in);

        // Hämtar alla transaktioner och placerar de i listan
        List<Transaction> transactions = transactionService.findAll();


        // Om inga transaktioner hittades så printas meddelande ut
        if (transactions.isEmpty()) {
            System.out.println("Inga transaktioner kunde hittas i systemet");
            return;
        }

        // För att skapa mellanrum
        System.out.println();

        // Alla transaktioner listas
        System.out.println("ALLA TRANSAKTIONER I SYSTEMET: ");
        for (Transaction t : transactions) {

            System.out.println("ID: " + t.getID() +
                    ", BESKRIVNING: " + t.getDescription() +
                    ", BELOPP: " + t.getAmount() +
                    ", DATUM: " + t.getDate().format(Transaction.DATE_FORMAT) +
                    ", TYP: " + t.getType());

        }

        // Mellanrum
        System.out.println();

        // Utskrift till användaren
        System.out.println("Vill du se (INKOMST / UTGIFT / SAMTLIGA) ");

        // Tar emot användarens svar om vilken typ de vill ha
        String typeInput = scan.nextLine().toUpperCase();


        // Switch case, baserat på vilken typ användaren valde
        switch (typeInput) {
            case "INKOMST":

                // Sätter filtret till inkomst endast
                transactions = transactionFilter.filterByType(transactions, TransactionType.INKOMST);
                break;

            case "UTGIFT":
                // Sätter filtret till utgift endast
                transactions = transactionFilter.filterByType(transactions, TransactionType.UTGIFT);
                break;

            case "SAMTLIGA":
                // Skippar filteringen helt
                break;

            default:
                // Sätts som standard ifall användaren inte har skrivit in något eller skrivit fel
                System.out.println("Ogiltigt val, visar samtliga");
        }

        // Filtrera efter datum
        System.out.println("Vill du filtrera (dag / vecka / månad / år / samtliga): ");

        // Tar emot filteringen från användaren
        String filter = scan.nextLine().trim().toLowerCase();

        switch (filter) {
            case "dag":
                // Filterar för dag
                System.out.println("Ange datum (yyyy-MM-dd): ");
                LocalDate day = LocalDate.parse(scan.nextLine());
                transactions = transactionFilter.filterByDay(transactions, day);
                break;
            case "vecka":
                // Filterar för vecka
                System.out.println("Ange datum inom veckan (yyyy-MM-dd): ");
                LocalDate weekDate = LocalDate.parse(scan.nextLine());
                transactions = transactionFilter.filterByWeek(transactions, weekDate);
                break;
            case "månad":
                // Filterar för månad
                System.out.println("Ange år och månad (yyyy-MM): ");
                YearMonth month = YearMonth.parse(scan.nextLine());
                transactions = transactionFilter.filterByMonth(transactions, month);
                break;
            case "år":
                // Filterar för år
                System.out.println("Ange år (yyyy): ");
                int year = Integer.parseInt(scan.nextLine());
                transactions = transactionFilter.filterByYear(transactions, year);
                break;
            case "samtliga":
                // Skippar filteringen helt om man inte skriver in något
                break;
            default:
                // Sätts som standard ifall användaren inte har skrivit in något eller skrivit fel
                System.out.println("Ogiltigt val, visar samtliga.");
        }

        // Om transaktionerna inte matchar filteringen
        if (transactions.isEmpty()) {
            System.out.println("Inga transaktioner hittades.");
            // Om transaktionerna matchar filteringen så printas de
        } else {
            System.out.println("TRANSAKTIONER:");
            for (Transaction t : transactions) {
                System.out.println("ID: " + t.getID() +
                        ", BESKRIVNING: " + t.getDescription() +
                        ", BELOPP: " + t.getAmount() +
                        ", DATUM: " + t.getDate().format(Transaction.DATE_FORMAT) +
                        ", TYP: " + t.getType());
            }
        }

    }

}
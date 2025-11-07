package commands;

import Transaction.Transaction;
import enums.TransactionType;
import services.ITransactionService;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Scanner;

public class ListTransactionCommand extends Command { // Ärver från Command

    public ListTransactionCommand(ITransactionService transactionService){ // Konstruktor för klassen'
        // Namnet samt beskrivningen av kommandot sätts, samt så används transactionService
        super("lista", "Få en lista av alla transaktioner", transactionService);
    }

    @Override
    public void execute() throws Exception {
        // Execute metoden för kommandot

        Scanner scan = new Scanner(System.in);
        // Deklarerar scanner

        LocalDateTime now = LocalDateTime.now();
        // Tar dagens datum

        // Utskrift till användaren med statistik
        System.out.println("=== INKOMST ===");
        System.out.println();
        System.out.println("Dagens inkomst: " + transactionService.getDailyIncome(now) + " SEK");
        System.out.println();
        System.out.println("Veckans inkomst " + transactionService.getWeeklyIncome(now) + " SEK");
        System.out.println();
        System.out.println("Månadens inkomst: " + transactionService.getMonthlyIncome(YearMonth.from(now)) + " SEK");
        System.out.println();
        System.out.println("Årets inkomst " + transactionService.getYearlyIncome(Year.of(now.getYear())) + " SEK");


        System.out.println("=== UTGIFT ===");
        System.out.println();
        System.out.println("Dagens utgift: " + transactionService.getDailySpending(now) + " SEK");
        System.out.println();
        System.out.println("Veckans utgift " + transactionService.getWeeklySpending(now) + " SEK");
        System.out.println();
        System.out.println("Månadens utgift: " + transactionService.getMonthlySpending(YearMonth.from(now)) + " SEK");
        System.out.println();
        System.out.println("Årets utgift " + transactionService.getYearlySpending(Year.of(now.getYear())) + " SEK");
        System.out.println();
        System.out.println("Total inkomst: " + transactionService.getTotalIncome() + " SEK");
        System.out.println();
        System.out.println("Total utgift " + transactionService.getTotalExpenses() + " SEK");
        System.out.println();


        // Hämta alla transaktioner från service
        List<Transaction> transactions = transactionService.findAll();


        // Formattera om datumet
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Loopar genom alla transaktioner och skriver ut de
        System.out.println("ALLA TRANSAKTIONER:");
        for (Transaction t : transactions) {
            System.out.println("ID " + t.getID() +
                    ", BESKRIVNING: " + t.getDescription() +
                    ", BELOPP: " + t.getAmount() +
                    ", DATUM: " + t.getDate().format(formatter) +
                    ", TYP: " + (t.getType()));
        }

        // Filteringen av transaktioner
        System.out.println();
        System.out.println("Vill du se (inkomst / utgift / samtliga): ");
        String type = scan.nextLine();

        // Val av inkomst eller utgift
        switch (type.toLowerCase()) {

            case "inkomst":
                transactions = transactions.stream()
                        .filter(transaction -> transaction.getType() == TransactionType.INKOMST)
                        .toList();
                break;

            case "utgift":
                transactions = transactions.stream()
                        .filter(transaction -> transaction.getType() == TransactionType.UTGIFT)
                        .toList();
                break;

            case "samtliga":

                break;

            default:
                System.out.println("Ogiltigt val, visar samtliga.");
                break;
        }

        // Filtering för dag, vecka, månad, år
        System.out.println();
        System.out.println("Vill du filtrera (dag / vecka / månad / år / samtliga): ");
        String filter = scan.nextLine();

        // Användaren väljer vad de vill filtera med
        switch (filter.toLowerCase()) {

            // Välja att filtera med dag
            case "dag":

                // Utskrift till användaren
                System.out.println("Ange datum (t.ex 2010-01-01): ");

                // Scanner tar emot datumet
                String dayInput = scan.nextLine();

                // Inputen parsar
                LocalDate day = LocalDate.parse(dayInput);

                // Filterar igenom hela transaktionslistan
                transactions = transactions.stream()

                        // Sen filteras listan med detta angivna datumet
                        .filter(transaction -> transaction.getDate().toLocalDate().equals(day))

                        // Resultatet skickas till listan
                        .toList();
                break;

                // Välja att filtera med vecka
            case "vecka":

                // Utskrift till användaren
                System.out.println("Ange datum inom veckan (t.ex 2000-01-01): ");

                // Scanner tar emot datumet
                String weekInput = scan.nextLine();

                // Inputen parsar
                LocalDate week = LocalDate.parse(weekInput);

                // Veckans start med måndag
                LocalDate weekStart = week.with(DayOfWeek.MONDAY);

                // Veckans slut med söndag
                LocalDate weekEnd = week.with(DayOfWeek.SUNDAY);

                // Filterar igenom hela transaktionlistan
                transactions = transactions.stream()

                        // Filterar om datumet finns inom veckans start och slut
                        .filter(t -> {

                            // Hämtar datumet
                            LocalDate date = t.getDate().toLocalDate();

                            // Returnerar true om transaktionens datum ligger inom veckan inklusive måndag och söndag
                            return !date.isBefore(weekStart) && !date.isAfter(weekEnd);
                        })

                        // Resultat skickas till listan
                        .toList();
                break;

                // Välja att filtera med månad
            case "månad":

                // Utskrift till användaren
                System.out.println("Ange år och månad (yyyy-MM):");

                // Tar emot datumet
                String monthInput = scan.nextLine();

                // Inputen parsar
                YearMonth month = YearMonth.parse(monthInput);

                // Filtererar igenom hela transaktionslistan
                transactions = transactions.stream()

                        // Sen filteras datumet och ser vilken månad transaktioner passar med
                        .filter(t -> YearMonth.from(t.getDate()).equals(month))

                        // Resultatet skickas till listan
                        .toList();
                break;

                // Välja att filtera med år
            case "år":

                // Utskrift till användaren
                System.out.println("Ange år (yyyy):");

                // Inputen parsas
                int year = Integer.parseInt(scan.nextLine());

                // Filterar igenom hela transaktionslistan
                transactions = transactions.stream()

                        // Sen filteras datumet och ser vilket år transaktioner passar med
                        .filter(t -> t.getDate().getYear() == year)

                        // Resultatet skickas till listan
                        .toList();

                break;


            case "samtliga":

                // Ingen filtering behövs för samtliga

                break;

                // Standard att detta skrivs ut
            default:
                System.out.println("Ogiltigt val, visar samtliga.");
                break;
        }

        // Kontrollera om listan är tom
        if (transactions.isEmpty()){
            System.out.println("Inga transaktioner hittades.");
            return;
        }

        // Loopar alla transaktioner samt skriver ut resultat efter filtering
        System.out.println("Efter filtering:");
        for (Transaction t : transactions) {
            System.out.println("ID " + t.getID() +
                    ", BESKRIVNING: " + t.getDescription() +
                    ", BELOPP: " + t.getAmount() +
                    ", DATUM: " + t.getDate().format(formatter) +
                    ", TYP: " + (t.getType()));
        }
    }
}

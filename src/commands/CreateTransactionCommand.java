package commands;


import Transaction.Transaction;
import enums.TransactionType;
import services.ITransactionService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class CreateTransactionCommand extends Command { // Ärver från kommando

    public CreateTransactionCommand(ITransactionService transactionService) { // Konstruktor för kommandot
        // Namn samt beskrivning för kommandot sätts, transactionService används
        super("skapa", "Lägg till en ny transaktion", transactionService);

    }

    @Override
    public void execute() throws Exception {
        // Metoden för att köra kommandot

        // Scanner deklareras
        Scanner scan = new Scanner(System.in);

        // Utskrift till användaren
        System.out.println("Skapa en transaktion");
        System.out.print("Ange mängden pengar i svenska kronor (SEK) ");

        // Transaktionens kronor tas emot
        int amount = Integer.parseInt(scan.nextLine());

        // Utskrift till användaren
        System.out.println("Ange beskrivning för transaktionen");

        // Beskrivningen för transaktionen tas emot
        String description = scan.nextLine();

        // Datumet sätts som null
        LocalDateTime date = null;

        // Formatterar om datumet
        DateTimeFormatter formatter = Transaction.DATE_FORMAT;

        // Medan date är null
        while (date == null) {

            // Användaren anger datumet för transaktionen
            System.out.println("Ange datum för transaktionen i följande format (t.ex. 2025-01-01 12:00)");

            // Scanner tar emot användarens svar
            String dateInput = scan.nextLine();

            // Try Catch
            try {
                // Ser så att datumet är korrekt formatterat
                date = LocalDateTime.parse(dateInput, formatter);
            } catch (DateTimeParseException e) {

                // Skriver ut felmeddelande
                System.out.println("Felaktigt datumformat! Försök igen");

            }
        }

        // Utskrift till användaren
        System.out.println("Är detta en inkomst eller utgift (INKOMST/UTGIFT)");

        // Scanner tar emot om vilken typ av transaktionen är
        String typeInput = scan.nextLine().trim().toUpperCase();

        TransactionType type;
        try {
            type = TransactionType.valueOf(typeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Felaktig typ. Sätts som UTGIFT som standard");
            type = TransactionType.UTGIFT;
        }

        // Behövs därför ingen else sats, för att alla transaktioner kommer som utgifter som standard

        // Transaktionen skapas
        Transaction transaction = new Transaction(description, amount, date, type);

        // Transaktionen sparas i service
        transactionService.save(transaction);

        // Utskrift till användaren
        System.out.println("Transaktionen " + description + " skapades." );

    }
}

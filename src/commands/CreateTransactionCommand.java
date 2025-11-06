package commands;


import Transaction.Transaction;
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

        // isIncome sätts alltid som false, dvs att alla transaktioner blir alltid utgifter
        boolean isIncome = false;

        // Utskrift till användaren
        System.out.println("Är detta en inkomst (JA/NEJ)");

        // Scanner tar emot om vilken typ av transaktionen är
        String incomeAnswer = scan.nextLine();

        // Om svaret är ja
        if (incomeAnswer.equalsIgnoreCase("ja")){

            // Då är transaktionen en inkomst
            isIncome = true;

        }

        // Behövs därför ingen else sats, för att alla transaktioner kommer som utgifter som standard

        // Transaktionen skapas
        Transaction transaction = new Transaction(description, amount, date, isIncome);

        // Transaktionen sparas i service
        transactionService.save(transaction);

        // Utskrift till användaren
        System.out.println("Transaktionen " + description + " skapades." );

    }
}

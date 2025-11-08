package commands;

import models.Transaction;
import services.ITransactionService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DeleteTransactionCommand extends Command { // Ärver från kommando

    public DeleteTransactionCommand(ITransactionService transactionService) { // Konstruktor för kommandot
        // Namn samt beskrivning för kommandot sätts, transactionService används
        super("radera", "Radera en befintlig transaktion", transactionService);
    }

    @Override
    public void execute() throws Exception {
        // Metoden för att köra kommandot

        // Hämtar samtliga transaktioner
        List<Transaction> transactions = transactionService.findAll();

        // Formatterar om datumformat
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Scanner deklareras
        Scanner scan = new Scanner(System.in);


        // Om listan är tom så skrivs en utskrift till användaren om detta
        if (transactions.isEmpty()){
            System.out.println("Inga transaktioner att hämta");
            return;
        }

        // Loopar genom listan
        for (Transaction t : transactions){

            // Printar alla transaktioner
            System.out.println("ID " + t.getID() +
                    ", Beskrivning: " + t.getDescription() +
                    ", Belopp: " + t.getAmount() +
                    ", Datum: " + t.getDate().format(formatter) +
                    ", Typ: " + t.getType());

        }

        // Utskrift till användaren
        System.out.println("Ange ID för den transaktionen du vill ta bort");

        // Tar emot ID för transaktionen genom scanner
        String idToDelete = scan.nextLine();

        // Try catch
        try {
            // Tar emot ID
            UUID id = UUID.fromString(idToDelete);

            // Genom transactionService så raderas transaktionen
            transactionService.delete(id);

            // Utskrift till användaren
            System.out.println("Transaktionen " + id + " har raderats från systemet");


        } catch (IllegalArgumentException e) {

            // Felmeddelande skrivs ut
            System.out.println("Felaktigt ID-format! Försök igen");

        }
    }
}

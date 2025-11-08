package repositories;

import models.Transaction;
import enums.TransactionType;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileTransactionRepository implements ITransactionRepository { // Implementerar ITransactionRepository


    // Alla filer sluter med .txt
    private static final String EXTENSION = ".txt";

    @Override
    public Transaction findById(UUID ID) throws Exception {
        // Metod för att hitta transaktion

        // Hämta filnamnet vilket är ID
        String fileName = getFileName(ID);

        // Sen körs BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){

            // Läser samtliga variabeler till Transaktionen
            reader.readLine();
            // Skippar ID:t för att vi redan har det

            // Läser samtliga övriga variabler
            String description = reader.readLine();
            String amountString = reader.readLine();
            String dateString = reader.readLine();
            String typeString = reader.readLine();

            // Parsar variabler
            int amount = Integer.parseInt(amountString.trim());
            LocalDateTime date = LocalDateTime.parse(dateString.trim(), Transaction.DATE_FORMAT);

            TransactionType type = TransactionType.valueOf(typeString.trim().toUpperCase());

            // Skapar transaktion
            Transaction transaction = new Transaction(ID, description, amount, date, type);

            // Returnerar transaktionen
            return transaction;
        }
    }

    @Override
    public List<Transaction> findAll() throws Exception {
        // Metod för att hitta alla transaktioner

        // Hämta listan med alla transaktioner
        ArrayList<Transaction> transactions = new ArrayList<>();

        // Filen hämtas inte från någon specifik mapp
        File directory = new File("./");
        // Alla transaktionsfiler hämtas från directory
        File[] transactionFiles = directory.listFiles();
        // Om det inte finns några filer eller av några orsaker inte fungerar
        if (transactionFiles == null) {
            // Då skickas endast en tom lista
            return transactions;
        }

        // For-loop genom samtliga transactionsFiler
        for (File transactionFile : transactionFiles){

            // Hämtar namnet för transactionsfilen
            String name = transactionFile.getName();

            // Om den slutar med .txt så fortsätter vi
            if (!name.endsWith(EXTENSION)){
                continue;
            }

            // För varje fil i mappen försöker vi läsa ut UUID från filnamnet
            String idPart = name.substring(0, name.length() - EXTENSION.length());
            try {
                UUID ID = UUID.fromString(idPart); // Konvertera filnamnet till UUID
                Transaction transaction = findById(ID); // Hämta transaktionen med ID
                transactions.add(transaction); // Lägg till i listan
            } catch (NumberFormatException ignored) {
                System.out.println("Ogiligt filnamn:" + name + " (hoppar över)");
            }
        }
        // Returnerar alla transaktioner
        return transactions;
    }

    @Override
    public void delete(UUID ID) throws Exception {
        // Metod för att radera transaktioner

        // Hämta filnamnet
        String fileName = getFileName(ID);

        // Deklarare filen
        File file = new File(fileName);

        // En boolean ifall filen är ignored då raderas den
        boolean ignored = file.delete();

    }

    @Override
    public void save(Transaction transaction) throws Exception {
        // Metod för att spara transaktioner

        // Hämta filnamnet som är UUID
        String fileName = getFileName(transaction.getID());

        // Try för att börja skriva i filen
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            // Samtliga variabler skrivs i filen
            writer.append(transaction.getID().toString())
                    .append("\n")
                    .append(transaction.getDescription())
                    .append("\n")
                    .append(Integer.toString(transaction.getAmount()))
                    .append("\n")
                    .append(transaction.getDate().format(Transaction.DATE_FORMAT))
                    .append("\n")
                    .append(transaction.getType().getDisplayName());
        }
    }

    // Metod för att hämta filnamnet
    private static String getFileName(UUID ID){

           return ID + EXTENSION;
    }
}

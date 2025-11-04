package repostiores;

import Transaction.Transaction;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileTransactionRepository implements ITransactionRepository {


    protected static final String EXTENSION = ".txt";

    @Override
    public Transaction findById(int ID) throws Exception {
        String fileName = getFileName(ID);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){

            reader.readLine();
            String description = reader.readLine();
            String amountString = reader.readLine();
            String dateString = reader.readLine();
            String isIncomeString = reader.readLine();

            int amount = Integer.parseInt(amountString.trim());
            LocalDateTime date = LocalDateTime.parse(dateString.trim(), Transaction.DATE_FORMAT);
            boolean isIncome = isIncomeString.equalsIgnoreCase("ja");

            Transaction transaction = new Transaction(description, amount, date, isIncome);
            transaction.setID(ID);

            if (ID >= Transaction.nextId) {

                Transaction.nextId = ID + 1;

            }


            return transaction;
        }
    }

    @Override
    public List<Transaction> findAll() throws Exception {
        ArrayList<Transaction> transactions = new ArrayList<>();

        File directory = new File("./");
        File[] transactionFiles = directory.listFiles();
        if (transactionFiles == null) {
            return transactions;
        }
        for (File transactionFile : transactionFiles){

            String name = transactionFile.getName();

            if (!name.endsWith(EXTENSION)){
                continue;
            }

            String idPart = name.substring(0, name.length() - EXTENSION.length());
            try {
                int id = Integer.parseInt(idPart);
                Transaction transaction = findById(id);
                transactions.add(transaction);
            } catch (NumberFormatException ignored) {
            }
        }
        return transactions;
    }

    @Override
    public void delete(int ID) throws Exception {
        String fileName = getFileName(ID);
        File file = new File(fileName);
        boolean ignored = file.delete();

    }

    @Override
    public void save(Transaction transaction) throws Exception {
        String fileName = getFileName(transaction.getID());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.append(Integer.toString(transaction.getID()))
                    .append("\n")
                    .append(transaction.getDescription())
                    .append("\n")
                    .append(Integer.toString(transaction.getAmount()))
                    .append("\n")
                    .append(transaction.getDate().format(Transaction.DATE_FORMAT))
                    .append("\n")
                    .append(transaction.getType() ? "Inkomst" : "Utgift");
        }
    }


    protected static String getFileName(int ID){

           return ID + EXTENSION;
    }
}

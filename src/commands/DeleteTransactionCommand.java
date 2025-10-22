package commands;

import Transaction.Transaction;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class DeleteTransactionCommand extends Command {

    protected final ArrayList<Transaction> transactions;

    public DeleteTransactionCommand(ArrayList<Transaction> transactions) {
        super("radera", "Radera en befintlig transaktion");
        this.transactions = transactions;
    }

    @Override
    public void execute() {

        Scanner scan = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        if (transactions.isEmpty()){
            System.out.println("Inga transaktioner att ta");
            return;
        }


        for (Transaction t : transactions){

            System.out.println("ID " + t.getID() +
                    ", Beskrivning: " + t.getDescription() +
                    ", Belopp: " + t.getAmount() +
                    ", Datum: " + t.getDateTime().format(formatter));

        }


        System.out.println("Ange ID för den transaktionen du vill ta bort");
        int idToDelete = scan.nextInt();

        Transaction transactionRemove = null;
        for (Transaction t : transactions) {
            if (idToDelete == t.getID()) {

                transactionRemove = t;
                break;


            }
        }

        if (transactionRemove != null){
            transactions.remove(transactionRemove);
            System.out.println("Transaktion med ID" + idToDelete + " togs bort.");
        } else {

            System.out.println("Ingen transaktion hittades med ID " + idToDelete + ".");

        }

    }
}

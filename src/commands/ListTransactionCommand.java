package commands;

import Transaction.Transaction;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class ListTransactionCommand extends Command {

    protected final ArrayList<Transaction> transactions;

    public ListTransactionCommand(ArrayList<Transaction> transactions){

        super("lista", "Få en lista av alla transaktioner");
        this.transactions = transactions;

    }

    @Override
    public void execute(){

        if (transactions.isEmpty()){

            System.out.println("Inga transaktioner hittades.");
            return;

        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Transaction t : transactions){

            System.out.println("ID " + t.getID() +
                    ", Beskrivning: " + t.getDescription() +
                    ", Belopp: " + t.getAmount() +
                    ", Datum: " + t.getDateTime().format(formatter));


        }
    }

}

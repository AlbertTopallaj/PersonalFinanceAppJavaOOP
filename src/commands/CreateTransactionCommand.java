package commands;


import Transaction.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class CreateTransactionCommand extends Command {

    protected final ArrayList<Transaction> transactions;

    public CreateTransactionCommand(ArrayList<Transaction> transactions) {
        super("skapa", "Lägg till en ny transaktion");
        this.transactions = transactions;
    }

    @Override
    public void execute(){

        Scanner scan = new Scanner(System.in);

        this.ID = nextId++;

        System.out.println("Ange beskrivning för transaktion");

        this.description = scan.nextLine();

        System.out.println();

        System.out.println("Ange pengar i svenska kronor (SEK)");

        this.amount = scan.nextInt();
        scan.nextLine();

        System.out.println("Ange datum för transaktionen (format: yyyy-MM-dd HH:mm):");

        String dateInput = scan.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.date = LocalDateTime.parse(dateInput, formatter);

        Transaction newTransaction = new Transaction(this.ID, this.description, this.amount, this.date);

        transactions.add(newTransaction);

        System.out.println("Transaktionen " + this.description + " skapades");


    }
}

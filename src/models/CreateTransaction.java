package models;

import Transaction.Transaction;
import commands.Command;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateTransaction {

    protected ArrayList<Transaction> transactions = new ArrayList<>();
    protected static int nextId = 1;
    protected int ID;
    protected String description;
    protected int amount;
    protected LocalDateTime date;



    public void run() {

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

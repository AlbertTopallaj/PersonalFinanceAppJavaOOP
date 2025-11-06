package commands;

import Transaction.Transaction;
import services.ITransactionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
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
                    ", TYP: " + (t.getType() ? "Inkomst" : "Utgift"));
        }

        // Filteringen av transaktioner
        System.out.println();
        System.out.println("Vill du se (inkomst / utgift / samtliga): ");
        String type = scan.nextLine();

        // Val av inkomst eller utgift
        switch (type.toLowerCase()) {

            case "inkomst":
                // OBS: Transaction::getType måste returnera boolean där true = inkomst
                transactions = transactions.stream()
                        .filter(Transaction::getType) // <-- OK om getType() returnerar true/false
                        .toList();
                break;

            case "utgift":
                transactions = transactions.stream()
                        .filter(transaction -> !transaction.getType()) // <-- OK om getType() returnerar true/false
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

            case "dag":
                System.out.println("Ange datum (t.ex 2010-01-01): ");
                String dayInput = scan.nextLine();

                // Bättre att använda LocalDate istället för LocalDateTime
                LocalDate day = LocalDate.parse(dayInput); // <-- FIX: ändrat från LocalDateTime
                transactions = transactions.stream()
                        .filter(transaction -> transaction.getDate().toLocalDate().equals(day)) // <-- OK
                        .toList();
                break;

            case "vecka":
                System.out.println("Ange datum inom veckan (t.ex 2000-01-01): ");
                String weekInput = scan.nextLine();
                LocalDate week = LocalDate.parse(weekInput); // <-- FIX: använd LocalDate

                WeekFields wf = WeekFields.ISO;
                int targetWeek = week.get(wf.weekOfWeekBasedYear());
                int targetYear = week.get(wf.weekBasedYear()); // <-- FIX: använd weekBasedYear() istället för getYear()

                transactions = transactions.stream()
                        .filter(t -> {
                            LocalDate tDate = t.getDate().toLocalDate();
                            int transactionWeek = tDate.get(wf.weekOfWeekBasedYear());
                            int transactionYear = tDate.get(wf.weekBasedYear()); // <-- FIX
                            return transactionWeek == targetWeek && transactionYear == targetYear;
                        })
                        .toList();
                break;

            case "månad":
                System.out.println("Ange år och månad (yyyy-MM):");
                String monthInput = scan.nextLine();
                YearMonth month = YearMonth.parse(monthInput);
                transactions = transactions.stream()
                        .filter(t -> YearMonth.from(t.getDate()).equals(month))
                        .toList();
                break;

            case "år":
                System.out.println("Ange år (yyyy):");
                int year = Integer.parseInt(scan.nextLine());
                transactions = transactions.stream()
                        .filter(t -> Year.from(t.getDate()).getValue() == year)
                        .toList();
                break;

            case "samtliga":

                break;

            default:
                System.out.println("Ogiltigt val, visar samtliga.");
                break;
        }

        // Kontrollera om listan är tom
        if (transactions.isEmpty()){
            System.out.println("Inga transaktioner hittades.");
            return;
        }
    }
}

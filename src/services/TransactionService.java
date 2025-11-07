package services;

import Transaction.Transaction;
import enums.TransactionType;
import repositories.ITransactionRepository;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

// Implementerar ITransactionService
public class TransactionService implements ITransactionService {

    // Deklarerar ITransactionRepository
    private final ITransactionRepository transactionRepository;

    // Konstruktor för ITransactionRepository
    public TransactionService(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction findById(UUID ID) throws Exception {
        // Hämtar en transaktion från repository baserat på dess unika ID
        return transactionRepository.findById(ID);
    }

    @Override
    public List<Transaction> findAll() throws Exception {
        // Hämtar samtliga transaktioner från repository
        return new ArrayList<>(transactionRepository.findAll());
    }

    @Override
    public void delete(UUID ID) throws Exception {
        // Raderar transaktion baserat på dess unika ID från repository
        transactionRepository.delete(ID);
    }

    @Override
    public void save(Transaction transaction) throws Exception {
        // Sparar transaktionen till repositoryt
        transactionRepository.save(transaction);

    }

    public double getDailyIncome(LocalDateTime dateTime) throws Exception {
        // Hämta den dagliga inkomsten
        return findAll().stream()
                .filter(t -> t.getType() == TransactionType.INKOMST)
                .filter(transaction -> transaction.getDate().equals(dateTime))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getWeeklyIncome(LocalDateTime dateTime) throws Exception {
        // Hämta den veckovisa inkomsten
        WeekFields wf = WeekFields.of(Locale.getDefault());
        int targetWeek = dateTime.get(wf.weekOfWeekBasedYear());
        int targetYear = dateTime.getYear();

        return findAll().stream()
                .filter(t -> t.getType() == TransactionType.INKOMST)
                .filter(t -> {
                    int week = t.getDate().get(wf.weekOfWeekBasedYear());
                    int year = t.getDate().getYear();
                    return week == targetWeek && year == targetYear;
                })
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getMonthlyIncome(YearMonth month) throws Exception {
        // Hämta inkomsten för månaden
        return findAll().stream()
                .filter(t -> t.getType() == TransactionType.INKOMST)
                .filter(t -> YearMonth.from(t.getDate()).equals(month))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }


    public double getYearlyIncome(Year year) throws Exception {
        // Hämta inkomsten för året
        return findAll().stream()
                .filter(t -> t.getType() == TransactionType.INKOMST)
                .filter(t -> t.getDate().getYear() == year.getValue())
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalIncome() throws Exception {
        // Hämta den totala inkomsten
          return findAll().stream()
                .filter(t -> t.getType() == TransactionType.INKOMST)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalExpenses() throws Exception {
        // Hämta den totala utgiften
        return findAll().stream()
                .filter(t -> t.getType() == TransactionType.UTGIFT)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getBalance() throws Exception {
        // Hämta kontobalansen
        return getTotalIncome() - getTotalExpenses();
    }

    public double getDailySpending(LocalDateTime dateTime) throws Exception {
        // Hämta dagens utgifter
        return findAll().stream()
                .filter(t -> t.getType() == TransactionType.UTGIFT)
                .filter(t -> t.getDate().equals(dateTime))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getWeeklySpending(LocalDateTime dateTime) throws Exception {
        // Hämta den veckovisa utgiften
        WeekFields wf = WeekFields.of(Locale.getDefault());
        int targetWeek = dateTime.get(wf.weekOfWeekBasedYear());
        int targetYear = dateTime.getYear();

        return findAll().stream()
                .filter(t -> t.getType() == TransactionType.UTGIFT)
                .filter(t -> {
                    int week = t.getDate().get(wf.weekOfWeekBasedYear());
                    int year = t.getDate().getYear();
                    return week == targetWeek && year == targetYear;
                })
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getMonthlySpending(YearMonth month) throws Exception {
        // Hämta utgifterna för månaden
        return findAll().stream()
                .filter(t -> t.getType() == TransactionType.UTGIFT)
                .filter(t -> YearMonth.from(t.getDate()).equals(month))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getYearlySpending(Year year) throws Exception {
        // Hämta utgifterna för året
        return findAll().stream()
                .filter(t -> t.getType() == TransactionType.UTGIFT)
                .filter(t -> t.getDate().getYear() == year.getValue())
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}

package services;

import Transaction.Transaction;
import enums.TransactionType;
import repositories.ITransactionRepository;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Implementerar ITransactionService
public class TransactionService implements ITransactionService {

    // Deklarerar ITransactionRepository
    private final ITransactionRepository transactionRepository;

    // Deklarerar ITransactionFilter
    private final ITransactionFilter transactionFilter;

    // Konstruktor för ITransactionRepository
    public TransactionService(ITransactionRepository transactionRepository, ITransactionFilter transactionFilter) {
        this.transactionRepository = transactionRepository;
        this.transactionFilter = transactionFilter;
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

    public double getDailyIncome(LocalDate dateTime) throws Exception {
        // Hämta den dagliga inkomsten
        List<Transaction> incomes = transactionFilter.filterByType(findAll(), TransactionType.INKOMST);
        List<Transaction> todayIncomes = transactionFilter.filterByDate(incomes, dateTime);
        return todayIncomes.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public double getWeeklyIncome(LocalDate dateTime) throws Exception {
        List<Transaction> incomes = transactionFilter.filterByType(findAll(), TransactionType.INKOMST);
        List<Transaction> weeklyIncomes = transactionFilter.filterByWeek(incomes, dateTime);
        return weeklyIncomes.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public double getMonthlyIncome(YearMonth month) throws Exception {
        // Hämta inkomsten för månaden
        List<Transaction> incomes = transactionFilter.filterByType(findAll(), TransactionType.INKOMST);
        List<Transaction> monthlyIncomes = transactionFilter.filterByMonth(incomes, month);
        return monthlyIncomes.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public double getYearlyIncome(int year) throws Exception {
        // Hämta inkomsten för året
        List<Transaction> incomes = transactionFilter.filterByType(findAll(), TransactionType.INKOMST);
        List<Transaction> yearlyIncomes = transactionFilter.filterByYear(incomes, year);
        return yearlyIncomes.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public double getTotalIncome() throws Exception {
        // Hämta den totala inkomsten
        List<Transaction> incomes = transactionFilter.filterByType(findAll(), TransactionType.INKOMST);
        return incomes.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public double getTotalExpenses() throws Exception {
        // Hämta den totala utgiften
        List<Transaction> expenses = transactionFilter.filterByType(findAll(), TransactionType.UTGIFT);
        return expenses.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public double getBalance() throws Exception {
        // Hämta kontobalansen
        return getTotalIncome() - getTotalExpenses();
    }

    public double getDailySpending(LocalDate dateTime) throws Exception {
        // Hämta dagens utgifter
        List<Transaction> expenses = transactionFilter.filterByType(findAll(), TransactionType.UTGIFT);
        List<Transaction> dailyExpenses = transactionFilter.filterByDay(expenses, dateTime);
        return dailyExpenses.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public double getWeeklySpending(LocalDate dateTime) throws Exception {
        List<Transaction> expenses = transactionFilter.filterByType(findAll(), TransactionType.UTGIFT);
        List<Transaction> weeklyExpenses = transactionFilter.filterByWeek(expenses, dateTime);
        return weeklyExpenses.stream().mapToDouble(Transaction::getAmount).sum();
    }

    public double getMonthlySpending(YearMonth month) throws Exception {
        // Hämta utgifterna för månaden
        List<Transaction> expenses = transactionFilter.filterByType(findAll(), TransactionType.UTGIFT);
        List<Transaction> monthlyExpenses = transactionFilter.filterByMonth(expenses, month);
        return monthlyExpenses.stream().mapToDouble(Transaction::getAmount).sum();
    }

    @Override
    public double getYearlySpending(int year) throws Exception {
        // Hämta utgifterna för året
        List<Transaction> expenses = transactionFilter.filterByType(findAll(), TransactionType.UTGIFT);
        List<Transaction> yearlyExpenses = transactionFilter.filterByYear(expenses, year);
        return yearlyExpenses.stream().mapToDouble(Transaction::getAmount).sum();
    }
}

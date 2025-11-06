package services;

import Transaction.Transaction;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

public interface ITransactionService {

    // Samtliga metoder för hanteringen av transaktioner

    Transaction findById(UUID ID) throws Exception;

    List<Transaction> findAll() throws Exception;

    void save(Transaction transaction) throws Exception;

    void delete(UUID ID) throws Exception;

    double getTotalIncome() throws Exception;

    double getTotalExpenses() throws Exception;

    double getBalance() throws Exception;

    double getDailySpending(LocalDateTime date) throws Exception;

    double getWeeklySpending(LocalDateTime date) throws Exception;

    double getMonthlySpending(YearMonth month) throws Exception;

    double getYearlySpending(Year year) throws Exception;

    double getDailyIncome(LocalDateTime date) throws Exception;

    double getWeeklyIncome(LocalDateTime date) throws Exception;

    double getMonthlyIncome(YearMonth month) throws Exception;

    double getYearlyIncome(Year year) throws Exception;
}

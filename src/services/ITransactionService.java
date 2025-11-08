package services;

import models.Transaction;

import java.time.LocalDate;
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

    double getDailySpending(LocalDate date) throws Exception;

    double getWeeklySpending(LocalDate date) throws Exception;

    double getMonthlySpending(YearMonth month) throws Exception;

    double getDailyIncome(LocalDate date) throws Exception;

    double getWeeklyIncome(LocalDate date) throws Exception;

    double getMonthlyIncome(YearMonth month) throws Exception;

    double getYearlyIncome(int year) throws Exception;

    double getYearlySpending(int year) throws Exception;
}

package services;

import models.Transaction;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

public interface ITransactionService {
    // Samtliga metoder för hanteringen av transaktioner

    Transaction findById(UUID ID) throws IOException;

    List<Transaction> findAll() throws IOException;

    void save(Transaction transaction) throws IOException;

    void delete(UUID ID) throws IOException;

    double getTotalIncome() throws IOException ;

    double getTotalExpenses() throws IOException ;

    double getBalance() throws IOException ;

    double getDailySpending(LocalDate date) throws IOException ;

    double getWeeklySpending(LocalDate date) throws IOException ;

    double getMonthlySpending(YearMonth month) throws IOException ;

    double getDailyIncome(LocalDate date) throws IOException ;

    double getWeeklyIncome(LocalDate date) throws IOException ;

    double getMonthlyIncome(YearMonth month) throws IOException ;

    double getYearlyIncome(int year) throws IOException ;

    double getYearlySpending(int year) throws IOException ;
}

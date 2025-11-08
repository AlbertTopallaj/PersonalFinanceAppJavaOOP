package services;

import Transaction.Transaction;
import enums.TransactionType;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;

public interface ITransactionFilter {

    // Interface som håller i filtrets funktioner

    List<Transaction> filterByType(List<Transaction> transactions, TransactionType type);
    List<Transaction> filterByDate(List<Transaction> transactions, LocalDate date);
    List<Transaction> filterByDay(List<Transaction> transactions, LocalDate day);
    List<Transaction> filterByWeek(List<Transaction> transactions, LocalDate date);
    List<Transaction> filterByMonth(List<Transaction> transactions, YearMonth month);
    List<Transaction> filterByYear(List<Transaction> transactions, int year);

}

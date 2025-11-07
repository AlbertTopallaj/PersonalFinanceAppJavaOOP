package utility;

import Transaction.Transaction;
import enums.TransactionType;
import services.ITransactionFilter;

import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

import static enums.TransactionType.INKOMST;
import static enums.TransactionType.UTGIFT;

public class TransactionFilter implements ITransactionFilter {

    @Override
    // Filtrera utifrån typ av transaktion
    public List<Transaction> filterByType(List<Transaction> transactions, TransactionType type) {
        // Returnera en switch case
        return switch (type) {
            case INKOMST -> transactions.stream()
                    // Filtrerar inkomster
                    .filter(t -> t.getType() == INKOMST)
                    // Samlar resultatet till en lista
                    .collect(Collectors.toList());

            case UTGIFT -> transactions.stream()
                    // Filtrerar utgifter
                    .filter(t -> t.getType() == UTGIFT)
                    .collect(Collectors.toList());

            // Standard: visa samtliga
            default -> transactions;
        };
    }
    
    @Override
    public List<Transaction> filterByDay(List<Transaction> transactions, LocalDate date) {
        return transactions.stream()
                .filter(t -> t.getDate().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> filterByWeek(List<Transaction> transactions, LocalDate date) {
        LocalDate startOfWeek = date.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = date.with(DayOfWeek.SUNDAY);

        return transactions.stream()
                .filter(t -> {
                    LocalDate transactionDate = t.getDate().toLocalDate();
                    return !transactionDate.isBefore(startOfWeek) && !transactionDate.isAfter(endOfWeek);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> filterByMonth(List<Transaction> transactions, YearMonth month) {
        return transactions.stream()
                .filter(t -> YearMonth.from(t.getDate()).equals(month))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> filterByYear(List<Transaction> transactions, int year) {
        return transactions.stream()
                .filter(t -> t.getDate().getYear() == year)
                .collect(Collectors.toList());
    }
}

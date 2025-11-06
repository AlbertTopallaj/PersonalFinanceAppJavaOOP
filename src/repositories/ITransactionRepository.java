package repositories;

import Transaction.Transaction;

import java.util.List;
import java.util.UUID;

public interface ITransactionRepository {

    // Metoder för transaktioner
    Transaction findById(UUID ID) throws Exception;
    List<Transaction> findAll() throws Exception;
    void save(Transaction transaction) throws Exception;
    void delete(UUID ID) throws Exception;

}

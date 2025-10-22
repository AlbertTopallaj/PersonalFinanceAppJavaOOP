package repository;

import Transaction.Transaction;

import java.util.List;

public interface ITransactionRepository {

    Transaction findById(int ID);
    List<Transaction> findAll();
    void save(Transaction transaction);
    void delete(int ID);

}

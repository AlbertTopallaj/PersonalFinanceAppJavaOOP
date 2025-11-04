package repostiores;

import Transaction.Transaction;

import java.util.List;

public interface ITransactionRepository {

    Transaction findById(int ID) throws Exception;
    List<Transaction> findAll() throws Exception;
    void save(Transaction transaction) throws Exception;
    void delete(int ID) throws Exception;

}

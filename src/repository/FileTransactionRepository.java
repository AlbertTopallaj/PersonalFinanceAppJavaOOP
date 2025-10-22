package repository;

import Transaction.Transaction;

import java.util.List;

public class FileTransactionRepository implements ITransactionRepository {


    @Override
    public Transaction findById(int ID) {
        return null;
    }

    @Override
    public List<Transaction> findAll() {
        return List.of();
    }

    @Override
    public void delete(int ID) {

    }

    @Override
    public void save(Transaction transaction) {

    }
}

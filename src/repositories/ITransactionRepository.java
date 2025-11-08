package repositories;

import models.Transaction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ITransactionRepository {

    // Metoder för transaktioner
    Transaction findById(UUID ID) throws FileNotFoundException, IOException;

    List<Transaction> findAll() throws IOException;

    void save(Transaction transaction) throws IOException;

    void delete(UUID ID) throws IOException;

}

package commands;

import models.DeleteTransaction;

public class DeleteTransactionCommand extends Command {


    public DeleteTransactionCommand() {
        super("radera", "Radera en befintlig transaktion");
    }

    @Override
    public void execute() {

        DeleteTransaction deleteTransaction = new DeleteTransaction();

        deleteTransaction.run();

    }
}

package commands;

import models.ListTransaction;



public class ListTransactionCommand extends Command {
    public ListTransactionCommand(){

        super("lista", "Få en lista av alla transaktioner");

    }

    @Override
    public void execute(){

        ListTransaction listTransaction = new ListTransaction();

        listTransaction.run();

    }

}

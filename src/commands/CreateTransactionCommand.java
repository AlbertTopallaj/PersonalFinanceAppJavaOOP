package commands;


import models.CreateTransaction;

import java.util.ArrayList;

public class CreateTransactionCommand extends Command {
    public CreateTransactionCommand() {
        super("skapa", "Lägg till en ny transaktion");
    }

    @Override
    public void execute(){

        CreateTransaction createTransaction = new CreateTransaction();

        createTransaction.run();


    }
}

package commands;

import services.ITransactionService;

import java.util.ArrayList;

public abstract class Command {

    protected final String name;
    protected final String description;
    protected final ITransactionService transactionService;

    public Command(String name, String description, ITransactionService transactionService) {

        this.name = name;
        this.description = description;
        this.transactionService = transactionService;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    @Override
    public String toString(){

        return name + " - " + description;

    }

    public abstract void execute() throws Exception;
}


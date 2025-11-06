package services;

import Transaction.Transaction;
import commands.Command;
import commands.ICommand;
import repositories.FileTransactionRepository;
import repositories.ITransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApplicationService implements ICommand {

    // Listan med alla kommandon deklarerad
    private final List<Command> commands = new ArrayList<>();

    // Deklarera ITransactionService
    private final ITransactionService transactionService;

    public ApplicationService(ITransactionService transactionService){ // Konstruktor för ITransactionService
        this.transactionService = transactionService;
    }

    public ITransactionService getTransactionService(){ // Getter för ITransactionService
        return transactionService;
    }

    @Override
    public void registerCommand(Command command){ // Metod för att registera kommandon
        commands.add(command);
    }

    @Override
    public void executeCommand(String commandInput) throws Exception {
        // Metod för att söka och köra kommandon
        for (Command command : commands) {
            if (command.getName().equalsIgnoreCase(commandInput)) {
                command.execute();
                return;
            }
        }
        // Utskrift till användaren
        System.out.println(commandInput + " är ett okänt kommando");
    }

    public List<Command> getCommands(){
        // Returnerar en lista med alla registerade kommandon
        return commands;
    }
}

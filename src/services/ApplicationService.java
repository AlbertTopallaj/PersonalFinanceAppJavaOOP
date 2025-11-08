package services;

import commands.Command;
import commands.ICommand;

import java.util.ArrayList;
import java.util.List;

public class ApplicationService implements ICommand {

    // Listan med alla kommandon deklarerad
    private final List<Command> commands = new ArrayList<>();

    // Deklarera ITransactionService
    private final ITransactionService transactionService;

    public ApplicationService(ITransactionService transactionService) { // Konstruktor för ITransactionService
        this.transactionService = transactionService;
    }

    // Getter för ITransactionService
    public ITransactionService getTransactionService() {
        return transactionService;
    }

    @Override
    // Metod för att registera kommando
    public void registerCommand(Command command) {
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

    public List<Command> getCommands() {
        // Returnerar en lista med alla registerade kommandon
        return commands;
    }
}

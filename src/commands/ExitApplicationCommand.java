package commands;

import services.ITransactionService;

public class ExitApplicationCommand extends Command { // Ärver från kommando


    public ExitApplicationCommand(ITransactionService transactionService) { // Konstruktor för kommandot
        // Namnet samt beskrivningen sätts för kommandot, transactionService används också
        super("avsluta", "Avsluta programmet", transactionService);
    }

    @Override
    public void execute() {
        // Metoden för kommandot

        // Utskrift till användaren
        System.out.println("Programmet avslutas");

        // Programmet stängs ner
        System.exit(0);

    }
}

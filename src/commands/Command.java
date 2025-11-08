package commands;

import services.ITransactionService;

import java.util.ArrayList;

/* Abstrakt basklass för alla kommandon
 * Funkar helt enkelt som en mall för alla kommandon
 * Alla kommandon har sina gemensamma funktioner såsom
 * name, description, transactionService
 * Syftet är att varje klass har sin egna execute metod för att köra sina egna uppgifter
 */

public abstract class Command {

    // Variabler för ett kommando

    private final String name;
    private final String description;
    protected final ITransactionService transactionService;

    public Command(String name, String description, ITransactionService transactionService) {

        // Konstruktor för alla kommandon
        // Värdena sätts


        this.name = name;
        this.description = description;
        this.transactionService = transactionService;

    }
    // Getters
    // Behövs inga setters då det är en abstract klass

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        // ToString, visas i listan av alla kommandon i transaktionen


        return name + " - " + description;

    }

    public abstract void execute() throws Exception;
    // Varje kommandon har sin egna execute metod men utgår från denna

}


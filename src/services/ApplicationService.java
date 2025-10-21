package services;

import commands.Command;
import commands.ICommand;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationService implements ICommand {

    private final ArrayList<Command> commands = new ArrayList<>();
    private boolean ApplicationIsRunning = true;


    public void registerCommand(Command command){
        commands.add(command);

    }

    @Override
    public void executeCommand(String commandInput) {

        for (Command command : commands){

            if (command.getName().equalsIgnoreCase(commandInput)) {

                command.execute();
                return;

            }
            System.out.println(commandInput + " är ett okänt kommando");
        }
    }


    public void run() {

        System.out.println("=== PERSONAL FINANCE APP ===");
        System.out.println("Välkommen! Välj ett kommando:\n");

        for (Command command : commands){

            System.out.println(" " + command.getName() + " - " + command.getDescription());

        }

        Scanner scan = new Scanner(System.in);
        while (ApplicationIsRunning){

            System.out.println("\n> ");
            String input = scan.nextLine();

            if (input.equalsIgnoreCase("exit")){

                System.out.println("Avslutar programmet...");
                ApplicationIsRunning = false;

            } else {
                executeCommand(input);
            }
        }
    }
}

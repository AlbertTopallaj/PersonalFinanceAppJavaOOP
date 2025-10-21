package services;

import commands.Command;
import commands.ICommand;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationService implements ICommand {

    private final ArrayList<Command> commands = new ArrayList<>();


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

        }
            System.out.println(commandInput + " är ett okänt kommando");

    }
    public void run() {

        Scanner scan = new Scanner(System.in);

        System.out.println("=== PERSONAL FINANCE APP ===");
        System.out.println("Välkommen! Välj ett kommando:\n");

        while (true) {

        for (Command command : commands){

            System.out.println(" " + command.getName() + " - " + command.getDescription());

        }




            System.out.print("> ");
            String input = scan.nextLine();

            executeCommand(input);


        }
    }
}

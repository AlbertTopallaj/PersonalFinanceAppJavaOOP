package models;


import commands.CreateTransactionCommand;
import commands.DeleteTransactionCommand;
import commands.ExitApplicationCommand;
import services.ApplicationService;

import java.util.Scanner;

public class Login {

    protected String username;
    protected String password;

    public void run() {

        Scanner scan = new Scanner(System.in);


        System.out.println();
        System.out.println();
        System.out.println("=== INLOGGNING PERSONAL FINANCE APP ===");

        System.out.println("Vänligen ange dina inloggningsuppgifter");

        System.out.println();

        System.out.println("Användarnamn: ");

        String username = scan.nextLine();

        System.out.println("Lösenord: ");

        String password = scan.nextLine();

        if (password.equals("1234") && username.equals("admin")) {

            System.out.println("Inloggningen lyckades ");

            ApplicationService app = new ApplicationService();

            app.registerCommand(new CreateTransactionCommand());
            app.registerCommand(new DeleteTransactionCommand());
            app.registerCommand(new ExitApplicationCommand());


            app.run();

        } else {

            System.out.println("Inloggnignen misslyckades");

        }
    }
}

package models;


import commands.CreateTransactionCommand;
import commands.DeleteTransactionCommand;
import commands.ExitApplicationCommand;
import commands.ListTransactionCommand;
import repositories.FileTransactionRepository;
import services.ApplicationService;
import services.ITransactionService;
import services.TransactionService;

import java.util.Scanner;

public class Login {

    private final IAuthenticator authenticator;

    public Login(IAuthenticator authenticator){
        this.authenticator = authenticator;

    }

    public boolean authenticate() {
        // Scanner deklarerad
        Scanner scan = new Scanner(System.in);

        // Utskrift till användaren
        System.out.println("\n=== INLOGGNING PERSONAL FINANCE APP ===");
        System.out.println("Användarnamn: ");

        // Scanner för användarnamn
        String username = scan.nextLine();

        System.out.println("Lösenord: ");

        // Scanner för lösenord
        String password = scan.nextLine();

        // Kollar om det är rätt lösenord och användarnamn
        if (authenticator.authenticate(username, password)) {

            // Utskrift till användaren
            System.out.println("Inloggninen lyckades");

            // Returnerar true vid lyckade autentisering
            return true;
        } else {

            // Utskrift till användaren
            System.out.println("Inloggning misslyckades");

            // Returnerar false vid misslyckad autentisering
            return false;

        }
    }
}

import commands.CreateTransactionCommand;
import commands.DeleteTransactionCommand;
import commands.ExitApplicationCommand;
import commands.ListTransactionCommand;
import models.Login;
import repositories.FileTransactionRepository;
import services.*;
import utility.TransactionFilter;

public class Main {
    public static void main(String[] args) throws Exception {

        // Starta Login objektet
        Login login = new Login();

        // Om man lyckas logga in korrekt
        if (login.authenticate()) {

            // Skapa objekt samt deklarera samtliga objekt

            ITransactionFilter transactionFilter = new TransactionFilter();

            // Deklarera transactionService samt FileTransactionRepository
            ITransactionService transactionService = new TransactionService(new FileTransactionRepository(), transactionFilter);

            // Deklarera applicationService
            ApplicationService applicationService = new ApplicationService(transactionService);


            // Registera samtliga kommandon
            applicationService.registerCommand(new CreateTransactionCommand(transactionService));
            applicationService.registerCommand(new ListTransactionCommand(transactionService, transactionFilter));
            applicationService.registerCommand(new DeleteTransactionCommand(transactionService));
            applicationService.registerCommand(new ExitApplicationCommand(transactionService));

            // Deklarera TerminalCommandService
            TerminalCommandService terminalCommandService = new TerminalCommandService(applicationService);

            // TerminalCommandService startas då den har en start metod
            terminalCommandService.start();

        }
    }
}
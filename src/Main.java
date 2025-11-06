import commands.CreateTransactionCommand;
import commands.DeleteTransactionCommand;
import commands.ExitApplicationCommand;
import commands.ListTransactionCommand;
import models.Login;
import repositories.FileTransactionRepository;
import services.ApplicationService;
import services.ITransactionService;
import services.TerminalCommandService;
import services.TransactionService;

public class Main {
    public static void main(String[] args) throws Exception {
        Login login = new Login(); // Starta Login objektet

        if (login.authenticate()) { // Om man lyckas logga in korrekt

            // Skapa objekt samt deklarera samtliga objekt

            // Deklarera transactionService samt FileTransactionRepository
            ITransactionService transactionService = new TransactionService(new FileTransactionRepository());

            // Deklarera applicationService
            ApplicationService applicationService = new ApplicationService(transactionService);


            // Registera samtliga kommandon
            applicationService.registerCommand(new CreateTransactionCommand(transactionService));
            applicationService.registerCommand(new ListTransactionCommand(transactionService));
            applicationService.registerCommand(new DeleteTransactionCommand(transactionService));
            applicationService.registerCommand(new ExitApplicationCommand(transactionService));

            // Deklarera TerminalCommandService
            TerminalCommandService terminalCommandService = new TerminalCommandService(applicationService);

            // TerminalCommandService startas då den har en start metod
            terminalCommandService.start();

        }
    }
}
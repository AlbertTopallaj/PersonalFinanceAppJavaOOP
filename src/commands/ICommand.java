package commands;

public interface ICommand {

    // Två metoder som alla kommandon har
    // Alla kommandon utgår ifrån detta Interface

    void registerCommand(Command command);
    void executeCommand(String commandInput) throws Exception;

}

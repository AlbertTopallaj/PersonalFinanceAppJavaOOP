package commands;

public interface ICommand {

    void registerCommand(Command command);
    void executeCommand(String commandInput);

}

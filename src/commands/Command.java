package commands;

import java.util.ArrayList;

public abstract class Command {

    protected final String name;
    protected final String description;

    private final ArrayList<Command> commands = new ArrayList<>();

    public Command(String name, String description){

        this.name = name;
        this.description = description;

    }

    public void createCommand(){

        commands.add(this);

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public abstract void execute();
}

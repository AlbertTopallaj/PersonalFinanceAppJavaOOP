package commands;

public class Command {

    protected String name;
    protected String description;

    public Command(String name, String description){

        this.name = name;
        this.description = description;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + description;
    }
}

package commands;

import models.ExitApplication;

public class ExitApplicationCommand extends Command {


    public ExitApplicationCommand() {
        super("avsluta", "Avsluta programmet");
    }

    @Override
    public void execute() {

        ExitApplication exitApplication = new ExitApplication();

        exitApplication.run();

    }
}

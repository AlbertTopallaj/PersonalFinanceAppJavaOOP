package services;

import models.Login;

public class SystemService {

    public static void run() {

        LoginService Login = new LoginService();

        LoginService.run();

    }

}

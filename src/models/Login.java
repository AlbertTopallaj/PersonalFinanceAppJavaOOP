package models;


import services.ApplicationService;

import java.util.Scanner;

public class Login {

    private void UserLoginOutprint(){

        Scanner scan = new Scanner(System.in);

        System.out.println("Ange ditt användarnamn ");

        String username = scan.nextLine();

        System.out.println();

        System.out.println("Ange ditt lösenord");

        String password = scan.nextLine();


    }

    private void LoginAuth(String username, String password){

        if (username == "admin" && password == "1234"){

            ApplicationService Application = new ApplicationService();

            Application.run();

        } else {

            System.out.println("Fel användarnamn eller lösenord");

        }

    }

}

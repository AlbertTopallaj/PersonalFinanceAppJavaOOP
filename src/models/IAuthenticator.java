package models;

public interface IAuthenticator {
    // Ett interface för autentisering

    boolean authenticate(String username, String password);


}

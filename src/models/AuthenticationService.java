package models;

public class AuthenticationService implements IAuthenticator{

    // Variablerna för auth
    private final String correctUsername;
    private final String correctPassword;

    // Konstruktor
    public AuthenticationService(String correctUsername, String correctPassword){
        this.correctUsername = correctUsername;
        this.correctPassword = correctPassword;
    }

    // Kollar och jämför så att uppgifterna faktiskt stämmer
    @Override
    public boolean authenticate(String username, String password){
        return correctUsername.equalsIgnoreCase(username) && correctPassword.equalsIgnoreCase(password);
    }

}

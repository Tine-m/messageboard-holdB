package app.services;

public class Validator {

    public static String validateUser(String username, String password) {
        if (username.isBlank() || username == null) {
            return "Brugernavn skal udfyldes";
        } else return null;
    }
}

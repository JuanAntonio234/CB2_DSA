package edu.upc.dsa.exceptions;

public class UserNotFoundException extends Throwable {
    private static final String errorMessage = "El usuario no existe";

    public UserNotFoundException() {
        super(errorMessage);
    }
}

package edu.upc.dsa.exceptions;

public class UserEnPartidaException extends Throwable {
    private static final String errorMessage = "El usuario ya tiene una partida activa";

    public UserEnPartidaException() {
        super(errorMessage);
    }
}

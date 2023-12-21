package edu.upc.dsa.exceptions;

public class JugadorYaExisteException extends Throwable {
    private static final String errorMessage = "Ese jugador ya existe (el email y el usuario tienen que ser únicos)";
    public JugadorYaExisteException() {
        super(errorMessage);
    }
}

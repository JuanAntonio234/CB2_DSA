package edu.upc.dsa.exceptions;

public class MismaDificultadException extends Exception {

    private static final String errorMessage = "El nivel de dificultad elegido es el mismo que se está jugando";

    public MismaDificultadException() {
        super(errorMessage);
    }
}

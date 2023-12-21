package edu.upc.dsa.exceptions;

public class FaltanDatosException extends Throwable {
    private static final String errorMessage = "Faltan datos";
    public FaltanDatosException() {super(errorMessage);
    }
}

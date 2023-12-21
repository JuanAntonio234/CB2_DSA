package edu.upc.dsa.exceptions;

public class NotAnEmailException extends Throwable {
    private static final String errorMessage = "No es un email";
    public NotAnEmailException() {super(errorMessage);
    }
}

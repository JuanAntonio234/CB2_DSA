package edu.upc.dsa.exceptions;

public class ErrorCredencialesException extends Throwable {

    private static final String errorMessage = "El usuario o la contraseña no es correcta";

    public ErrorCredencialesException() {
        super(errorMessage);
    }
}

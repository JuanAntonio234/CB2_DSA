package edu.upc.dsa.exceptions;

public class CapitalInsuficienteException extends Throwable{

    private static final String errorMessage = "El usuario o la contraseña no es correcta";

    public CapitalInsuficienteException() {super(errorMessage);}
}

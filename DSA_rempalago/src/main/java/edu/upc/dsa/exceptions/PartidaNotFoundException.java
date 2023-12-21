package edu.upc.dsa.exceptions;

public class PartidaNotFoundException extends Exception {
    private static final String errorMessage = "La partida no existe";
    public PartidaNotFoundException() {super(errorMessage);}
}

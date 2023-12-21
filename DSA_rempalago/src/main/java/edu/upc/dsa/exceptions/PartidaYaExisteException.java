package edu.upc.dsa.exceptions;

public class PartidaYaExisteException extends Exception {
    private static final String errorMessage = "La partida ya existe";
    public PartidaYaExisteException() {super(errorMessage);}
}

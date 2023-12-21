package edu.upc.dsa.exceptions;

public class AvatarYaExisteException extends Exception {
    private static final String errorMessage = "Ese avatar ya existe ";
    public AvatarYaExisteException() {
        super(errorMessage);
    }
}

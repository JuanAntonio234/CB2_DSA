package edu.upc.dsa.exceptions;

public class ProductoYaExisteException extends Throwable{

    private static final String errorMessage = "Ese Producto ya existe (El nombre tiene que ser único)";
    public ProductoYaExisteException() {super(errorMessage);}
}

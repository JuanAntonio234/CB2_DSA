package edu.upc.dsa.exceptions;

public class ProductoNotFoundException extends Throwable {
    private static final String errorMessage = "El producto no existe";

    public ProductoNotFoundException() {super(errorMessage);}
}

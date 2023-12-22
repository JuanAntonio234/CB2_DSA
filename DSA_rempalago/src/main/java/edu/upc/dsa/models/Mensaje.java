package edu.upc.dsa.models;

public class Mensaje {
    String mensaje;

    public Mensaje(){

    }
    public Mensaje(String mensaje){
        this.mensaje=mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

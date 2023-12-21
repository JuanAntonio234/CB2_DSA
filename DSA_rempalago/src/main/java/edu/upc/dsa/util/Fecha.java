package edu.upc.dsa.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fecha {

    public static String getFecha(){
        Date fechaActual = new Date();

        // Definir el formato que deseas para la fecha
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        // Obtener la fecha formateada como una cadena
        String fechaFormateada = formatoFecha.format(fechaActual);

        return fechaFormateada;
    }
}

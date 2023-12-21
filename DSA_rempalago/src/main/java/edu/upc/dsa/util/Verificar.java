package edu.upc.dsa.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verificar {

    public static boolean esDireccionCorreoValida(String correo) {

        String expresionRegular = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

        Pattern patron = Pattern.compile(expresionRegular);
        Matcher matcher = patron.matcher(correo);

        return matcher.matches();
    }


}

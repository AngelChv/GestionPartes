package org.example.gestionpartes.util;

/**
 * Clase para validar los datos de entrada.
 */
public class Validator {
    public static boolean validarNumAsig(String numAsig) {
        // Verifica si el input tiene exactamente 4 dígitos
        return numAsig != null && numAsig.matches("\\d{4}");
    }

    public static boolean validarNumExp(String numExp) {
        //Cambiar en caso de que el numero de expediente tenga mas de 4 digitos
        return numExp != null && numExp.matches("\\d{4}");
    }

}

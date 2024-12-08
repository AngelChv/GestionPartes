package org.example.gestionpartes.util;

/**
 * Clase para validar los datos de entrada.
 */
public class Validator {
    public static boolean validarNumAsig(String numAsig) {
        // Verifica si el input tiene exactamente 4 dígitos
        return numAsig != null && numAsig.matches("\\d{4}");
    }

}

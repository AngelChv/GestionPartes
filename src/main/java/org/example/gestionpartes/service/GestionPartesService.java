package org.example.gestionpartes.service;

import org.example.gestionpartes.model.Profesor;

public class GestionPartesService {
    private static String css;
    private static Profesor profesor;

    public static Profesor getProfesor() {
        return profesor;
    }

    public static void setProfesor(Profesor profesor) {
        GestionPartesService.profesor = profesor;
    }

    public static String getCss() {
        return css;
    }

    public static void setCss(String css) {
        GestionPartesService.css = css;
    }
}

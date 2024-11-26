package org.example.gestionpartes.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Clase de utilidad para mostrar diferentes alertas.
 * {@link Alert}
 */
public class AlertShow {
    public static void info(String message) {
        generate(message, "Info", null, Alert.AlertType.INFORMATION);
    }

    public static void error(String message) {
        generate(message, "Error", null, Alert.AlertType.ERROR);
    }

    public static void warning(String message) {
        generate(message, "Warning", null, Alert.AlertType.WARNING);
    }
    public static boolean confirmation(String message) {
        return generate(message, "Confirmation", null, Alert.AlertType.CONFIRMATION);
    }

    public static boolean generate(String contentText, String title, String headerText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        Optional<ButtonType> result = alert.showAndWait();
        return (result.isPresent() && result.get() == ButtonType.OK);
    }
}

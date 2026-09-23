package org.gestiontalentoshumanos.system.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertInformation {

    private AlertInformation() {
    }

    private static Optional<ButtonType> show(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();
    }

    public static void showInformation(String title, String message) {
        show(AlertType.INFORMATION, title, message);
    }

    public static void showWarning(String title, String message) {
        show(AlertType.WARNING, title, message);
    }

    public static void showError(String title, String message) {
        show(AlertType.ERROR, title, message);
    }

    public static boolean showConfirmation(String title, String message) {
        Optional<ButtonType> result = show(AlertType.CONFIRMATION, title, message);
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}

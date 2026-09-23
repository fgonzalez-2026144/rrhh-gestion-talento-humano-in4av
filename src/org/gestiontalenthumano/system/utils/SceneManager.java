package org.gestiontalentoshumanos.system.utils;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private static Stage primaryStage;

    private SceneManager() {
    }

    public static void setStage(Stage stage) {
        primaryStage = stage;
    }

    public static void switchTo(ViewFactory view) {
        try {
            Parent root = view.load();
            Scene scene = new Scene(root, view.getWidth(), view.getHeight());
            scene.getStylesheets().add(view.getStylesheet());
            primaryStage.setScene(scene);
            primaryStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
            AlertInformation.showError("Error", "No se pudo cargar la vista " + view.name());
        }
    }
}

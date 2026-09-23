package org.gestiontalentoshumanos.system.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public enum ViewFactory {

    LOGIN("LoginView.fxml", "LoginStyles.css", 1000, 640),
    DASHBOARD("DashboardView.fxml", "DashboardStyles.css", 1200, 720),
    REGISTER("RegistroView.fxml", "RegisterUserStyles.css", 1000, 780);

    private static final String VIEW_PATH = "/org/gestiontalentoshumanos/system/view/";
    private static final String STYLE_PATH = "/org/gestiontalentoshumanos/system/resources/styles/";

    private final String fxml;
    private final String css;
    private final double width;
    private final double height;

    ViewFactory(String fxml, String css, double width, double height) {
        this.fxml = fxml;
        this.css = css;
        this.width = width;
        this.height = height;
    }

    public Parent load() throws IOException {
        return FXMLLoader.load(ViewFactory.class.getResource(VIEW_PATH + fxml));
    }

    public String getStylesheet() {
        return ViewFactory.class.getResource(STYLE_PATH + css).toExternalForm();
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}

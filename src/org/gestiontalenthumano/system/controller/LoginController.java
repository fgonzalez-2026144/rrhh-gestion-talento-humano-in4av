package org.gestiontalentoshumanos.system.controller;

import org.gestiontalentoshumanos.system.service.AuthenticationService;
import org.gestiontalentoshumanos.system.service.AuthenticationStatus;
import org.gestiontalentoshumanos.system.utils.SceneManager;
import org.gestiontalentoshumanos.system.utils.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Label lblError;
    @FXML
    private Button btnIniciarSesion;

    private final AuthenticationService authenticationService = new AuthenticationService();

    @FXML
    private void initialize() {
        lblError.setText("");
        txtContrasena.setOnAction(event -> onIniciarSesion());
    }

    @FXML
    private void onIniciarSesion() {
        lblError.setText("");

        AuthenticationStatus status = authenticationService.login(txtUsuario.getText(), txtContrasena.getText());

        switch (status) {
            case LOGIN_SUCCESS:
                SceneManager.switchTo(ViewFactory.DASHBOARD);
                break;
            case EMPTY_FIELDS:
                lblError.setText("Ingresa tu usuario y contrasena.");
                break;
            case NOT_EXIST_USER:
                lblError.setText("El usuario no existe.");
                break;
            case INVALID_PASSWORD:
                lblError.setText("La contrasena es incorrecta.");
                txtContrasena.clear();
                break;
            case DATABASE_ERROR:
                lblError.setText("No se pudo conectar con la base de datos.");
                break;
        }
    }
}

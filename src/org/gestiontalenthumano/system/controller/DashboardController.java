package org.gestiontalenthumano.system.controller;

import org.gestiontalentoshumanos.system.model.EmpleadoResumen;
import org.gestiontalentoshumanos.system.model.Users;
import org.gestiontalentoshumanos.system.service.EmpleadoService;
import org.gestiontalentoshumanos.system.utils.AlertInformation;
import org.gestiontalentoshumanos.system.utils.SceneManager;
import org.gestiontalentoshumanos.system.utils.Session;
import org.gestiontalentoshumanos.system.utils.ViewFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label lblUsuarioActivo;
    @FXML
    private Label lblRolActivo;
    @FXML
    private Label lblTotalColaboradores;
    @FXML
    private Label lblNuevosEsteMes;
    @FXML
    private Label lblEnProceso;
    @FXML
    private Label lblContratados;

    private final EmpleadoService empleadoService = new EmpleadoService();

    @FXML
    private void initialize() {
        Users user = Session.getUser();
        if (user != null) {
            lblUsuarioActivo.setText(user.getNombreUsuario());
            lblRolActivo.setText(user.getNombreRol());
        }

        EmpleadoResumen resumen = empleadoService.readResumen();
        lblTotalColaboradores.setText(String.valueOf(resumen.getTotal()));
        lblNuevosEsteMes.setText(String.valueOf(resumen.getNuevosEsteMes()));
        lblEnProceso.setText(String.valueOf(resumen.getEnProceso()));
        lblContratados.setText(String.valueOf(resumen.getContratados()));
    }

    @FXML
    private void onRegistrarColaborador() {
        SceneManager.switchTo(ViewFactory.REGISTER);
    }

    @FXML
    private void onConsultarColaboradores() {
        SceneManager.switchTo(ViewFactory.CONSULTA);
    }

    @FXML
    private void onCerrarSesion() {
        if (AlertInformation.showConfirmation("Cerrar sesion", "¿Deseas cerrar tu sesion?")) {
            Session.clear();
            SceneManager.switchTo(ViewFactory.LOGIN);
        }
    }
}

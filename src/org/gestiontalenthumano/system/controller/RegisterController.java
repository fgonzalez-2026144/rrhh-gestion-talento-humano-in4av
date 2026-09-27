package org.gestiontalentoshumanos.system.controller;

import org.gestiontalentoshumanos.system.model.Catalogo;
import org.gestiontalentoshumanos.system.model.Empleado;
import org.gestiontalentoshumanos.system.service.CatalogoService;
import org.gestiontalentoshumanos.system.service.EmpleadoService;
import org.gestiontalentoshumanos.system.service.EmpleadoStatus;
import org.gestiontalentoshumanos.system.utils.EmpleadoContext;
import org.gestiontalentoshumanos.system.utils.SceneManager;
import org.gestiontalentoshumanos.system.utils.ViewFactory;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private Label lblPageTitle;
    @FXML
    private Label lblPageSubtitle;
    @FXML
    private Label lblFeedback;
    @FXML
    private TextField txtIdEmpleado;
    @FXML
    private TextField txtNombreCompleto;
    @FXML
    private ComboBox<Catalogo> cbPuesto;
    @FXML
    private ComboBox<Catalogo> cbDepartamento;
    @FXML
    private DatePicker dpFechaContratacion;
    @FXML
    private TextField txtSalarioBase;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;

    private final EmpleadoService empleadoService = new EmpleadoService();
    private final CatalogoService catalogoService = new CatalogoService();

    /** Si no es null, el formulario esta editando este colaborador en vez de crear uno nuevo. */
    private Integer empleadoIdEnEdicion;

    @FXML
    private void initialize() {
        lblFeedback.setText("");

        List<Catalogo> puestos = catalogoService.getPuestos();
        List<Catalogo> departamentos = catalogoService.getDepartamentos();
        cbPuesto.setItems(FXCollections.observableArrayList(puestos));
        cbDepartamento.setItems(FXCollections.observableArrayList(departamentos));

        if (puestos.isEmpty() || departamentos.isEmpty()) {
            showFeedback("No se pudieron cargar los puestos o departamentos.", false);
        }

        empleadoIdEnEdicion = EmpleadoContext.getEmpleadoId();
        if (empleadoIdEnEdicion != null) {
            cargarModoEdicion(empleadoIdEnEdicion);
        }
    }

    private void cargarModoEdicion(int empleadoId) {
        Empleado empleado = empleadoService.searchById(empleadoId);
        if (empleado == null) {
            showFeedback("No se encontro el colaborador seleccionado.", false);
            empleadoIdEnEdicion = null;
            EmpleadoContext.clear();
            return;
        }

        lblPageTitle.setText("Editar Colaborador");
        lblPageSubtitle.setText("Actualiza la informacion del colaborador.");
        btnGuardar.setText("\uD83D\uDCBE  Actualizar");

        txtIdEmpleado.setText(String.valueOf(empleado.getEmpleadoId()));
        txtIdEmpleado.setDisable(true);
        txtNombreCompleto.setText(empleado.getNombreCompleto());
        dpFechaContratacion.setValue(empleado.getFechaContratacion());
        txtSalarioBase.setText(empleado.getSalarioBase().toPlainString());

        cbPuesto.getItems().stream()
                .filter(item -> item.getId() == empleado.getPuestoId())
                .findFirst()
                .ifPresent(item -> cbPuesto.getSelectionModel().select(item));

        cbDepartamento.getItems().stream()
                .filter(item -> item.getId() == empleado.getDepartamentoId())
                .findFirst()
                .ifPresent(item -> cbDepartamento.getSelectionModel().select(item));
    }

    @FXML
    private void onGuardar() {
        boolean editando = empleadoIdEnEdicion != null;

        EmpleadoStatus status = editando
                ? empleadoService.update(
                        txtIdEmpleado.getText(),
                        txtNombreCompleto.getText(),
                        cbPuesto.getValue(),
                        cbDepartamento.getValue(),
                        dpFechaContratacion.getValue(),
                        txtSalarioBase.getText())
                : empleadoService.create(
                        txtIdEmpleado.getText(),
                        txtNombreCompleto.getText(),
                        cbPuesto.getValue(),
                        cbDepartamento.getValue(),
                        dpFechaContratacion.getValue(),
                        txtSalarioBase.getText());

        switch (status) {
            case CREATED:
                showFeedback("Colaborador registrado correctamente.", true);
                clearForm();
                break;
            case UPDATED:
                EmpleadoContext.clear();
                SceneManager.switchTo(ViewFactory.CONSULTA);
                break;
            case INVALID_ID:
                showFeedback("El ID de empleado debe ser un numero entero positivo.", false);
                break;
            case INVALID_NAME:
                showFeedback("Ingresa el nombre completo (nombre y apellido, solo letras).", false);
                break;
            case INVALID_POSITION:
                showFeedback("Selecciona un puesto laboral.", false);
                break;
            case INVALID_DEPARTMENT:
                showFeedback("Selecciona un departamento.", false);
                break;
            case INVALID_DATE:
                showFeedback("Selecciona una fecha de contratacion valida.", false);
                break;
            case INVALID_SALARY:
                showFeedback("El salario debe ser mayor a 0 y tener maximo 2 decimales. Ej. 8000.00", false);
                break;
            case DUPLICATE_ID:
                showFeedback("Ya existe un colaborador con ese ID de empleado.", false);
                break;
            case NOT_FOUND:
                showFeedback("El colaborador ya no existe. Puede que haya sido eliminado.", false);
                break;
            default:
                showFeedback("No se pudo guardar el colaborador. Intenta de nuevo.", false);
                break;
        }
    }

    @FXML
    private void onCancelar() {
        boolean editando = empleadoIdEnEdicion != null;
        EmpleadoContext.clear();
        SceneManager.switchTo(editando ? ViewFactory.CONSULTA : ViewFactory.DASHBOARD);
    }

    private void showFeedback(String message, boolean success) {
        lblFeedback.getStyleClass().removeAll("feedback-ok", "feedback-error");
        lblFeedback.getStyleClass().add(success ? "feedback-ok" : "feedback-error");
        lblFeedback.setText(message);
    }

    private void clearForm() {
        txtIdEmpleado.clear();
        txtNombreCompleto.clear();
        cbPuesto.getSelectionModel().clearSelection();
        cbDepartamento.getSelectionModel().clearSelection();
        dpFechaContratacion.setValue(null);
        txtSalarioBase.clear();
    } 
}

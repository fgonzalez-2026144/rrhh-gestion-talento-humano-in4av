package org.gestiontalenthumano.system.controller;

import org.gestiontalentoshumanos.system.model.Empleado;
import org.gestiontalentoshumanos.system.model.Users;
import org.gestiontalentoshumanos.system.service.EmpleadoService;
import org.gestiontalentoshumanos.system.service.EmpleadoStatus;
import org.gestiontalentoshumanos.system.utils.AlertInformation;
import org.gestiontalentoshumanos.system.utils.EmpleadoContext;
import org.gestiontalentoshumanos.system.utils.SceneManager;
import org.gestiontalentoshumanos.system.utils.Session;
import org.gestiontalentoshumanos.system.utils.ViewFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class ConsultaController {

    private static final DateTimeFormatter FECHA_FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    private Label lblUsuarioActivo;
    @FXML
    private Label lblRolActivo;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<Empleado> tablaEmpleados;
    @FXML
    private TableColumn<Empleado, Integer> colId;
    @FXML
    private TableColumn<Empleado, String> colNombre;
    @FXML
    private TableColumn<Empleado, String> colPuesto;
    @FXML
    private TableColumn<Empleado, String> colDepartamento;
    @FXML
    private TableColumn<Empleado, LocalDate> colFecha;
    @FXML
    private TableColumn<Empleado, BigDecimal> colSalario;
    @FXML
    private TableColumn<Empleado, Void> colAcciones;

    private final EmpleadoService empleadoService = new EmpleadoService();
    private final ObservableList<Empleado> empleadosData = FXCollections.observableArrayList();
    private final FilteredList<Empleado> empleadosFiltrados = new FilteredList<>(empleadosData, empleado -> true);

    @FXML
    private void initialize() {
        Users user = Session.getUser();
        if (user != null) {
            lblUsuarioActivo.setText(user.getNombreUsuario());
            lblRolActivo.setText(user.getNombreRol());
        }

        colId.setCellValueFactory(new PropertyValueFactory<>("empleadoId"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colPuesto.setCellValueFactory(new PropertyValueFactory<>("nombrePuesto"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("nombreDepartamento"));

        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaContratacion"));
        colFecha.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDate value, boolean empty) {
                super.updateItem(value, empty);
                setText(empty || value == null ? null : value.format(FECHA_FORMATO));
            }
        });

        colSalario.setCellValueFactory(new PropertyValueFactory<>("salarioBase"));
        colSalario.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(BigDecimal value, boolean empty) {
                super.updateItem(value, empty);
                setText(empty || value == null ? null : "Q " + value.setScale(2, RoundingMode.HALF_UP));
            }
        });

        configurarColumnaAcciones();

        tablaEmpleados.setItems(empleadosFiltrados);
        tablaEmpleados.setPlaceholder(new Label("No se encontraron colaboradores."));

        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> aplicarFiltro(newValue));

        cargarEmpleados();
    }

    private void configurarColumnaAcciones() {
        colAcciones.setCellFactory(column -> new TableCell<>() {
            private final Button btnEditar = new Button("\u270F Editar");
            private final Button btnEliminar = new Button("\uD83D\uDDD1 Eliminar");
            private final HBox contenedor = new HBox(8, btnEditar, btnEliminar);

            {
                btnEditar.getStyleClass().addAll("btn-icon", "btn-icon-edit");
                btnEliminar.getStyleClass().addAll("btn-icon", "btn-icon-delete");
                btnEditar.setOnAction(event -> onEditar(getTableRow().getItem()));
                btnEliminar.setOnAction(event -> onEliminar(getTableRow().getItem()));
            }

            @Override
            protected void updateItem(Void value, boolean empty) {
                super.updateItem(value, empty);
                setGraphic(empty ? null : contenedor);
            }
        });
    }

    private void cargarEmpleados() {
        empleadosData.setAll(empleadoService.readAll());
    }

    private void aplicarFiltro(String texto) {
        String filtro = texto == null ? "" : texto.trim().toLowerCase();
        empleadosFiltrados.setPredicate(empleado ->
                filtro.isEmpty()
                        || String.valueOf(empleado.getEmpleadoId()).contains(filtro)
                        || empleado.getNombreCompleto().toLowerCase().contains(filtro)
                        || empleado.getNombrePuesto().toLowerCase().contains(filtro)
                        || empleado.getNombreDepartamento().toLowerCase().contains(filtro));
    }

    private void onEditar(Empleado empleado) {
        if (empleado == null) {
            return;
        }
        EmpleadoContext.setEmpleadoId(empleado.getEmpleadoId());
        SceneManager.switchTo(ViewFactory.REGISTER);
    }

    private void onEliminar(Empleado empleado) {
        if (empleado == null) {
            return;
        }
        boolean confirmar = AlertInformation.showConfirmation(
                "Eliminar colaborador",
                "¿Deseas eliminar a " + empleado.getNombreCompleto() + "? Esta accion no se puede deshacer.");
        if (!confirmar) {
            return;
        }

        EmpleadoStatus status = empleadoService.delete(empleado.getEmpleadoId());
        if (status == EmpleadoStatus.DELETED) {
            cargarEmpleados();
        } else {
            AlertInformation.showError("No se pudo eliminar", "El colaborador ya no existe o ocurrio un error.");
        }
    }

    @FXML
    private void onNuevoColaborador() {
        EmpleadoContext.clear();
        SceneManager.switchTo(ViewFactory.REGISTER);
    }

    @FXML
    private void onInicio() {
        SceneManager.switchTo(ViewFactory.DASHBOARD);
    }

    @FXML
    private void onCerrarSesion() {
        if (AlertInformation.showConfirmation("Cerrar sesion", "¿Deseas cerrar tu sesion?")) {
            Session.clear();
            SceneManager.switchTo(ViewFactory.LOGIN);
        }
    }
}

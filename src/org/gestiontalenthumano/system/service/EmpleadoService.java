package org.gestiontalentoshumanos.system.service;

import org.gestiontalentoshumanos.system.model.Catalogo;
import org.gestiontalentoshumanos.system.model.Empleado;
import org.gestiontalentoshumanos.system.model.EmpleadoResumen;
import org.gestiontalentoshumanos.system.repository.EmpleadoRepository;
import org.gestiontalentoshumanos.system.utils.Validations;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoService {

    private static final int MYSQL_DUPLICATE_ENTRY = 1062;

    private final EmpleadoRepository empleadoRepository = new EmpleadoRepository();

    private EmpleadoStatus validate(String idText, String nombre, Catalogo puesto, Catalogo departamento,
                                    LocalDate fecha, String salarioText) {
        if (!Validations.isValidEmployeeId(idText)) {
            return EmpleadoStatus.INVALID_ID;
        }
        if (!Validations.isValidFullName(nombre)) {
            return EmpleadoStatus.INVALID_NAME;
        }
        if (puesto == null) {
            return EmpleadoStatus.INVALID_POSITION;
        }
        if (departamento == null) {
            return EmpleadoStatus.INVALID_DEPARTMENT;
        }
        if (!Validations.isValidHireDate(fecha)) {
            return EmpleadoStatus.INVALID_DATE;
        }
        if (!Validations.isValidSalary(salarioText)) {
            return EmpleadoStatus.INVALID_SALARY;
        }
        return null;
    }

    private Empleado build(String idText, String nombre, Catalogo puesto, Catalogo departamento,
                           LocalDate fecha, String salarioText) {
        return new Empleado(
                Integer.parseInt(idText.trim()),
                nombre.trim().replaceAll("\\s+", " "),
                puesto.getId(),
                departamento.getId(),
                fecha,
                Validations.parseSalary(salarioText));
    }

    public EmpleadoStatus create(String idText, String nombre, Catalogo puesto, Catalogo departamento,
                                 LocalDate fecha, String salarioText) {
        EmpleadoStatus invalid = validate(idText, nombre, puesto, departamento, fecha, salarioText);
        if (invalid != null) {
            return invalid;
        }
        try {
            Empleado empleado = build(idText, nombre, puesto, departamento, fecha, salarioText);
            return empleadoRepository.create(empleado) ? EmpleadoStatus.CREATED : EmpleadoStatus.DATABASE_ERROR;
        } catch (SQLException e) {
            if (e.getErrorCode() == MYSQL_DUPLICATE_ENTRY) {
                return EmpleadoStatus.DUPLICATE_ID;
            }
            e.printStackTrace();
            return EmpleadoStatus.DATABASE_ERROR;
        }
    }

    public List<Empleado> readAll() {
        try {
            return empleadoRepository.readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Empleado searchById(int empleadoId) {
        try {
            return empleadoRepository.searchById(empleadoId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public EmpleadoStatus update(String idText, String nombre, Catalogo puesto, Catalogo departamento,
                                 LocalDate fecha, String salarioText) {
        EmpleadoStatus invalid = validate(idText, nombre, puesto, departamento, fecha, salarioText);
        if (invalid != null) {
            return invalid;
        }
        try {
            Empleado empleado = build(idText, nombre, puesto, departamento, fecha, salarioText);
            return empleadoRepository.update(empleado) ? EmpleadoStatus.UPDATED : EmpleadoStatus.NOT_FOUND;
        } catch (SQLException e) {
            e.printStackTrace();
            return EmpleadoStatus.DATABASE_ERROR;
        }
    }

    public EmpleadoStatus delete(int empleadoId) {
        try {
            return empleadoRepository.delete(empleadoId) ? EmpleadoStatus.DELETED : EmpleadoStatus.NOT_FOUND;
        } catch (SQLException e) {
            e.printStackTrace();
            return EmpleadoStatus.DATABASE_ERROR;
        }
    }

    public EmpleadoResumen readResumen() {
        try {
            return empleadoRepository.readResumen();
        } catch (SQLException e) {
            e.printStackTrace();
            return new EmpleadoResumen(0, 0, 0, 0);
        }
    }
}

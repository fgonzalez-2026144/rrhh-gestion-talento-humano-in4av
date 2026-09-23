package org.gestiontalenthumano.system.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Empleado {

    private int empleadoId;
    private String nombreCompleto;
    private int puestoId;
    private String nombrePuesto;
    private int departamentoId;
    private String nombreDepartamento;
    private LocalDate fechaContratacion;
    private BigDecimal salarioBase;

    public Empleado() {
    }

    public Empleado(int empleadoId, String nombreCompleto, int puestoId, int departamentoId,
                    LocalDate fechaContratacion, BigDecimal salarioBase) {
        this.empleadoId = empleadoId;
        this.nombreCompleto = nombreCompleto;
        this.puestoId = puestoId;
        this.departamentoId = departamentoId;
        this.fechaContratacion = fechaContratacion;
        this.salarioBase = salarioBase;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getPuestoId() {
        return puestoId;
    }

    public void setPuestoId(int puestoId) {
        this.puestoId = puestoId;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public int getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(int departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public BigDecimal getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(BigDecimal salarioBase) {
        this.salarioBase = salarioBase;
    }
}

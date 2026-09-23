package org.gestiontalenthumano.system.model;

public class EmpleadoResumen {

    private final int total;
    private final int nuevosEsteMes;
    private final int enProceso;
    private final int contratados;

    public EmpleadoResumen(int total, int nuevosEsteMes, int enProceso, int contratados) {
        this.total = total;
        this.nuevosEsteMes = nuevosEsteMes;
        this.enProceso = enProceso;
        this.contratados = contratados;
    }

    public int getTotal() {
        return total;
    }

    public int getNuevosEsteMes() {
        return nuevosEsteMes;
    }

    public int getEnProceso() {
        return enProceso;
    }

    public int getContratados() {
        return contratados;
    }
}

package org.gestiontalenthumano.system.model;

public class Users {

    private int usuarioId;
    private String nombreUsuario;
    private int rolId;
    private String nombreRol;

    public Users() {
    }

    public Users(int usuarioId, String nombreUsuario, int rolId, String nombreRol) {
        this.usuarioId = usuarioId;
        this.nombreUsuario = nombreUsuario;
        this.rolId = rolId;
        this.nombreRol = nombreRol;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}

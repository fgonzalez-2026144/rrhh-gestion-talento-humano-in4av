package org.gestiontalentoshumanos.system.service;

public enum AuthenticationStatus {
    LOGIN_SUCCESS,
    EMPTY_FIELDS,
    NOT_EXIST_USER,
    INVALID_PASSWORD,
    DATABASE_ERROR
}

package org.gestiontalentoshumanos.system.utils;

import org.gestiontalentoshumanos.system.model.Users;

public class Session {

    private static Users currentUser;

    private Session() {
    }

    public static void setUser(Users user) {
        currentUser = user;
    }

    public static Users getUser() {
        return currentUser;
    }

    public static boolean isActive() {
        return currentUser != null;
    }

    public static void clear() {
        currentUser = null;
    }
}

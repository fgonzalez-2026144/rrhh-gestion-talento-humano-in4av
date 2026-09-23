package org.gestiontalentoshumanos.system.service;

import org.gestiontalentoshumanos.system.model.Users;
import org.gestiontalentoshumanos.system.repository.AuthenticationRepository;
import org.gestiontalentoshumanos.system.utils.Session;
import org.gestiontalentoshumanos.system.utils.Validations;
import java.sql.SQLException;

public class AuthenticationService {

    private final AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    private final UserService userService = new UserService();

    public AuthenticationStatus login(String username, String password) {
        if (!Validations.isNotBlank(username) || !Validations.isNotBlank(password)) {
            return AuthenticationStatus.EMPTY_FIELDS;
        }

        UserStatus userStatus = userService.verifyUser(username);
        if (userStatus == UserStatus.DATABASE_ERROR) {
            return AuthenticationStatus.DATABASE_ERROR;
        }
        if (userStatus == UserStatus.NOT_EXIST_USER) {
            return AuthenticationStatus.NOT_EXIST_USER;
        }

        try {
            Users user = authenticationRepository.login(username.trim(), password);
            if (user == null) {
                return AuthenticationStatus.INVALID_PASSWORD;
            }
            Session.setUser(user);
            return AuthenticationStatus.LOGIN_SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            return AuthenticationStatus.DATABASE_ERROR;
        }
    }
}

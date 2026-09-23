package org.gestiontalentoshumanos.system.service;

import org.gestiontalentoshumanos.system.model.Users;
import org.gestiontalentoshumanos.system.repository.UserRepository;
import org.gestiontalentoshumanos.system.utils.Validations;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    public UserStatus verifyUser(String username) {
        try {
            return userRepository.searchByUsername(username.trim()) != null
                    ? UserStatus.USER_FOUND
                    : UserStatus.NOT_EXIST_USER;
        } catch (SQLException e) {
            e.printStackTrace();
            return UserStatus.DATABASE_ERROR;
        }
    }

    public UserStatus create(String username, String password, int rolId) {
        if (!Validations.isNotBlank(username) || !Validations.isNotBlank(password) || rolId <= 0) {
            return UserStatus.INVALID_DATA;
        }
        try {
            if (userRepository.searchByUsername(username.trim()) != null) {
                return UserStatus.USER_ALREADY_EXISTS;
            }
            return userRepository.create(username.trim(), password, rolId)
                    ? UserStatus.CREATED
                    : UserStatus.DATABASE_ERROR;
        } catch (SQLException e) {
            e.printStackTrace();
            return UserStatus.DATABASE_ERROR;
        }
    }

    public List<Users> readAll() {
        try {
            return userRepository.readAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Users searchById(int usuarioId) {
        try {
            return userRepository.searchById(usuarioId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserStatus update(int usuarioId, String username, String password, int rolId) {
        if (!Validations.isNotBlank(username) || !Validations.isNotBlank(password) || rolId <= 0) {
            return UserStatus.INVALID_DATA;
        }
        try {
            return userRepository.update(usuarioId, username.trim(), password, rolId)
                    ? UserStatus.UPDATED
                    : UserStatus.NOT_EXIST_USER;
        } catch (SQLException e) {
            e.printStackTrace();
            return UserStatus.DATABASE_ERROR;
        }
    }

    public UserStatus delete(int usuarioId) {
        try {
            return userRepository.delete(usuarioId)
                    ? UserStatus.DELETED
                    : UserStatus.NOT_EXIST_USER;
        } catch (SQLException e) {
            e.printStackTrace();
            return UserStatus.DATABASE_ERROR;
        }
    }
}

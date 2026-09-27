package org.gestiontalentoshumanos.system.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://" + Enviroment.LOCATION_SERVICE + "/" + Enviroment.DATA_BASE
            + "?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

    private static ConexionDB instance;
    private Connection connection;

    private ConexionDB() {
    }

    public static ConexionDB getInstance() {
        if (instance == null) {
            instance = new ConexionDB();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, Enviroment.USER, Enviroment.PASSWORD);
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

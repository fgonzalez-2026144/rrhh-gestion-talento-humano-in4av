/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.gestiontalenthumano.system.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    //se crea 
    private static ConexionDB instanConexionDB;
    private Connection connection;
        
    private ConexionDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + 
                    Enviroment.LOCATION_SERVICE + "/" +
                    Enviroment.DATA_BASE,
                    Enviroment.USER,
                    Enviroment.PASSWORD);
            
        } catch (ClassNotFoundException classNotFound) {
            System.out.println("ERROR CLASE NO SE ENCUENTRA");
        } catch (SQLException sQLException){
            System.out.println("ERROR DE CONEXION A DB");
        } catch (Exception e){
            System.out.println("ERROR PADRE" + e.getMessage());
        }
    }

    public static ConexionDB getInstanConexionDB() {
        if(instanConexionDB == null){
            instanConexionDB = new ConexionDB();
        }
        return instanConexionDB;
    }

    public static void setInstanConexionDB(ConexionDB instanConexionDB) {
        ConexionDB.instanConexionDB = instanConexionDB;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
}

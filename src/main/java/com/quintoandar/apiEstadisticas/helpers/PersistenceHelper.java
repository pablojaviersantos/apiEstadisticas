package com.quintoandar.apiEstadisticas.helpers;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class PersistenceHelper {

    private static final String URL = "jdbc:postgresql://"+System.getenv("POSTGRES_HOST")+":"+System.getenv("POSTGRES_PORT")+"/"+System.getenv("POSTGRES_DB");

    private static final String USER = System.getenv("POSTGRES_USER");
    
    private static final String PASSWORD = System.getenv("POSTGRES_PASSWORD");

    public static Connection getConnection() {
        try {
            // Cargar el driver JDBC para PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Establecer la conexión con la base de datos
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

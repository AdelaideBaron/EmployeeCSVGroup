package com.sparta.ab.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection;

    public static Connection getConnection() {

        String url = PropertiesLoader.getProperties().getProperty("url");
        String userName = PropertiesLoader.getProperties().getProperty("userName");
        String password = PropertiesLoader.getProperties().getProperty("password");

        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

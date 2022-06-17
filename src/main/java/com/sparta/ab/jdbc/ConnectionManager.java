package com.sparta.ab.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection connection;

    public static Connection getConnection() {

        String url = propertiesLoader.getProperties().getProperty("url");
        String userName = propertiesLoader.getProperties().getProperty("userName");
        String password = propertiesLoader.getProperties().getProperty("password");

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


package com.somxhai.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private final String database;

    public MySQL(String db) {
        database = db;
    }
    private Connection connection;
    public boolean isConnected() {
        return (connection != null);
    }
    public void connect() throws SQLException {
        if (!isConnected()) {
            String password = "";
            String username = "root";
            String port = "3306";
            String host = "localhost";
            connection = DriverManager.getConnection("jdbc:mysql://" +
                            host + ":" + port + "/" + database + "?useSSL=false",
                    username, password);
        }
    }
    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public Connection getConnection() {
        return connection;
    }
}

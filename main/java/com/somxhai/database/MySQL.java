package com.somxhai.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private final String host = "localhost";
    private final String port = "3306";
    private final String database;
    private final String username = "root";
    private final String password = "";

    public MySQL(String db) {
        database = db;
    }
    private Connection connection;
    public boolean isConnected() {
        return (connection != null);
    }
    public void connect() throws ClassNotFoundException, SQLException {
        if (!isConnected()) {
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

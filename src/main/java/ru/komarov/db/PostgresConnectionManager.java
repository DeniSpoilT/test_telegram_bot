package ru.komarov.db;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PostgresConnectionManager {
    private static final String URL_KEY = "postgres.url";
    private static final String LOGIN_KEY = "postgres.username";
    private static final String PASSWORD_KEY = "postgres.password";
    static Connection connection;

    public PostgresConnectionManager() {
        loadDriver();
        openConnection();
    }

    private static void loadDriver() {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    private static void openConnection(){
        try {
            connection =  DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(LOGIN_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){
        return connection;
    }
}

package main.java.com.homeservice.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/home_service";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    static {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
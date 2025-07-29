package src;

import src.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
            try(Connection connection = DatabaseUtil.getConnection()){
                if(connection != null && !connection.isClosed()){
                    System.out.println("Connection established");
                }else{
                    System.out.println("Connection not established");
                }
            } catch (SQLException e) {
                System.out.println("❌ Error: " + e.getMessage());
                e.printStackTrace();
            }
    }
}

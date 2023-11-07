package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class connection {

    public static Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:33860/spp";
        String user = "root";
        String password = "";

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) {
        try {
            Connection c = getConnection();
            if (c != null) {
                System.out.println(String.format("Connected to database %s successfully.", c.getCatalog()));
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static PreparedStatement prepareStatement(String query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

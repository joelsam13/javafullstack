package auth;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthBackend {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/login_auth_db";
    private static final String DB_USER = "root";         // change if different
    private static final String DB_PASS = "MYSQL"; // change if different
    

    // Register a new user
    public static boolean register(String username, String password, String email) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Registration Failed: " + e.getMessage());
            return false;
        }
    }

    // Login user
    public static boolean login(String username, String password) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            ps.setString(2, SecurityUtils.hashPassword(password));

            return rs.next();
        } catch (SQLException e) {
            System.out.println("Login Failed: " + e.getMessage());
            return false;
        }
    }

    // For testing
    public static void main(String[] args) {
        // Test registration
        boolean reg = register("samuser", "pass123", "sam@example.com");
        System.out.println("Registration: " + (reg ? "Success" : "Failed"));

        // Test login
        boolean log = login("samuser", "pass123");
        System.out.println("Login: " + (log ? "Success" : "Invalid Credentials"));
        
    }
    
}

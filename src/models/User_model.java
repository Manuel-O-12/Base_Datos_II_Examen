package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;

public class User_model {
	
	private List<User> usuarios = new ArrayList<User>(); 
	
	DBConfig credentials = new DBConfig();
	
	String url = credentials.getUrl();
	String userName = credentials.getUsername();
	String passwordC = credentials.getPassword();

	
	public boolean registerUser(String firstName, String lastName, String maternalSurname, 
            String email, String password){
        
        String sql = "INSERT INTO users (first_name, last_name, maternal_surname, email, password) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(url, userName, passwordC);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
        	pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, maternalSurname);
            pstmt.setString(4, email);
            pstmt.setString(5, password); 
                        
            int affectedRows = pstmt.executeUpdate(); // Faltaba esta línea me lleva la VRG

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	
}
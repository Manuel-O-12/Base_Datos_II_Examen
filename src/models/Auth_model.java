package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;

public class Auth_model {
	
	private List<User> usuarios = new ArrayList<User>(); 
	
	DBConfig credentials = new DBConfig();
	
	String url = credentials.getUrl();
	String userNameDB = credentials.getUsername();
	String passwordDB = credentials.getPassword();
	
	//SE USA PARA PODER ACCEDER AL LOGIN
	public User login(String email, String password) {
	    System.out.println("=== INTENTANDO INICIO DE SESIÓN ===");
	    System.out.println("Email: " + email);
	    
	    String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
	    
	    try (Connection conn = DriverManager.getConnection(url, userNameDB, passwordDB);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setString(1, email);
	        pstmt.setString(2, password);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                // Usuario encontrado, crear objeto User
	                User user = new User();
	                user.setId(rs.getInt("id"));
	                user.setFirstName(rs.getString("first_name"));
	                user.setLastName(rs.getString("last_name"));
	                user.setMaternalSurname(rs.getString("maternal_surname"));
	                user.setEmail(rs.getString("email"));
	                user.setPassword(rs.getString("password"));
	                
	                System.out.println("INICIO DE SESIÓN EXITOSO");
	                System.out.println("Bienvenido: " + user.getFirstName() + " " + user.getLastName());
	                return user;
	            } else {
	                System.out.println("CREDENCIALES INCORRECTAS");
	                return null;
	            }
	        }
	        
	    } catch (SQLException e) {
	        System.err.println("ERROR EN INICIO DE SESIÓN: " + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	}

}

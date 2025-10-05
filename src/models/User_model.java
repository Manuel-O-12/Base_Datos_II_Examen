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
	String userNameDB = credentials.getUsername();
	String passwordDB = credentials.getPassword();

	//AQUI REGISTRAMOS A UN NUEVO USUARIO
	public boolean registerUser(String firstName, String lastName, String maternalSurname, 
            String email, String password){
        
        String sql = "INSERT INTO users (first_name, last_name, maternal_surname, email, password) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(url, userNameDB, passwordDB);
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
	
	// AQUI OBTENEMOS SOLO LOS DATOS BÁSICOS DEL USUARIO
	public User getBasicUserInfo(int userId) {
	    String sql = "SELECT id, first_name, last_name, maternal_surname, email, created_at FROM users WHERE id = ?";
	    
	    try (Connection conn = DriverManager.getConnection(url, userNameDB, passwordDB);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setInt(1, userId);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            User user = new User();
	            user.setId(rs.getInt("id"));
	            user.setFirstName(rs.getString("first_name"));
	            user.setLastName(rs.getString("last_name"));
	            user.setMaternalSurname(rs.getString("maternal_surname"));
	            user.setEmail(rs.getString("email"));
	            user.setCreatedAt(rs.getTimestamp("created_at"));
	            
	            return user;
	        }
	        
	    } catch (SQLException e) {
	        System.err.println("Error obteniendo información básica por ID: " + e.getMessage());
	    }
	    
	    return null;
	}
	
	// Verificar si un email existe
	public boolean emailExists(String email) {
	    String sql = "SELECT id FROM users WHERE email = ?";
	    
	    try (Connection conn = DriverManager.getConnection(url, userNameDB, passwordDB);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setString(1, email);
	        ResultSet rs = pstmt.executeQuery();
	        
	        return rs.next(); // Retorna true si existe, false si no
	        //IMPORTANTE
	        
	    } catch (SQLException e) {
	        System.err.println("Error verificando email: " + e.getMessage());
	        return false;
	    }
	}
	
	//AQUI OPTENEMOS LAS CUENTAS QUE TIENE EL USUARIO
	public List<Account> getUserAccounts(int userId) {
	    List<Account> accounts = new ArrayList<>();
	    String sql = "SELECT * FROM accounts WHERE user_id = ?";
	    
	    try (Connection conn = DriverManager.getConnection(url, userNameDB, passwordDB);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setInt(1, userId);
	        ResultSet rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            Account account = new Account();
	            account.setId(rs.getInt("id"));
	            account.setAccountNumber(rs.getString("account_number"));
	            account.setAccountType(rs.getString("account_type"));
	            account.setBalance(rs.getDouble("balance"));
	            account.setUserId(rs.getInt("user_id"));
	            accounts.add(account);
	        }
	        
	        System.out.println("Cuentas encontradas: " + accounts.size());
	        
	    } catch (SQLException e) {
	        System.out.println("Error al obtener cuentas: " + e.getMessage());
	    }
	    
	    return accounts;
	}
	
}
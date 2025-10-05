package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;

public class Account_model {
	
	DBConfig credentials = new DBConfig();
    String url = credentials.getUrl();
    String userNameDB = credentials.getUsername();
    String passwordDB = credentials.getPassword();
    
    // Método para crear una nueva cuenta
    public boolean createAccount(int userId, String accountType, int accountNumber) {
        String sql = "INSERT INTO accounts (account_number, account_type, user_id) VALUES (?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(url, userNameDB, passwordDB);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, accountNumber);
            pstmt.setString(2, accountType);
            pstmt.setInt(3, userId);
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Cuenta creada: " + accountNumber + " tipo: " + accountType);
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error creando cuenta: " + e.getMessage());
        }
        return false;
    }
    	
    // Método para obtener todas las cuentas de un usuario
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
                account.setCreatedAt(rs.getTimestamp("created_at"));
                accounts.add(account);
            }
            
        } catch (SQLException e) {
            System.err.println("Error obteniendo cuentas: " + e.getMessage());
        }
        
        return accounts;
    }


}

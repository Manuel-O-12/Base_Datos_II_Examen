package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import config.DBConfig;

public class Transaction_model {
	
	DBConfig credentials = new DBConfig();
    String url = credentials.getUrl();
    String userNameDB = credentials.getUsername();
    String passwordDB = credentials.getPassword();
     
    //SE UTLIZA PARA TRANFERIR CUENTAS
    public boolean transfer(String originAccount, String destinationAccount, double amount) {
        if (amount < 1) {
            System.out.println("El monto debe ser al menos $1");
            return false;
        }
        
        if (originAccount.equals(destinationAccount)) {
            System.out.println("No se puede transferir a la misma cuenta");
            return false;
        }
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userNameDB, passwordDB);

            conn.setAutoCommit(false);
            
            // 1. Verificar que ambas cuentas existan
            if (!accountExists(originAccount, conn) || !accountExists(destinationAccount, conn)) {
            	JOptionPane.showMessageDialog(null, "Una o ambas cuentas no existen", "Error", JOptionPane.ERROR_MESSAGE);
                conn.rollback();
                return false;
            }
            
            // 2. Verificar saldo suficiente en cuenta origen
            if (!hasSufficientBalance(originAccount, amount, conn)) {
            	JOptionPane.showMessageDialog(null, "Saldo insuficiente en cuenta origen", "Error", JOptionPane.ERROR_MESSAGE);
                conn.rollback();
                return false;
            }
            
            //Verificar que la cuenta destino no supere 10,000 si es normal
            if (!canReceiveAmount(destinationAccount, amount, conn)) {
                JOptionPane.showMessageDialog(null, "La cuenta destino es 'normal' y no puede tener más de $10,000 de saldo", 
                                              "Límite de cuenta normal", JOptionPane.WARNING_MESSAGE);
                conn.rollback();
                return false;
            }
            
            // 3. Restar de cuenta origen
            String subtractSql = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            try (PreparedStatement subtractStmt = conn.prepareStatement(subtractSql)) {
                subtractStmt.setDouble(1, amount);
                subtractStmt.setString(2, originAccount);
                int affectedRows = subtractStmt.executeUpdate();
                
                if (affectedRows == 0) {
                    conn.rollback();
                    return false;
                }
            }
            
            // 4. Sumar a cuenta destino
            String addSql = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            try (PreparedStatement addStmt = conn.prepareStatement(addSql)) {
                addStmt.setDouble(1, amount);
                addStmt.setString(2, destinationAccount);
                int affectedRows = addStmt.executeUpdate();
                
                if (affectedRows == 0) {
                    conn.rollback();
                    return false;
                }
            }
            
            // 5. Registrar la transacción
            String transactionSql = "INSERT INTO transactions (transaction_type, amount, origin_account, destination_account) VALUES (?, ?, ?, ?)";
            try (PreparedStatement transStmt = conn.prepareStatement(transactionSql)) {
                transStmt.setString(1, "transfer");
                transStmt.setDouble(2, amount);
                transStmt.setString(3, originAccount);
                transStmt.setString(4, destinationAccount);
                transStmt.executeUpdate();
            }
            
            conn.commit();
            System.out.println("Transferencia exitosa: $" + amount + " de " + originAccount + " a " + destinationAccount);
            return true;
            
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.err.println("Error en transferencia: " + e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private boolean accountExists(String accountNumber, Connection conn) throws SQLException {
        String sql = "SELECT id FROM accounts WHERE account_number = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }

    private boolean hasSufficientBalance(String accountNumber, double amount, Connection conn) throws SQLException {
        String sql = "SELECT balance FROM accounts WHERE account_number = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                return balance >= amount;
            }
            return false;
        }
    }
    
    private boolean canReceiveAmount(String accountNumber, double amount, Connection conn) throws SQLException {
        String sql = "SELECT account_type, balance FROM accounts WHERE account_number = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String type = rs.getString("account_type");
                double currentBalance = rs.getDouble("balance");
                double newBalance = currentBalance + amount;

                // Si la cuenta es normal y el nuevo saldo excede 10,000, no se permite
                if (type.equalsIgnoreCase("normal") && newBalance > 10000) {
                    return false;
                }
            }
        }
        return true;
    }

}

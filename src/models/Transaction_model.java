package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBConfig;

public class Transaction_model {
	
	DBConfig credentials = new DBConfig();
    String url = credentials.getUrl();
    String userNameDB = credentials.getUsername();
    String passwordDB = credentials.getPassword();
    
    public boolean deposit(String accountNumber, double amount) {
        if (amount < 0.1) {
            System.out.println("El monto debe ser al menos $0.1");
            return false;
        }
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userNameDB, passwordDB);
            conn.setAutoCommit(false); // Iniciar transacción
            
            // 1. Verificar que la cuenta existe y obtener su tipo
            String checkAccountSql = "SELECT account_type, balance FROM accounts WHERE account_number = ?";
            String accountType = "";
            double currentBalance = 0;
            
            try (PreparedStatement checkStmt = conn.prepareStatement(checkAccountSql)) {
                checkStmt.setString(1, accountNumber);
                ResultSet rs = checkStmt.executeQuery();
                
                if (!rs.next()) {
                    System.out.println("La cuenta no existe: " + accountNumber);
                    conn.rollback();
                    return false;
                }
                
                accountType = rs.getString("account_type");
                currentBalance = rs.getDouble("balance");
            }
            
            // 2. Validar límite para cuentas normales
            if (accountType.equals("normal") && (currentBalance + amount) > 1000) {
                System.out.println("Cuenta normal no puede exceder $1000. Saldo actual: $" + currentBalance);
                conn.rollback();
                return false;
            }
            
            // 3. Actualizar el saldo
            String updateSql = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setDouble(1, amount);
                updateStmt.setString(2, accountNumber);
                int affectedRows = updateStmt.executeUpdate();
                
                if (affectedRows == 0) {
                    conn.rollback();
                    return false;
                }
            }
            
            // 4. Registrar la transacción
            String transactionSql = "INSERT INTO transactions (transaction_type, amount, destination_account) VALUES (?, ?, ?)";
            try (PreparedStatement transStmt = conn.prepareStatement(transactionSql)) {
                transStmt.setString(1, "deposit");
                transStmt.setDouble(2, amount);
                transStmt.setString(3, accountNumber);
                transStmt.executeUpdate();
            }
            
            conn.commit();
            System.out.println("Depósito exitoso: $" + amount + " en cuenta " + accountNumber);
            return true;
            
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Error en depósito: " + e.getMessage());
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
                System.out.println("Una o ambas cuentas no existen");
                conn.rollback();
                return false;
            }
            
            // 2. Verificar saldo suficiente en cuenta origen
            if (!hasSufficientBalance(originAccount, amount, conn)) {
                System.out.println("Saldo insuficiente en cuenta origen");
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
            System.err.println("❌ Error en transferencia: " + e.getMessage());
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
}

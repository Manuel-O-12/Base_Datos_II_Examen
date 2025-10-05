package models;

import java.sql.Timestamp;

public class Account {

	private int id;
	private int accountNumber;
	private String accountType; // Normal o premium
	private double balance;
	private int userId;
	private Timestamp createdAt;
	
	public Account() {
		
	}
	
	public Account(int accountNumber, String accountType, double balance, int userId) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.userId = userId;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
}

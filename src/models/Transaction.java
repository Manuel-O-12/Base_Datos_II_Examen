package models;

import java.sql.Timestamp;

public class Transaction {

	private int id;
    private String transactionType; // "deposit" o "transfer"
    private double amount;
    private String originAccount;
    private String destinationAccount;
    private Timestamp transactionDate;
    
    public Transaction() {
		// TODO Auto-generated constructor stub
	}
    
    public Transaction(String transactionType, double amount, String originAccount, String destinationAccount) {
    	
    	this.transactionType = transactionType;
        this.amount = amount;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
    	
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getOriginAccount() {
		return originAccount;
	}

	public void setOriginAccount(String originAccount) {
		this.originAccount = originAccount;
	}

	public String getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(String destinationAccount) {
		this.destinationAccount = destinationAccount;
	}

	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}
	
    
    
}

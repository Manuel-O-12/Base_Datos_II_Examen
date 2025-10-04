package models;

import java.sql.Timestamp;

public class Transactions {

	private int id;
    private String transactionType; // "deposit" o "transfer"
    private double amount;
    private String originAccount;
    private String destinationAccount;
    private Timestamp transactionDate;
    
    public Transactions() {
		// TODO Auto-generated constructor stub
	}
    
    public Transactions(String transactionType, double amount, String originAccount, String destinationAccount) {
    	
    	this.transactionType = transactionType;
        this.amount = amount;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
    	
    }
	
}

package controller;

import java.util.List;

import models.Account;
import models.Account_model;
import models.User;
import models.User_model;
import views.Transfer_view;
import views.User_view;

public class Transaction_controller {

	
	private User_view userView;
	private User_model model_user;
	private Account_model model_account;
	private Transfer_view transferView;
	
	public Transaction_controller() {
		this.userView = new User_view();
		this.model_user = new User_model();
		this.model_account = new Account_model();
		this.transferView = new Transfer_view();
	}
	
	public void make_transaction(int id) {
		User myUser =  model_user.getBasicUserInfo(id);
		List<Account> myAccount = model_account.getUserAccounts(id);
		
		transferView.setUserData(myUser, myAccount);
	}
}

package controller;

import java.util.List;
import java.util.ArrayList;

import models.Account;
import models.Account_model;
import models.User;
import models.User_model;
import views.User_view;

public class Account_controller {

	private User_view userView;
	private User_model model_user;
	private Account_model model_account;
	
	public Account_controller() {
		this.userView = new User_view();
		this.model_user = new User_model();
		this.model_account = new Account_model();
	}
	
	public void user_accounts(int id) {
		User myUser =  model_user.getBasicUserInfo(id);
		List<Account> myAccount = model_account.getUserAccounts(id);
		
		userView.accounts(myUser, myAccount);	
	}
	
	public void create_account(int id) {
		User myUser =  model_user.getBasicUserInfo(id);

		userView.new_account(myUser);
	}
	
}

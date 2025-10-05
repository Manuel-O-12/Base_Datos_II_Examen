package controller;

import java.util.List;
import java.util.ArrayList;

import models.User_model;
import views.User_view;
import models.Account;
import models.Account_model;
import models.User;

public class User_controller {
	
	private User_view view;
	private User_model model_user;
	private Account_model model_account;
	private List<User> users = new ArrayList<>();
	
	public User_controller() {
		this.view = new User_view();
		this.model_user = new User_model();
		this.model_account = new Account_model();
	}
	
	public void user_dashboard(int id) {
		User myUser =  model_user.getBasicUserInfo(id);
		List<Account> myAccount = model_account.getUserAccounts(id);
		
		view.dashboard(myUser, myAccount);
        this.view.setVisible(true);
	}
	
	public void create_user_account() {
		
		//aqui iria el el view de cracion de la cuenta ejem:
		//view.create_account
	}
	
	public void user_tranfer() {
		
		//se pondria el view de transferenacia ejem:
		//view.create_user_account
	}
}

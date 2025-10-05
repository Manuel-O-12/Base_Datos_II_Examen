package controller;

import views.Auth_view;

public class Auth_controller {

	private Auth_view vista;
	
	public Auth_controller(){
		this.vista = new Auth_view();
	}
	
	public void login() {
		vista.login();
	}

}

package models;

import config.DBConfig;

public class Auth_models {
	
	DBConfig credentials = new DBConfig();
	
	String url = credentials.getUrl();
	String user = credentials.getUsername();
	String password = credentials.getPassword();

}

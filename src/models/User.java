package models;

import java.sql.Date;
import java.sql.Timestamp;


public class User {

	private int id;
    private String firstName;
    private String lastName;
    private String maternalSurname;
    private String email;
    private String password;
    private Timestamp createdAt;
	
    public User(int id, String firstName, String lastName, String maternalSurname, String email, String password, Timestamp createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maternalSurname = maternalSurname;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMaternalSurname() {
		return maternalSurname;
	}

	public void setMaternalSurname(String maternalSurname) {
		this.maternalSurname = maternalSurname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
    
}

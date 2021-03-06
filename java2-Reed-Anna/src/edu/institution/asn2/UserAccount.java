package edu.institution.asn2;

import java.io.Serializable;
import java.util.Objects;

public abstract class UserAccount implements Serializable {
	private String username;
	private String password;
	private String type;
	
	//Constructors for UserAccount
	public UserAccount () {
		setUsername(null);
		setPassword(null);
		setType(null);  
	}
	
	public UserAccount (String username, String password) {
		setUsername(username);
		setPassword(password);
		setType(null); 
	}
	public UserAccount (String username, String password, String type) {
		setUsername(username);
		setPassword(password);
		setType(type);
	}

	//Getters and Setters for variables
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	//will test the password sent in with the password stored
	public boolean isPasswordCorrect(String password) {
		boolean test = false;
		
		if (password.equals(this.password))
			test = true;	//if the passwords match a true value is returned
		else if (password.isBlank())
			test = false;
		else if (!(password.equals(this.password)))
			test = false;	//if the passwords don't match a false value is returned
		return test;
	}

	@Override
	public String toString() {
		return "UserAccount [username=" + username + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public boolean equals(Object obj) {
		/*if (this.equals(obj))
			return true;
		if (obj.equals(null))
			return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;*/
		UserAccount other = (UserAccount) obj;
		return Objects.equals(username, other.username);
	}

	

	

}


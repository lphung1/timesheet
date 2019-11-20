package com.skillstorm.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	
	String userName, password, firstName, lastName, jobTitle;
	int userId, role;
	public User() {
		super();
	}
	
	public User(ResultSet r) throws SQLException {
		
		userId = r.getInt("userId");
		userName = r.getString("userName");
		password = r.getString("password");
		firstName = r.getString("firstName");
		lastName = r.getString("lastName");
		role = r.getInt("roleId");
		jobTitle = r.getString("jobTitle");
		
	}
	
	
	
	public User(String userName, String password, String firstName, String lastName, int role, String jobTitle,
			int userId) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.jobTitle = jobTitle;
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
	
	public int getRole() {
		return role;
	}
	
	public void setRole(int role) {
		this.role = role;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}



	@Override
	public String toString() {
		return "User [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role
				+ ", userId=" + userId + "]";
	}
	
	
	
	

}

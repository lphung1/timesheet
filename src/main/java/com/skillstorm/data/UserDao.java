package com.skillstorm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.skillstorm.models.User;

public class UserDao {

	public static void main(String[] args) throws SQLException {
		
		String url = "jdbc:mysql://localhost:3306/TimesheetPortal";
		Connection conn = DriverManager.getConnection(url, "root", "");
		
		//run sql commands
		PreparedStatement stm = conn.prepareStatement("Select * from users;");
		
		ResultSet results = stm.executeQuery(); //wll return the rowcount
		
		while (results.next()) {
		System.out.println("results " + results.getString("userId") );
		}
		
		UserDao u = new UserDao();
		
		System.out.println("Get User Method " + u.getUserById(2) );
		
		
		
	}
	
	
	public User getUserById(int i) throws SQLException {
		
		String url = "jdbc:mysql://localhost:3306/TimesheetPortal";
		Connection conn = DriverManager.getConnection(url, "root", "");
		
		//run sql commands
		PreparedStatement stm = conn.prepareStatement("Select * from users where userId = ? ;");
		
		stm.setInt(1, i);
		ResultSet results = stm.executeQuery(); //wll return the rowcount
		
		results.next();
		
		
		User user = new User(results);
		
		
		
		return user;
	}
	
	

}

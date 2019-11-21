package com.skillstorm.data;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import com.skillstorm.models.User;

public class UserDao {
	
	
	
	

	public static void main(String[] args) throws SQLException, ParseException {//
		
		String pattern = "YYYY/MM/DD";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		java.util.Date date = Calendar.getInstance().getTime();
		String parsedDate = simpleDateFormat.format(date) ;
		
		String url = "jdbc:mysql://localhost:3306/TimesheetPortal";
		Connection conn = DriverManager.getConnection(url, "root", "");
		
		
		System.out.println(parsedDate);
		//run sql commands
		PreparedStatement stm = conn.prepareStatement("Select * from users;");
		
		ResultSet results = stm.executeQuery(); //wll return the rowcount
		
		while (results.next()) {
		System.out.println("results " + results.getString("userId") );
		}
		
		UserDao u = new UserDao();
		
		System.out.println("Get User Method " + u.getUserById(2) );
		u.verifyUser("lphung1", "pickles");
		
		TimeSheetDAO t = new TimeSheetDAO();
		
//		t.addNewTimeSheet(u.getUserById(1) ,  parsedDate ) ;
//		
//		System.out.println(t.findAllTimeSheet());
		
		
		
	}//main
	
	
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
	
	
	public boolean verifyUser(String usrName, String passWord) throws SQLException {
		
//		  `userId` INT(11) NOT NULL AUTO_INCREMENT,
//		  `firstName` VARCHAR(45) NOT NULL,
//		  `lastName` VARCHAR(45) NOT NULL,
//		  `userName` VARCHAR(45) NOT NULL,
//		  `password` VARCHAR(45) NOT NULL,
//		  `jobTitle` VARCHAR(45) NULL DEFAULT NULL,
//		  `roleId` INT(11) NULL DEFAULT NULL,
		
		
		String url = "jdbc:mysql://localhost:3306/TimesheetPortal";
		Connection conn = DriverManager.getConnection(url, "root", "");
		
		usrName.toLowerCase();
		
		PreparedStatement stm = conn.prepareStatement("Select * from users where userName = ? AND password = ?;");
		stm.setString(1, usrName);
		stm.setString(2, passWord);
		
		System.out.println("Result set for verify user " + stm.executeQuery().getRow());
		
		
		
		
		return false;
	}
	
	

}

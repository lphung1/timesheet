package com.skillstorm.service;

import java.io.IOError;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import com.skillstorm.data.UserDao;
import com.skillstorm.models.User;

public class UserService {
	
	UserDao userDao = new UserDao();
	

	
	public boolean verifyUser(String usrName, String password) {
		
		System.out.println("UserService: verifyUser: ");
		if(userDao.verifyUser(usrName, password)) {
			return true;
		}
		
		
		return false;
	}
	
	public User verifyAndGetUserByUserObj(User u) {
		
		
		User thisUser = new User();
		
		System.out.println("UserService, getuser called");
		System.out.println("usrName : " + u.getUserName() + " password: " + u.getPassword());
		
		if (userDao.verifyUser(u.getUserName(), u.getPassword() ) ){
			
			System.out.println("User verified in service");
			thisUser = userDao.getUser(u.getUserName() ,u.getPassword());
			
			
		}
		
		
		return thisUser;
		
		
	}

	public User verifyAndGetUser(String usrName, String password, HttpServletResponse resp) {
		User thisUser = new User();
		
		System.out.println("UserService, getuser called");
		System.out.println("usrName : " + usrName + " password: " + password);
		
		if (userDao.verifyUser(usrName, password)){
			
			System.out.println("User verified in service");
			thisUser = userDao.getUser(usrName, password);
			
		}
		else {
			resp.setStatus(401);
		}
		
		return null;
		
		
		
	}
	
	
	

}

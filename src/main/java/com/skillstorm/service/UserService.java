package com.skillstorm.service;

import com.skillstorm.data.UserDao;
import com.skillstorm.models.User;

public class UserService {
	
	UserDao userDao = new UserDao();
	
	
	public User verifyAndGetUser(String usrName, String password) {
		
		User thisUser = new User();
		
		System.out.println("UserService, getuser called");
		System.out.println("usrName : " + usrName + " password: " + password);
		
		if (userDao.verifyUser(usrName, password)){
			
			System.out.println("User verified in service");
			thisUser = userDao.getUser(usrName, password);
			
			
		}
		
		
		return thisUser;
		
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
	
	
	

}

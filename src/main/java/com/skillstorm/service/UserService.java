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
	
	
	

}

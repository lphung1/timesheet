package com.skillstorm.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.models.TimeSheet;
import com.skillstorm.models.User;
import com.skillstorm.service.UserService;

public class UserController {

	UserService u = new UserService();


	public void getUser(HttpServletRequest req, HttpServletResponse resp)
			throws NumberFormatException, JsonProcessingException, IOException {

		resp.setContentType("application/json");

		System.out.println("User Controller, get user called");
		
		if (req.getParameter("userName") != null && req.getParameter("password") != null) {
			resp.getWriter().println(
					new ObjectMapper().writeValueAsString(u.verifyAndGetUser(req.getParameter("userName") , req.getParameter("password") ) ));
			resp.setStatus(201);

		}
		
	}
	
	public void postUser(HttpServletRequest req, HttpServletResponse resp) throws JsonParseException, JsonMappingException, IOException {
		resp.setContentType("application/json");
		
		
//		resp.setContentType("application/json");
//		resp.getWriter().println(new ObjectMapper().writeValueAsString(
//				artistService.save(new ObjectMapper().readValue(req.getInputStream(), Artist.class))));
//		// headers
//		resp.setStatus(201);
		
		System.out.println("User Controller, Post User valled ");
		resp.getWriter().println((new ObjectMapper().writeValueAsString( u.verifyAndGetUserByUserObj(new ObjectMapper().readValue(req.getInputStream(), User.class))) ) );
		
	}
	

}
package com.skillstorm.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

}
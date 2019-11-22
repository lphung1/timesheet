package com.skillstorm.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet{
	
	
	private TimeSheetController tsController = new TimeSheetController();
	private UserController usrController = new UserController();
	
	protected void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		System.out.println("URI = " + uri);
		
		switch (uri) {
		case "/timesheet-portal/api/User":
			if (req.getMethod().equals("GET")) {
				usrController.verifyUser(req, resp);
				return;
			}
			break;
		case "/timesheet-portal/api/timesheets":
			if(req.getMethod().equals("GET")) {
				
				System.out.println("Get method, get all timesheets called: ");
				tsController.getAllTimesheets(req, resp);
				
				
			}
			break;
		default:
			break;
		}
		
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doDispatch(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doDispatch(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doDispatch(req, resp);
	}
	
	

}

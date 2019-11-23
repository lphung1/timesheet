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
		case "/timesheet-portal/api/user":
			
			if (req.getMethod().equals("POST")) {
				System.out.println("user POST method in front controller called: ");
				usrController.postUser(req, resp);
				return;
			}else if(req.getMethod().equals("GET")) {
				System.out.println("user GET method in front controller called: ");
				usrController.getUser(req, resp);
				
			}
			break;
		case "/timesheet-portal/api/timesheets":
			
			if(req.getMethod().equals("GET")) {
				System.out.println("timesheets/ Get method in front controller called: ");
				
				tsController.getTimesheets(req, resp);
				
			}
			//URI for adding a new timesheet
			else if(req.getMethod().equals("POST")) {
				System.out.println("timesheets/ POST method in front controller called: ");
				tsController.postTimeSheets(req, resp);
				
			}
			//URI Controller for updating DB
			else if(req.getMethod().equals("PUT")) {
				
				System.out.println("timesheets/ PUT method in front controller called: ");
				tsController.putTimesheets(req, resp);
				
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

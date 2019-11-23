package com.skillstorm.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.models.TimeSheet;
import com.skillstorm.service.TimeSheetService;

public class TimeSheetController {

	TimeSheetService tsService = new TimeSheetService();

	//
	public void getTimesheets(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {
		// TODO Auto-generated method stub

		resp.setContentType("application/json");

		// if userId is null, return all timesheets
		if (req.getParameter("userId") == null) {
			resp.getWriter().println(new ObjectMapper().writeValueAsString(tsService.getAllTimeSheet()));
			resp.setStatus(201);
		} else if (req.getParameter("userId") != null) {
			resp.getWriter().println(new ObjectMapper().writeValueAsString(tsService.getUserTimeSheets(Integer.parseInt(req.getParameter("userId")))));
			resp.setStatus(201);
		}

	}

	public void postTimeSheets(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {

		resp.setContentType("application/json");
		
		System.out.println("Request params userid" + req.getParameter("userId") + " Date: " + req.getParameter("date") );

		if (req.getParameter("userId") != null && req.getParameter("date") != null) {
			
			System.out.println("Timesheet controller, POST timesheet, add time sheet called: ");
			
			tsService.addNewTimesheet(Integer.parseInt(req.getParameter("userId")), req.getParameter("date"));
			resp.getWriter().println(new ObjectMapper()
					.writeValueAsString(tsService.getUserTimeSheets(Integer.parseInt(req.getParameter("userId")))));
			resp.setStatus(201);
			
		}
		else { //return error
			
			
		}
		
		
	}
	
	public void putTimesheets(HttpServletRequest req, HttpServletResponse resp) throws JsonParseException, JsonMappingException, IOException{
		resp.setContentType("application/json");
		
		if(req.getParameter("timesheetId") != null) {
			
			
			tsService.update(new ObjectMapper().readValue(req.getInputStream(), TimeSheet.class));
			
		}
		
		
		
	}

}

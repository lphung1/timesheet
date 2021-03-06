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
		if (req.getParameter("timesheetId") != null) {
			System.out.println("Timesheet controller: timesheetID != null");
			resp.getWriter().println(new ObjectMapper().writeValueAsString(tsService.getTimeSheetById(Integer.parseInt(req.getParameter("timesheetId")) )));

		} else if (req.getParameter("userId") != null) {
			resp.getWriter().println(new ObjectMapper().writeValueAsString(tsService.getUserTimeSheets(Integer.parseInt(req.getParameter("userId")))));
			resp.setStatus(201);
		}
		else {
			System.out.println("TimeSheetController: getTimesheets, userId null, retrieving all timesheets");
			resp.getWriter().println(new ObjectMapper().writeValueAsString(tsService.getAllTimeSheet()));
			resp.setStatus(201);
		}

	}

	public void postTimeSheets(HttpServletRequest req, HttpServletResponse resp)
			throws JsonProcessingException, IOException {

		resp.setContentType("application/json");
		
		System.out.println("Request params userid" + req.getParameter("userId") + " Date: " + req.getParameter("date") );

		if (req.getParameter("userId") != null && req.getParameter("date") != null) {
			
			System.out.println("Timesheet controller, POST timesheet, add time sheet called: ");
			
			if(tsService.dateExist(Integer.parseInt(req.getParameter("userId")), req.getParameter("date")) ) {
				
				System.out.println("date already exist.");
				resp.setStatus(406);
				
			}
			else {
				tsService.addNewTimesheet(Integer.parseInt(req.getParameter("userId")), req.getParameter("date"));
				resp.getWriter().println(new ObjectMapper()
						.writeValueAsString(tsService.getUserTimeSheets(Integer.parseInt(req.getParameter("userId")))));
				resp.setStatus(201);
			}
			
			
			
		}
		else { //return error
			resp.setStatus(402);
			
		}
		
		
	}
	
	public void putTimesheets(HttpServletRequest req, HttpServletResponse resp) throws JsonParseException, JsonMappingException, IOException{
		resp.setContentType("application/json");
			System.out.println("Put called, trying to update timehseet");
			tsService.update(new ObjectMapper().readValue(req.getInputStream(), TimeSheet.class));

		
	}
	
	public void deleteTimesheet(HttpServletRequest req, HttpServletResponse resp) {
		
		resp.setContentType("application/json");
		System.out.println("DeleteTimesheet called in timesheet controller, trying to delete");
		
		tsService.deleteTimeSheet(Integer.parseInt(req.getParameter("timesheetId")));
		
		
	}
	
	
	

}

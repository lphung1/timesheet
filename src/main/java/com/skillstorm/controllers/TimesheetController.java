package com.skillstorm.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.service.TimeSheetService;

public class TimeSheetController {
	
	TimeSheetService tsService = new TimeSheetService();

	public void getAllTimesheets(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		// TODO Auto-generated method stub
		
		resp.setContentType("application/json");
		
		resp.getWriter().println(new ObjectMapper().writeValueAsString(tsService.getAllTimeSheet()));

		
	}



}

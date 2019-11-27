package com.skillstorm.service;

import com.skillstorm.data.TimeSheetDAO;
import com.skillstorm.models.TimeSheet;
import com.skillstorm.models.User;

public class TimeSheetService {

	TimeSheetDAO t = new TimeSheetDAO();
	
	
	public Object getAllTimeSheet() {

		return t.findAllTimeSheet();

	}
	
	public Object getUserTimeSheets(int i ) {
		
		return t.findTimeSheetsByUser(i);
		
	}

	
	/**
	 * Adds a new timesheet to the DB taking user and date inputs, and returns a list of all the timesheets by the user.
	 * 
	 * @param usr - id of user to add
	 * @param parsedDate - week end date of timesheet
	 */
	public void addNewTimesheet(int usr, String parsedDate) {
		
		
		System.out.println("TimesheetService: add new timesheet called");
		t.addNewTimeSheet(usr, parsedDate);
		
		
	}

	public void update(TimeSheet readValue) {
		// TODO Auto-generated method stub
		System.out.println("Timesheet Service, Update called, Timesheet: " + readValue);
		t.save(readValue);

		
	}
	
	public Object getTimeSheetById(int i) {
		
		System.out.println("GetTimeSheetById called in service: ");
		return t.getTimeSheetById(i);
	}
	
	
	

}

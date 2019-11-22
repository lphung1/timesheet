package com.skillstorm.service;

import com.skillstorm.data.TimeSheetDAO;

public class TimeSheetService {

	public static Object getAllTimeSheet() {
		
		
		TimeSheetDAO t = new TimeSheetDAO();
		// TODO Auto-generated method stub
		
		
		return t.findAllTimeSheet();
	}
	
	

}

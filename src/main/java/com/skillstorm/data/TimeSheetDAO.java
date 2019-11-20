package com.skillstorm.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skillstorm.models.TimeSheet;
import com.skillstorm.models.User;

public class TimeSheetDAO {
	
//	public User findByUsername() {};
//	public List<TimeSheet> findTimeSheetsByUser(int id){};
//	public Timesheet findTimeSheetById() {}
//	public Timesheet save(Timesheet t) {}
//	public Timesheet update(Timesheet t){}
	
	
	public TimeSheet getTimeSheetById(int i) throws SQLException{
		
		
		String url = "jdbc:mysql://localhost:3306/TimesheetPortal";
		Connection conn = DriverManager.getConnection(url, "root", "");
		//run sql commands
		PreparedStatement stm = conn.prepareStatement("Select * from timesheet where timesheetId = ? ;");
		stm.setInt(1, i);
		ResultSet results = stm.executeQuery(); //wll return the rowcount
		results.next();
		TimeSheet retrievedTimeSheet = new TimeSheet(results);
		return retrievedTimeSheet;

		
	}
	
	public static void main(String[] args) throws SQLException {
		TimeSheetDAO t = new TimeSheetDAO();
		 

		
		
		
	}
	
	public List<TimeSheet> findAllTimeSheet() throws SQLException{
		
		List<TimeSheet> timeList = new ArrayList<>();
		
		String url = "jdbc:mysql://localhost:3306/TimesheetPortal";
		Connection conn = DriverManager.getConnection(url, "root", "");
		//run sql commands
		PreparedStatement stm = conn.prepareStatement("Select * from timesheet;");
		//stm.setInt(1, i);
		ResultSet results = stm.executeQuery(); //wll return the rowcount
		results.next();
		
		while(results.next()) {
			
			timeList.add(new TimeSheet(results));

		}
		
		
		return timeList;
		
	}
	
	public void save(TimeSheet t) throws SQLException {
		
//		  `timeSheetId` INT(11) NOT NULL AUTO_INCREMENT,
//		  `userId` INT(11) NOT NULL,
//		  `statusId` INT(11) NOT NULL,
//		  `mon_hours` FLOAT UNSIGNED NULL DEFAULT '0',
//		  `tue_hours` FLOAT UNSIGNED NULL DEFAULT '0',
//		  `wed_hours` FLOAT UNSIGNED NULL DEFAULT '0',
//		  `thu_hours` FLOAT UNSIGNED NULL DEFAULT '0',
//		  `fri_hours` FLOAT UNSIGNED NULL DEFAULT '0',
//		  `sat_hours` FLOAT UNSIGNED NULL DEFAULT '0',
//		  `sun_hours` FLOAT UNSIGNED NULL DEFAULT '0',
//		  `week_end_date` DATETIME NOT NULL,
		
		
		
		
		int savedTimeSheetId = t.getTimeSheetId();
		
		String url = "jdbc:mysql://localhost:3306/TimesheetPortal";
		Connection conn = DriverManager.getConnection(url, "root", "");
		//run sql commands
		PreparedStatement stm = conn.prepareStatement("UPDATE timesheet ON "
				+ "mon_hours = ?, tue_hours = ?, wed_hours = ?, thr_hours = ?, fri_hours = ?, sat_hours = ?, sun_hours = ? "
				+ "WHERE timesheetID = ?; ");
		//TODO finish the rest
		stm.setFloat(1, t.getMonHours());
		stm.setFloat(2, t.getTueHours());
		stm.setFloat(3, t.getWedHours());
		stm.setFloat(4, t.getThuHours());
		stm.setFloat(5, t.getFriHours());
		stm.setFloat(6, t.getSatHours());
		stm.setFloat(7, t.getSunHours());
		stm.setInt(8, t.getTimeSheetId());
		
		ResultSet results = stm.executeQuery(); //wll return the rowcount
		
		
		
		
	}
	
	
	
	

}

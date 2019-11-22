package com.skillstorm.data;

import java.sql.Connection;

import java.sql.Date;
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

//	public Timesheet findTimeSheetById() {}
//	public Timesheet save(Timesheet t) {}
//	public Timesheet update(Timesheet t){}
	
	
	public Connection getConnection() {
		
		Connection conn = null;
		
		try {
			String url = "jdbc:mysql://localhost:3306/TimesheetPortal";
			conn = DriverManager.getConnection(url, "root", "");
			return conn;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return conn;
		}
		
		
		
	}
	
	
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
	

	
	public List<TimeSheet> findAllTimeSheet(){
		
		List<TimeSheet> timeList = new ArrayList<>();
		
		String url = "jdbc:mysql://localhost:3306/TimesheetPortal";
		
		try {
		
		Connection conn = getConnection();
		//run sql commands
		PreparedStatement stm = conn.prepareStatement("Select * from timesheet;");
		//stm.setInt(1, i);
		ResultSet results = stm.executeQuery(); //wll return the rowcount
		results.next();
		
		while(results.next()) {
			
			timeList.add(new TimeSheet(results));

		}
		
		return timeList;
		
		}catch(SQLException e) {
			
			
			System.out.println(e);
			
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
		
		
		if(t.getMonHours() < 0) {
			System.out.println("negative number");
			throw new SQLException();
		}
		
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
	
	
	public void addNewTimeSheet(User u, String parsedDate) throws SQLException {
		
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
		
		
		String url = "jdbc:mysql://localhost:3306/TimesheetPortal";
		Connection conn = DriverManager.getConnection(url, "root", "");
		//run sql commands
		
		
		
		
		
		
		PreparedStatement stm = conn.prepareStatement("Insert into timesheet (userId, week_end_date)  Values( ?, ? ) ;");
		
		stm.setInt(1, u.getUserId());
		stm.setString(2, parsedDate);
		System.out.println("Prepared Stm" + stm.toString());
		stm.executeUpdate(); //wll return the rowcount
		
		
		
	}
	
	
	public List<TimeSheet> findTimeSheetsByUser(int id){
		
		Connection conn = getConnection();
		List<TimeSheet> timeList = new ArrayList<>();
		
		
		try {
		
		PreparedStatement stm = conn.prepareStatement("Select * from timesheet where userId = ? ;");
		stm.setInt(1, id);
		//stm.setInt(1, i);
		ResultSet results = stm.executeQuery(); //wll return the rowcount
		results.next();
		
		while(results.next()) {
			
			timeList.add(new TimeSheet(results));

		}
		
		return timeList;
		
		}catch(SQLException e) {
			System.out.println("FindTimeSheetByUser DAO: " + e);
			return timeList;
		}
		
		
		
		
	}
	
	
	

}

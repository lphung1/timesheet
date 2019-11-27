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

	// todo delete Method
	
	

    /**
     * Method to establish connection to database
     * 
     * @return returns connection object
     */
	public Connection getConnection() {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/TimesheetPortal";
			conn = DriverManager.getConnection(url, "root", "");
			return conn;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return conn;
		}

	}

	/**
	 * 
	 * @param i - id of the time sheet
	 * @return Timesheet object 
	 */
	public TimeSheet getTimeSheetById(int i) {

		Connection conn = getConnection();

		try {
			// run sql commands
			PreparedStatement stm = conn.prepareStatement("Select * from timesheet where timesheetId = ? ;");
			stm.setInt(1, i);
			ResultSet results = stm.executeQuery(); // wll return the rowcount
			results.next();
			TimeSheet retrievedTimeSheet = new TimeSheet(results);

			return retrievedTimeSheet;

		} catch (SQLException e) {

			System.out.println(e);
			return new TimeSheet();
		}

	}

	/**
	 * Finds and returns a List of all the timesheets in the DB
	 * @return List<TimeSheet> list of all timesheets
	 */
	public List<TimeSheet> findAllTimeSheet() {

		List<TimeSheet> timeList = new ArrayList<>();

		try {

			Connection conn = getConnection();
			// run sql commands
			PreparedStatement stm = conn.prepareStatement("Select * from timesheet;");
			// stm.setInt(1, i);
			ResultSet results = stm.executeQuery(); // wll return the rowcount

			results.next();

			while (results.next()) {

				timeList.add(new TimeSheet(results));

			}

			return timeList;

		} catch (SQLException e) {

			System.out.println(e);
			return timeList;

		}

	}
	
	
	/**
	 * saves and updates an existing timesheet in the database
	 * @param t - Timesheet Object that is being updated
	 */
	public void save(TimeSheet t) {

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

		try {

			if (t.getMonHours() < 0) {
				System.out.println("negative number");
				throw new SQLException();
			}

			int savedTimeSheetId = t.getTimeSheetId();

			Connection conn = getConnection();
			// run sql commands
			PreparedStatement stm = conn.prepareStatement("UPDATE timesheet SET "
					+ "mon_hours = ?, tue_hours = ?, wed_hours = ?, thu_hours = ?, fri_hours = ?, sat_hours = ?, sun_hours = ?, statusId = ? "
					+ "WHERE timesheetID = ?; ");
			// TODO finish the rest
			stm.setFloat(1, t.getMonHours());
			stm.setFloat(2, t.getTueHours());
			stm.setFloat(3, t.getWedHours());
			stm.setFloat(4, t.getThuHours());
			stm.setFloat(5, t.getFriHours());
			stm.setFloat(6, t.getSatHours());
			stm.setFloat(7, t.getSunHours());
			stm.setInt(8, t.getStatusId());
			stm.setInt(9, t.getTimeSheetId());

			System.out.println("stm query statement: " + stm.toString());
			int rowAffected = stm.executeUpdate(); // wll return the rowcount
			
			System.out.println("Save/Update timesheet called, rows Affected: " + rowAffected);

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	
	/**
	 * Adds a new blank timesheet for the user and using the specified date
	 * 
	 * @param u - User object
	 * @param parsedDate - String of date in the format YYYY/MM/DD
	 */
	public void addNewTimeSheet(int uId, String parsedDate) {

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

		Connection conn = getConnection();
		// run sql commands

		try {

			PreparedStatement stm = conn.prepareStatement("Insert into timesheet (userId, week_end_date)  Values( ?, ? ) ;");

			stm.setInt(1, uId);
			stm.setString(2, parsedDate);
			System.out.println("Prepared Stm to be added " + stm.toString());
			
			stm.executeUpdate(); // wll return the rowcount
			

		} catch (SQLException e) {
			System.out.println(e);
		}

	}
	
	

	/**
	 * Returns all the timesheets for the specified user
	 * 
	 * @param id - User Id
	 * @return List<TimeSheet> list of timesheets associated with the id of the user in the parameter
	 */
	public List<TimeSheet> findTimeSheetsByUser(int id) {

		Connection conn = getConnection();
		List<TimeSheet> timeList = new ArrayList<>();

		try {

			PreparedStatement stm = conn.prepareStatement("Select * from timesheet where userId = ? ;");
			stm.setInt(1, id);
			// stm.setInt(1, i);
			ResultSet results = stm.executeQuery(); // wll return the rowcount
			results.next();

			while (results.next()) {

				timeList.add(new TimeSheet(results));

			}

			return timeList;

		} catch (SQLException e) {
			System.out.println("FindTimeSheetByUser DAO: " + e);
			return timeList;
		}

	}

}

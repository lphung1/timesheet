package com.skillstorm.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TimeSheet {
	
	float monHours, tueHours, wedHours, thuHours, friHours, satHours, sunHours, totalHours;
	int timeSheetId, userId, statusId;
	Date weekEndDate;
	
	public TimeSheet(){
		
	}
	
	public TimeSheet(ResultSet r) throws SQLException {
		
		timeSheetId = r.getInt("timeSheetId");
		userId = r.getInt(2);
		statusId = r.getInt(3);
		monHours = r.getFloat(4);
		tueHours = r.getFloat(5);
		wedHours = r.getFloat(6);
		thuHours = r.getFloat(7);
		friHours = r.getFloat(8);
		satHours = r.getFloat(9);
		sunHours = r.getFloat(10);
		weekEndDate = r.getDate(11);

		
	}
	
	
	
	public float getMonHours() {
		return monHours;
	}
	public void setMonHours(float monHours) {
		this.monHours = monHours;
	}
	public float getTueHours() {
		return tueHours;
	}
	public void setTueHours(float tueHours) {
		this.tueHours = tueHours;
	}
	public float getWedHours() {
		return wedHours;
	}
	public void setWedHours(float wedHours) {
		this.wedHours = wedHours;
	}
	public float getThuHours() {
		return thuHours;
	}
	public void setThuHours(float thuHours) {
		this.thuHours = thuHours;
	}
	public float getFriHours() {
		return friHours;
	}
	public void setFriHours(float friHours) {
		this.friHours = friHours;
	}
	public float getSatHours() {
		return satHours;
	}
	public void setSatHours(float satHours) {
		this.satHours = satHours;
	}
	public float getSunHours() {
		return sunHours;
	}
	public void setSunHours(float sunHours) {
		this.sunHours = sunHours;
	}
	public int getTimeSheetId() {
		return timeSheetId;
	}
	public void setTimeSheetId(int timeSheetId) {
		this.timeSheetId = timeSheetId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public Date getWeekEndDate() {
		return weekEndDate;
	}
	public void setWeekEndDate(Date weekEndDate) {
		this.weekEndDate = weekEndDate;
	}
	
	public void calcTotalHours() {
		totalHours = monHours + tueHours + wedHours + thuHours + friHours + satHours + sunHours;
	}
	
	public float getTotalHours(){
		return totalHours;
	}

	@Override
	public String toString() {
		return "TimeSheet [monHours=" + monHours + ", tueHours=" + tueHours + ", wedHours=" + wedHours + ", thuHours="
				+ thuHours + ", friHours=" + friHours + ", satHours=" + satHours + ", sunHours=" + sunHours
				+ ", totalHours=" + totalHours + ", timeSheetId=" + timeSheetId + ", userId=" + userId + ", statusId="
				+ statusId + ", weekEndDate=" + weekEndDate + "]";
	}
	
	
	
	
	
	
	

}

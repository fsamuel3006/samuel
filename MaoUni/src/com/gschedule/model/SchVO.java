package com.gschedule.model;

import java.sql.Date;

public class SchVO implements java.io.Serializable{
	private Integer schId;
	private Integer groomerId;
	private Date schDate;
	private String schStatus;
	
	
	public Integer getSchId() {
		return schId;
	}
	public void setSchId(Integer schId) {
		this.schId = schId;
	}
	public Integer getGroomerId() {
		return groomerId;
	}
	public void setGroomerId(Integer groomerId) {
		this.groomerId = groomerId;
	}
	public Date getSchDate() {
		return schDate;
	}
	public void setSchDate(Date schDate) {
		this.schDate = schDate;
	}
	public String getSchStatus() {
		return schStatus;
	}
	public void setSchStatus(String schStatus) {
		this.schStatus = schStatus;
	}
	
	
	
}

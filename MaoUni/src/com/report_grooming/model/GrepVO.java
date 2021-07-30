package com.report_grooming.model;

import java.sql.Timestamp;

public class GrepVO implements java.io.Serializable{
	private Integer rptId;
	private Integer apmId;
	private Integer memId;
	private Integer groomerId;
	private String content;
	private Integer rptStatus;
	private Integer result;
	private Timestamp rptUpdate;
	
	public Integer getRptId() {
		return rptId;
	}
	public void setRptId(Integer rptId) {
		this.rptId = rptId;
	}
	public Integer getApmId() {
		return apmId;
	}
	public void setApmId(Integer apmId) {
		this.apmId = apmId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getGroomerId() {
		return groomerId;
	}
	public void setGroomerId(Integer groomerId) {
		this.groomerId = groomerId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getRptStatus() {
		return rptStatus;
	}
	public void setRptStatus(Integer rptStatus) {
		this.rptStatus = rptStatus;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public Timestamp getRptUpdate() {
		return rptUpdate;
	}
	public void setRptUpdate(Timestamp rptUpdate) {
		this.rptUpdate = rptUpdate;
	}
	
	
	
	
}

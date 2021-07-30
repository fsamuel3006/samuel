package com.member.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;


public class MemberVO implements java.io.Serializable{
	
	private Integer memId;//會員編號
	private String memName; //會員姓名
	private String memEmail; //email
	private String memPassword; //密碼
	private String memIdenity;//身分證
	private String memGender;//性別
	private Integer memPh;//電話
	private String memAddres;//地址
	private Date memBirthday; //生日
	private Integer memPosition;//身分(一般會員或美容師）
	private Integer memReserve; //美容完成預約次數
	private Integer memSurvive; //狀態
	private Timestamp memUpdate;//上次更新日期
	
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	public String getMemIdenity() {
		return memIdenity;
	}
	public void setMemIdenity(String memIdenity) {
		this.memIdenity = memIdenity;
	}
	public String getMemGender() {
		return memGender;
	}
	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}
	public Integer getMemPh() {
		return memPh;
	}
	public void setMemPh(Integer memPh) {
		this.memPh = memPh;
	}
	public String getMemAddres() {
		return memAddres;
	}
	public void setMemAddres(String memAddres) {
		this.memAddres = memAddres;
	}
	public Date getMemBirthday() {
		return memBirthday;
	}
	public void setMemBirthday(Date memBirthday) {
		this.memBirthday = memBirthday;
	}
	public Integer getMemPosition() {
		return memPosition;
	}
	public void setMemPosition(Integer memPosition) {
		this.memPosition = memPosition;
	}
	public Integer getMemReserve() {
		return memReserve;
	}
	public void setMemReserve(Integer memReserve) {
		this.memReserve = memReserve;
	}
	public Integer getMemSurvive() {
		return memSurvive;
	}
	public void setMemSurvive(Integer memSurvive) {
		this.memSurvive = memSurvive;
	}
	public Timestamp getMemUpdate() {
		return memUpdate;
	}
	public void setMemUpdate(Timestamp memUpdate) {
		this.memUpdate = memUpdate;
	}
	

	
}

package com.forumpost.model;

import java.sql.Date;

public class ForumPostVO implements java.io.Serializable{
	private Integer fpId; //貼文ID
	private Integer fpMemId; //會員ID
	private String fpTitle; //貼文標題
	private String fpContent; //貼文內容
	private Integer fpStatus; //狀態 0: 草稿  1: 公開  2: 隱藏
	private Date fpUpdate; //編輯時間
	
	public Integer getFpId() {
		return fpId;
	}
	public void setFpId(Integer fpId) {
		this.fpId = fpId;
	}
	public Integer getFpMemId() {
		return fpMemId;
	}
	public void setFpMemId(Integer fpMemId) {
		this.fpMemId = fpMemId;
	}
	public String getFpTitle() {
		return fpTitle;
	}
	public void setFpTitle(String fpTitle) {
		this.fpTitle = fpTitle;
	}
	public String getFpContent() {
		return fpContent;
	}
	public void setFpContent(String fpContent) {
		this.fpContent = fpContent;
	}
	public Integer getFpStatus() {
		return fpStatus;
	}
	public void setFpStatus(Integer fpStatus) {
		this.fpStatus = fpStatus;
	}
	public Date getFpUpdate() {
		return fpUpdate;
	}
	public void setFpUpdate(Date fpUpdate) {
		this.fpUpdate = fpUpdate;
	}
	
}

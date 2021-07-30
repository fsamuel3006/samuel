package com.reportforumpost.model;

import java.sql.Date;

public class ReportForumPostVO implements java.io.Serializable{
	private Integer prepId; //貼文檢舉ID
	private Integer prepPostId; //討論區貼文ID
	private Integer prepMemId; //檢舉會員ID
	private String prepContent; //檢舉內容
	private Integer prepStatus; //狀態
	private Integer prepResult; //審核結果
	private Date prepUpdate; //上次修改狀態時間
	
	public Integer getPrepId() {
		return prepId;
	}
	public void setPrepId(Integer prepId) {
		this.prepId = prepId;
	}
	public Integer getPrepPostId() {
		return prepPostId;
	}
	public void setPrepPostId(Integer prepPostId) {
		this.prepPostId = prepPostId;
	}
	public Integer getPrepMemId() {
		return prepMemId;
	}
	public void setPrepMemId(Integer prepMemId) {
		this.prepMemId = prepMemId;
	}
	public String getPrepContent() {
		return prepContent;
	}
	public void setPrepContent(String prepContent) {
		this.prepContent = prepContent;
	}
	public Integer getPrepStatus() {
		return prepStatus;
	}
	public void setPrepStatus(Integer prepStatus) {
		this.prepStatus = prepStatus;
	}
	public Integer getPrepResult() {
		return prepResult;
	}
	public void setPrepResult(Integer prepResult) {
		this.prepResult = prepResult;
	}
	public Date getPrepUpdate() {
		return prepUpdate;
	}
	public void setPrepUpdate(Date prepUpdate) {
		this.prepUpdate = prepUpdate;
	}

	

	
}


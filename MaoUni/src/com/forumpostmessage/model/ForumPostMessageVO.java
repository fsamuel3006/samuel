package com.forumpostmessage.model;

import java.sql.Date;

public class ForumPostMessageVO implements java.io.Serializable{
	private Integer fpmId; //貼文留言ID
	private Integer fpmPostId; //貼文ID
	private Integer fpmMemId; //會員ID
	private String fpmContent; //留言內容
	private Date fpmUpdate; //留言時間
	
	public Integer getFpmId() {
		return fpmId;
	}
	public void setFpmId(Integer fpmId) {
		this.fpmId = fpmId;
	}
	public Integer getFpmPostId() {
		return fpmPostId;
	}
	public void setFpmPostId(Integer fpmPostId) {
		this.fpmPostId = fpmPostId;
	}
	public Integer getFpmMemId() {
		return fpmMemId;
	}
	public void setFpmMemId(Integer fpmMemId) {
		this.fpmMemId = fpmMemId;
	}
	public String getFpmContent() {
		return fpmContent;
	}
	public void setFpmContent(String fpmContent) {
		this.fpmContent = fpmContent;
	}
	public Date getFpmUpdate() {
		return fpmUpdate;
	}
	public void setFpmUpdate(Date fpmUpdate) {
		this.fpmUpdate = fpmUpdate;
	}
	
}


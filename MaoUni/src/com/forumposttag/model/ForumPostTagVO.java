package com.forumposttag.model;

public class ForumPostTagVO implements java.io.Serializable{
	private Integer fptId; //標籤ID
	private String fptName; //標籤名稱
	
	public Integer getFptId() {
		return fptId;
	}
	public void setFptId(Integer fptId) {
		this.fptId = fptId;
	}
	public String getFptName() {
		return fptName;
	}
	public void setFptName(String fptName) {
		this.fptName = fptName;
	}
	
}


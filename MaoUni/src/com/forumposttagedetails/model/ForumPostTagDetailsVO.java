package com.forumposttagedetails.model;

public class ForumPostTagDetailsVO implements java.io.Serializable{
	private Integer fptdTagId; //標籤ID
	private Integer fptdPostId; //貼文ID
	
	public Integer getFptdTagId() {
		return fptdTagId;
	}
	public void setFptdTagId(Integer fptdTagId) {
		this.fptdTagId = fptdTagId;
	}
	public Integer getFptdPostId() {
		return fptdPostId;
	}
	public void setFptdPostId(Integer fptdPostId) {
		this.fptdPostId = fptdPostId;
	}
	
}


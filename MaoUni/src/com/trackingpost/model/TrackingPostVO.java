package com.trackingpost.model;

public class TrackingPostVO implements java.io.Serializable{
	private Integer ptraPostId; //貼文ID
	private Integer ptraMemId; //會員ID
	
	public Integer getPtraPostId() {
		return ptraPostId;
	}
	public void setPtraPostId(Integer ptraPostId) {
		this.ptraPostId = ptraPostId;
	}
	public Integer getPtraMemId() {
		return ptraMemId;
	}
	public void setPtraMemId(Integer ptraMemId) {
		this.ptraMemId = ptraMemId;
	}

}

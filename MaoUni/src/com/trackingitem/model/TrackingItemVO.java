package com.trackingitem.model;

public class TrackingItemVO implements java.io.Serializable{
	private Integer itraMemId; //商品ID
	private Integer itraItemId; //會員ID
	
	public Integer getItraMemId() {
		return itraMemId;
	}
	public void setItraMemId(Integer itraMemId) {
		this.itraMemId = itraMemId;
	}
	public Integer getItraItemId() {
		return itraItemId;
	}
	public void setItraItemId(Integer itraItemId) {
		this.itraItemId = itraItemId;
	}
	
}


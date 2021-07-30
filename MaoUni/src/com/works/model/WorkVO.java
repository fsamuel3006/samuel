package com.works.model;

import java.sql.Timestamp;

public class WorkVO implements java.io.Serializable{
	private Integer wid;
	private Integer groomerId;
	private byte[] item;
	private String itemBase64; // not in table
	private Integer likes;
	private Timestamp wupdate;
	
	public Integer getWid() {
		return wid;
	}
	public void setWid(Integer wid) {
		this.wid = wid;
	}
	public Integer getGroomerId() {
		return groomerId;
	}
	public void setGroomerId(Integer groomerId) {
		this.groomerId = groomerId;
	}
	public byte[] getItem() {
		return item;
	}
	public void setItem(byte[] item) {
		this.item = item;
	}
	public String getItemBase64() {
		return itemBase64;
	}
	public void setItemBase64(String itemBase64) {
		this.itemBase64 = itemBase64;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public Timestamp getWupdate() {
		return wupdate;
	}
	public void setWupdate(Timestamp wupdate) {
		this.wupdate = wupdate;
	}
	
}
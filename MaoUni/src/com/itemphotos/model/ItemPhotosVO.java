package com.itemphotos.model;

import java.sql.Date;

public class ItemPhotosVO implements java.io.Serializable{
	private Integer ipId; //商品照片ID
	private Integer ipItemId; //商品ID
	private byte[] ipItem; //商品照片
	private Date ipUpdate; //上次修改時間
	private String ipItemBase64; // not in table
	
	public Integer getIpId() {
		return ipId;
	}
	public void setIpId(Integer ipId) {
		this.ipId = ipId;
	}
	public Integer getIpItemId() {
		return ipItemId;
	}
	public void setIpItemId(Integer ipItemId) {
		this.ipItemId = ipItemId;
	}
	public byte[] getIpItem() {
		return ipItem;
	}
	public void setIpItem(byte[] ipItem) {
		this.ipItem = ipItem;
	}
	public Date getIpUpdate() {
		return ipUpdate;
	}
	public void setIpUpdate(Date ipUpdate) {
		this.ipUpdate = ipUpdate;
	}
	public String getIpItemBase64() {
		return ipItemBase64;
	}
	public void setIpItemBase64(String ipItemBase64) {
		this.ipItemBase64 = ipItemBase64;
	}

	
}



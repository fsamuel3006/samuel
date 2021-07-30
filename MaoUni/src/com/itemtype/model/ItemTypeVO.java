package com.itemtype.model;

public class ItemTypeVO implements java.io.Serializable{
	private Integer itemtId; //類別ID
	private String itemtName; //類別名稱
	
	public Integer getItemtId() {
		return itemtId;
	}
	public void setItemtId(Integer itemtId) {
		this.itemtId = itemtId;
	}
	public String getItemtName() {
		return itemtName;
	}
	public void setItemtName(String itemtName) {
		this.itemtName = itemtName;
	}
	
}

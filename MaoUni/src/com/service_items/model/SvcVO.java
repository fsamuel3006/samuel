package com.service_items.model;

public class SvcVO implements java.io.Serializable{
	private Integer svcId;
	private String item;
	private String pet;
	
	public Integer getSvcId() {
		return svcId;
	}
	public void setSvcId(Integer svcId) {
		this.svcId = svcId;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getPet() {
		return pet;
	}
	public void setPet(String pet) {
		this.pet = pet;
	}
	
	
}
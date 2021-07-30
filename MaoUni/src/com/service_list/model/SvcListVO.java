package com.service_list.model;

public class SvcListVO implements java.io.Serializable{
	private Integer groomerId;
	private Integer svcId;
	private Integer price;
	private Integer svcTime;
	private Integer svcProvided;
	private String svcItem;  // from service_items
	private String svcPet;   // from service_items
	
	public Integer getGroomerId() {
		return groomerId;
	}
	public void setGroomerId(Integer groomerId) {
		this.groomerId = groomerId;
	}
	public Integer getSvcId() {
		return svcId;
	}
	public void setSvcId(Integer svcId) {
		this.svcId = svcId;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getSvcTime() {
		return svcTime;
	}
	public void setSvcTime(Integer svcTime) {
		this.svcTime = svcTime;
	}
	public Integer getSvcProvided() {
		return svcProvided;
	}
	public void setSvcProvided(Integer svcProvided) {
		this.svcProvided = svcProvided;
	}
	public String getSvcItem() {
		return svcItem;
	}
	public void setSvcItem(String svcItem) {
		this.svcItem = svcItem;
	}
	public String getSvcPet() {
		return svcPet;
	}
	public void setSvcPet(String svcPet) {
		this.svcPet = svcPet;
	}
	
	
}

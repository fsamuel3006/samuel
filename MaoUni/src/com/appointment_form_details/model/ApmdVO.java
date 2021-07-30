package com.appointment_form_details.model;

public class ApmdVO implements java.io.Serializable{
	private Integer apmId;
	private Integer svcId;
	private Integer price;
	private String item; // from SvcListVO

	public Integer getApmId() {
		return apmId;
	}
	public void setApmId(Integer apmId) {
		this.apmId = apmId;
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
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
}

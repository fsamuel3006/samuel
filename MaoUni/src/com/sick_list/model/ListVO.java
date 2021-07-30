package com.sick_list.model;

public class  ListVO implements java.io.Serializable{
	
	private Integer slId;//疾病編號
	private Integer slPetId; //毛孩id
	private String slOther;//其他
	public Integer getSlId() {
		return slId;
	}
	public void setSlId(Integer slId) {
		this.slId = slId;
	}
	public Integer getSlPetId() {
		return slPetId;
	}
	public void setSlPetId(Integer slPetId) {
		this.slPetId = slPetId;
	}
	public String getSlOther() {
		return slOther;
	}
	public void setSlOther(String slOther) {
		this.slOther = slOther;
	}
	

	

}

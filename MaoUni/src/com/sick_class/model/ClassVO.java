package com.sick_class.model;

public class ClassVO  implements java.io.Serializable {

	private Integer scId;	// ai 類別ID
	private String scPain;	// 疾病名稱
	
	public Integer getScId() {
		return scId;
	}
	public void setScId(Integer scId) {
		this.scId = scId;
	}
	public String getScPain() {
		return scPain;
	}
	public void setScPain(String scPain) {
		this.scPain = scPain;
	}

	
}

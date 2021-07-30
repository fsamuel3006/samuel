package com.variety.model;

public class VarietyVO  implements java.io.Serializable {

	private Integer varId; //品種編號
	private String varName; //品種名稱
	private String varKind; //品種類別狗或貓
	
	public Integer getVarId() {
		return varId;
	}
	public void setVarId(Integer varId) {
		this.varId = varId;
	}
	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
	public String getVarKind() {
		return varKind;
	}
	public void setVarKind(String varKind) {
		this.varKind = varKind;
	}
	
}

package com.pet.model;

public class PetVO implements java.io.Serializable {

	private Integer petId; //毛孩編號
	private Integer petMemId; //毛主人編號(會員編號)
	private String petName; //毛孩名字
	private String petSort; //毛孩種類(貓或狗)
	private Integer petVarId; //品種
	private String petGender; //性別
	private Integer petAge; //年齡
	private Integer petSurvive; //狀態活著還是怎麼樣了
	
	public Integer getPetId() {
		return petId;
	}
	public void setPetId(Integer petId) {
		this.petId = petId;
	}
	public Integer getPetMemId() {
		return petMemId;
	}
	public void setPetMemId(Integer petMemId) {
		this.petMemId = petMemId;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public String getPetSort() {
		return petSort;
	}
	public void setPetSort(String petSort) {
		this.petSort = petSort;
	}
	public Integer getPetVarId() {
		return petVarId;
	}
	public void setPetVarId(Integer petVarId) {
		this.petVarId = petVarId;
	}
	public String getPetGender() {
		return petGender;
	}
	public void setPetGender(String petGender) {
		this.petGender = petGender;
	}
	public Integer getPetAge() {
		return petAge;
	}
	public void setPetAge(Integer petAge) {
		this.petAge = petAge;
	}
	public Integer getPetSurvive() {
		return petSurvive;
	}
	public void setPetSurvive(Integer petSurvive) {
		this.petSurvive = petSurvive;
	}
	

	
}
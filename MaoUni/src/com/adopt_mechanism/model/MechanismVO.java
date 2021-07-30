package com.adopt_mechanism.model;

public class MechanismVO implements java.io.Serializable{
Integer id;
String name;
String address;
String tel;
String url;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getTel() {
	return tel;
}

public void setTel(String tel) {
	this.tel = tel;
}

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public MechanismVO() {
	
	this.id = id;
	this.name = name;
	this.address = address;
	this.tel = tel;
	this.url = url;
}

 

}
package com.backstage_staff.model;

public class StaffVO implements java.io.Serializable{
	private Integer id;
	private Integer status;
	private String name;
	private Integer age;
	private String edu;
	private String add;
	private String cont;
	private String tel;
	private String password;
	private String username;
	
	

	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}




	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public String getEdu() {
		return edu;
	}


	public void setEdu(String edu) {
		this.edu = edu;
	}




	public String getAdd() {
		return add;
	}


	public void setAdd(String add) {
		this.add = add;
	}




	public String getCont() {
		return cont;
	}




	public void setCont(String cont) {
		this.cont = cont;
	}



	public StaffVO() {
	}


	@Override
	public String toString() {
		return "StaffVO [id=" + id + ", status=" + status + ", name=" + name + ", age=" + age + ", edu=" + edu
				+ ", add=" + add + ", cont=" + cont + ", tel=" + tel + "]";
	}
	
	}
	
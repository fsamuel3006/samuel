package com.backstage_function.model;

public class FunctionVO implements java.io.Serializable{

	private Integer id;
	private String name;
	
	public Integer getId() {
		return id;
	}
	@Override
	public String toString() {
		return "FunctionVO [id=" + id + ", name=" + name + "]";
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

}
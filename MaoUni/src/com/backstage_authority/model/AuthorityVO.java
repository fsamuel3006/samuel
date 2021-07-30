package com.backstage_authority.model;

public class AuthorityVO implements java.io.Serializable{
	private Integer id;
	private Integer fun;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFun() {
		return fun;
	}

	public void setFun(Integer fun) {
		this.fun = fun;
	}

	@Override
	public String toString() {
		return "AuthorityVO [id=" + id + ", fun=" + fun + "]";
	}
	

	
	
}
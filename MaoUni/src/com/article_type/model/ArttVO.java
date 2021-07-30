package com.article_type.model;


//ArttVO  �峹����
public class ArttVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer  id;        //  ARTT_ID     1.����ID ( PK )
	private String   name;      //  ARTT_NAME   2.�����W�� 
	
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
	

}

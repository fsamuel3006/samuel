package com.adopt_imf.model;

public class ImfVO implements java.io.Serializable{
	private Integer id;
	private Integer adopt;
	private Integer mech;
	private String name;
	private Integer year;
	private String sit;
	private byte[] photo;
	private String mech2;
	
	
	

	public String getMech2() {
		return mech2;
	}

	public void setMech2(String mech2) {
		this.mech2 = mech2;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public Integer getAdopt() {
		return adopt;
	}

	public void setAdopt(Integer adopt) {
		this.adopt = adopt;
	}

	public Integer getMech() {
		return mech;
	}

	public void setMech(Integer mech) {
		this.mech = mech;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getSit() {
		return sit;
	}

	public void setSit(String sit) {
		this.sit = sit;
	}

	public void ImfVO(Integer id, Integer adopt, Integer mech, String name, Integer year, String sit) {
	}
	
		
	public ImfVO() {
		
		this.id = id;
		this.adopt = adopt;
		this.mech = mech;
		this.name = name;
		this.year = year;
		this.sit = sit;
	}

	public void setPhoto(String string) {
		// TODO Auto-generated method stub
		
	}
	
}	
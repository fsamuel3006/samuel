package com.groomer.model;

import java.io.InputStream;

public class GroVO implements java.io.Serializable{
	private Integer groomerId;
	private Integer memId;
	private String gname;
	private String center;
	private Integer grange;
	private byte[] license;
	private byte[] pcrc;
	private byte[] fid;
	private byte[] bid;
	private String schDate;
	private String schTime;
	private Integer gstatus;
	private Integer reserve;
	private Integer com;
	private Integer comg;
	private Integer reped;
	private byte[] avatar;
	private String avatarBase64;
	private String intro;
	
	
	public Integer getGroomerId() {
		return groomerId;
	}
	public void setGroomerId(Integer groomerId) {
		this.groomerId = groomerId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public Integer getGrange() {
		return grange;
	}
	public void setGrange(Integer grange) {
		this.grange = grange;
	}
	public byte[] getLicense() {
		return license;
	}
	public void setLicense(byte[] license) {
		this.license = license;
	}
	public byte[] getPcrc() {
		return pcrc;
	}
	public void setPcrc(byte[] pcrc) {
		this.pcrc = pcrc;
	}
	public byte[] getFid() {
		return fid;
	}
	public void setFid(byte[] fid) {
		this.fid = fid;
	}
	public byte[] getBid() {
		return bid;
	}
	public void setBid(byte[] bid) {
		this.bid = bid;
	}
	public String getSchDate() {
		return schDate;
	}
	public void setSchDate(String schDate) {
		this.schDate = schDate;
	}
	public String getSchTime() {
		return schTime;
	}
	public void setSchTime(String schTime) {
		this.schTime = schTime;
	}
	public Integer getGstatus() {
		return gstatus;
	}
	public void setGstatus(Integer gstatus) {
		this.gstatus = gstatus;
	}
	public Integer getReserve() {
		return reserve;
	}
	public void setReserve(Integer reserve) {
		this.reserve = reserve;
	}
	public Integer getCom() {
		return com;
	}
	public void setCom(Integer com) {
		this.com = com;
	}
	public Integer getComg() {
		return comg;
	}
	public void setComg(Integer comg) {
		this.comg = comg;
	}
	public Integer getReped() {
		return reped;
	}
	public void setReped(Integer reped) {
		this.reped = reped;
	}
	public byte[] getAvatar() {
		return avatar;
	}
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	public String getAvatarBase64() {
		return avatarBase64;
	}
	public void setAvatarBase64(String avatarBase64) {
		this.avatarBase64 = avatarBase64;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	
	
	
}


package com.appointment_form.model;

import java.sql.Blob;
import java.sql.Date;

public class ApmVO implements java.io.Serializable{
	private Integer apmId;
	private Integer memId;
	private Integer groomerId;
	private Integer pid;
	private Date apmDate;
	private Integer dateOfMonth;
	private Integer dateOfWeek;
	private Integer stime;
	private Integer etime;
	private String stimeFormated;
	private String etimeFormated;
	private Integer total;
	private Integer apmStatus;
	private Integer star;
	private String apmComment;
	private byte[] photo;
	private String photoBase64;
	private String address;
	private String lng;
	private String lat;
	
	
	public Integer getApmId() {
		return apmId;
	}
	public void setApmId(Integer apmId) {
		this.apmId = apmId;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getGroomerId() {
		return groomerId;
	}
	public void setGroomerId(Integer groomerId) {
		this.groomerId = groomerId;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Date getApmDate() {
		return apmDate;
	}
	public void setApmDate(Date apmDate) {
		this.apmDate = apmDate;
	}
	public Integer getDateOfMonth() {
		return dateOfMonth;
	}
	public void setDateOfMonth(Integer dateOfMonth) {
		this.dateOfMonth = dateOfMonth;
	}
	public Integer getDateOfWeek() {
		return dateOfWeek;
	}
	public void setDateOfWeek(Integer dateOfWeek) {
		this.dateOfWeek = dateOfWeek;
	}
	public Integer getStime() {
		return stime;
	}
	public void setStime(Integer stime) {
		this.stime = stime;
	}
	public Integer getEtime() {
		return etime;
	}
	public void setEtime(Integer etime) {
		this.etime = etime;
	}
	public String getStimeFormated() {
		return stimeFormated;
	}
	public void setStimeFormated(String stimeFormated) {
		this.stimeFormated = stimeFormated;
	}
	public String getEtimeFormated() {
		return etimeFormated;
	}
	public void setEtimeFormated(String etimeFormated) {
		this.etimeFormated = etimeFormated;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getApmStatus() {
		return apmStatus;
	}
	public void setApmStatus(Integer apmStatus) {
		this.apmStatus = apmStatus;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public String getApmComment() {
		return apmComment;
	}
	public void setApmComment(String apmComment) {
		this.apmComment = apmComment;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getPhotoBase64() {
		return photoBase64;
	}
	public void setPhotoBase64(String photoBase64) {
		this.photoBase64 = photoBase64;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	
}

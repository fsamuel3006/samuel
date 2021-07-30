package com.message.model;

import java.sql.Date;
import java.sql.Timestamp;

public class MessageVO {

	private Integer id;
	private Integer memId;
	private Timestamp time;
	private String contain;
	private Integer status;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getContain() {
		return contain;
	}
	public void setContain(String contain) {
		this.contain = contain;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "MessageVO [id=" + id + ", memId=" + memId + ", time=" + time + ", contain=" + contain + ", status="
				+ status + "]";
	}
	
}
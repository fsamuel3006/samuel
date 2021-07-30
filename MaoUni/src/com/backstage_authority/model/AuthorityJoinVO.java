package com.backstage_authority.model;

import com.backstage_staff.model.StaffVO;

public class AuthorityJoinVO {
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public StaffVO getStaffVO() {
		return staffVO;
	}
	public void setStaffVO(StaffVO staffVO) {
		this.staffVO = staffVO;
	}
	private Integer id;
	private StaffVO staffVO;
}

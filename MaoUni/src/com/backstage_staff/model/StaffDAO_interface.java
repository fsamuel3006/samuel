package com.backstage_staff.model;
import java.util.*;

import com.StaffSignIn.model.signInVO;
public interface StaffDAO_interface {
	
	public void insert(StaffVO StaffVO);
	public void update(StaffVO StaffVO); 
	public void delete(Integer ID);
	public StaffVO findByPrimaryKey(Integer ID);
	public List<StaffVO> getAll();
	StaffVO findByMGnameAndPassword(String username, String password);


}


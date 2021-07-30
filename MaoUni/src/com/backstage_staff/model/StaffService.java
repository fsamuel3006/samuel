package com.backstage_staff.model;

import java.util.List;

import com.StaffSignIn.model.signInVO;

public class StaffService {
	
	private StaffDAO_interface dao;
	
	public StaffService() {
		dao = new StaffJDBCDAO();
	}
	public StaffVO addStaff(Integer id,Integer status,String name,Integer age,String edu,String add,String cont,String tel,String password,String username) {
		StaffVO staffVO = new StaffVO();
		staffVO.setId(id);
		staffVO.setName(name);
		staffVO.setAdd(add);
		staffVO.setEdu(edu);
		staffVO.setAge(age);
		staffVO.setCont(cont);
		staffVO.setStatus(status);
		staffVO.setTel(tel);
		staffVO.setPassword(password);
		staffVO.setUsername(username);
		System.out.println(staffVO);
		dao.insert(staffVO);
		
		return staffVO;
	}
	public void updateStaff(Integer id,String name,Integer status,String add,Integer age,String cont,String edu,String tel,String password,String username) {
		StaffVO staffVO = new StaffVO();
		staffVO.setId(id);
		staffVO.setStatus(status);
		staffVO.setAdd(add);
		staffVO.setEdu(edu);
		staffVO.setAge(age);
		staffVO.setCont(cont);
		staffVO.setName(name);
		staffVO.setTel(tel);
		staffVO.setPassword(password);
		staffVO.setUsername(username);
		System.out.println(staffVO);
		dao.update(staffVO);	
	}
	public void deleteStaff(Integer Id) {
		dao.delete(Id);
	}
	public StaffVO getOneStaff(Integer Id) {
		return dao.findByPrimaryKey(Id);
	}
	public List<StaffVO>getAll(){
		return dao.getAll();
	}
	
	public StaffVO findByMGnameAndPassword(String username,String password) {
		return dao.findByMGnameAndPassword(username, password);
	
}
}

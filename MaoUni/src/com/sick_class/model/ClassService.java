package com.sick_class.model;

import java.util.List;

public class ClassService {

	private ClassDAO_interface dao;
	public ClassService() {
	dao = new ClassJDBCDAO();
	
	}
	public Integer insert(String scPain) {
		return dao.insert(scPain);
	}
	public List<ClassVO> getAll() {
		return dao.getAll();
	}
	
//	public Integer update(String scPain) {
//		return dao.update(scPain);
//	}
	
//	public void delete(Integer ScId) {
//		dao.delete(ScId);
//	}
//	public ClassVO findByPrimaryKey(Integer ScId) {
//		return dao.findByPrimaryKey(ScId);
//	}
	
	
}

package com.works.model;

import java.util.List;

public class WorkService {
	private WorkDAO_interface dao;
	public WorkService() {
		this.dao = new WorkDAO();
	}
	
	public void addItem(Integer groomerId, byte[] item) {
		WorkVO workVO = new WorkVO();
		workVO.setGroomerId(groomerId);
		workVO.setItem(item);
		dao.insert(workVO);
	}
	
	public void delete(Integer wid) {
		dao.delete(wid);
	}
	
	public List<WorkVO> getOneList(Integer groomerId){
		return dao.getOneList(groomerId);
	}

}

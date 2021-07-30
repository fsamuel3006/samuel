package com.sick_list.model;

import java.util.List;

public class ListService {

	private ListDAO_interface dao;
	public ListService() {
	dao =new ListJDBCDAO();
	}
	
	public void insert(Integer slId, Integer slPetId , String slOther) {
		ListVO listVO = new ListVO();
		listVO.setSlId(slId);
		listVO.setSlPetId(slPetId);
		listVO.setSlOther(slOther);
		dao.insert(listVO);
	}
	public void update(Integer slId, Integer slPetId , String slOther) {
		ListVO listVO = new ListVO();
		listVO.setSlId(slId);
		listVO.setSlPetId(slPetId);
		listVO.setSlOther(slOther);
		dao.update(listVO);
	}
	
	public ListVO findByPrimaryKey(Integer slId){
		return dao.findByPrimaryKey(slId);
	}

	public List<ListVO> getAll() {
		return dao.getAll();
	}


	}
	

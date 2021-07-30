package com.service_list.model;

import java.util.List;

public class SvcListService {

	private SvcListDAO_interface dao;
	
	public SvcListService() {
		this.dao = new SvcListDAO();
	}
	
	public void addService(Integer groomerId, Integer svcId, Integer price, Integer svcTime) {
		SvcListVO svcListVO = new SvcListVO();
		svcListVO.setGroomerId(groomerId);
		svcListVO.setSvcId(svcId);
		svcListVO.setPrice(price);
		svcListVO.setSvcTime(svcTime);
		
		dao.insert(svcListVO);
	}
	
	public List<SvcListVO> getAll(Integer groomerId){
		return dao.getAll(groomerId);
	}
	
}

package com.service_items.model;

import java.util.List;

public class SvcService {
	
	private SvcDAO_interface dao;
	
	public SvcService() {
		dao = new SvcDAO();
	}
	
    public SvcVO addServiceItem(String item, String pet) {
    	SvcVO svcVO = new SvcVO();
    	svcVO.setItem(item);
    	svcVO.setPet(pet);
    	dao.insert(svcVO);
    	
    	return svcVO;
    };
    public void update(Integer svcId, String item, String pet) {
    	SvcVO svcVO = new SvcVO();
    	svcVO.setSvcId(svcId);
    	svcVO.setItem(item);
    	svcVO.setPet(pet);
    	dao.update(svcVO);
    };
    
    
    public List<SvcVO> findByPet(String pet) {
    	return dao.findByPet(pet);
    }
    
    
    public List<SvcVO> getAll(){
    	return dao.getAll();
    };
}

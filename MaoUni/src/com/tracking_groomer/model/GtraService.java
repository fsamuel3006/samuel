package com.tracking_groomer.model;

public class GtraService {
	GtraDAO_interface dao;

	public GtraService() {
		this.dao = new GtraDAO();
	}

	public void addGroomerTraking(Integer memId, Integer groomerId) {
		GtraVO gtraVO = new GtraVO();
		gtraVO.setMemId(memId);
		gtraVO.setGroomerId(groomerId);

		dao.insert(gtraVO);
	}
	
	public void delete(Integer memId, Integer groomerId) {
		GtraVO gtraVO = new GtraVO();
		gtraVO.setMemId(memId);
		gtraVO.setGroomerId(groomerId);
		
		dao.delete(gtraVO);
	}

	public static void main(String[] args) {
		GtraService gtraService = new GtraService();
//		gtraService.addGroomerTraking(4, 2);
//		gtraService.delete(3, 2);

	}
	


}

package com.tracking_adopt.model;

import java.util.List;

public class TrackingService {

	private TrackingDAO_interface dao;
	
	public TrackingService() {
		
		dao = new TrackingJDBCDAO();
	}
	
	public Integer addTracking(Integer id,Integer det) {
		TrackingVO trackingVO = new TrackingVO();
		trackingVO.setId(id);
		trackingVO.setDet(det);
		Integer completeNum = dao.insert(trackingVO);
		
		return completeNum;
	}
	public TrackingVO updateTracking(Integer id,Integer det) {
		TrackingVO trackingVO = new TrackingVO();
		trackingVO.setId(id);
		trackingVO.setDet(det);
		dao.update(trackingVO);
		
		return trackingVO;
	}
	public void deleteTracking(Integer id) {
		dao.delete(id);
	}
	public TrackingVO getOneTracking(Integer id) {
		return dao.findByPrimaryKey(id);
	}
	public List<TrackingVO>getAll(){
		return dao.getAll();
	}
	
}

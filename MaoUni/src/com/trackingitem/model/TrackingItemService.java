package com.trackingitem.model;

import java.util.List;

public class TrackingItemService {

	private TrackingItemDAO_interface dao;

	public TrackingItemService() {
		dao = new TrackingItemDAO();
	}

	public TrackingItemVO addTrackingItem(Integer itraItemId, Integer itraMemId) {

		TrackingItemVO trackingItemVO = new TrackingItemVO();
		trackingItemVO.setItraItemId(itraItemId);
		trackingItemVO.setItraMemId(itraMemId);
		
		dao.insert(trackingItemVO);

		return trackingItemVO;
	}


	public void deleteTrackingItem(Integer itraMemId, Integer itraItemId) {
		dao.delete(itraMemId,itraItemId);
	}

	public TrackingItemVO getOneTrackingItem(Integer itraItemId) {
		return dao.findByPrimaryKey(itraItemId);
	}

	public List<TrackingItemVO> getAll() {
		return dao.getAll();
	}
	
	
//----------------------------TestMainMethod----------------------------
//public static void main(String [] args) throws ClassNotFoundException, SQLException {
//		  TrackingItemDAO dao = new TrackingItemDAO();

	  
// ----------------------------測試insert----------------------------
//			TrackingItemVO trackingItemVO = new TrackingItemVO();
//			trackingItemVO.setItraMemId(1);
//			trackingItemVO.setItraItemId(1);
//			dao.insert(trackingItemVO);
//		    System.out.println("OK");
	  
// ----------------------------測試delete----------------------------	  
//			dao.delete(1,1);
//	   	System.out.println("OK");
	
// ----------------------------測試getAll----------------------------	  
//		  List<TrackingItemVO> list = dao.getAll();
//		  for(TrackingItemVO tt : list) {
//		   System.out.println(tt.getItraMemId()+",");
//		   System.out.println(tt.getItraItemId()+",");
//		   System.out.println("---分隔線----");
//		   }	
//		}
	
}

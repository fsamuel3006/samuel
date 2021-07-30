package com.trackingpost.model;

import java.util.List;

public class TrackingPostService {

	private TrackingPostDAO_interface dao;

	public TrackingPostService() {
		dao = new TrackingPostDAO();
	}

	public TrackingPostVO addTrackingPost(Integer ptraPostId, Integer ptraMemId) {

		TrackingPostVO trackingPostVO = new TrackingPostVO();
		trackingPostVO.setPtraPostId(ptraPostId);
		trackingPostVO.setPtraMemId(ptraPostId);
		
		dao.insert(trackingPostVO);

		return trackingPostVO;
	}


	public void deleteTrackingPost(Integer ptraPostId, Integer ptraMemId) {
		dao.delete(ptraPostId,ptraMemId);
	}

	public TrackingPostVO getOneTrackingPost(Integer ptraPostId) {
		return dao.findByPrimaryKey(ptraPostId);
	}

	public List<TrackingPostVO> getAll() {
		return dao.getAll();
	}

	
//----------------------------TestMainMethod----------------------------	
//public static void main(String [] args) throws ClassNotFoundException, SQLException {
//		  TrackingPostDAO dao = new TrackingPostDAO();

	  
//----------------------------測試insert----------------------------
//			TrackingPostVO trackingPostVO = new TrackingPostVO();
//			trackingPostVO.setPtraPostId(1);
//			trackingPostVO.setPtraMemId(3);
//			dao.insert(trackingPostVO);
//		    System.out.println("OK");
	  
//----------------------------測試delete----------------------------	  
//			dao.delete(1,3);
//			System.out.println("OK");	  
	
//----------------------------測試getAll----------------------------	  
//		  List<TrackingPostVO> list = dao.getAll();
//		  for(TrackingPostVO tp : list) {
//		   System.out.println(tp.getPtraPostId()+",");
//		   System.out.println(tp.getPtraMemId()+",");
//		   System.out.println("----------");
//		   }	
//		}
	
}

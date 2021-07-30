package com.reportforumpost.model;

import java.util.List;

public class ReportForumPostService {
	
	private ReportForumPostDAO_interface dao;
	
	public ReportForumPostService( ) {
		dao = new ReportForumPostDAO();
	}
	
	public ReportForumPostVO addReportForumPost(Integer prepPostId,Integer prepMemId,String prepContent,Integer prepStatus,Integer prepResult,java.sql.Date prepUpdate) {

		ReportForumPostVO reportForumPostVO = new ReportForumPostVO();

		reportForumPostVO.setPrepPostId(prepPostId);
		reportForumPostVO.setPrepMemId(prepMemId);
		reportForumPostVO.setPrepContent(prepContent);
		reportForumPostVO.setPrepStatus(prepStatus);
		reportForumPostVO.setPrepResult(prepResult);
		reportForumPostVO.setPrepUpdate(prepUpdate);
		
		dao.insert(reportForumPostVO);
		return reportForumPostVO;
	}

	public ReportForumPostVO updateReportForumPost(Integer prepId,Integer prepPostId,Integer prepMemId,String prepContent,Integer prepStatus,Integer prepResult,java.sql.Date prepUpdate) {

		ReportForumPostVO reportForumPostVO = new ReportForumPostVO();

		reportForumPostVO.setPrepId(prepId);
		reportForumPostVO.setPrepPostId(prepPostId);
		reportForumPostVO.setPrepMemId(prepMemId);
		reportForumPostVO.setPrepContent(prepContent);
		reportForumPostVO.setPrepStatus(prepStatus);
		reportForumPostVO.setPrepResult(prepResult);
		reportForumPostVO.setPrepUpdate(prepUpdate);
		dao.update(reportForumPostVO);
		return reportForumPostVO;
	}
	
	public void deleteReportForumPost(Integer prepId) {
		dao.delete(prepId);
	}

	
	public List<ReportForumPostVO> getAll() {
		return dao.getAll();
	}	
	
	
//----------------------------TestMainMethod----------------------------	
//public static void main(String [] args) throws ClassNotFoundException, SQLException {
//		ReportForumPostJDBCDAO dao = new ReportForumPostJDBCDAO();	
	
//----------------------------測試insert----------------------------
//		ReportForumPostVO reportForumPostVO = new ReportForumPostVO();
//		reportForumPostVO.setPrepPostId(1);
//		reportForumPostVO.setPrepMemId(3);
//		reportForumPostVO.setPrepContent("測試內容");
//		reportForumPostVO.setPrepStatus(1);
//		reportForumPostVO.setPrepResult(1);
	
//		dao.insert(reportForumPostVO);
//	    System.out.println("OK");  

  
	
//----------------------------測試update----------------------------
//		ReportForumPostVO reportForumPostVO2 = new ReportForumPostVO();
//		reportForumPostVO2.setPrepId(3);
//		reportForumPostVO2.setPrepPostId(1);
//		reportForumPostVO2.setPrepMemId(3);
//		reportForumPostVO2.setPrepContent("測試修改內容");
//		reportForumPostVO2.setPrepStatus(1);
//		reportForumPostVO2.setPrepResult(1);
	
//		dao.update(reportForumPostVO2);
//	    System.out.println("OK");  

  
//----------------------------測試delete----------------------------	  
//		dao.delete(3);
//		System.out.println("OK");	         
    
    
//----------------------------測試getAll----------------------------	  
//  List<ReportForumPostVO> list = dao.getAll();
//  for(ReportForumPostVO rfp : list) {
//  System.out.println(rfp.getPrepId()+",");
//  System.out.println(rfp.getPrepPostId()+",");
//  System.out.println(rfp.getPrepMemId()+",");
//  System.out.println(rfp.getPrepContent()+",");
//  System.out.println(rfp.getPrepStatus()+",");
//  System.out.println(rfp.getPrepResult()+",");
//  System.out.println(rfp.getPrepUpdate()+",");
//  System.out.println("----------");
//   }	
//  }
	
}

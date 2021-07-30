package com.forumpost.model;

import java.util.List;
import java.util.Map;


public class ForumPostService {

	private ForumPostDAO_interface dao;

	public ForumPostService() {
		dao = new ForumPostDAO();
	}

	public ForumPostVO addForumPost(Integer fpMemId, String fpTitle, String fpContent,
			Integer fpStatus, java.sql.Date fpUpdate) {

		ForumPostVO forumPostVO = new ForumPostVO();

		forumPostVO.setFpMemId(fpMemId);
		forumPostVO.setFpTitle(fpTitle);
		forumPostVO.setFpContent(fpContent);
		forumPostVO.setFpStatus(fpStatus);
		forumPostVO.setFpUpdate(fpUpdate);
		dao.insert(forumPostVO);

		return forumPostVO;
	}

	public ForumPostVO updateForumPost(Integer fpId, Integer fpMemId, String fpTitle, String fpContent,
			Integer fpStatus, java.sql.Date fpUpdate) {

		ForumPostVO forumPostVO = new ForumPostVO();
		
		forumPostVO.setFpId(fpId);
		forumPostVO.setFpMemId(fpMemId);
		forumPostVO.setFpTitle(fpTitle);
		forumPostVO.setFpContent(fpContent);
		forumPostVO.setFpStatus(fpStatus);
		forumPostVO.setFpUpdate(fpUpdate);
		dao.update(forumPostVO);
		return forumPostVO;
	}

	public void deleteForumPost(Integer fpId) {
		dao.delete(fpId);
	}

	public ForumPostVO getOneForumPost(Integer fpId) {
		return dao.findByPrimaryKey(fpId);
	}
	
	public List<ForumPostVO> getAll() {
		return dao.getAll();
	}
	
	public List<ForumPostVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	

//----------------------------TestMainMethod----------------------------	
//public static void main(String [] args) throws ClassNotFoundException, SQLException {
//	ForumPostJDBCDAO dao = new ForumPostJDBCDAO();	
	
//----------------------------測試insert----------------------------
//	ForumPostVO forumPostVO = new ForumPostVO();
//	forumPostVO.setFpMemId(2);
//	forumPostVO.setFpTitle("測試4");
//	forumPostVO.setFpContent("測試內容4");
//	forumPostVO.setFpStatus(1);
//	
//	dao.insert(forumPostVO);
//  System.out.println("OK");  

  
	
//----------------------------測試update----------------------------
//	ForumPostVO forumPostVO2 = new ForumPostVO();
//	forumPostVO2.setFpId(6);
//	forumPostVO2.setFpMemId(2);
//	forumPostVO2.setFpTitle("測試修改文章標題");
//	forumPostVO2.setFpContent("測試修改文章內容");
//	forumPostVO2.setFpStatus(1); 
//	
//	dao.update(forumPostVO2);
//    System.out.println("OK");  

  
//----------------------------測試delete----------------------------	  
//	dao.delete(6);
//	System.out.println("OK");	

	
//----------------------------查詢文章ID----------------------------
//	ForumPostVO forumPostVO3 = dao.findByPrimaryKey(1);
//	System.out.print(forumPostVO3.getFpId() + ",");
//	System.out.print(forumPostVO3.getFpMemId() + ",");
//	System.out.print(forumPostVO3.getFpTitle() + ",");
//	System.out.print(forumPostVO3.getFpContent() + ",");
//	System.out.print(forumPostVO3.getFpStatus() + ",");
//	System.out.print(forumPostVO3.getFpUpdate() + ",");
//	System.out.println("---------------------");          
    
    
//----------------------------測試getAll----------------------------	  
//  List<ForumPostVO> list = dao.getAll();
//  for(ForumPostVO tp : list) {
//  System.out.println(tp.getFpId()+",");
//  System.out.println(tp.getFpMemId()+",");
//  System.out.println(tp.getFpTitle()+",");
//  System.out.println(tp.getFpContent()+",");
//  System.out.println(tp.getFpStatus()+",");
//  System.out.println(tp.getFpUpdate()+",");
//  System.out.println("----------");
//   }	
//  }
}

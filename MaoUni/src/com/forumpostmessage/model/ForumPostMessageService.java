package com.forumpostmessage.model;

import java.util.List;

import com.forumpost.model.ForumPostVO;

public class ForumPostMessageService {

	private ForumPostMessageDAO_interface dao;
	
	public ForumPostMessageService( ) {
		dao = new ForumPostMessageDAO();
	}
	
	public ForumPostMessageVO addForumPostMessage(Integer fpmPostId, Integer fpmMemId,
			String fpmContent, java.sql.Date fpmUpdate) {

		ForumPostMessageVO forumPostMessageVO = new ForumPostMessageVO();

		forumPostMessageVO.setFpmPostId(fpmPostId);
		forumPostMessageVO.setFpmMemId(fpmMemId);
		forumPostMessageVO.setFpmContent(fpmContent);
		forumPostMessageVO.setFpmUpdate(fpmUpdate);
		dao.insert(forumPostMessageVO);

		return forumPostMessageVO;
	}

	public ForumPostMessageVO updateForumPostMessage(Integer fpmId, Integer fpmPostId, Integer fpmMemId,
			String fpmContent, java.sql.Date fpmUpdate) {

		ForumPostMessageVO forumPostMessageVO = new ForumPostMessageVO();
		
		forumPostMessageVO.setFpmId(fpmId);
		forumPostMessageVO.setFpmPostId(fpmPostId);
		forumPostMessageVO.setFpmMemId(fpmMemId);
		forumPostMessageVO.setFpmContent(fpmContent);
		forumPostMessageVO.setFpmUpdate(fpmUpdate);
		dao.update(forumPostMessageVO);
		return forumPostMessageVO;
	}

	public void deleteForumPostMessage(Integer fpmId) {
		dao.delete(fpmId);
	}

	public ForumPostMessageVO getOneForumPostMessage(Integer fpmId) {
		return dao.findByPrimaryKey(fpmId);
	}
	
	public List<ForumPostMessageVO> getAll() {
		return dao.getAll();
	}
	
	
	
//----------------------------TestMainMethod----------------------------		
//public static void main(String [] args) throws ClassNotFoundException, SQLException {
//		ForumPostMessageJDBCDAO dao = new ForumPostMessageJDBCDAO();	
		
//----------------------------測試insert----------------------------
//		ForumPostMessageVO forumPostMessageVO = new ForumPostMessageVO();
//		forumPostMessageVO.setFpmPostId(2);
//		forumPostMessageVO.setFpmMemId(2);
//		forumPostMessageVO.setFpmContent("測試留言1");
	
//		dao.insert(forumPostMessageVO);
//	    System.out.println("OK");  

	  
//----------------------------測試update----------------------------
//		ForumPostMessageVO forumPostMessageVO2 = new ForumPostMessageVO();
//		forumPostMessageVO2.setFpmId(3);
//		forumPostMessageVO2.setFpmPostId(2);
//		forumPostMessageVO2.setFpmMemId(2);
//		forumPostMessageVO2.setFpmContent("測試留言2");
	
//		dao.update(forumPostMessageVO2);
//	    System.out.println("OK");  

	  
//----------------------------測試delete----------------------------	  
//		dao.delete(3);
//		System.out.println("OK");	

		
//----------------------------查詢文章ID----------------------------
//		ForumPostMessageVO forumPostMessageVO3 = dao.findByPrimaryKey(1);
//		System.out.print(forumPostMessageVO3.getFpmId() + ",");
//		System.out.print(forumPostMessageVO3.getFpmPostId() + ",");
//		System.out.print(forumPostMessageVO3.getFpmMemId() + ",");
//		System.out.print(forumPostMessageVO3.getFpmContent() + ",");
//		System.out.print(forumPostMessageVO3.getFpmUpdate() + ",");
//		System.out.println("---------------------");          
	    
	    
//----------------------------測試getAll----------------------------	  
//  List<ForumPostMessageVO> list = dao.getAll();
//  for(ForumPostMessageVO fpm : list) {
//  System.out.println(fpm.getFpmId()+",");
//  System.out.println(fpm.getFpmPostId()+",");
//  System.out.println(fpm.getFpmMemId()+",");
//  System.out.println(fpm.getFpmContent()+",");
//  System.out.println(fpm.getFpmUpdate()+",");
//  System.out.println("---------");
//   }	
//  }
	
}

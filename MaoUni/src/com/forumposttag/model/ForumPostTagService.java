package com.forumposttag.model;

import java.util.List;

public class ForumPostTagService {

	private ForumPostTagDAO_interface dao;
	
	public ForumPostTagService( ) {
		dao = new ForumPostTagDAO();
	}
	
	public ForumPostTagVO addForumPostTag(String fptName) {

		ForumPostTagVO forumPostTagVO = new ForumPostTagVO();

		forumPostTagVO.setFptName(fptName);
		dao.insert(forumPostTagVO);
		return forumPostTagVO;
	}

	public ForumPostTagVO updateForumPostTag(Integer fptId,String fptName) {

		ForumPostTagVO forumPostTagVO = new ForumPostTagVO();
		
		forumPostTagVO.setFptId(fptId);
		forumPostTagVO.setFptName(fptName);
		dao.update(forumPostTagVO);
		return forumPostTagVO;
	}

	public void deleteForumPostTag(Integer fptId) {
		dao.delete(fptId);
	}

	public ForumPostTagVO getOneForumPostTag(Integer fptId) {
		return dao.findByPrimaryKey(fptId);
	}
	
	public List<ForumPostTagVO> getAll() {
		return dao.getAll();
	}	
	
	
//----------------------------TestMainMethod----------------------------	
//public static void main(String [] args) throws ClassNotFoundException, SQLException {
//		ForumPostTagJDBCDAO dao = new ForumPostTagJDBCDAO();	
	
//----------------------------測試insert----------------------------
//		ForumPostTagVO forumPostTagVO = new ForumPostTagVO();
//		forumPostTagVO.setFptName("測試標籤");
	
//		dao.insert(forumPostTagVO);
//	    System.out.println("OK");  

  
//----------------------------測試update----------------------------
//		ForumPostTagVO forumPostTagVO2 = new ForumPostTagVO();
//		forumPostTagVO2.setFptId(7);
//		forumPostTagVO2.setFptName("測試修改標籤");

//		dao.update(forumPostTagVO2);
//	    System.out.println("OK");  

  
//----------------------------測試delete----------------------------	  
//		dao.delete(7);
//		System.out.println("OK");	

	
//----------------------------查詢標籤ID----------------------------
//		ForumPostTagVO forumPostTagVO3 = dao.findByPrimaryKey(1);
//		System.out.print(forumPostTagVO3.getFptId() + ",");
//		System.out.print(forumPostTagVO3.getFptName() + ",");
        
    
//----------------------------測試getAll----------------------------	  
//  List<ForumPostTagVO> list = dao.getAll();
//  for(ForumPostTagVO fpt : list) {
//  System.out.println(fpt.getFptId()+",");
//  System.out.println(fpt.getFptName()+",");
//  System.out.println("-------------");
//   }	
//  }

}

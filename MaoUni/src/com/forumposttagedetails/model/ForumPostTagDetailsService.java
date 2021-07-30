package com.forumposttagedetails.model;

import java.util.List;

public class ForumPostTagDetailsService {
	
	private ForumPostTagDetailsDAO_interface dao;
	
	public ForumPostTagDetailsService( ) {
		dao = new ForumPostTagDetailsDAO();
	}
	
	public ForumPostTagDetailsVO addForumPostTagDetails(Integer fptdTagId,Integer fptdPostId) {

		ForumPostTagDetailsVO forumPostTagDetailsVO = new ForumPostTagDetailsVO();

		forumPostTagDetailsVO.setFptdTagId(fptdTagId);
		forumPostTagDetailsVO.setFptdPostId(fptdPostId);
		dao.insert(forumPostTagDetailsVO);
		return forumPostTagDetailsVO;
	}


	public void deleteForumPostTagDetails(Integer fptdTagId,Integer fptdPostId) {
		dao.delete(fptdTagId,fptdPostId);
	}

	public ForumPostTagDetailsVO getOneForumPostTag(Integer fptdTagId) {
		return dao.findByPrimaryKey(fptdTagId);
	}
	
	public List<ForumPostTagDetailsVO> getAll() {
		return dao.getAll();
	}	
	
	
//----------------------------TestMainMethod----------------------------	
//public static void main(String [] args) throws ClassNotFoundException, SQLException {
//		  ForumPostTagDetailsJDBCDAO dao = new ForumPostTagDetailsJDBCDAO();

	  
//----------------------------測試insert----------------------------
//		  ForumPostTagDetailsVO forumPostTagDetailsVO = new ForumPostTagDetailsVO();
//			forumPostTagDetailsVO.setFptdTagId(1);
//			forumPostTagDetailsVO.setFptdPostId(1);
//			dao.insert(forumPostTagDetailsVO);
//		    System.out.println("OK");
	  
//----------------------------測試delete----------------------------	  
//			dao.delete(1,1);
//			System.out.println("OK");	  
	
//----------------------------測試getAll----------------------------	  
//		  List<ForumPostTagDetailsVO> list = dao.getAll();
//		  for(ForumPostTagDetailsVO fptd : list) {
//		   System.out.println(fptd.getFptdTagId()+",");
//		   System.out.println(fptd.getFptdPostId()+",");
//		   System.out.println("----------");
//		   }	
//		}
	
}

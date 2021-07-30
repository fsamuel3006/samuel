package com.article_type.model;

import java.util.List;
import java.util.Set;

import com.article.model.ArtVO;



//ArttVO  �峹����
public class ArttService {
	
    private ArttDAO_interface dao;
	
	public ArttService() {
		dao = new ArttJDBCDAO();
	}
	
	//�s�W
//	public ArttVO addArtt(String name) {
//		
//		ArttVO arttVO = new ArttVO();
//		
//		arttVO.setName(name);
//		dao.insert(arttVO);
//		
//		return arttVO;
//	}
	
	//�ק�
//	public ArttVO updateArtt(Integer id,String name) {
//		
//		ArttVO arttVO = new ArttVO();
//		
//		arttVO.setId(id);
//		arttVO.setName(name);
//		dao.insert(arttVO);
//		
//		return arttVO;
//	}
	
	//�d�ߥ���
	public List<ArttVO> getAll() {
		return dao.getAll();
	}
	
	//�d�߳浧
		public ArttVO getOneByID(Integer id) {
			return dao.findByPrimaryKey(id);
		}
		
	//�d�߬Y�峹���������Ѥ峹(�@��h)
		public Set<ArtVO> getArtByArtt(Integer artt_id){
			return dao.getArtByArtt(artt_id);
		}
	
	//�R��
		public void deleteArtt(Integer id) {
			dao.delete(id);
		}
	
}

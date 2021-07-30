package com.article.model;

import java.sql.Date;
import java.util.List;

//���Ѥ峹    article
public class ArtService {
	
	  private ArtDAO_interface dao;
		
		public ArtService() {
			dao = new ArtJDBCDAO();
		}
		
		//�s�W
		public ArtVO addArt(Integer artt_id , String  name , byte[] pic ,String contnt,
				       Date update ) {
			
			ArtVO artVO = new ArtVO();
			
			artVO.setArtt_id(artt_id);
			artVO.setName(name);
			artVO.setPic(pic);
			artVO.setContnt(contnt);
			artVO.setUpdate(update);
			dao.insert(artVO);
			
			return artVO;
		}
		
	//�ק�
		public ArtVO updateArt(Integer id,Integer artt_id , String  name , byte[] pic ,
				String contnt,Date update ) {
			
			ArtVO artVO = new ArtVO();
			artVO.setId(id);
			artVO.setArtt_id(artt_id);
			artVO.setName(name);
			artVO.setPic(pic);
			artVO.setContnt(contnt);
			artVO.setUpdate(update);
			
			dao.update(artVO);
			
			return artVO;
		}
		
	//�R��
		public void deleteArt(Integer id) {
			dao.delete(id);
		}
		
	//�d�ߤ@�����
		public ArtVO getOneByID(Integer id) {
			return dao.findByPrimaryKey(id);
		}
		
	//�d�ߥ���
		public List<ArtVO> getAll() {
			return dao.getAll();
		}

}

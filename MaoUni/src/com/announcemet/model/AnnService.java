package com.announcemet.model;

import java.util.List;

//���i  ANNOUNCEMET
public class AnnService {

	private AnnDAO_interface dao;
	
	public AnnService() {
		dao = new AnnJDBCDAO();
	}
	
	//�s�W
	public AnnVO addAnn( String content,  java.sql.Date update , byte[] pic ) {
		
		AnnVO annVO = new AnnVO();
		
		annVO.setContent(content);
		annVO.setUpdate(update);
		annVO.setPic(pic);
		dao.insert(annVO);
		
		return annVO;
	}
	
	//�ק�
	public AnnVO updateAnn( Integer id, String content, java.sql.Date update, byte[] pic ) {
		
		AnnVO annVO = new AnnVO();
		
		annVO.setId(id);
		annVO.setContent(content);
		annVO.setUpdate(update);
		annVO.setPic(pic);
		dao.update(annVO);
		
		return annVO;
	}
	
	//�R��
	public void deleteAnn(Integer id) {
		dao.delete(id);
	}
	//�d�߳浧
	public AnnVO getOneAnn(Integer id) {
		return dao.findByPrimaryKey(id);
	}
	//�d�ߥ���
	public List<AnnVO> getAll() {
		return dao.getAll();
	}

	
}

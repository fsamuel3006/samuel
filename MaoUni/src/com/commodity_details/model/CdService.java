package com.commodity_details.model;

import java.util.List;

public class CdService {

	private CdDAO_interface dao;
	public CdService() {
		dao = new CdJDBCDAO();
	}
	public void insert(Integer cdoId,Integer cdItemId,Integer cdAmount, Integer cdMoney) {

		CdVO cdVO = new CdVO();
		cdVO.setCdoId(cdoId);
		cdVO.setCdItemId(cdItemId);
		cdVO.setCdAmount(cdAmount);
		cdVO.setCdMoney(cdMoney);
		
		dao.insert(cdVO);
	}
	
	//商品訂單與明細同時新增↓↓
	public void insert2(Integer cdoId,Integer cdItemId,Integer cdAmount, Integer cdMoney) {

		CdVO cdVO = new CdVO();
		cdVO.setCdoId(cdoId);
		cdVO.setCdItemId(cdItemId);
		cdVO.setCdAmount(cdAmount);
		cdVO.setCdMoney(cdMoney);
		
		dao.insert(cdVO);
	}	
	
	public CdVO update(Integer cdoId,Integer cdItemId,Integer cdAmount, Integer cdMoney) {

		CdVO cdVO = new CdVO();
		cdVO.setCdoId(cdoId);
		cdVO.setCdItemId(cdItemId);
		cdVO.setCdAmount(cdAmount);
		cdVO.setCdMoney(cdMoney);
		
		return cdVO;
	}
	public void delete(Integer CdOId) {
		dao.delete(CdOId);
	}
	public CdVO findByPrimaryKey(Integer CdOId) {
		return dao.findByPrimaryKey(CdOId);
	}
	public List<CdVO> getAll() {
		return dao.getAll();
	}
	

	
}

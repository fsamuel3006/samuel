package com.co_complain.model;

import java.util.List;

public class CoService {

	private CoDAO_interface dao;
	public CoService() {
		dao = new CoJDBCDAO();
	}
	public void insert(Integer complainMemId,Integer complainMgId, Integer complainOId, String complainQa, Integer complainDate, Integer complainOut, String complainQac) {
	
	CoVO coVO =new CoVO();	
		
	coVO.setComplainMemId(complainMemId);
	coVO.setComplainMgId(complainMgId);
	coVO.setComplainOId(complainOId);
	coVO.setComplainQa(complainQa);
	coVO.setComplainDate(complainDate);
	coVO.setComplainOut(complainOut);
	coVO.setComplainQac(complainQac);	
		
		dao.insert(coVO);
	}
	public void update(Integer complainMemId,Integer complainMgId, Integer complainOId, String complainQa, Integer complainDate, Integer complainOut, String complainQac) {

		CoVO coVO =new CoVO();	
		
		coVO.setComplainMemId(complainMemId);
		coVO.setComplainMgId(complainMgId);
		coVO.setComplainOId(complainOId);
		coVO.setComplainQa(complainQa);
		coVO.setComplainDate(complainDate);
		coVO.setComplainOut(complainOut);
		coVO.setComplainQac(complainQac);	
		dao.update(coVO);
	}
	public void delete(Integer deptno) {
		dao.delete(deptno);
	}
	public CoVO findByPrimaryKey(Integer ComplainId) {
		return dao.findByPrimaryKey(ComplainId);
	}
	public List<CoVO> getAll() {
		return dao.getAll();
	}
	

	
}

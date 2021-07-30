package com.variety.model;

import java.util.List;

public class VarietyService {

	private VarietyDAO_interface dao;
	public VarietyService() {
		dao = new VarietyJDBCDAO();
	} 


	public VarietyVO update(String varName, String varKind) {
		VarietyVO varietyVO = new VarietyVO();
		
		varietyVO.setVarName(varName);
		varietyVO.setVarKind(varKind);
		return varietyVO;
		
	}
	
	public void delete(Integer VarId) {
	
		dao.delete(VarId);
	}
		
	public VarietyVO findByPrimaryKey(Integer varId) {
	 return dao.findByPrimaryKey(varId);
	}
	
	public List<VarietyVO> getAll() {
		return dao.getAll();
	}


	public VarietyVO getOnevarity(Integer varId) {
		return null;
	}
	
	public VarietyVO INSERT_STMT(String varName, String varKind) {
		VarietyVO varietyVO = new VarietyVO();
		
		varietyVO.setVarName(varName);
		varietyVO.setVarKind(varKind);
		return varietyVO;
	}
}

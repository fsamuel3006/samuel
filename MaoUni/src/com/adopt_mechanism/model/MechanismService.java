package com.adopt_mechanism.model;

import java.util.List;

import com.adopt_imf.model.ImfVO;

public class MechanismService {

	private MechanismDAO_interface dao;
	
	public MechanismService() {
		dao = new MechanismJDBCDAO();
	}
	
	public MechanismVO addMechanism(Integer ID,String NAME,String ADDRESS,String TEL,String URL) {
		MechanismVO mechanismVO = new MechanismVO();
		mechanismVO.setId(ID);
		mechanismVO.setName(NAME);
		mechanismVO.setAddress(ADDRESS);
		mechanismVO.setTel(TEL);
		mechanismVO.setUrl(URL);
		
		
		dao.insert(mechanismVO);
		
		return mechanismVO;
	}
	public MechanismVO updateMechanism(Integer id,String name,String address,String tel,String url) {
		MechanismVO mechanismVO = new MechanismVO();
		mechanismVO.setId(id);
		mechanismVO.setName(name);
		mechanismVO.setAddress(address);
		mechanismVO.setTel(tel);
		mechanismVO.setUrl(url);
		
		dao.update(mechanismVO);
		return mechanismVO;
		
	}
	public void deleteMechanism(Integer id) {
		dao.delete(id);
	}
	public MechanismVO getOneMechanism(Integer id) {
		return dao.findByPrimaryKey(id);
	}
	public List<MechanismVO>getAll(){
		return dao.getAll();
	}
	
	
}

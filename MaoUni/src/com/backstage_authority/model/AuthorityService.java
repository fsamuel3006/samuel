package com.backstage_authority.model;

import java.util.List;
import java.util.Set;

import com.adopt_mechanism.model.MechanismVO;
import com.backstage_staff.model.StaffVO;

public class AuthorityService {

private AuthorityJDBCAO dao;
	
	public AuthorityService() {
		dao = new AuthorityJDBCAO();
	}
	public AuthorityVO addAuthority(Integer id,Integer fun) {
		AuthorityVO authorityVO = new AuthorityVO();
		authorityVO.setId(id);
		authorityVO.setFun(fun);
				
		dao.insert(authorityVO);
		
		return authorityVO;
	}
	public AuthorityVO updateAuthority(Integer id,Integer fun) {
		AuthorityVO authorityVO = new AuthorityVO();
		authorityVO.setId(id);
		authorityVO.setFun(fun);
		
		dao.update(authorityVO);
		
		return authorityVO;
	}
	
	public AuthorityVO findByPrimaryKey(Integer id) {
		return dao.findByPrimaryKey(id);
	}
	public List<AuthorityVO>getAll(){
		return dao.getAll();
	}
	
//	public Set<StaffVO> getStaffbyAI(Integer BA_ID){
//	
//	}
	
	
	
	
	
	
	
}

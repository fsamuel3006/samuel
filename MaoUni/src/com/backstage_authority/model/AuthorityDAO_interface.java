package com.backstage_authority.model;
import java.util.*;

import com.backstage_function.model.FunctionVO;
import com.backstage_staff.model.StaffVO;

public interface AuthorityDAO_interface {
	
	public void insert(AuthorityVO authorityVO);
	public void update(AuthorityVO authorityVO); 
	public AuthorityVO findByPrimaryKey(Integer BA_ID);
	public List<AuthorityVO> getAll();
	
	//查詢一對多關係(回傳set)
	public List<AuthorityJoinVO> getAllbyJoin();
	
	public Set<StaffVO> get_StaffS_byAI_STMT(Integer id);
	public Set<FunctionVO> get_Functions_byId_STMT(Integer id);
	
}

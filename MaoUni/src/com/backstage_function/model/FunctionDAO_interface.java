package com.backstage_function.model;
import java.util.*;

import com.backstage_authority.model.AuthorityVO;
public interface FunctionDAO_interface {
	public void insert(FunctionVO functionVO);
	public void update(FunctionVO functionVO); 
	public void delete(Integer id);
	public FunctionVO findByPrimaryKey(Integer id);
	public List<FunctionVO> getAll();
	
	public Set<AuthorityVO> getAuthorityBYFunction(Integer BF_ID);
	
	//
}

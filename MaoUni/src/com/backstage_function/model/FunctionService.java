package com.backstage_function.model;

import java.util.List;


public class FunctionService {

	private FunctionDAO_interface dao;
	
	public FunctionService() {
		dao = new FunctionJDBCDAO();
	}
	
	public FunctionVO addFunction(Integer id,String name) {
		FunctionVO functionVO = new FunctionVO();
		functionVO.setId(id);
		functionVO.setName(name);
				
		dao.insert(functionVO);
		
		return functionVO;
	}
	
	public FunctionVO updateFunction(Integer id,String name)  {
		FunctionVO functionVO = new FunctionVO();
		functionVO.setId(id);
		functionVO.setName(name);
		
		dao.update(functionVO);
		
		return functionVO;
	}
	public void deleteFunction(Integer id) {
		dao.delete(id);
	}
	public FunctionVO getOneFunction(Integer id) {
		return dao.findByPrimaryKey(id);
	}
	public List<FunctionVO> getAll(){
		return dao.getAll();
	}
	
}

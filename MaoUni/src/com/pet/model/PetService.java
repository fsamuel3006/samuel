package com.pet.model;

import java.util.List;

public class PetService {

	private PetDAO_interface dao;
	public PetService() {
		dao= new PetJDBCDAO();
	}
	public void insert(Integer petMemId,String petName,String petSort,Integer petVarId,String petGender,Integer petAge,Integer petSurvive) {

		PetVO petVO =new PetVO();

		petVO.setPetMemId(petMemId);
		petVO.setPetName(petName);
		petVO.setPetSort(petSort);
		petVO.setPetVarId(petVarId);
		petVO.setPetGender(petGender);
		petVO.setPetAge(petAge);
		petVO.setPetSurvive(petSurvive);
		
		dao.insert(petVO);
		
	}
	public void update(PetVO petVO) {
		
		dao.update(petVO);
	}
	public void delete(Integer PetId) {
		dao.delete(PetId);
	}
	public PetVO findByPrimaryKey(Integer PetId) {
		return dao.findByPrimaryKey(PetId);
	}
	public List<PetVO> getAll() {
		return dao.getAll();
	}

	
}

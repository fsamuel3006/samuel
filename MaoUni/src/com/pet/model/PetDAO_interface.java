package com.pet.model;

import java.util.*;

public interface PetDAO_interface {

	public void insert(PetVO petVO);
    public void update(PetVO petVO);
    public void delete(Integer PetId);
    public PetVO findByPrimaryKey(Integer PetId);
    public List<PetVO> getAll();
	
}

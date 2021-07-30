package com.adopt_mechanism.model;

import java.util.*;

public interface MechanismDAO_interface {
	public void insert(MechanismVO MechanismVO);
	public void update(MechanismVO MechanismVO); 
	public void delete(Integer ID);
	public MechanismVO findByPrimaryKey(Integer ID);
	public List<MechanismVO> getAll();
}

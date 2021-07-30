package com.adopt_imf.model;

import java.util.*;

import com.adopt_mechanism.model.model.MechanismVO;
import com.variety.model.VarietyVO;

public interface ImfDAO_interface {
	public void insert (ImfVO imfVO);
	public void update(ImfVO imfVO); 
	public void delete(Integer id);
	public ImfVO findByPrimaryKey(Integer id);
	public List<ImfVO> getAll();
	
	
	public Set<MechanismVO> getMechanismsByid(Integer id);
	public Set<VarietyVO> getVaritybyid(Integer id);
	
	
	public byte[] getPictureByteArray(String path);
}



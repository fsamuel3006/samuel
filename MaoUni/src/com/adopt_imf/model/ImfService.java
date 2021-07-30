package com.adopt_imf.model;

import java.io.IOException;
import java.util.List;

import com.adopt_mechanism.model.model.MechanismDAO_interface;
import com.adopt_mechanism.model.model.MechanismVO;

public class ImfService {

	private ImfDAO_interface dao;
	
	
	
	public ImfService() {
		dao = new ImfJDBCDAO();
		
	}
	public ImfVO addImf(Integer id,Integer adopt,Integer mech,String name,Integer year,String sit,String Photo) throws IOException{
		ImfVO imfVO = new ImfVO();
		byte[] pic = dao.getPictureByteArray(Photo);
		imfVO.setId(id);
		imfVO.setAdopt(adopt);
		imfVO.setMech(mech);
		imfVO.setName(name);
		imfVO.setYear(year);
		imfVO.setSit(sit);	
		imfVO.setPhoto(pic);
		
		dao.insert(imfVO);
		
		return imfVO;
	}
	public ImfVO updateImf(Integer id,Integer adopt,Integer mech,String name,Integer year,String sit,String Photo) {
		ImfVO imfVO = new ImfVO();
		byte[] pic = dao.getPictureByteArray(Photo);
		imfVO.setId(id);
		imfVO.setAdopt(adopt);
		imfVO.setMech(mech);
		imfVO.setName(name);
		imfVO.setYear(year);
		imfVO.setSit(sit);	
		imfVO.setPhoto(pic);
		
		dao.update(imfVO);
		return imfVO;
	}
	public void deleteImf(Integer id) {
		dao.delete(id);
	}
	public ImfVO getOneImf(Integer id) {
		return dao.findByPrimaryKey(id);
	}
	public List<ImfVO>getAll(){
		return dao.getAll();
	}

}


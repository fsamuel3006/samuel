package com.tracking_adopt.model;


import java.util.*;

import com.adopt_imf.model.ImfVO;




public interface TrackingDAO_interface {
	public Integer insert (TrackingVO trackingVO);
	public void update(TrackingVO trackingVO); 
	public void delete(Integer Id);
	public TrackingVO findByPrimaryKey(Integer Id);
	public List<TrackingVO> getAll();
	
	public Set<MemberVO> getMemberByid(Integer id);
	public Set<ImfVO> getImfVObyid(Integer id);
	
	
	
}

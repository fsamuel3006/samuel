package com.message.model;

import java.util.List;
import java.util.Set;



public interface MessageDAO_interface {
	public void insert(MessageVO messageVO);
	public void update(MessageVO messageVO); 
	public void delete(Integer id);
	public MessageVO findByPrimaryKey(Integer id);
	public List<MessageVO> getAll();
	
	public Set<memberVO> get_Member_byMessage(Integer memId);
//	public Set<COVO> get_COMPLAIN_byMessage(Integer MEM_ID);
}

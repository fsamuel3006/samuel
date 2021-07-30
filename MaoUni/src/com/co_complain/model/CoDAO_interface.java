package com.co_complain.model;

import java.util.*;

//客服訂單處理介面

public interface CoDAO_interface {

    public void insert(CoVO coVO);
    public void update(CoVO  coVO);
    public void delete(Integer deptno);
    public CoVO findByPrimaryKey(Integer ComplainId);
    public List<CoVO> getAll();
    
    //查詢(一對多)(回傳 Set)
//    public Set<CoVO> getEmpsByDeptno(Integer ComplainId);
	
}

package com.commodity_details.model;

import java.sql.Connection;
import java.util.*;

public interface CdDAO_interface {

    public void insert(CdVO cdVO);
    public void update(CdVO cdVO);
    public void delete(Integer CdOId);
    public CdVO findByPrimaryKey(Integer CdOId);
    
    public List<CdVO> getAll();
    
//商品訂單與明細同時新增↓↓
	void insert2(CdVO CdVO, Connection con);
		
}
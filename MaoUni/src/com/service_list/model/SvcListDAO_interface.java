package com.service_list.model;
import java.util.*;


public interface SvcListDAO_interface {
    public void insert(SvcListVO svcListVO);
    public List<SvcListVO> getAll(Integer groomerId);				// 取得某位美容師的服務項目
    public SvcListVO getItem(Integer groomerId, Integer svcId);		// 取得某位美容師某項服務
    
//    public void update(SvcListVO svcListVO);
//    public void delete(Integer svcId); 
}

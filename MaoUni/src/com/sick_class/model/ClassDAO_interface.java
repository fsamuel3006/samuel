package com.sick_class.model;

import java.util.*;

public interface ClassDAO_interface {

	  public Integer insert(String scPain);
      public List<ClassVO> getAll();
      
//      public Integer update(String scPain);	// 直接供其他table join取得，沒有需要單一取得名稱的情境 (by Esther)
//      public void delete(Integer ScId);      // 因為是FK，不可delete (by Esther)
//      public ClassVO findByPrimaryKey(Integer ScId);	// 直接供其他table join取得，沒有需要單一取得名稱的情境 (by Esther)
	
}

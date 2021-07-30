package com.sick_list.model;

import java.util.*;

public interface ListDAO_interface {

	  public void insert(ListVO listVO);
      public void update(ListVO listVO);
      public ListVO findByPrimaryKey(Integer slScId);
      public List<ListVO> getAll();

}

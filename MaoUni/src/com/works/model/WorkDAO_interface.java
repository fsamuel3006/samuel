package com.works.model;
import java.util.*;

public interface WorkDAO_interface {
    public void insert(WorkVO workVO);
    public void delete(Integer wid);
    public List<WorkVO> getOneList(Integer groomerId);  // 取得某位美容師的作品
    
//    public void update(WorkVO workVO);
//    public List<WorkVO> getAll(); 
}

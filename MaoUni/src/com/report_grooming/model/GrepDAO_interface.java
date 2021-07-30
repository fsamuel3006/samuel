package com.report_grooming.model;
import java.util.*;


public interface GrepDAO_interface {
    public Integer insert(GrepVO grepVO);
    public String update(GrepVO grepVO);
    public List<GrepVO> getAll();						// undone
//    public void delete(Integer grep_id);
//    public List<GrepVO> findByGroomer(Integer memId); 
}

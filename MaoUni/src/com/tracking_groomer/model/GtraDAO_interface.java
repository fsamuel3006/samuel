package com.tracking_groomer.model;
import java.util.*;

public interface GtraDAO_interface {
    public void insert(GtraVO gtraVO);
    public void delete(GtraVO gtraVO); 
    public List<Integer> findById(Integer id); // 取得id的陣列，再分別用GroDAO的方法取得GroVO list、MemDAO方法取得MemVO -- undone
//    public List<GtraVO> getAll(); 
}

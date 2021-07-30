package com.service_items.model;
import java.util.*;


public interface SvcDAO_interface {
    public void insert(SvcVO svcVO);
    public void update(SvcVO svcVO);	// 如果打錯可以更改項目名稱和類別 ? -- not done
    public List<SvcVO> findByPet(String pet);	// 取得寵物類別內的服務項目
    public List<SvcVO> getAll();
    
//  public void delete(Integer seri_id);
}

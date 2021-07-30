package com.item.model;

import java.util.*;

public interface ItemDAO_interface {
    public void insert(ItemVO itemVO); //新增
    public void update(ItemVO itemVO); //修改
    public ItemVO findByPrimaryKey(Integer itemId); //以itemId(商品ID)查詢
    public ItemVO findByPrimaryKey(String itemName); //以itemName(商品名稱)查詢
    public List<ItemVO> getAll(); //全部列出
    public List<ItemVO> getAll(Map<String, String[]> map); //複合查詢

}

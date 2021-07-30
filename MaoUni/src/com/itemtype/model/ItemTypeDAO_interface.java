package com.itemtype.model;

import java.util.*;

import com.item.model.ItemVO;

public interface ItemTypeDAO_interface {
	public void insert(ItemTypeVO itemTypeVO); //新增
    public void update(ItemTypeVO itemTypeVO); //修改
    public void delete(Integer itemtId); //刪除
    public ItemTypeVO findByPrimaryKey(Integer itemtId); //以itemtId(商品類別ID)查詢
    public List<ItemTypeVO> getAll(); //全部列出
    public Set<ItemVO> getItemsByItemtId(Integer itemtId); //以ItemtId(商品類別ID)取得商品
}

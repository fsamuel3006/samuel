package com.trackingitem.model;

import java.util.*;

import com.item.model.ItemVO;

public interface TrackingItemDAO_interface {
	public void insert(TrackingItemVO trackingItemVO); //新增
	public void delete(Integer itraMemId, Integer itraItemId); //刪除
	public TrackingItemVO findByPrimaryKey(Integer itraItemId); //以itraItemId(商品ID)查詢
	public List<TrackingItemVO> getAll(); //全部列出
}

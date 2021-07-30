package com.trackingpost.model;

import java.util.*;

public interface TrackingPostDAO_interface {
	public void insert(TrackingPostVO trackingPostVO); //新增
    public void delete(Integer ptraPostId, Integer ptraMemId); //刪除
    public TrackingPostVO findByPrimaryKey(Integer ptraPostId); //以ptraPostId(貼文ID)查詢
    public List<TrackingPostVO> getAll(); //全部列出
}

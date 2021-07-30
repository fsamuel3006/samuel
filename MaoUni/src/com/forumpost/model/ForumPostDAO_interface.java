package com.forumpost.model;

import java.util.*;

public interface ForumPostDAO_interface {
	public void insert(ForumPostVO forumPostVO); //新增
    public void update(ForumPostVO forumPostVO); //修改
    public void delete(Integer fpId); //刪除
    public ForumPostVO findByPrimaryKey(Integer fpId); //以FpId(貼文ID)查詢
    public List<ForumPostVO> getAll();	//全部列出
    public List<ForumPostVO> getAll(Map<String, String[]> map); //萬用複合查詢
}

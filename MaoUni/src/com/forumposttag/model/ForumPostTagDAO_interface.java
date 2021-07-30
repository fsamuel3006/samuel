package com.forumposttag.model;

import java.util.*;

public interface ForumPostTagDAO_interface {
	public void insert(ForumPostTagVO forumPostTagVO); //新增
    public void update(ForumPostTagVO forumPostTagVO); //修改
    public void delete(Integer fptId); //刪除
    public ForumPostTagVO findByPrimaryKey(Integer fptId); //以fptId(標籤ID)查詢
    public List<ForumPostTagVO> getAll(); //全部列出
}

package com.forumposttagedetails.model;

import java.util.*;

public interface ForumPostTagDetailsDAO_interface {
	public void insert(ForumPostTagDetailsVO forumPostTagDetailsVO); //新增
    public void delete(Integer fptdTagId, Integer fptdPostId); //刪除
    public ForumPostTagDetailsVO findByPrimaryKey(Integer fptdTagId); //以fptTagId(標籤ID)查詢
    public List<ForumPostTagDetailsVO> getAll(); //全部列出
}

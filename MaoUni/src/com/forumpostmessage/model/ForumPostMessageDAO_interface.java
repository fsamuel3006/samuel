package com.forumpostmessage.model;

import java.util.*;

public interface ForumPostMessageDAO_interface {
	public void insert(ForumPostMessageVO forumPostMessageVO); //新增
    public void update(ForumPostMessageVO forumPostMessageVO); //修改
    public void delete(Integer fpmId); //刪除
    public ForumPostMessageVO findByPrimaryKey(Integer fpmId); //以貼文留言ID查詢
    public List<ForumPostMessageVO> getAll(); //全部列出
}

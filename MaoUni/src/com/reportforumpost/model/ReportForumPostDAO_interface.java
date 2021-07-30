package com.reportforumpost.model;

import java.util.*;

public interface ReportForumPostDAO_interface {
	public void insert(ReportForumPostVO reportForumPostVO); //新增
    public void update(ReportForumPostVO reportForumPostVO); //修改
    public void delete(Integer prepId); //刪除
    public List<ReportForumPostVO> getAll();  //全部列出
}

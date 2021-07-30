package com.itemphotos.model;

import java.util.*;

public interface ItemPhotosDAO_interface {
    public void insert(ItemPhotosVO itemPhotosVO); //新增
    public void update(ItemPhotosVO itemPhotosVO); //修改
    public void delete(Integer ipId); //刪除
    public ItemPhotosVO findByPrimaryKey(Integer ipId); //以ipId搜尋
    public List<ItemPhotosVO> getAll();	//全部列出
    public List<ItemPhotosVO> getItemPhoto(Integer ipItemId); //以ipItemId取得圖片
    public byte[] insertItemPhoto(String path); //新增圖片
	public byte[] updateItemPhoto(String ipItem); //修改圖片
	public byte[] getOnePic(Integer ipId);
}

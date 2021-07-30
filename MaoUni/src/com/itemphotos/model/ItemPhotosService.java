package com.itemphotos.model;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class ItemPhotosService {

	private ItemPhotosDAO_interface dao;

	public ItemPhotosService() {
		dao = new ItemPhotosDAO();
	}

	public ItemPhotosVO addItemPhotos(Integer ipItemId, String ipItem, java.sql.Date ipUpdate) throws IOException {

		ItemPhotosVO itemPhotosVO = new ItemPhotosVO();
		byte[]pic = dao.insertItemPhoto(ipItem);
		itemPhotosVO.setIpItemId(ipItemId);
		itemPhotosVO.setIpItem(pic); 
		itemPhotosVO.setIpUpdate(ipUpdate);
		dao.insert(itemPhotosVO);

		return itemPhotosVO;
	}

	public ItemPhotosVO updateItemPhotos(Integer ipId, Integer ipItemId, String ipItem, java.sql.Date ipUpdate) throws IOException {

		ItemPhotosVO itemPhotosVO = new ItemPhotosVO();
		byte[]pic = dao.updateItemPhoto(ipItem);
		itemPhotosVO.setIpItemId(ipItemId);
		itemPhotosVO.setIpItem(pic);	
		itemPhotosVO.setIpUpdate(ipUpdate);	
		itemPhotosVO.setIpId(ipId);
		dao.update(itemPhotosVO);

		return itemPhotosVO;
	}

	public void deleteItemType(Integer ipId) {
		dao.delete(ipId);
	}

	public ItemPhotosVO getOneItemPhotos(Integer ipId) {
		return dao.findByPrimaryKey(ipId);
	}

	public List<ItemPhotosVO> getAll() {
		return dao.getAll();
	}
	
	
	public String getOneItemPic(Integer ipId) {
		byte[] img = dao.getOnePic(ipId);
		String data = Base64.getEncoder().encodeToString(img);
		System.out.println(data);
		return data;
		
	}
	
	
	
	
	
	

	public static void main(String [] args) {
		ItemPhotosService ivc = new ItemPhotosService();
		try {
			//新增圖片
			ivc.addItemPhotos(8, "InsertPic/8.jpg", java.sql.Date.valueOf("2021-07-07"));
			//更新圖片
//			ivc.updateItemPhotos(8, 2, "test/1.jpg", java.sql.Date.valueOf("2021-07-07"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("OK");
	}
	
	
	
//----------------------------TestMainMethod----------------------------		
//public static void main(String [] args) throws ClassNotFoundException, SQLException {	
//		  ItemPhotosDAO dao = new ItemPhotosDAO();
	  
	  
// ----------------------------insert----------------------------
//			ItemPhotosVO itemPhotosVO = new ItemPhotosVO();
//			itemPhotosVO.setIpItemId(1);
//			itemPhotosVO.setIpItem(null); 
//			itemPhotosVO.setIpUpdate(java.sql.Date.valueOf("2021-02-01"));
//			dao.insert(itemPhotosVO);

		
// ----------------------------delete----------------------------	  
//			dao.delete(7);
//			System.out.println("OK");
	  
	
// ----------------------------Update----------------------------
//			ItemPhotosVO itemVO2 = new ItemPhotosVO();
//			itemVO2.setIpId(1);
//			itemVO2.setIpItemId(1);
//			itemVO2.setIpItem(null);	
//			itemVO2.setIpUpdate(java.sql.Date.valueOf("2021-02-01"));	
//			dao.update(itemVO2);
//			System.out.println("OK");	  
	
	
// ----------------------------全部列出----------------------------	  
//		  List<ItemTypeVO> list = dao.getAll();
//		  for(ItemTypeVO itemTypeVO : list) {
//			  System.out.println(itemTypeVO.getItemtId()+",");
//			  System.out.println(itemTypeVO.getItemtName()+",");
//		  }
//		}
	
}

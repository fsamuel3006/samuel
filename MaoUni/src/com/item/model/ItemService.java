package com.item.model;

import java.util.List;
import java.util.Map;

public class ItemService {

	private ItemDAO_interface dao;

	public ItemService() {
		dao = new ItemDAO();
	}

	public ItemVO addItem(Integer itemTypeId, String itemPetType, String itemName, String itemContent,
			Integer itemPrice, Integer itemAmount) {

		ItemVO itemVO = new ItemVO();

		itemVO.setItemTypeId(itemTypeId);
		itemVO.setItemPetType(itemPetType);
		itemVO.setItemName(itemName);
		itemVO.setItemContent(itemContent);
		itemVO.setItemPrice(itemPrice);
		itemVO.setItemAmount(itemAmount);
		dao.insert(itemVO);

		return itemVO;
	}

	public ItemVO updateItem(Integer itemId, Integer itemTypeId, String itemPetType, String itemName, String itemContent,
			Integer itemPrice, Integer itemAmount, Integer itemStatus) {

		ItemVO itemVO = new ItemVO();
		
		
		itemVO.setItemTypeId(itemTypeId);
		itemVO.setItemPetType(itemPetType);
		itemVO.setItemName(itemName);
		itemVO.setItemContent(itemContent);
		itemVO.setItemPrice(itemPrice);
		itemVO.setItemAmount(itemAmount);
		itemVO.setItemStatus(itemStatus);
		itemVO.setItemId(itemId);
		dao.update(itemVO);

		return itemVO;
	}

	public ItemVO getOneItem(Integer itemId) {
		return dao.findByPrimaryKey(itemId);
	}

	public ItemVO getOneItem(String itemName) {
		return dao.findByPrimaryKey(itemName);
	}
	
	public List<ItemVO> getAll() {
		return dao.getAll();
	}
	
	public List<ItemVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	
	
//----------------------------TestMainMethod----------------------------
//public static void main(String [] args) throws ClassNotFoundException, SQLException {
//		  ItemDAO dao = new ItemDAO();

	  
// ----------------------------測試insert----------------------------
//			ItemVO itemVO = new ItemVO();
//			itemVO.setItemTypeId(1);
//			itemVO.setItemPetType("貓");
//			itemVO.setItemName("測試商品1");
//			itemVO.setItemContent("測試商品內容1");
//			itemVO.setItemPrice(777);
//			itemVO.setItemAmount(77);
//			dao.insert(itemVO);
//			System.out.println("OK");

	  
// ----------------------------測試Update----------------------------
//			ItemVO itemVO2 = new ItemVO();
//			itemVO2.setItemId(1);
//			itemVO2.setItemTypeId(1);
//			itemVO2.setItemPetType("狗");
//			itemVO2.setItemName("精選貓糧");
//			itemVO2.setItemContent("這是改過的測試");
//			itemVO2.setItemPrice(999);
//			itemVO2.setItemAmount(50);
//			itemVO2.setItemStatus(1);
//			dao.update(itemVO2);
//			System.out.println("OK");
	  

		
// ----------------------------測試getAll----------------------------	  
//		  List<ItemVO> list = dao.getAll();
//		  for(ItemVO aitem : list) {
//		   System.out.println(aitem.getItemId()+",");
//		   System.out.println(aitem.getItemTypeId()+",");
//		   System.out.println(aitem.getItemPetType()+",");
//		   System.out.println(aitem.getItemName()+",");
//		   System.out.println(aitem.getItemContent()+",");
//		   System.out.println(aitem.getItemPrice()+",");
//		   System.out.println(aitem.getItemAmount()+",");
//		   System.out.println(aitem.getItemStatus()+",");
//		   System.out.println(aitem.getItemUpdate()+",");
//		   System.out.println("-------------------------------------");
//		  }
//		}
	
}

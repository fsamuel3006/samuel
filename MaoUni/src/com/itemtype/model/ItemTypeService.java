package com.itemtype.model;

import java.util.List;
import java.util.Set;

import com.item.model.ItemVO;

public class ItemTypeService {

	private ItemTypeDAO_interface dao;

	public ItemTypeService() {
		dao = new ItemTypeDAO();
	}

	public ItemTypeVO addItemType(String itemtName) {

		ItemTypeVO itemTypeVO = new ItemTypeVO();
		itemTypeVO.setItemtName(itemtName);
		dao.insert(itemTypeVO);


		return itemTypeVO;
	}

	public ItemTypeVO updateItemType(Integer itemtId, String itemtName) {

		ItemTypeVO itemTypeVO = new ItemTypeVO();

		itemTypeVO.setItemtId(itemtId);
		itemTypeVO.setItemtName(itemtName);
		dao.insert(itemTypeVO);

		return itemTypeVO;
	}

	public void deleteItemType(Integer itemtId) {
		dao.delete(itemtId);
	}

	public ItemTypeVO getOneItemType(Integer itemtId) {
		return dao.findByPrimaryKey(itemtId);
	}

	public List<ItemTypeVO> getAll() {
		return dao.getAll();
	}
	
	public Set<ItemVO> getItemsByItemtId(Integer itemtId) {
		return dao.getItemsByItemtId(itemtId);
	}
	
//----------------------------TestMainMethod----------------------------
//public static void main(String [] args) throws ClassNotFoundException, SQLException {
//		  ItemTypeJDBCDAO dao = new ItemTypeJDBCDAO();
	  
	  
// ----------------------------測試insert----------------------------
//			ItemTypeVO itemTypeVO = new ItemTypeVO();
//			itemTypeVO.setItemtName("測試商品類別");
//			dao.insert(itemTypeVO);
//			System.out.println("OK");
	  
// ----------------------------測試delete----------------------------	  
//			dao.delete(7);
//			System.out.println("OK");
	  
// ----------------------------測試Update----------------------------
//			ItemTypeVO itemVO2 = new ItemTypeVO();
//			itemVO2.setItemtId(7);
//			itemVO2.setItemtName("測試修改類別名稱");
//			dao.update(itemVO2);
//			System.out.println("OK");	  
	  
// ----------------------------測試查詢商品類別----------------------------	  
//		  List<ItemTypeVO> list = dao.getAll();
//		  for(ItemTypeVO itemTypeVO : list) {
//			  System.out.println(itemTypeVO.getItemtId()+",");
//			  System.out.println(itemTypeVO.getItemtName()+",");
//		  }

// ----------------------------測試用商品類別查詢商品----------------------------	
//			Set<ItemVO> set = dao.getItemsByItemtId(1);
//			for (ItemVO item : set) {
//				System.out.print(item.getItemId());
//				System.out.print(item.getItemTypeId());
//				System.out.print(item.getItemName());
//				System.out.print(item.getItemContent());
//				System.out.print(item.getItemPrice());
//				System.out.print(item.getItemAmount());
//				System.out.print(item.getItemStatus());
//				System.out.print(item.getItemUpdate());
//				System.out.println();
//			}
//		}
	
}

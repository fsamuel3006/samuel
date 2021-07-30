package com.item.model;

import java.sql.Date;

public class ItemVO implements java.io.Serializable{
	private Integer itemId; //商品ID
	private Integer itemTypeId; //商品類別ID
	private String itemPetType;
	private String itemName; //商品名稱
	private String itemContent; //商品內容
	private Integer itemPrice; //商品價格
	private Integer itemAmount; //商品數量
	private Integer itemStatus; //商品狀態
	private Date itemUpdate; //上次修改狀態時間
	private String itemPhotoFirst;
	private String itemTypeName;
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getItemTypeId() {
		return itemTypeId;
	}
	public void setItemTypeId(Integer itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	public String getItemPetType() {
		return itemPetType;
	}
	public void setItemPetType(String itemPetType) {
		this.itemPetType = itemPetType;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemContent() {
		return itemContent;
	}
	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}
	public Integer getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}
	public Integer getItemAmount() {
		return itemAmount;
	}
	public void setItemAmount(Integer itemAmount) {
		this.itemAmount = itemAmount;
	}
	public Integer getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(Integer itemStatus) {
		this.itemStatus = itemStatus;
	}
	public Date getItemUpdate() {
		return itemUpdate;
	}
	public void setItemUpdate(Date itemUpdate) {
		this.itemUpdate = itemUpdate;
	}
	public String getItemPhotoFirst() {
		return itemPhotoFirst;
	}
	public void setItemPhotoFirst(String itemPhotoFirst) {
		this.itemPhotoFirst = itemPhotoFirst;
	}
	public String getItemTypeName() {
		return itemTypeName;
	}
	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	
	
	
	
	
}




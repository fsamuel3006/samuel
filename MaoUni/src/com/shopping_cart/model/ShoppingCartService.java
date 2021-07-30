package com.shopping_cart.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.item.model.ItemDAO;
import com.item.model.ItemDAO_interface;
import com.item.model.ItemVO;

import Redis.util.jedis.JedisHandleShoppingCart;

public class ShoppingCartService {
	private Gson gson = new Gson();
	private ItemDAO_interface itemDAO = new ItemDAO();
	
	
	public ShoppingCartService() {
		
	}
	
	// user cookie("cart", sessionId)
	// Redis List (sessionId, {"itemId": "xxx", "count": "x"})
	// 使用者進入首頁時，檢查是否有key為shoppingCart的cookie，有的話取得sessionId，沒有則用給予cookie  (可以做在filter，讓使用者不管進入哪個註冊到的頁面都先做此動作?)
	// 用sessionId自redis取得購物車內的資料：jedis.lrange(key, 0, -1)，用itemId比對mySQL取得商品價格、比對庫存
	
	public List<ShoppingCartItemVO> getCart(String sessionId){
		List cartItemsList = new ArrayList();
		
		// search cart from redis
		List<String> cartItems = JedisHandleShoppingCart.getCart(sessionId);	// {"itemId": "xxx", "count": "x"}
		for(int i = 0; i < cartItems.size(); i++) {
			ShoppingCartItemVO cartItemVO = gson.fromJson(cartItems.get(i), ShoppingCartItemVO.class);
			ItemVO itemVO = itemDAO.findByPrimaryKey(cartItemVO.getItemId());
			cartItemVO.setItemName(itemVO.getItemName());
			cartItemVO.setItemPrice(itemVO.getItemPrice());
			
			cartItemsList.add(cartItemVO);
		}
		return cartItemsList;	// 讓servlet取得後渲染於購物車頁面
	}
	
	public void addItem(String sessionId, Integer itemId, Integer count) {
		
		ShoppingCartItemVO cartItemVO = new ShoppingCartItemVO();
		cartItemVO.setItemId(itemId);
		cartItemVO.setCount(count);
		
		JedisHandleShoppingCart.addItem(sessionId, cartItemVO);
		
	}
	
	public void deleteItem(String sessionId, Integer itemId) {
		JedisHandleShoppingCart.deleteItem(sessionId, itemId);
	}

	public void deleteCart(String sessionId) {
		JedisHandleShoppingCart.deleteCart(sessionId);
	};
}

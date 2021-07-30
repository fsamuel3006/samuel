package Redis.util.jedis;

import java.util.List;

import com.google.gson.Gson;
import com.item.model.ItemDAO;
import com.item.model.ItemDAO_interface;
import com.item.model.ItemVO;
import com.shopping_cart.model.ShoppingCartItemVO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleShoppingCart {
	
	////////////////   需再做個EXPIREAT控制，COOKIE是設定3天
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	public static List<String> getCart(String sessionId){
		Jedis jedis = null;
		jedis = pool.getResource();
		List<String> cartItems = jedis.lrange(sessionId, 0, -1);
		jedis.close();
		return cartItems;
	}
	
	public static void addItem(String sessionId, ShoppingCartItemVO cartItemVO) {
		Gson gson = new Gson();
		
		Jedis jedis = null;
		jedis = pool.getResource();
		
		List<String> cartItems = getCart(sessionId);
		
		if(cartItems != null) {
			for(int i = 0; i < cartItems.size(); i++) {
				ShoppingCartItemVO itemVOtoAdd = gson.fromJson(cartItems.get(i), ShoppingCartItemVO.class);
				
				// 若購物車內已有該商品ID則增加數量
				
				Integer cartItemId = cartItemVO.getItemId();
				Integer itemVOtoAddId = itemVOtoAdd.getItemId();
				
//System.out.println("cartItemId: " + cartItemId);
//System.out.println("itemVOtoAddId: " + itemVOtoAddId);
System.out.println(cartItemId == itemVOtoAddId);  // got false ??????
				
				if( cartItemId.equals(itemVOtoAddId)) {
					Integer count = itemVOtoAdd.getCount();
					
					count += 1;									// 購物車內無調整數量功能，由使用者觸發「add」按鈕後+1
					itemVOtoAdd.setCount(count);
					
					String str = gson.toJson(itemVOtoAdd);
					jedis.lset(sessionId, i, str);
					jedis.close();
					return;
				}
		}
		
			// 若沒有該商品ID則新增
			String strVO = gson.toJson(cartItemVO);
			jedis.rpush(sessionId, strVO);
			jedis.close();
		}
	}
	
	public static void deleteItem(String sessionId, Integer itemId) {
		Gson gson = new Gson();
		
		Jedis jedis = null;
		jedis = pool.getResource();
		
		List<String> cartItems = getCart(sessionId);
		
		for(int i = 0; i < cartItems.size(); i++) {
			ShoppingCartItemVO itemVO = gson.fromJson(cartItems.get(i), ShoppingCartItemVO.class);
			
			if(itemVO.getItemId().equals(itemId)) {
				jedis.lrem(sessionId, 0, cartItems.get(i));	// 刪除該商品
				jedis.close();
				return;
			}
		}
	}
	
	public static void deleteCart(String sessionId) {
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.del(sessionId);	// 清空購物車刪除key
		jedis.close();
	}
}

package Redis.util.jedis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)
	private static JedisPool pool = JedisPoolUtil.getJedisPool(); // 相當於ds
	
	public static List<String> getHistoryMsg(String sender, String receiver){
		String key = new StringBuilder(sender).append(":").append(receiver).toString();
		Jedis jedis = null;
		jedis = pool.getResource();	// 相當於 ds getConnection
		List<String> historyData = jedis.lrange(key, 0, -1);	// 因為要有順序性，因此使用list
		jedis.close();
		return historyData;
	}
	
	public static void saveChatMessage(String sender, String receiver, String message) {
		// 對雙方來說，都要各存著歷史聊天記錄
		String senderKey = new StringBuilder(sender).append(":").append(receiver).toString();
		String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString();
		Jedis jedis = pool.getResource();
		// 存一樣的訊息內容，因為在各自的角度本身都是發送者
		jedis.rpush(senderKey, message);
		jedis.rpush(receiverKey, message);
		
		jedis.close();
	}
	
	
	public static String saveUnreadNum(String sender, String receiver) {
		String key = new StringBuilder("unread").append(":").append(sender).append(":").append(receiver).toString();	// receiver是自己
		Jedis jedis = null;
		jedis = pool.getResource();
		if(!jedis.exists(key)) {
			jedis.set(key,"0");
		}
		jedis.incr(key);
		String unReadNum = jedis.get(key);
		jedis.close();
		return unReadNum;
	}
	
	
	public static void cleanUnreadNum(String sender, String receiver) {
		String key = new StringBuilder("unread").append(":").append(sender).append(":").append(receiver).toString();	// receiver是自己
		Jedis jedis = null;
		jedis = pool.getResource();
		String unReadNum = jedis.get(key);

		if(unReadNum != null) {
			jedis.decrBy(key, new Integer(unReadNum)); 		
		}
		jedis.close();
	}
	
}

package com.chatbox.controller;

import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.chatbox.model.ChatMessageVO;
import com.chatbox.model.StateVO;
import com.google.gson.Gson;

import Redis.util.jedis.JedisHandleMessage;

@ServerEndpoint("/FriendWS/{userName}")
public class FriendWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>(); // JDK5，將同步鎖定的範圍縮小了(可以很多執行緒並行，只要key
																					// vlue索取的空間不同)
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		// save the new user in the map
		sessionsMap.put(userName, userSession);
		// Sends all the connected users to the new user
		Set<String> userNames = sessionsMap.keySet(); // 取的所有上線者的userName
		StateVO stateMessage = new StateVO("open", userName, userNames);
		String stateMessageJson = gson.toJson(stateMessage);
		Collection<Session> sessions = sessionsMap.values(); // 取得所有上線者的userSession
		for (Session session : sessions) {
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(stateMessageJson); // 推播告知有新的上線者
			}
		}

		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
				userName, userNames); // %n為換行
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatMessageVO chatMessageVO = gson.fromJson(message, ChatMessageVO.class); // 透過類別書將JSON還原為物件
		String sender = chatMessageVO.getSender();
		String receiver = chatMessageVO.getReceiver();
		String timestamp = chatMessageVO.getTimestamp();

		if ("history".equals(chatMessageVO.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMsg = gson.toJson(historyData);
			ChatMessageVO cmHistory = new ChatMessageVO("history", sender, receiver, historyMsg, timestamp);
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
				JedisHandleMessage.cleanUnreadNum(receiver, sender); // history 的 sender是自己
				System.out.println("history = " + gson.toJson(message));
				return;
			}
		}

		Session receiverSession = sessionsMap.get(receiver);
		if (receiverSession != null && receiverSession.isOpen()) {
			String unreadNum = JedisHandleMessage.saveUnreadNum(sender, receiver);
			chatMessageVO.setUnreadNum(unreadNum);
			
			String gsonStr = gson.toJson(chatMessageVO);
			receiverSession.getAsyncRemote().sendText(gsonStr);
			
//			receiverSession.getAsyncRemote().sendText(message); // 發送給對方
			userSession.getAsyncRemote().sendText(message); // 發送給自己
			JedisHandleMessage.saveChatMessage(sender, receiver, message);
			System.out.println("saveUnreadNumg: " + unreadNum);
		}
		

		
		
		System.out.println("Message received: " + message);
		
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			StateVO stateMessage = new StateVO("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}

}

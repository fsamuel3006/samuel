package com.appointment_form.controller;

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
import com.google.gson.Gson;


@ServerEndpoint("/ApmWS/{userId}")
public class ApmWS {
	private static final Map<String, Session> sessionsMap = new ConcurrentHashMap<>();	// JDK5，將同步鎖定的範圍縮小了(可以很多執行緒並行，只要key vlue索取的空間不同)
	Gson gson = new Gson();
	
	@OnOpen
	public void onOpen(@PathParam("userId") String userId, Session userSession) throws IOException{
		// save the new user in the map
		sessionsMap.put(userId, userSession);
		System.out.println("Connect Success!");
		String text = String.format("session ID = %s, usersId: %s", userSession.getId(), userId);
		System.out.println(text);
	}
	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatMessageVO chatMsgVO = gson.fromJson(message, ChatMessageVO.class);	// 參考類別書還原物件
		String sender = chatMsgVO.getSender();
		String receiver = chatMsgVO.getReceiver();
		
		if("newApm".equals(chatMsgVO.getType())) {
			Session receiverSession = sessionsMap.get(receiver);
			System.out.println("receiverSession" + receiverSession);
			if(receiverSession != null && receiverSession.isOpen()) {
				receiverSession.getAsyncRemote().sendText(message);
			};
		}
		
		
		if("apmStatusChange".equals(chatMsgVO.getType())) {
			Session receiverSession = sessionsMap.get(receiver);
			System.out.println("receiverSession" + receiverSession);
			if(receiverSession != null && receiverSession.isOpen()) {
				receiverSession.getAsyncRemote().sendText(message);
			};
		}
		
		
		
		System.out.println("Message receiver:" + message);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userIdClose = null;
		Set<String> userIds = sessionsMap.keySet();
		for(String userId:userIds) {
			if(sessionsMap.get(userId).equals(userSession)) {
				userIdClose = userId;
				sessionsMap.remove(userId);
				break;
			}
		}
		
		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userIds);
		System.out.println(text);
		
		
	}
}

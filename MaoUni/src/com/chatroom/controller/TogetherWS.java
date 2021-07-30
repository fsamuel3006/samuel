package com.chatroom.controller;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;



//================== 此為群體聊天室範例 =========================

@ServerEndpoint("/TogetherWS/{userName}")
public class TogetherWS {
	// 一個client會有一個session，將連線存放在做好同步處理的集合物件synchronizedSet
	// 此集合可視為所有在線的使用者，可以運用在好友列表顯示上線的圖示	
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	
	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	
	@OnOpen // 當建立連線時執行
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException{
		connectedSessions.add(userSession);  // client 進入連線時加入集合
		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), userName);
		System.out.println(text);
	}
	
	@OnMessage // 當收到message時執行
	public void onMessage(Session userSession, String message) {
		for(Session session : connectedSessions) {
			if(session.isOpen()) // 確認連線開啟才可發送訊息
				session.getAsyncRemote().sendText(message); // 以非同步的方式傳送
		}
		System.out.println("Message recevied: " + message);
	}
	
	@OnClose // 當連線結束時執行;舊的session只要斷了就是垃圾了，重新連線就是新的session，因此只能維持在「當前」頁面的連線(因此forward頁面會斷線)
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession); // 移除集合中的session
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println(text);
	}
	
	@OnError  // 當有例外發生時執行
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}











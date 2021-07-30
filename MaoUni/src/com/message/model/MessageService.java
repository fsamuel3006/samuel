package com.message.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MessageService {

	private MessageDAO_interface dao;

	public MessageService() {
		dao = new MessageJDBCDAO();
	}

	public MessageVO addMessage(int memId, String contain, int status) {
		MessageVO messageVO = new MessageVO();

		messageVO.setMemId(memId);

		messageVO.setContain(contain);
		messageVO.setStatus(status);
		dao.insert(messageVO);

		return messageVO;
	}

	public MessageVO updateMessage(int memId, String contain, int status) {

		MessageVO messageVO = new MessageVO();

		messageVO.setMemId(memId);

		messageVO.setContain(contain);
		messageVO.setStatus(status);

		dao.update(messageVO);

		return messageVO;
	}

	public List<MessageVO> getAll() {
		return dao.getAll();
	}

	public MessageVO getOneMessage(Integer id) {
		return dao.findByPrimaryKey(id);
	}

	public Set<memberVO> get_Member_byMessag(Integer memId) {
		return dao.get_Member_byMessage(memId);
	}

	public void sendMail(String to, String subject, StringBuffer messageText) {
			
		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
			// ●須將myGmail的【安全性較低的應用程式存取權】打開
			final String myGmail = "CFA1012038@gmail.com";
			final String myGmail_password = "0981008841";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			// 設定信中的內容
			message.setText("您的回應我們收到了，我們將盡快解決您的需求");

			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

		String to = "fsamuel3006@hotmail.com";

		String subject = "您的回應我們收到了";

		String ch_name = "親愛的顧客";
		String passRandom = "我們將盡快解決您的需求";
		String messageText = "Hello! " + ch_name + passRandom + "\n";

		messageEmail mailService = new messageEmail();
		mailService.sendMail(to, subject, messageText);

	}

	

//	public Set<COVO> get_COMPLAIN_byMessage(COVO){
//		return 
//	}

}

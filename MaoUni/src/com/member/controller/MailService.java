package com.member.controller;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class MailService {
	//信件認證的執行緒
	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void JavaMail(String to, String subject, String messageText) {
			
	   try {
		   // 設定使用SSL連線至 Gmail smtp Server
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
       // ●須將myGmail的【安全性較低的應用程式存取權】打開
		     final String myGmail = "healwriter@gmail.com";
		     final String myGmail_password = "003cindy";

		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		  
		   //設定信中的主旨  
		   message.setSubject(subject);
		   //設定信中的內容 
		   message.setText(messageText);

		   Transport.send(message);
		   System.out.println("傳送成功！");
     }catch (MessagingException e){
	     System.out.println("傳送失敗！");
	     e.printStackTrace();
     }
   }
	
	public static void main (String args[]){

      String to = "healwriter@gmail.com";
      String subject = "【毛孩有你】驗證碼通知";
      MemberService memSvc = new MemberService();
      MemberVO memEmail =memSvc.MemberVO("memEamil");
      int min = 1000;
      int max = 9999;
      Double passRandom = Math.random()*(max-min) + min; //產生四位數的驗證碼
      String messageText = "Hello! " + memEmail + " 請謹記此驗證碼: " + passRandom + "\n" +" (已經啟用)"; 
       
      MailService mailService = new MailService();
      mailService.JavaMail(to, subject, messageText);

   }


}
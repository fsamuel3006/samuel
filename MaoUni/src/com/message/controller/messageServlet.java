package com.message.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.factory.SendMailFactory;

import com.backstage_staff.model.StaffService;
import com.backstage_staff.model.StaffVO;

import com.message.model.MessageService;
import com.message.model.MessageVO;
import com.message.model.messageEmail;
import com.sun.mail.util.MailConnectException;
import com.util.MailService;

@WebServlet("/messageServlet")
public class messageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		if ("getAll".equals(action)) {

			try {
				Integer id = new Integer(req.getParameter("id"));
				System.out.println(id);
				MessageService messageSvc = new MessageService();
				MessageVO messageVO = (MessageVO) messageSvc.getAll();

				req.setAttribute("MessageVO", "messageVO");
				String url = "/front-end/message/allMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOneMessage".equals(action)) {
			List<String> errorsMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer id = new Integer(req.getParameter("id"));
				System.out.println(id);
				MessageService messageSvc = new MessageService();
				MessageVO messageVO = (MessageVO) messageSvc.getOneMessage(id);

				req.setAttribute("MessageVO", messageVO);
				String url = "/front-end/message/allMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
				failureView.forward(req, res);
			}
		}
		if ("updateMessage".equals(action)) {
			List<String> errorsMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				Integer id = new Integer(req.getParameter("id"));
				Integer memId = new Integer(req.getParameter("memId"));
				Integer status = new Integer(req.getParameter("status"));
				String contain = new String(req.getParameter("contain"));

				MessageService messageSvc = new MessageService();
				MessageVO messageVO = (MessageVO) messageSvc.updateMessage(memId, contain, status);

				req.setAttribute("MessageVO", messageVO);
				String url = "/front-end/message/allMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				System.out.println("12345");
				e.printStackTrace();
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
				failureView.forward(req, res);
			}
		}
		if ("addMessage".equals(action)) {
			System.out.println(action);
			try {
				
				Integer memId = new Integer(req.getParameter("memId"));
				
				System.out.println(memId);
				String contain = new String(req.getParameter("contain"));
				System.out.println(contain);
				Integer status = new Integer(req.getParameter("status"));

				MessageService MessageSvc = new MessageService();
				MessageVO messageVO = MessageSvc.addMessage(memId, contain, status);
				System.out.println("12345");
				req.setAttribute("MessageVO", messageVO);
				String url = "/front-end/Message/AllMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("請再檢查一遍" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
				failureView.forward(req, res);
			}
		}
		if ("add".equals(action)) {
			System.out.println(action);
			try {
				Integer id = new Integer(req.getParameter("id"));
				Integer memId = new Integer(req.getParameter("memId"));
				Integer status = new Integer(req.getParameter("status"));
				String contain = new String(req.getParameter("contain"));
				System.out.print(memId);
				MessageService MessageSvc = new MessageService();
				MessageVO messageVO = MessageSvc.addMessage(memId, contain, status);

				req.setAttribute("messageVO", messageVO);
				String url = "/front-end/message/allMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("請再檢查一遍" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
				failureView.forward(req, res);
			}
		}
		if ("sendMail".equals(action)) {

			System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");
			MailService mailService = new MailService();
			mailService.setTo("project60237@gmail.com");
			mailService.setSubject("MaoUni 毛孩有你");
			StringBuffer messageText = new StringBuffer();
			messageText.append("親愛的顧客，我們將盡快解決您的需求");
			
			
			mailService.setMessageText(messageText.toString());
			mailService.start();

			String url = "/front-end/message/allMessage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {
			doGet(req, res);
		} catch (ServletException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package com.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;


@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
              
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action: " + action);

		if ("signup".equals(action)) { // 註冊用的

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String memName = req.getParameter("memName");
			String memEmail = req.getParameter("memEmail").trim();
			String memPassword = req.getParameter("memPassword");
			String memIdenity = req.getParameter("memIdenity").trim();
			String memGender = req.getParameter("memGender");
			Integer memPh = new Integer(req.getParameter("memPh").trim());
			String memAddres = req.getParameter("memAddres").trim();
			Date memBirthday = java.sql.Date.valueOf(req.getParameter("memBirthday"));

			try {
				
				MemberService memSvc = new MemberService(); // 呼叫serverc方法
				memSvc.signup(memName, memEmail, memPassword, memIdenity, memGender, memPh, memAddres, memBirthday);

//			/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				// 新增成功後轉交到驗證的頁面，讓頁面轉跳
				successView.forward(req, res);

			} catch (Exception e) {
				e.printStackTrace(System.err);
				}
				}
		
	
	}

}

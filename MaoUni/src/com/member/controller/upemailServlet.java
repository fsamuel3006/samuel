package com.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.util.Arrays;
import com.member.model.MemberJDBCDAO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import javax.servlet.http.HttpServletRequest;

public class upemailServlet extends HttpServlet {
	public static final long SerialVersionUID = 1l;
	public MemberJDBCDAO memberjdbcdao;

	public void init() {

		memberjdbcdao = new MemberJDBCDAO();

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action: " + action);

		if ("upemail".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String memEmail = new String(req.getParameter("memEmail")); // 獲取表單提交的email	
			
			if(memEmail !=null) {
			
				MailService memSvc = new MailService (); // 把service串聯過來,就可以調用之前寫的對比帳密的方法了
			
		}
			
			
		}
	}
}

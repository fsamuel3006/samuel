package com.SignIn.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.StaffSignIn.model.signInService;
import com.StaffSignIn.model.signInVO;
import com.adopt_imf.model.ImfService;
import com.adopt_imf.model.ImfVO;

@WebServlet("/SignIn/SignIn_Controller")
public class SignIn_Controller extends HttpServlet {
	
//	private signInService ajx = new signInService();  
 


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 解決亂碼問題
		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		String action = request.getParameter("action");
		request.getParameter("username");
		
		if ("login".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String username = request.getParameter("username");
					String password = request.getParameter("password");
					String usernameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					String passwordReg = "^[0-9]*${1,2}$";
					
					if(username == null || username.trim().isEmpty()) {
						
						errorMsgs.put("username", "帳號請勿空白");
						request.setAttribute(username, "帳號錯誤");
						String url = "/back-end/staff/select.jsp";
						RequestDispatcher failureView = request.getRequestDispatcher(url);
						failureView.forward(request, response);						
					}else if(!username.trim().matches(usernameReg)) {
						errorMsgs.put("username", "請輸入有效的帳號");
						String url = "/back-end/staff/select.jsp";
						RequestDispatcher failureView = request.getRequestDispatcher(url);
						failureView.forward(request, response);
					}
					
					if(password == null || password.trim().isEmpty()) {
						errorMsgs.put("password", "密碼請勿空白");
						String url = "/back-end/staff/select.jsp";
						RequestDispatcher failureView = request.getRequestDispatcher(url);
						failureView.forward(request, response);
					}else if(!password.trim().matches(passwordReg)) {
						errorMsgs.put("password", "密碼長度限制6-20");
						String url = "/back-end/staff/select.jsp";
						RequestDispatcher failureView = request.getRequestDispatcher(url);
						failureView.forward(request, response);
					}
					if(!errorMsgs.isEmpty()) {
						request.getSession().setAttribute(username, password);
						request.getRequestDispatcher("/back-end/staff/select.jsp").forward(request, response);
						HttpSession session =request.getSession();
						session.setAttribute(username, password);
									
					}
					signInService signInSvc = new signInService();
					signInVO signInVO = signInSvc.findByUsernameAndPassword(username, password);
					String url = "/back-end/staff/backImage.jsp";
					RequestDispatcher successView = request.getRequestDispatcher(url);
					successView.forward(request, response);
					HttpSession session =request.getSession();
					session.setAttribute(username, password);
		}
					
		}
	
	
	
	
	
	
	
	
	
	
	
	
}

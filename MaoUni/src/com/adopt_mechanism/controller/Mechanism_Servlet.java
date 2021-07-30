package com.adopt_mechanism.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adopt_mechanism.model.*;
import com.backstage_authority.model.AuthorityVO;

/**
 * Servlet implementation class Mechanism_Servlet
 */
@WebServlet("/Mechanism_Servlet")
public class Mechanism_Servlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
			
			if("getAll".equals(action)) {
			List<String> errorsMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorsMsgs);
			
			try {
				
			
				MechanismService mechanismSvc=new MechanismService();
				List<MechanismVO> mechanismVo = mechanismSvc.getAll();
				
				req.setAttribute("mechanismVo", mechanismVo );
				String url = "/back-end/mechanism/getAllMechanism.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req,res);				
				}catch (Exception e) {
				e.printStackTrace();
				errorsMsgs.add("請重新輸入"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
				failureView.forward(req, res);
		}
	}
			if("getOneMechanism".equals(action)) {
				List<String> errorsMsgs = new LinkedList<String>();
				req.setAttribute("errorsMsgs",errorsMsgs);
				try {
					String str = req.getParameter("id");
					if(str == null ||str.trim().length() == 0) {
						errorsMsgs.add("請勿空白");
					}
					if(!errorsMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("");
						failureView.forward(req, res);
					}
					Integer id = null;
					try {
						id = new Integer (str);
					}catch (Exception e) {
						errorsMsgs.add("格式不正確");
					}
					if(!errorsMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("");
						return;
					}
					MechanismService mechanismSvc = new MechanismService();
					MechanismVO MechanismVO = mechanismSvc.getOneMechanism(id);
					if(!errorsMsgs.isEmpty()) {
						errorsMsgs.add("查無資料");
						RequestDispatcher failureView = req.getRequestDispatcher("");
						return;
					}
					req.setAttribute("MechanismVO", MechanismVO);
					String url="";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}catch(Exception e) {
					e.printStackTrace();
					errorsMsgs.add("無法取得資料");
					RequestDispatcher failureView = req.getRequestDispatcher("");
					failureView.forward(req, res);
				}
			}
			if("updateMechanism".equals(action)) {
				List<String> errorsMsgs = new LinkedList<String>();
				req.setAttribute("errorsMsgs",errorsMsgs);
				
				try {
					Integer id = new Integer(req.getParameter("id"));
					MechanismService mechanismService = new MechanismService();
					MechanismVO MechanismVO = mechanismService.getOneMechanism(id);
					req.setAttribute("MechanismVO", MechanismVO);
					String url = "";
					RequestDispatcher successView=req.getRequestDispatcher(url);
					successView.forward(req, res);
				}catch(Exception e) {
					e.printStackTrace();
					errorsMsgs.add("沒有資料"+e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("");
					failureView.forward(req, res);
				}
			}
			if("update".equals(action)) {
				List<String> errorsMsgs = new LinkedList<String>();
				req.setAttribute("errorsMsgs",errorsMsgs);
				
				try {
					Integer id = new Integer(req.getParameter("id").trim());
					
					String name=req.getParameter("name");
					String nameReg="^[{\u4e00-\u9fa5)(a-zA-Z_)] {2,10}$";			
					if(name==null || name.trim().length()==0) {
						errorsMsgs.add("請輸入正確");
						System.out.println(name);
					}else if(!name.trim().matches(nameReg)) {
						errorsMsgs.add("員工姓名:只能是中英文，且長度必須在2到10位數");
					}
					String address=req.getParameter("address");
					String addressReg="^[{\u4e00-\u9fa5)(a-zA-Z_)] {2,10}$";			
					if(address==null || address.trim().length()==0) {
						errorsMsgs.add("請輸入正確");
						System.out.println(address);
					}else if(!address.trim().matches(addressReg)) {
						errorsMsgs.add("員工姓名:只能是中英文，且長度必須在2到10位數");
					}
					String tel=req.getParameter("tel");
					String telReg="^[{\u4e00-\u9fa5)(a-zA-Z_)] {2,10}$";			
					if(tel==null || tel.trim().length()==0) {
						errorsMsgs.add("請輸入正確");
						System.out.println(name);
					}else if(!tel.trim().matches(telReg)) {
						errorsMsgs.add("員工姓名:只能是中英文，且長度必須在2到10位數");
					}
					String url = new String(req.getParameter("id").trim());
					

					MechanismService mechanismSvc = new MechanismService();
					MechanismVO mechanismVO = mechanismSvc.updateMechanism(id, name, address, tel, url);
					req.setAttribute("MechanismVO", mechanismVO);
					String url1 ="";
				}catch(Exception e) {
					e.printStackTrace();
					errorsMsgs.add("請重新輸入");
					RequestDispatcher failureView = req.getRequestDispatcher("");
					failureView.forward(req, res);
				}
			}
			if("insert".equals(action)) {
				List<String> errorsMsgs = new LinkedList<String>();
				req.setAttribute("errorsMsgs",errorsMsgs);
				
				try {
					Integer id = new Integer(req.getParameter("id").trim());
					
					String name=req.getParameter("name");
					String nameReg="^[{\u4e00-\u9fa5)(a-zA-Z_)] {2,10}$";			
					if(name==null || name.trim().length()==0) {
						errorsMsgs.add("請輸入正確");
						System.out.println(name);
					}else if(!name.trim().matches(nameReg)) {
						errorsMsgs.add("員工姓名:只能是中英文，且長度必須在2到10位數");
					}
					String address=req.getParameter("address");
					String addressReg="^[{\u4e00-\u9fa5)(a-zA-Z_)] {2,10}$";			
					if(address==null || address.trim().length()==0) {
						errorsMsgs.add("請輸入正確");
						System.out.println(address);
					}else if(!address.trim().matches(addressReg)) {
						errorsMsgs.add("員工姓名:只能是中英文，且長度必須在2到10位數");
					}
					String tel=req.getParameter("tel");
					String telReg="^[{\u4e00-\u9fa5)(a-zA-Z_)] {2,10}$";			
					if(tel==null || tel.trim().length()==0) {
						errorsMsgs.add("請輸入正確");
						System.out.println(name);
					}else if(!tel.trim().matches(telReg)) {
						errorsMsgs.add("員工姓名:只能是中英文，且長度必須在2到10位數");
					}
					String url = new String(req.getParameter("id").trim());

					MechanismService mechanismSvc = new MechanismService();
					MechanismVO mechanismVO = mechanismSvc.addMechanism(id, name, address, tel, url);
					req.setAttribute("MechanismVO", mechanismVO);
					String url1 ="";
				}catch(Exception e) {
					e.printStackTrace();
					errorsMsgs.add("請重新輸入");
					RequestDispatcher failureView = req.getRequestDispatcher("");
					failureView.forward(req, res);
				}
			}
	}
	
	
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}

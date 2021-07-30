package com.backstage_authority.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backstage_authority.model.AuthorityService;
import com.backstage_authority.model.AuthorityVO;
import com.backstage_function.model.FunctionService;
import com.backstage_function.model.FunctionVO;
import com.backstage_staff.model.StaffVO;


@WebServlet("/backstage_authority/AuthorityServlet")
public class AuthorityServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		
		if("getAll".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				//接收參數
				String str=req.getParameter("id");
				if(str==null||str.trim().length()==0) {
					errorMsgs.add("請勿空白");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView=req.getRequestDispatcher("/front-end/staff/wrong.jsp");
					failureView.forward(req, res);
					return;
				}
				String fun=req.getParameter("fun").trim();
				if(fun==null||fun.trim().length()==0) {
					errorMsgs.add("功能請勿空白");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView=req.getRequestDispatcher("/front-end/staff/wrong.jsp");
					failureView.forward(req, res);
					return;
				}
				//開始查詢資料
				AuthorityService authoritySvc=new AuthorityService();
				AuthorityVO authorityVO=(AuthorityVO) authoritySvc.getAll();
				if(authorityVO==null) {
					errorMsgs.add("查無資料");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView=req.getRequestDispatcher("/front-end/staff/wrong.jsp");
					failureView.forward(req, res);
					return;
				}
				req.getAttribute("AuthorityVO");
				String url="/back-end/authority/allAuthority.jsp";
				RequestDispatcher successView=req.getRequestDispatcher(url);
				successView.forward(req,res);		
			}catch(Exception e) {
				e.printStackTrace();
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView=req.getRequestDispatcher("/front-end/staff/wrong.jsp");
				failureView.forward(req,res);
			}
			
		}
		
		if("get_One_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		try{
//			
			Integer id= new Integer(req.getParameter("id"));
			System.out.print(id);
			AuthorityService authoritySvc=new AuthorityService();
			AuthorityVO authorityVO=authoritySvc.findByPrimaryKey(id);			
			
			req.setAttribute("AuthorityVO", authorityVO);
			String url="/back-end/authority/getOneAuthority.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
//			try {
//				id= new Integer(str);
//			}catch(Exception e) {
//				errorMsgs.add("格式不正確");
//			}
//			if(!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView=req.getRequestDispatcher("/back-end/staff/wrong.jsp");
//				failureView.forward(req, res);
//			}
//		
//			//開始查詢資料
//			AuthorityService authoritySvc=new AuthorityService();
//			AuthorityVO authorityVO=authoritySvc.get_primary_key(id);
//			if(authorityVO==null) {
//				errorMsgs.add("查無資料");
//			}
//			if(!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView=req.getRequestDispatcher("/back-end/staff/wrong.jsp");
//				failureView.forward(req, res);
//				return ;
//			}
//			
//			//查詢完成準備轉交
//			req.setAttribute("AuthorityVO", authorityVO);
//			String url = "/back-end/Authority/GetOneAuthority.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);  
//			successView.forward(req, res);
			
		}catch(Exception e) {
			errorMsgs.add("無法取得資料:"+e.getMessage());
			RequestDispatcher failureView=req.getRequestDispatcher("/back-end/staff/wrong.jsp");
			failureView.forward(req, res);
		}
		}
		if("inser_Authority".equals(action)) {
			List<String>errorMsgs=new LinkedList<String>();
			req.setAttribute("erroeMsgs",errorMsgs);
			try {
				//給予參數
				Integer id =new Integer(req.getParameter("id"));
				System.out.println(id);
			
				AuthorityService authoritySvc=new AuthorityService();
				AuthorityVO authorityVO = authoritySvc.findByPrimaryKey(id);
	
				//開始查詢
				req.setAttribute("AuthorityVO", authorityVO);
				String url="/back-end/authority/addAuthority.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
		
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print("1234");
			errorMsgs.add("無法更新資料"+e.getMessage());
			RequestDispatcher failureView=req.getRequestDispatcher("/back-end/staff/wrong.jsp");
			failureView.forward(req, res);
		}
	}
		
		
		if("insert".equals(action)) {
			List<String>errorMsgs=new LinkedList<String>();
			req.setAttribute("erroeMsgs",errorMsgs);
			try {
				
				Integer id=null;
				try {
					id=new Integer(req.getParameter("id").trim());
			}catch (NumberFormatException e) {
				System.out.println(1234);
				e.printStackTrace();
				errorMsgs.add("請正確填入ID");
			}
				Integer fun=null;
				try{
					fun=new Integer(req.getParameter("fun").trim());
				}catch(NumberFormatException e) {
					e.printStackTrace();
					errorMsgs.add("請正確填入功能代號");
				}
				
				
				AuthorityVO authorityVO = new AuthorityVO();
				authorityVO.setId(id);
				authorityVO.setFun(fun);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("AuthorityVO", authorityVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
					failureView.forward(req, res);
					return;
				}
				
				AuthorityService authoritySvc=new AuthorityService();
				authoritySvc.addAuthority(id, fun);
				//準備轉交
					String url="/back-end/authority/allAuthority.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
			}catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("請注意輸入規則");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getOne_For_Updat".equals(action)) {
			List<String>errorMsgs=new LinkedList<String>();
			req.setAttribute("erroeMsgs",errorMsgs);
			try {
				Integer id =new Integer(req.getParameter("id"));
				AuthorityService authoritySvc=new AuthorityService();
				AuthorityVO authorityVO = authoritySvc.findByPrimaryKey(id);
				
				System.out.println(authorityVO);
				
				//開始查詢
				req.setAttribute("AuthorityVO", authorityVO);
				String url="/back-end/authority/updateAuthority.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
		
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print("1234");
			errorMsgs.add("無法更新資料"+e.getMessage());
			RequestDispatcher failureView=req.getRequestDispatcher("/back-end/staff/wrong.jsp");
			failureView.forward(req, res);
		}
	}

			
		if("update".equals(action)){
			List<String>errorMsgs=new LinkedList<String>();
			req.setAttribute("erroeMsgs",errorMsgs);
			try {
				Integer id=null;
				try {
						id=new Integer(req.getParameter("id").trim());
						System.out.println(id);
			}catch (NumberFormatException e) {
				
				e.printStackTrace();
				errorMsgs.add("請正確填入ID");
				
			}
				Integer fun=null;
				try{
					fun=new Integer(req.getParameter("fun").trim());
				}catch(NumberFormatException e) {
					e.printStackTrace();
					errorMsgs.add("請正確填入功能代號");
				}
	
				
				AuthorityVO authorityVO = new AuthorityVO();
				authorityVO.setId(id);
				authorityVO.setFun(fun);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("AuthorityVO", authorityVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
					failureView.forward(req, res);
					return;
				}
				
			
				AuthorityService authoritySvc=new AuthorityService();
				authorityVO = authoritySvc.updateAuthority(id, fun);
				//準備轉交
					String url="/back-end/authority/allAuthority.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
			}catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("請注意輸入規則");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
	}
		


	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);	
		
		
	}
}





















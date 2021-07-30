package com.backstage_function.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backstage_function.model.FunctionService;
import com.backstage_function.model.FunctionVO;


@WebServlet("/backstage_function/functionServlet")
public class FunctionServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		
		if("getAll".equals(action)){
			try {
				
				List<String> errorMsgs=new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				String str=req.getParameter("id");
				
				if(str==null||str.trim().length()==0) {
					errorMsgs.add("請勿空白");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView=req.getRequestDispatcher("/front-end/staff/wrong.jsp");
					failureView.forward(req, res);
					return;
				}
				String name=req.getParameter("name");
				System.out.println(name);
				
				String nameReg="^[{\u4e00-\u9fa5)(a-zA-Z_)] {2,10}$";
			
				if(name==null || name.trim().length()==0) {
					errorMsgs.add("請輸入正確");
				}else if(!name.trim().matches(nameReg)) {
					errorMsgs.add("員工姓名:只能是中英文，且長度必須在2到10位數");
				}
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView=req.getRequestDispatcher("/front-end/staff/wrong.jsp");
					failureView.forward(req, res);
					return;
				}
			
			FunctionService functionSvc=new FunctionService();
			List<FunctionVO> functionVO=functionSvc.getAll();		
			req.setAttribute("FunctionVO", functionVO);
			
			String url = "/back-end/function/allFunction.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/staff/wrong.jsp");
		}
			
		}
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs= new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String id = req.getParameter("id");
				System.out.println(id);
				if(id ==null||id.trim().length()==0) {
					errorMsgs.add("請勿空白");
				}
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView=req.getRequestDispatcher("/back-end/staff/wrong.jsp");
					failureView.forward(req, res);
					return;
				}
				String name=req.getParameter("name");
				System.out.println(name);
//				
//				String nameReg="^[{\u4e00-\u9fa5)(a-zA-Z_)] {2,10}$";
//			
//				if(name==null || name.trim().length()==0) {
//					errorMsgs.add("請輸入正確");
//				}else if(!name.trim().matches(nameReg)) {
//					errorMsgs.add("員工姓名:只能是中英文，且長度必須在2到10位數");
//				}
//				if(!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView=req.getRequestDispatcher("/back-end/staff/wrong.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				System.out.println(name);
			FunctionService functionSvc=new FunctionService();
			
			FunctionVO functionVO=functionSvc.getOneFunction(Integer.parseInt(id));	
			
			req.setAttribute("functionVO", functionVO);
			
			String url ="/back-end/function/getOneFunction.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);				
			}catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("請重新輸入"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
				failureView.forward(req, res);
			}
		}
		if("updateFunction".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				Integer id=new Integer(req.getParameter("id"));
			System.out.print(id);
			FunctionService functionSvc=new FunctionService();
			FunctionVO functionVO=functionSvc.getOneFunction(id);
			System.out.print(id);
			//查詢完畢轉交
			req.setAttribute("functionVO", functionVO);
			String url= "/back-end/function/updatFunction.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
		}catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher failureView=req.getRequestDispatcher("/back-end/staff/wrong.jsp");
			failureView.forward(req, res);
		}
	
	}
		if("updat".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				Integer id = new Integer(req.getParameter("id").trim());
				System.out.println(id);
			String name=req.getParameter("name");
			
			
			String nameReg="^.[A-Za-z]{2,10}$";
			
			if(name==null || name.trim().length()==0) {
				errorMsgs.add("請輸入正確");
				System.out.println(name);
			}
				else if(!name.trim().matches(nameReg)) {
				System.out.println(name);
				errorMsgs.add("員工姓名:只能是中英文，且長度必須在2到10位數");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView=req.getRequestDispatcher("/back-end/staff/wrong.jsp");
				failureView.forward(req, res);
				return;
			}
		
			FunctionService functionSvc=new FunctionService();
			FunctionVO functionVO=functionSvc.updateFunction(id, name);		
			req.setAttribute("FunctionVO", functionVO);
		
			String url = "/back-end/function/allFunction.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);				
			}catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add("請重新輸入"+e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
			failureView.forward(req, res);
		}
		
	}
		if("insertFunction".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			try {
				String id = req.getParameter("id");
				
				FunctionService functionSvc = new FunctionService();
				FunctionVO oneFunction = functionSvc.getOneFunction(Integer.parseInt(id));
				req.setAttribute("oneFunction", oneFunction);
				String url="/back-end/function/addFunction.jsp";
				req.getRequestDispatcher(url).forward(req, res);
			}catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("請輸入正確");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/staff/wrong.jsp");
				failureView.forward(req, res);
			}
		}
		if("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs",errorMsgs);
			try{
				String id = req.getParameter("id");
				
			if(id==null||id.trim().length()==0) {
				errorMsgs.add("請勿空白");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView=req.getRequestDispatcher("/front-end/staff/wrong.jsp");
				failureView.forward(req, res);
				return;
			}
			String name=req.getParameter("name");
			System.out.println(name);
			
			String nameReg="^[{\u4e00-\u9fa5)(a-zA-Z_)] {2,10}$";
		
			if(name==null || name.trim().length()==0) {
				errorMsgs.add("請輸入正確");
			}else if(!name.trim().matches(nameReg)) {
				errorMsgs.add("員工姓名:只能是中英文，且長度必須在2到10位數");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView=req.getRequestDispatcher("/front-end/staff/wrong.jsp");
				failureView.forward(req, res);
				return;
			}
			FunctionService functionSvc = new FunctionService();
			FunctionVO functionVO= functionSvc.addFunction(Integer.parseInt(id), name);
			
			String url="/back-end/function/allFunction.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
			}catch(Exception e) {
				e.printStackTrace();
				errorMsgs.add("請重新輸入");
				RequestDispatcher failureView=req.getRequestDispatcher("/back-end/staff/wrong.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);	
	}

}

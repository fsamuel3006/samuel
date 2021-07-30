package com.service_items.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.service_items.model.SvcService;
import com.service_items.model.SvcVO;

public class SvcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				String item = req.getParameter("svcItem");
				String pet = req.getParameter("svcPet");
				// item未輸入資料時得到的是"" (空值)，非null
				// 此處寫 == null為防呆用，捕捉getParameter的參數因打錯字而未找到資料的錯誤，若未設置，有誤時將會跳出例外反而不便除錯
				if(item == null || item.trim().length() == 0) {
					errorMsgs.put("item", "服務項目請勿空白");
				}
				
				if(!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groomer/svcItemList.jsp");
//					failureView.forward(req, res);
					res.sendRedirect("draft/groomer/svcItemList.jsp");
					return;
				}
				
				SvcService svcService = new SvcService();
				svcService.addServiceItem(item, pet);
				
//				RequestDispatcher successView = req.getRequestDispatcher("/back-end/groomer/svcItemList.jsp");
//				successView.forward(req, res);
				res.sendRedirect("draft/groomer/svcItemList.jsp");
				
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groomer/svcItemList.jsp");
//				failureView.forward(req, res);
				res.sendRedirect("draft/groomer/svcItemList.jsp");
			} 

		}
		
		// groomerInfoEdit.jsp 透過 ajax呼叫
		if("findByPet".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			res.setContentType("text/html;charset=UTF-8");
			List<SvcVO> list = null;
			
			try {
				String pet = req.getParameter("svcPet");
				SvcService svcService = new SvcService();
				list = svcService.findByPet(pet);
				
				String jsonList = new JSONArray(list).toString();
				PrintWriter out = res.getWriter();
				out.print(jsonList);
				out.flush();
				out.close();
				
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groomer/svcItemList.jsp");
//				failureView.forward(req, res);
				res.sendRedirect("draft/groomer/svcItemList.jsp");
			}
		}

	}

}

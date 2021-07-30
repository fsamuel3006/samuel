package com.itemtype.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.item.model.ItemVO;
import com.itemtype.model.ItemTypeService;

@WebServlet("/back-end/Item/ItemTypeServlet")
public class ItemTypeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
				doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	    // 來自select_page.jsp的請求                                  // 來自 dept/listAllDept.jsp的請求
	if ("listItems_ByItemtId_A".equals(action) || "listItems_ByItemtId_B".equals(action)) {

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收請求參數 ****************************************/
			Integer itemtId = new Integer(req.getParameter("itemtId"));

			/*************************** 2.開始查詢資料 ****************************************/
			ItemTypeService deptSvc = new ItemTypeService();
			Set<ItemVO> set = deptSvc.getItemsByItemtId(itemtId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listItems_ByItemtId", set);    // 資料庫取出的list物件,存入request

			String url = null;
			if ("listItems_ByItemtId_A".equals(action))
				url = "/item/listItems_ByItemtId.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
			else if ("listEmps_ByDeptno_B".equals(action))
				url = "/item/listAllItemType.jsp";              // 成功轉交 dept/listAllDept.jsp

			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 ***********************************/
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}		
		
		
		
		
		
		
		
		
		
		
		
	}
}

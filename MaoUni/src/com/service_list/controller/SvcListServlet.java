package com.service_list.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.service_list.model.SvcListService;
import com.service_list.model.SvcListVO;

public class SvcListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8"); 
		
		String action = req.getParameter("action");
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		if("addService".equals(action)) {
			Integer groomerId = new Integer(req.getParameter("groomerId"));
			Integer svcId = new Integer(req.getParameter("svcId"));
			Integer price = new Integer(req.getParameter("price"));
			Integer svcTime = new Integer(req.getParameter("svcTime"));
			
			try {
				SvcListService svcListSvc = new SvcListService();
				svcListSvc.addService(groomerId, svcId, price, svcTime);
				
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
			}
		}
		
		
		if("getSvcList".equals(action)) {
			Integer groomerId = new Integer(req.getParameter("groomerId"));
			
			try {
				SvcListService svcListSvc = new SvcListService();
				List<SvcListVO> list = svcListSvc.getAll(groomerId);

				String jsonList = new JSONArray(list).toString();
				PrintWriter out = res.getWriter();
				out.print(jsonList);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
}

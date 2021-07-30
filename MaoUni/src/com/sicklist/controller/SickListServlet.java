package com.sicklist.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sick_list.model.ListService;
import com.sick_list.model.ListVO;

public class SickListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("GET_ALL_STMT".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

				Integer slId = new Integer(req.getParameter("slId").trim());
				ListService lSvc = new ListService();
				List<ListVO> listVO = lSvc.getAll(); 
				
				if (listVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("");
					failureView.forward(req, res);
					return;
				}


				req.setAttribute("listVO", listVO); 
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url); //
				successView.forward(req, res);

		}

		if ("GET_ONE_STMT".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer slId = new Integer(req.getParameter("slId").trim());

				ListService lSvc = new ListService();
				ListVO listVO = lSvc.findByPrimaryKey(slId);

				req.setAttribute("listVO", listVO); 
				String url = "/pet/listonePet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/login.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { 

			System.out.println("11111111111111111111111111111111");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			Integer slId = new Integer(req.getParameter("slId").trim());
			Integer slPetId = new Integer(req.getParameter("slPetId").trim());
			String slOther = req.getParameter("slOther").trim();

			ListVO listVO = new ListVO();
			
			listVO.setSlId(slId);
			listVO.setSlPetId(slPetId);
			listVO.setSlOther(slOther);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("listVO", listVO); 
				RequestDispatcher failureView = req.getRequestDispatcher("/member/login.jsp");
				failureView.forward(req, res);
				return; 
			}
			ListService lSvc = new ListService();
			lSvc.update(slId, slPetId ,slOther);

			req.setAttribute("listVO", listVO); 
			String url = "/member/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);

		}			
			
			if ("insert".equals(action)) { 

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {

					Integer slId = new Integer(req.getParameter("slId").trim());
					Integer slPetId = new Integer(req.getParameter("slPetId").trim());
					String slOther = req.getParameter("slOther").trim();

					ListVO listVO = new ListVO();
					
					listVO.setSlId(slId);
					listVO.setSlPetId(slPetId);
					listVO.setSlOther(slOther);


					ListService lSvc = new ListService();
					lSvc.insert(slId,slPetId,slOther);

					String url = "/member/login.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);

				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/member/login.jsp");
					failureView.forward(req, res);
				}
			}
	}
}




package com.variety.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.variety.model.*;

public class VarietyServlet  extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String str = req.getParameter("vaeId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請勿空白");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/variety/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer varId = null;
				try {
					varId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("請輸入品種編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/variety/select_page.jsp");
					failureView.forward(req, res);
					return;//中斷
				}
				
				
				VarietyService varSvc = new VarietyService();
				VarietyVO varietyVO = varSvc.getOnevarity(varId);
				if (varietyVO == null) {
					errorMsgs.add("格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/variety/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				

				req.setAttribute("varietyVO", varietyVO); 
				String url = "/variety/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);


			} catch (Exception e) {
				errorMsgs.add("查詢失敗" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {

				Integer varId = new Integer(req.getParameter("varId"));
				

				VarietyService varSvc = new VarietyService();
				VarietyVO varietyVO = varSvc.getOnevarity(varId);
								

				req.setAttribute("varietyVO", varietyVO);  
				String url = "/variety/update_variety_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);


			} catch (Exception e) {
				errorMsgs.add("更新失敗" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/variety/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {

				Integer varId = new Integer(req.getParameter("varId").trim());
				
				String varName = req.getParameter("varName");
				String varNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (varName == null || varName.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				} else if(!varName.trim().matches(varNameReg)) { 
					errorMsgs.add("請輸入...");
	            }
				
				String varKind = req.getParameter("varKind").trim();
				if (varKind == null || varKind.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				}	

//				Integer varId = new Integer(req.getParameter("varId").trim());
				
				VarietyVO varietyVO = new VarietyVO();
				varietyVO.setVarName(varName);
				varietyVO.setVarKind(varKind);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("varietyVO", varietyVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/variety/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				

				VarietyService varSvc = new VarietyService();
				varietyVO = varSvc.update(varName, varKind);
				

				req.setAttribute("varietyVO", varietyVO); 
				String url = "/variety/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);


			} catch (Exception e) {
				errorMsgs.add("修改資料失敗"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/variety/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String varName = req.getParameter("varName");
				String varNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (varName == null || varName.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				} else if(!varName.trim().matches(varNameReg)) { 
					errorMsgs.add("只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String varKind = req.getParameter("varKind").trim();
				if (varKind == null || varKind.trim().length() == 0) {
					errorMsgs.add("類別請無空白");
				}
				
				Integer varId = new Integer(req.getParameter("varId").trim());

				VarietyVO varietyVO= new VarietyVO();
				varietyVO.setVarName(varName);
				varietyVO.setVarKind(varKind);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("varietyVO", varietyVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/variety/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				VarietyService varSvc = new VarietyService();
				varietyVO = varSvc.INSERT_STMT(varName, varKind);
				
				String url = "/emp/listAllVariety.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/variety/addVariety.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {

				Integer varId = new Integer(req.getParameter("varId"));
				
				VarietyService varSvc = new VarietyService();
				varSvc.delete(varId);
										
				String url = "/variety/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/variety/listAllVariety.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

package com.backstage_staff.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
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
import com.backstage_staff.model.StaffService;
import com.backstage_staff.model.StaffVO;

/**
 * Servlet implementation class StaffServlet
 */
@WebServlet("/StaffServlet")
public class StaffServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insertStaff".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer id = new Integer(req.getParameter("id"));

				System.out.print(id);
				StaffService staffSvc = new StaffService();
				StaffVO staffVO = staffSvc.getOneStaff(id);

				req.setAttribute("StaffVO", staffVO);
				String url = "/back-end/staff/addStaff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer id = new Integer(req.getParameter("id").trim());
				String name = req.getParameter("name");

				System.out.println(name);

				String nameReg = "^[{\u4e00-\u9fa5)(a-zA-Z_)] {2,10}$";

				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("請輸入正確");
				} else if (!name.trim().matches(nameReg)) {
					errorMsgs.add("員工姓名:只能是中英文，且長度必須在2到10位數");
				}
				Integer status = null;
				try {
					status = new Integer(req.getParameter("status").trim());
					System.out.println(status);
				} catch (Exception e) {
					errorMsgs.add("狀態請確實填上");
				}
				String edu = req.getParameter("edu");

				System.out.println(edu);

				String eduReg = "^[{\\u4e00-\\u9fa5)(a-zA-Z_)] {2,15}$";
				if (edu == null || edu.trim().length() == 0) {
					errorMsgs.add("學歷只能是中英文混合沒有數字");
				} else if (!edu.trim().matches(eduReg)) {
					errorMsgs.add("請輸入完整且正確");
				}
				String cont = req.getParameter("cont");

				System.out.println(cont);

				String contReg = "^[{\\u4e00-\\u9fa5)(a-zA-Z_)] {2,15}$";
				if (cont == null || cont.trim().length() == 0) {
					errorMsgs.add("聯絡人沒有數字");
				} else if (!cont.trim().matches(contReg)) {
					errorMsgs.add("請輸入完整且正確");
				}
				Integer age = null;
				try {
					age = new Integer(req.getParameter("age").trim());

					System.out.println(age);

				} catch (Exception e) {

					errorMsgs.add("請填入數字");
				}
				String add = req.getParameter("add");

				System.out.println(add);

				String addReg = "^[{\u4e00-\u9fa5)(a-zA-Z0-9_)] {2,10}$";
				if (add == null || add.trim().length() == 0) {
					errorMsgs.add("地址請輸入正確");
				} else if (!add.trim().matches(addReg)) {
					errorMsgs.add("請輸入完整且正確");
				}
				String tel = req.getParameter("tel");

				String password = req.getParameter("password");

				System.out.println(password);

				String passwordReg = "^[{\u4e00-\u9fa5)(a-zA-Z_)] {2,15}$";

				if (password == null || password.trim().length() == 0) {
					errorMsgs.add("請輸入正確");
				} else if (!passwordReg.trim().matches(nameReg)) {
					errorMsgs.add("請輸入正確密碼");
				}
				
				String username = req.getParameter("username");

				System.out.println(username);

				String usernameReg = "^[{\u4e00-\u9fa5)(a-zA-Z_)] {2,15}$";

				if (username == null || username.trim().length() == 0) {
					errorMsgs.add("請輸入正確");
				} else if (!usernameReg.trim().matches(usernameReg)) {
					errorMsgs.add("請輸入正確密碼");
				}
				

				StaffVO staffVO = new StaffVO();
				staffVO.setId(id);
				staffVO.setStatus(status);
				staffVO.setName(name);
				staffVO.setAge(age);
				staffVO.setEdu(edu);
				staffVO.setAdd(add);
				staffVO.setTel(tel);
				staffVO.setPassword(password);
				staffVO.setUsername(username);

				StaffService StaffSvc = new StaffService();
				StaffSvc.addStaff(id, status, name, age, edu, add, cont, tel, password,username);
				String url = "/back-end/staff/allStaff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getAll_FOR_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				String str = req.getParameter("id");
				if (str == null || (str.trim().length() == 0)) {
					errorMsgs.add("請重新輸入");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
					failureView.forward(req, res);
					return;
				}
				Integer id = null;

				try {
					id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("請重新輸入");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/wrong.jsp");
					failureView.forward(req, res);
				}

				// 開始查詢資料
				StaffService staffSvc = new StaffService();
				StaffVO staffVO = staffSvc.getOneStaff(id);
				if (staffVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/allStaff.jsp");
					failureView.forward(req, res);
					return;
				}

				// 查詢完成準備轉交
				req.setAttribute("StaffVO", staffVO);
				String url = "/back-end/staff/allStaff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �N�{���X�����浹jsp�h�e�q
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/allStaff.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOneStaff".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer id = new Integer(req.getParameter("id"));
				System.out.print(id);
				StaffService staffSvc = new StaffService();
				StaffVO staffVO = staffSvc.getOneStaff(id);

				req.setAttribute("StaffVO", staffVO);
				String url = "/back-end/staff/getOneStaff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/allStaff.jsp");
				failureView.forward(req, res);
			}
		}

		if ("UpdateStaff".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer id = new Integer(req.getParameter("id"));
				System.out.print(id);
				StaffService staffSvc = new StaffService();
				StaffVO staffVO = staffSvc.getOneStaff(id);

				req.setAttribute("StaffVO", staffVO);
				String url = "/back-end/staff/updateStaff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/allStaff.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer id = new Integer(req.getParameter("id").trim());
				String name = req.getParameter("name");
				String nameReg = "^[{\u4e00-\u9fa5)(a-zA-Z_)] {2,10}$";

				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("請輸入正確");
					System.out.println(name);
				} else if (!name.trim().matches(nameReg)) {
					errorMsgs.add("員工姓名:只能是中英文，且長度必須在2到10位數");
				}
				Integer status = null;
				try {
					System.out.println(status);
					status = new Integer(req.getParameter("status").trim());

				} catch (Exception e) {
					errorMsgs.add("狀態請確實填上");
				}

				String edu = req.getParameter("edu");
				String eduReg = "^[{\\u4e00-\\u9fa5)(a-zA-Z_)] {2,15}$";
				if (edu == null || edu.trim().length() == 0) {
					System.out.println(edu);
					errorMsgs.add("學歷只能是中英文混合沒有數字");
				} else if (!edu.trim().matches(eduReg)) {
					errorMsgs.add("請輸入完整且正確");
				}
				String cont = req.getParameter("cont");
				String contReg = "^[{\\u4e00-\\u9fa5)(a-zA-Z_)] {2,15}$";
				if (cont == null || cont.trim().length() == 0) {
					System.out.println(cont);
					errorMsgs.add("聯絡人沒有數字");
				} else if (!cont.trim().matches(contReg)) {
					errorMsgs.add("請輸入完整且正確");
				}
				Integer age = null;
				try {
					age = new Integer(req.getParameter("age").trim());
					System.out.println(age);
				} catch (Exception e) {
					e.printStackTrace();
					errorMsgs.add("請填入數字");
				}
				String add = req.getParameter("add");
				String addReg = "^[{\u4e00-\u9fa5)(a-zA-Z0-9_)] {2,10}$";
				if (add == null || add.trim().length() == 0) {
					System.out.println(add);
					errorMsgs.add("地址請輸入正確");
				} else if (!add.trim().matches(addReg)) {
					errorMsgs.add("請輸入完整且正確");
				}
				String tel = req.getParameter("tel");
				
				String password = req.getParameter("password");
				System.out.println(password);
				String passwordReg ="^[{\u4e00-\u9fa5)(a-zA-Z0-9_)] {2,10}$";
				if (password == null || password.trim().length() == 0) {
					errorMsgs.add("請輸入正確");
				} else if (!passwordReg.trim().matches(nameReg)) {
					errorMsgs.add("請輸入正確信箱格式");
				}
				String username = req.getParameter("username");
				System.out.println(username);
				String usernameReg ="^[{\u4e00-\u9fa5)(a-zA-Z0-9_)] {2,10}$";
				if (username == null || username.trim().length() == 0) {
					errorMsgs.add("請輸入正確");
				} else if (!usernameReg.trim().matches(usernameReg)) {
					errorMsgs.add("請輸入正確信箱格式");
				}
				

				StaffVO staffVO = new StaffVO();
				staffVO.setId(id);
				staffVO.setName(name);
				staffVO.setAge(age);
				staffVO.setEdu(edu);
				staffVO.setAdd(add);
				staffVO.setCont(cont);
				staffVO.setStatus(status);
				staffVO.setTel(tel);
				staffVO.setPassword(password);
				staffVO.setUsername(username);

				StaffService StaffSvc = new StaffService();
				StaffSvc.updateStaff(id, name, status, add, age, cont, edu, tel, password,username);
				String url = "/back-end/staff/allStaff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/allStaff.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer id = new Integer(req.getParameter("id"));
				StaffService staffSvc = new StaffService();
				staffSvc.deleteStaff(id);
				String url = "/back-end/staff/allStaff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				System.out.println(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("資料刪除失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/staff/allStaff.jsp");
				failureView.forward(req, res);
			}

		}
		if("login".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);			
			String username = req.getParameter("username");
					String password = req.getParameter("password");
					String usernameReg = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
					String passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					
					if(username == null || username.trim().isEmpty()) {
						
						errorMsgs.put("username", "帳號請勿空白");
						req.setAttribute(username, "帳號錯誤");
						String url = "/back-end/staff/select.jsp";
						RequestDispatcher failureView = req.getRequestDispatcher(url);
						failureView.forward(req, res);						
					}else if(!username.trim().matches(usernameReg)) {
						errorMsgs.put("username", "請輸入有效的帳號");
						String url = "/back-end/staff/select.jsp";
						RequestDispatcher failureView = req.getRequestDispatcher(url);
						failureView.forward(req, res);
					}
					
					if(password == null || password.trim().isEmpty()) {
						errorMsgs.put("password", "密碼請勿空白");
						String url = "/back-end/staff/select.jsp";
						RequestDispatcher failureView = req.getRequestDispatcher(url);
						failureView.forward(req, res);
					}else if(!password.trim().matches(passwordReg)) {
						errorMsgs.put("password", "密碼長度限制6-20");
						String url = "/back-end/staff/select.jsp";
						RequestDispatcher failureView = req.getRequestDispatcher(url);
						failureView.forward(req, res);
					}
					if(!errorMsgs.isEmpty()) {
						req.getSession().setAttribute(username, password);
						req.getRequestDispatcher("/back-end/staff/select.jsp").forward(req, res);
						HttpSession session =req.getSession();
						session.setAttribute(username, password);
									
					}
					StaffService staffSvc = new StaffService();
					StaffVO staffVO = staffSvc.findByMGnameAndPassword(username, password);
					String url = "/back-end/staff/backImage.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					HttpSession session =req.getSession();
					session.setAttribute(username, password);
		}
					
	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
	}

}

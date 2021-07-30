package com.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.tribes.util.Arrays;
import com.member.model.MemberJDBCDAO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import javax.servlet.http.HttpServletRequest;

public class MemberServlet extends HttpServlet {
	public static final long SerialVersionUID = 1l;
//	public MemberJDBCDAO memberjdbcdao;
//
//	public void init() {
//
//		memberjdbcdao = new MemberJDBCDAO();
//
//	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action: " + action);

		if ("findByPrimaryKey".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer str = new Integer(req.getParameter("memId"));

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) { // 如果字串長度>0就開始回傳
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/addMember.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/

				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.findByPrimaryKey(str);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memberVO", memberVO);
				String url = "/back-end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/member_select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// 修改用 => 後台_listAllMember.jsp的請求 
	    if ("getOne_For_Update".equals(action)) {
	    	//集合存在請求範圍中，發送 Error
	    	List<String> errorMsgs = new LinkedList<String>();
	    	req.setAttribute("errorMsgs", errorMsgs);
	    	
	    	try {
	          //1.接收請求參數
	    		Integer memId = new Integer(req.getParameter("memId"));
	    		
	    	 //2.開始查詢資料
	    		MemberService memSvc = new MemberService();
			    MemberVO memberVO = memSvc.getOneMember(memId);
			    
			 //3.查詢完成,準備轉交
			    req.setAttribute("memberVO", memberVO);
			    String url = "/back-end/member/Update_member.jsp";
			    RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			//其他可能的錯誤處理
	    	}catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}		
		
		
		
		if ("update".equals(action)) { // 來自請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String memName = req.getParameter("memName");
				String memEmail = req.getParameter("memEmail").trim();
				String memPassword = req.getParameter("memPassword");
				String memIdenity = req.getParameter("memIdenity").trim();
				String memGender = req.getParameter("memGender");
				Integer memPh = new Integer(req.getParameter("memPh").trim()); // 因為前端輸入文字時都是String，因此需要轉型
				String memAddres = req.getParameter("memAddres").trim();
				Date memBirthday = java.sql.Date.valueOf(req.getParameter("memBirthday")); // Date要用這個方式輸入進去
				Integer memPosition = new Integer(req.getParameter("memPosition").trim());
				Integer memReserve = new Integer(req.getParameter("memReserve").trim());
				Integer memSurvive = new Integer(req.getParameter("memSurvive").trim());
				Integer memId = new Integer(req.getParameter("memId"));

				MemberVO memberVO = new MemberVO();
				memberVO.setMemName(memName);
				memberVO.setMemEmail(memEmail);
				memberVO.setMemPassword(memPassword);
				memberVO.setMemIdenity(memIdenity);
				memberVO.setMemGender(memGender);
				memberVO.setMemPh(memPh);
				memberVO.setMemAddres(memAddres);
				memberVO.setMemBirthday(memBirthday);
				memberVO.setMemPosition(memPosition);
				memberVO.setMemReserve(memReserve);
				memberVO.setMemSurvive(memSurvive);
				memberVO.setMemId(memId);

				/*************************** 2.開始查詢資料 ****************************************/
				MemberService memSvc = new MemberService();
				memSvc.update(memberVO);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/member/update_member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/update_member.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println(action);
			String memName = req.getParameter("memName");
			String memEmail = req.getParameter("memEmail").trim();
			String memPassword = req.getParameter("memPassword");
			String memIdenity = req.getParameter("memIdenity").trim();
			String memGender = req.getParameter("memGender");
			Integer memPh = new Integer(req.getParameter("memPh").trim()); // 因為前端輸入文字時都是String，因此需要轉型
			String memAddres = req.getParameter("memAddres").trim();
			Date memBirthday = java.sql.Date.valueOf(req.getParameter("memBirthday")); // Date要用這個方式輸入進去
			Integer memPosition = new Integer(req.getParameter("memPosition").trim());
			Integer memReserve = new Integer(req.getParameter("memReserve").trim());
			Integer memSurvive = new Integer(req.getParameter("memSurvive").trim());
//			Timestamp memUpdate = java.sql.Timestamp.valueOf(req.getParameter("memUpdate").trim());

//			MemberVO memberVO = new MemberVO();
//			memberVO.setMemName(memName); //比對用的
//			memberVO.setMemEmail(memEmail);
//			memberVO.setMemPassword(memPassword);
//			memberVO.setMemIdenity(memIdenity);
//			memberVO.setMemGender(memGender);
//			memberVO.setMemPh(memPh);
//			memberVO.setMemAddres(memAddres);
//			memberVO.setMemBirthday(memBirthday);
//			memberVO.setMemPosition(memPosition);
//			memberVO.setMemReserve(memReserve);
//			memberVO.setMemSurvive(memSurvive);
//			memberVO.setMemUpdate(memUpdate);
			try {
				MemberService memSvc = new MemberService();
				memSvc.insert(memName, memEmail, memPassword, memIdenity, memGender, memPh, memAddres, memBirthday,
						memPosition, memReserve, memSurvive);

//			/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAll.jsp，讓頁面轉跳
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}

//------------------------------------------------------

		if ("delete".equals(action)) { // 來自listAll.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// 將集合儲存再請求範圍內，以防發生錯誤
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer memId = new Integer(req.getParameter("memId"));

				/*************************** 2.開始刪除資料** *************************************/
				MemberService memSvc = new MemberService();
				memSvc.delete(memId);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/member/listAllmember.jsp"; // 給轉跳的頁面放的位子
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}

//以下為登入及會員註冊等等//		

		if ("findByUseremailAndpassword".equals(action)) { // 來自login.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String memEmail = new String(req.getParameter("memEmail")); // 獲取表單提交的email
			String memPassword = new String(req.getParameter("memPassword")); // 獲取表單提交的密碼
			MemberService memSvc = new MemberService(); // 把service串聯過來,就可以調用之前寫的對比帳密的方法了
			MemberVO memberVO = memSvc.findByUseremailAndpassword(memEmail, memPassword);
			HttpSession session = req.getSession();

			if (memberVO != null) {// 先做簡單的對比找出有的資料
				String url = req.getContextPath() + "/front-end/home/HomePage.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);

				session.setAttribute("memEmail", memEmail); // 確認有這個帳號後存入session中，以備登出用
				session.setAttribute("MemberVO", memberVO);
				res.sendRedirect(url);
				// session.setAttributec會在一個時間點保留存的值
//				successView.forward(req, res);	

			} else {
				errorMsgs.add("帳號密碼有誤，請重新輸入");
				String url = "/back-end/member/addMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			}
		}

		if ("OutUser".equals(action)) {

//			req.getSession().invalidate();
//			HttpSession session = req.getSession();
//			String mem = (String)session.getAttribute("memberVO"); 
////			HttpSession session = req.getSession();
//			session.removeAttribute(mem);
			HttpSession session = req.getSession();
			session.invalidate();
			
			res.sendRedirect(req.getContextPath() + "/front-end/home/HomePage.jsp");
			
//			String url = req.getContextPath() + "/front-end/Home/HomePage.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
			// login的session存了甚麼就移除甚麼
			// session.removeAttribute();用於清空指定屬性的session，它的,session還是原來的session
			// session.invalidate();直接清空當前session的所有相關資訊，就摧毀
		}
		System.out.println(action);
		if ("signup".equals(action)) {
System.out.println("ccccccccccccccccc");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String memName = req.getParameter("memName");
			String memEmail = req.getParameter("memEmail").trim();
			String memPassword = req.getParameter("memPassword");
			String memIdenity = req.getParameter("memIdenity").trim();
			String memGender = req.getParameter("memGender");
			Integer memPh = new Integer(req.getParameter("memPh").trim()); // 因為前端輸入文字時都是String，因此需要轉型
			String memAddres = req.getParameter("memAddres").trim();
			Date memBirthday = java.sql.Date.valueOf(req.getParameter("memBirthday")); // Date要用這個方式輸入進去

			try {
				MemberService memSvc = new MemberService(); // 呼叫serverc方法
				memSvc.signup(memName, memEmail, memPassword, memIdenity, memGender, memPh, memAddres, memBirthday);

//		/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = req.getContextPath() + "/front-end/Home/HomePage.jsp";
				res.sendRedirect(req.getContextPath() + "/front-end/home/HomePage.jsp");
//				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
}

//				req.setAttribute("memberVO");
//				String url = "/member/member_select_page2.jsp"; // 給轉跳的頁面放的位子
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功後轉交
//				successView.forward(req, res);	

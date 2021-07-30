package com.tracking_adopt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backstage_staff.model.StaffService;
import com.backstage_staff.model.StaffVO;
import com.tracking_adopt.model.TrackingService;
import com.tracking_adopt.model.TrackingVO;


@WebServlet("/TrackingController")
public class TrackingController extends HttpServlet {
	

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req,res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
			//從主鍵做查詢的寫法
		
				if("getOne_For_Display".equals(action)) {
					List<String>errorMsgs=new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
				try {
					//這邊接收請求參數-輸入格式錯誤處理
					String id = req.getParameter("id");
					if(id==null||id.trim().length()==0){
						errorMsgs.add("請重新輸入");
					}
					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView=req.getRequestDispatcher("");
						failureView.forward(req, res);
						return;
					}
					
					//2.這邊開始查詢認養追蹤資料
					TrackingService trackingSvc=new TrackingService();
					TrackingVO trackingVO = trackingSvc.getOneTracking(Integer.parseInt(id));
					if(trackingVO==null) {
						errorMsgs.add("查無資料");
					}
					if(!errorMsgs.isEmpty()) {
						RequestDispatcher failureView=req.getRequestDispatcher("");
						return;
					}
					//3.認養追蹤的ID查詢送出
					req.setAttribute("TrackingVO",trackingVO);
					String url="";
					RequestDispatcher successView=req.getRequestDispatcher(url);
					successView.forward(req, res);
				}catch(Exception e) {
					errorMsgs.add("無法取得資料"+e.getMessage());
					RequestDispatcher failureView=req.getRequestDispatcher("");
					failureView.forward(req, res);
				}
				}
					//新的查詢-有關於修改的寫法
				if("getOne_For_Update".equals(action)) {
					List<String>errorMsgs=new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					try{
						//這是接收請求的參數
						Integer id=new Integer(req.getParameter("id"));
						Integer det=new Integer(req.getParameter("det"));
						//開始查詢資料
						TrackingService trackingSvc = new TrackingService();
						TrackingVO trackingVO=trackingSvc.addTracking(id, det);
						//查詢完成準備轉交給網頁呈現給使用者
						req.setAttribute("TrackingVO", trackingVO);
						String url="";
						RequestDispatcher successView=req.getRequestDispatcher(url);
						successView.forward(req, res);
					}catch(Exception e){
						errorMsgs.add("查無資料"+e.getMessage());
						RequestDispatcher failureView=req.getRequestDispatcher("");
						failureView.forward(req, res);
					}
				}
					//這是修改的寫法
					if("update".equals(action)) {
						List<String>errorMsgs=new LinkedList<String>();
						req.setAttribute("errorMsgs", errorMsgs);
						try{     //如果接受錯誤的輸入處理
							Integer id =new Integer(req.getParameter("id").trim());   //這裡要處理detail要修改成已認養或未認養的code
							//Integer DET=new Integer();
							
							String det=req.getParameter("det");
							if(det==null||det.trim().length()==0) {
								errorMsgs.add("認養請勿空白");
								if(id==null){
									errorMsgs.add("編號請勿空白");
								}
								}
							if(!errorMsgs.isEmpty()) {
							TrackingService trackingSvc = new TrackingService();
							TrackingVO trackingVO=trackingSvc.updateTracking(id, Integer.parseInt(det));
							req.setAttribute("TrackingVO", trackingVO);
							RequestDispatcher failureView=req.getRequestDispatcher("");
							failureView.forward(req, res);
							return;
						}
						//修改資料
							TrackingService trackingSvc=new TrackingService();
							TrackingVO trackingVO=trackingSvc.updateTracking(id, Integer.parseInt(det));
						req.setAttribute("TrackingVO", "trackingVO");
						String url="";
						RequestDispatcher successView=req.getRequestDispatcher(url);
						successView.forward(req, res);				
						} catch(Exception e) {
							errorMsgs.add("修改資料失敗"+e.getMessage());
							RequestDispatcher failureView=req.getRequestDispatcher("");
							failureView.forward(req,res);
						}
			}
						
					//加入資料
					if("insert".equals(action)) {
						List<String>errorMsgs=new LinkedList<String>();
						try {
							
							Integer id=new Integer(req.getParameter("id"));
							Integer det = new Integer(req.getParameter("det"));
							System.out.print(id);
							TrackingService trackingSvc=new TrackingService();
							TrackingVO trackingVO=trackingSvc.addTracking(id, det);
							
							req.setAttribute("TrackingVO",  trackingVO);
							String url ="/front-end/adopt/adopt_home.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url);
							successView.forward(req,res);
						}catch(Exception e){
							errorMsgs.add("無法取得資料"+e.getMessage());
							RequestDispatcher failureView=req.getRequestDispatcher("/back-end/staff/wrong.jsp");
							failureView.forward(req, res);
						}
					}
	
					if("insertall".equals(action)) {
						List<String>errorMsgs=new LinkedList<String>();
						try {
													
							Integer id=new Integer(req.getParameter("id"));
							Integer det = new Integer(req.getParameter("det"));
							System.out.print(id);
							TrackingService trackingSvc=new TrackingService();
							Integer completeNum =trackingSvc.addTracking(id, det);
							
							PrintWriter out = res.getWriter();
							out.write(completeNum);
							out.flush();
							out.close();
							
//							String url ="/front-end/adopt/adopt_home.jsp";
//							RequestDispatcher successView = req.getRequestDispatcher(url);
//							successView.forward(req,res);
						}catch(Exception e){
							errorMsgs.add("無法取得資料"+e.getMessage());
							RequestDispatcher failureView=req.getRequestDispatcher("/back-end/staff/wrong.jsp");
							failureView.forward(req, res);
						}
					}
	
	
	
	}
}

//				if("delete".equals(action)) {
//					List<String>errorMsgs=new LinkedList<String>();
//					req.setAttribute("errorMsgs",errorMsgs);
//					try {
//						Integer ID=new Integer(req.getParameter("Id"));
//						DetailService DetailSvc=new DetailService();
//						DetailSvc.addDetail(id, det);
//						String url="";
//						RequestDispatcher successView=req.getRequestDispatcher(url);
//						successView.forward(req, res);
//					}catch (Exception e) {
//						errorMsgs.add("刪除資料失敗"+e.getMessage());
//						RequestDispatcher failureView=req.getRequestDispatcher("");
//						failureView.forward(req, res);
					
	
package com.gschedule.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.gschedule.model.SchService;
import com.gschedule.model.SchVO;


public class SchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain");
		res.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		
		if("autoInsertData".equals(action)) {
			SchService schSvc = new SchService();
			try {
				schSvc.autoInsertData();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
		
		if("getByGroomerId".equals(action)) {
			Integer groomerId = new Integer(req.getParameter("groomerId"));
			SchService schSvc = new SchService();
			
			try {
				List<SchVO> list = schSvc.getByGroomerId(groomerId);
				String jsonList = new JSONArray(list).toString();
				PrintWriter out = res.getWriter();
				out.print(jsonList);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
		}
		
//		if("getSchStatus".equals(action)) {
//			Integer schId = new Integer(req.getParameter("schId"));
//			SchService schSvc = new SchService();
//			try {
//				SchVO schVO = schSvc.getOneDaySchdule(schId);
//				PrintWriter out = res.getWriter();
//				String schStatus = schVO.getSchStatus();
//				out.print(schStatus);
//			} catch (IOException e) {
//				e.printStackTrace(System.err);
//			}
//
//		}
//		
		if("getSchStatusByDate".equals(action)) {
			String apmDate = req.getParameter("apmDate");
			Integer groomerId = new Integer(req.getParameter("groomerId"));
			SchService schSvc = new SchService();
			try {
				SchVO schVO = schSvc.getOneDaySchdule(apmDate, groomerId);
				String schVOjson = new JSONObject(schVO).toString();
				
				PrintWriter out = res.getWriter();
				out.write(schVOjson);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
		}
	}

}

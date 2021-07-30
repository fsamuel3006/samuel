package com.report_grooming.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.report_grooming.model.GrepService;


public class GrepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/plain");
		res.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");

		if("addGreport".equals(action)) {
			Integer apmId = new Integer(req.getParameter("apmId"));
			Integer memId = new Integer(req.getParameter("memId"));
			Integer groomerId = new Integer(req.getParameter("groomerId"));
			String content = req.getParameter("content");
			
			GrepService grepSvc = new GrepService();
			Integer completeNum = null;

			try {
				completeNum = grepSvc.addGreport(apmId, memId, groomerId, content);
				
				PrintWriter out = res.getWriter();
				
				out.write(completeNum.toString());
				out.flush();
				out.close();
				
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}

		if("update".equals(action)) {		
			Integer rptId = new Integer(req.getParameter("rptId"));
			Integer rptStatus = new Integer(req.getParameter("rptStatus"));
			Integer groomerId = new Integer(req.getParameter("groomerId"));
			String message = "";
			GrepService grepSvc = new GrepService();
			try {
				message += grepSvc.update(rptId, rptStatus, groomerId);
				
				PrintWriter out = res.getWriter();
				out.write(message);
				out.flush();
				out.close();
				
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
}

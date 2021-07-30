package com.appointment_form_details.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.appointment_form_details.model.ApmdService;
import com.appointment_form_details.model.ApmdVO;


public class ApmdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8"); 
		
		if("getByAppointmentId".equals(action)){
			Integer apmId = new Integer(req.getParameter("apmId"));
			ApmdService apmdSvc = new ApmdService();
			try {
				List<ApmdVO> list = apmdSvc.getByAppointmentId(apmId);
				String listJson = new JSONArray(list).toString();
				PrintWriter out = res.getWriter();
				out.print(listJson);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace(System.err);
			}
		}
		
		
		
	}

}

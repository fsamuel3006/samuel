package com.tracking_groomer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tracking_groomer.model.GtraService;


public class GtraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("addGroomerTraking".equals(action)) {
			Integer memId = new Integer(req.getParameter("memId"));
			Integer groomerId = new Integer(req.getParameter("groomerId"));
			
			try {
				GtraService gtraSvc = new GtraService();
				gtraSvc.addGroomerTraking(memId, groomerId);
				
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}

		}
	}

}

//package com.addressGeo.controller;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.addressGeo.model.GeocodingService;
//
//
//@WebServlet("/GeocodingServlet")
//public class GeocodingServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
//	}
//
//	
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//			req.setCharacterEncoding("UTF-8");
//			String action = req.getParameter("action");
//			
//			if("addGeo".equals(action)) {
//				GeocodingService geoSvc = new GeocodingService();
//				Integer memId = new Integer(req.getParameter("memId"));
//				String address = req.getParameter("address");
//				
//				try {
//					geoSvc.addGeo(memId, address);
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//
//	}
//
//}

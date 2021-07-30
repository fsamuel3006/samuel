package com.adopt_imf.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adopt_imf.model.ImfService;
import com.adopt_imf.model.ImfVO;
import com.sun.xml.internal.bind.v2.model.core.ID;

@WebServlet("/ImfPhotoServlet")
public class ImfPhotoServlet extends HttpServlet {
	
  
      
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	
		ServletOutputStream out = res.getOutputStream();
		
			
			String AI_ID = req.getParameter("AI_ID").trim();
			ImfService imfService = new ImfService();
			ImfVO oneImf = imfService.getOneImf(Integer.parseInt(AI_ID));
			byte[] photo = oneImf.getPhoto();
			out.write(photo);
			
    }
	
		
	
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}

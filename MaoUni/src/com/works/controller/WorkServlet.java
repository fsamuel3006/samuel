package com.works.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.works.model.WorkService;
import com.works.model.WorkVO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class WorkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("uploadItems".equals(action)) {
			
			Integer groomerId = new Integer(req.getParameter("groomerId"));
			try {
				WorkService workSvc = new WorkService();
				Set<Part> parts = new HashSet(req.getParts());
				
				
				for(Part part: parts) {
					if("item".equals(part.getName())) {
						InputStream fin = part.getInputStream();
						byte[] item = new byte[fin.available()];
						fin.read(item);
						workSvc.addItem(groomerId, item);
						fin.close();
					}
				}
				
				
				// 檢查上傳的檔案類型是否為照片檔
				res.sendRedirect("front-end/groomer/groomer_infoEdit.jsp");
				
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
		
		if("deleteItem".equals(action)) {
			res.setContentType("text/html; charset=UTF-8");
			Integer wid = new Integer(req.getParameter("wid"));
			
			try {
				WorkService workSvc = new WorkService();
				workSvc.delete(wid);
				
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
}

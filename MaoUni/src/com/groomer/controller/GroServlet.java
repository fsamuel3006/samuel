package com.groomer.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONArray;

import com.groomer.model.GroService;
import com.groomer.model.GroVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class GroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("addGroomer".equals(action)) {
			String url = "/";
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// memId和name之後會自動帶入會員資料，並將input改為disable，因此無檢查格式之必要
				// 由前端進行基本檢查如：數字、字串、是否輸入空白、上傳檔案類型
				Integer memId = new Integer(req.getParameter("memId"));
				String gname = req.getParameter("gname");
				String center = req.getParameter("center");
				Integer grange = new Integer(req.getParameter("grange"));

				InputStream licenseIn = req.getPart("license").getInputStream();
				InputStream pcrcIn = req.getPart("pcrc").getInputStream();
				InputStream fidIn = req.getPart("fid").getInputStream();
				InputStream bidIn = req.getPart("bid").getInputStream();

				byte[] license = new byte[licenseIn.available()];
				byte[] pcrc = new byte[pcrcIn.available()];
				byte[] fid = new byte[fidIn.available()];
				byte[] bid = new byte[bidIn.available()];

				licenseIn.read(license);
				pcrcIn.read(pcrc);
				fidIn.read(fid);
				bidIn.read(bid);

				// 防呆機制，捕捉getParameter參數錯字
				if (memId == null || gname == null || center == null || grange == null) {
					errorMsgs.put("Parameter", "Parameter is null !");
				}

				GroService grosvc = new GroService();
				grosvc.addGroomer(memId, gname, center, grange, license, pcrc, fid, bid);
				req.setAttribute("success", "新增成功");
//				RequestDispatcher successView = req.getRequestDispatcher(url);	
//				successView.forward(req, res);
				res.sendRedirect("front-end/groomer/groomer_application.jsp");	// 需改成導向首頁
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher(url);	
//				failureView.forward(req, res);
				res.sendRedirect("front-end/groomer/groomer_application.jsp");	// 需改成導向首頁
			}

		}

		if ("findPhotosBygmId".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			;
			try {
				Integer groomerId = Integer.parseInt(req.getParameter("groomerId"));
				GroService groSvc = new GroService();
				Map<String, String> photos = groSvc.findPhotosBygmId(groomerId);

				
				req.setAttribute("photos", photos);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/groomer/groomerList.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groomer/groomerList.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updateStatus".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				GroService groSvc = new GroService();
				Integer groomerId = Integer.parseInt(req.getParameter("groomerId"));
				Integer gstatus = Integer.parseInt(req.getParameter("gstatus"));
				groSvc.updateStatus(groomerId, gstatus);

				req.setAttribute("upsatesuccess", "update success!");
//				RequestDispatcher successView = req.getRequestDispatcher("/back-end/groomer/groomerList.jsp");
//				successView.forward(req, res);
				res.sendRedirect("back-end/groomer/groomerList.jsp");
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groomer/groomerList.jsp");
//				failureView.forward(req, res);
				res.sendRedirect("back-end/groomer/groomerList.jsp");
			}

		}
		
		
		if ("getListByCondition".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if(map == null) {
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map", map); // 供設置分頁時用
					map = map1;
				}
				
				GroService groSvc = new GroService();
				List<GroVO> list = groSvc.getAll(map);
	
				req.setAttribute("list", list);
				req.setAttribute("search", "true");
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/groomer/groomerList.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/groomer/groomerList.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("updateInfo".equals(action)) {
			Integer groomerId = new Integer(req.getParameter("groomerId"));
			System.out.println(groomerId);
			String gname = req.getParameter("gname");
			String[] schDate = req.getParameterValues("schDate");
			Integer  schStartTime = new Integer(req.getParameter("schStartTime"));
			Integer schEndTime = new Integer(req.getParameter("schEndTime"));
			InputStream avatarIn = req.getPart("avatar").getInputStream();
			String intro = req.getParameter("intro");
			
			try {
				byte[] avatar = new byte[avatarIn.available()];
				avatarIn.read(avatar);
				
				GroService groSvc = new GroService();
				groSvc.updateInfo(groomerId, gname, schDate, schStartTime, schEndTime, avatar, intro);
				
				res.sendRedirect("front-end/groomer/groomer_infoEdit.jsp");
				
				
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}

	}

//	public String getFileNameFromPart(Part part) {
//		String header = part.getHeader("content-disposition");
//		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		if(filename.length() == 0) {
//			return null;
//		}
//		return filename;
//	}

}

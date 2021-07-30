package com.article.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.article.model.*;
import com.article.model.ArtService;
import com.article.model.ArtVO;

//���Ѥ峹
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ArtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ArtServlet() {
        super();
    }

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//��x(��@�d��)_getOne_For_Display => �Ӧ�select_page_art.jsp���ШD
		
		if ("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//�ШD�Ѽ�--��J�榡���~�B�z
				String str = req.getParameter("id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�峹�s��");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page_art.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer id = null;
				try {
					id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�峹�s���榡�����T");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page_art.jsp");
					failureView.forward(req, res);
					return;
				}
				
			//�}�l�d�߸��_getOne_For_Display
				ArtService artSvc = new ArtService();
				ArtVO artVO = artSvc.getOneByID(id);
				
				  if (artVO == null) {
					   errorMsgs.add("�d�L���");
				  }
				  if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/article/select_page_art.jsp");
						failureView.forward(req, res);
						return;
					}
				  
			//�d�ߧ��� �A�ǳ����_getOne_For_Display => listOneArt.jsp
				  req.setAttribute("artVO", artVO);
				  String url = "/back-end/article/listOneArt.jsp";
				  
				  RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
				
		    //��L�i����~���B�z_getOne_For_Display
			      } catch (Exception e) {
				     errorMsgs.add("�L�k���o���:" + e.getMessage());
				     RequestDispatcher failureView = req
						  .getRequestDispatcher("/back-end/article/select_page_art.jsp");
				     failureView.forward(req, res);
			    }
		   }
		
//��x(�ק�)_getOne_For_Update => �Ӧ� listAllArt.jsp ���ШD	
		
		if ("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//�����ШD�Ѽ�_getOne_For_Update
				
				   Integer id = new Integer(req.getParameter("id"));
				
				//�}�l�d�߸��_getOne_For_Update
				   
				  ArtService artSvc = new ArtService();
				  ArtVO artVO = artSvc.getOneByID(id);
				  
				 //�d�ߧ���,�ǳ����_getOne_For_Update => update_art_input.jsp
				
				  req.setAttribute("artVO", artVO); 
				  String url = "/back-end/article/update_art_input.jsp";
				  RequestDispatcher successView = req.getRequestDispatcher(url);
				  successView.forward(req, res);
				
			    // ��L�i�઺���~�B�z_getOne_For_Update
				  
			      }catch (Exception e) {
				      errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				      RequestDispatcher failureView = req
						         .getRequestDispatcher("/back-end/article/listAllArt.jsp");
				      failureView.forward(req, res);
			       }
		}
		
//�e�x(��@�d��)_getOne_For_Check => �Ӧ� listAllArt_f.jsp ���ШD	
		
				if ("getOne_For_Check".equals(action)) {
					
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					
					try {
						//�����ШD�Ѽ�_getOne_For_Update
						
						   Integer id = new Integer(req.getParameter("id"));
						
						//�}�l�d�߸��_getOne_For_Update
						   
						  ArtService artSvc = new ArtService();
						  ArtVO artVO = artSvc.getOneByID(id);
						  
						 //�d�ߧ���,�ǳ����_getOne_For_Update => update_art_input.jsp
						
						  req.setAttribute("artVO", artVO); 
						  String url = "/front-end/article/listOneArt_f.jsp";
						  RequestDispatcher successView = req.getRequestDispatcher(url);
						  successView.forward(req, res);
						
					    // ��L�i�઺���~�B�z_getOne_For_Update
						  
					      }catch (Exception e) {
						      errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
						      RequestDispatcher failureView = req
								         .getRequestDispatcher("/front-end/article/listOneArt_f.jsp");
						      failureView.forward(req, res);
					       }
				}
		
//��x(�ק�)_update => �Ӧ� update_art_input.jsp ���ШD	
		
		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				//�����ШD�Ѽ� - ��J�榡�����~�B�z_update
				 //�峹id
				Integer id = new Integer(req.getParameter("id").trim());
				
				 //����id
				Integer artt_id = new Integer(req.getParameter("artt_id").trim());
				
				 //�峹�W��
				String name = req.getParameter("name");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50000}$";
				
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("�峹�W��: �ФŪť�");
				} else if(!name.trim().matches(enameReg)) { 
					errorMsgs.add("�峹�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��500����");
	            }
				
				 //�峹�Ϥ�
				InputStream inP = req.getPart("pic").getInputStream();
				 byte[] pic = new byte[inP.available()];
				
				 inP.read(pic);
				 inP.close();
				 
				 //�峹���e
				 String  contnt = req.getParameter("contnt");
				 String contntReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,500}$";
					
					if (contnt == null || contnt.trim().length() == 0) {
						errorMsgs.add("�峹���e: �ФŪť�");
					} else if(!contnt.trim().matches(enameReg)) { 
						errorMsgs.add("�峹���e: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��500����");
		            }
				
				 //�o���ɶ�
					java.sql.Date update = null;
					try {
						update = java.sql.Date.valueOf(req.getParameter("update").trim());
					} catch (IllegalArgumentException e) {
						update=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("�п�J���!");
					}
					
					ArtVO artVO = new ArtVO();
					artVO.setId(id);
					artVO.setArtt_id(artt_id);
					artVO.setName(name);
					artVO.setPic(pic);
					artVO.setContnt(contnt);
					artVO.setUpdate(update);
					
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("artVO", artVO); 
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/article/update_art_input.jsp");
						failureView.forward(req, res);
						return; 
					}
					
				//�}�l�ק���__update
					ArtService artSvc = new ArtService();
					artVO = artSvc.updateArt( id, artt_id ,name , pic ,contnt, update );
					
				//�ק粒��,�ǳ����__update => listOneArt.jsp
					req.setAttribute("artVO", artVO);
					String url = "/back-end/article/listOneArt.jsp";
					
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					
				//��L�i�઺���~�B�z__update
			       } catch (Exception e) {
				      errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				      RequestDispatcher failureView = req
						        .getRequestDispatcher("/back-end/article/update_art_input.jsp");
				       failureView.forward(req, res);
			         }
		 }
			
		
//��x(�s�W)_insert => �Ӧ� addArt.jsp ���ШD		
		
		 if ("insert".equals(action)) {
			 
			 List<String> errorMsgs = new LinkedList<String>();
			 req.setAttribute("errorMsgs", errorMsgs);
			 
			 try {
				// �����ШD�Ѽ� - ��J�榡�����~�B�z_insert
				  //����id
				 Integer artt_id = new Integer(req.getParameter("artt_id").trim());
				 
				 //�峹�W��
				 String name = req.getParameter("name");
				 String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,500}$";
					
					if (name == null || name.trim().length() == 0) {
						errorMsgs.add("�峹�W��: �ФŪť�");
					} else if(!name.trim().matches(enameReg)) { 
						errorMsgs.add("�峹�W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��500����");
		            }
					
				  //�峹�Ϥ�
					InputStream inP = req.getPart("pic").getInputStream();
					
					byte[] pic = new byte[inP.available()];
					
					inP.read(pic);
					inP.close();
					
				  //�峹���e
					String  contnt = req.getParameter("contnt");
					String contntReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,500}$";
						
					if (contnt == null || contnt.trim().length() == 0) {
							errorMsgs.add("�峹���e: �ФŪť�");
					} else if(!contnt.trim().matches(enameReg)) { 
							errorMsgs.add("�峹���e: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��500����");
			          }
					
				  //�o���ɶ�
					java.sql.Date update = null;
					try {
						update = java.sql.Date.valueOf(req.getParameter("update").trim());
					} catch (IllegalArgumentException e) {
						update=new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("�п�J���!");
					}
					
					ArtVO artVO = new ArtVO();
					artVO.setArtt_id(artt_id);
					artVO.setName(name);
					artVO.setPic(pic);
					artVO.setContnt(contnt);
					artVO.setUpdate(update);
				 
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("artVO", artVO); 
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/article/addArt.jsp");
						failureView.forward(req, res);
						return;
					}
				 
				//�}�l�s�W���_insert
					ArtService artSvc = new ArtService();
					artVO = artSvc.addArt( artt_id ,name , pic ,contnt, update );
				
			    //�s�W����,�ǳ����_insert => listAllArt.jsp
					String url = "/back-end/article/listAllArt.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				 
			    //��L�i�઺���~�B�z
				 
			       } catch (Exception e) {
					     errorMsgs.add(e.getMessage());
					     RequestDispatcher failureView = req
							     .getRequestDispatcher("/back-end/article/addArt.jsp");
					     failureView.forward(req, res);
				     }
		 }
			
//��x(�R��)_delete => �Ӧ� listAllArt.jsp ���ШD	
		 
		 if ("delete".equals(action)) {
			 
			 List<String> errorMsgs = new LinkedList<String>();
			 req.setAttribute("errorMsgs", errorMsgs);
			 
			 try {
				 //�����ШD�Ѽ�_delete
				 Integer id = new Integer(req.getParameter("id"));
				 
				 //�}�l�R�����_delete
				 ArtService artSvc = new ArtService();
				 artSvc.deleteArt(id);
				 
				 //�R������,�ǳ����_delete =>
				 String url = "/back-end/article/listAllArt.jsp";
				 RequestDispatcher successView = req.getRequestDispatcher(url);
				 successView.forward(req, res);
				 
				// ��L�i�઺���~�B�z
			       }catch (Exception e) {
					     errorMsgs.add("�R����ƥ���:"+e.getMessage());
					     RequestDispatcher failureView = req
							    .getRequestDispatcher("/emp/listAllEmp.jsp");
					     failureView.forward(req, res);
		            }
			
		   }
		 
		
	}
}

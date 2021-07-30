	package com.announcemet.controller;
	
	import java.io.*;
	import java.util.*;
	import java.io.InputStream;
	
	import javax.servlet.*;
	import javax.servlet.http.*;
	
	import javax.servlet.annotation.MultipartConfig;
	//import com.announcement.model.*;
	import com.announcemet.model.AnnService;
	import com.announcemet.model.AnnVO;
	
	
	@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
	public class AnnServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    public AnnServlet() {
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
			
			
// ��@�d��_�Ӧ�select_page.jsp���ШD (��x������@�d��)
			if ("getOne_For_Display".equals(action)) { 
	
				List<String> errorMsgs = new LinkedList<String>();
				// �N���X�x�s�b�ШD�d��
				// �o�e���~.
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					                                         //1.�����ШD�Ѽ� - ��J�榡�����~�B�z
					String str = req.getParameter("id");
					                                         //trim() �r��}�Y�ε����h���Ů��
					                                         //��^�@�ӷs���r��C�o�Ӧr��N�R���F��l�r���Y���M�������Ů�
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("�п�J���i�s��");
					}
					// �p�G�����~�A�бN�A���o�e�^���
					//isEmpty() �p�Glength()��0����k��^true�A�_�h��^false
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/announcemet/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
					
					Integer id = null;
					try {
						id = new Integer(str);
					} catch (Exception e) {
						errorMsgs.add("���i�s���榡�����T");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/announcemet/select_page.jsp");
						failureView.forward(req, res);
						                                    //�{�����_
						return;
					}
                                                            //2.��d�߸�� getOne_For_Display
					AnnService annSvc = new AnnService();
					AnnVO annVO = annSvc.getOneAnn(id);
					if (annVO == null) {
						errorMsgs.add("�d�L���");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/announcemet/select_page.jsp");
						failureView.forward(req, res);
						return;//�{�����_
					}
			                                                 //3.�d�ߧ���,�ǳ����(Send the Success view)	
					req.setAttribute("annVO", annVO);
					                                                   // ���\��� listOneEmp.jsp
					String url = "/back-end/announcemet/listOneAnn.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
					
			                                                     //��L�d�ߥi�઺���~�B�z	
			   }catch (Exception e) {
					errorMsgs.add("�L�k���o���i�s�����:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcemet/select_page.jsp");
					failureView.forward(req, res);
				}
			}
			
			
// ��@�d�� => �e�x_ listAllAnnf.jsp���ШD (�e�x������@�d��)
						if ("getOne_For_front".equals(action)) { 
				
							List<String> errorMsgs = new LinkedList<String>();
							                                           // �N���X�x�s�b�ШD�d�� �B�o�e���~.
							req.setAttribute("errorMsgs", errorMsgs);
					
							try {
								                                    //1.�����ШD�Ѽ� - ��J�榡�����~�B�z
								String str = req.getParameter("id");
								                                    //trim() �r��}�Y�ε����h���Ů��
								                                    //��^�@�ӷs���r��C�o�Ӧr��N�R���F��l�r���Y���M�������Ů�
							if (str == null || (str.trim()).length() == 0) {
								errorMsgs.add("�п�J���i�s��");
							}
								                           // �p�G�����~�A�бN�A���o�e�^���
								                          //isEmpty() �p�Glength()��0����k��^true�A�_�h��^false
							if (!errorMsgs.isEmpty()) {
								RequestDispatcher failureView = req
										.getRequestDispatcher("/front-end/announcemet/listAllAnnf.jsp");
								failureView.forward(req, res);
									return;//�{�����_
							}
								
							Integer id = null;
							try {
								id = new Integer(str);
							} catch (Exception e) {
								errorMsgs.add("���i�s���榡�����T");
							}
							if (!errorMsgs.isEmpty()) {
								RequestDispatcher failureView = req
									.getRequestDispatcher("/front-end/announcemet/listAllAnnf.jsp");
								failureView.forward(req, res);
									         //�{�����_
								return;
							}
			                                                            //2.��d�߸�� getOne_For_Display
							AnnService annSvc = new AnnService();
							AnnVO annVO = annSvc.getOneAnn(id);
							if (annVO == null) {
								errorMsgs.add("�d�L���");
							}
							if (!errorMsgs.isEmpty()) {
								RequestDispatcher failureView = req
										.getRequestDispatcher("/front-end/announcemet/listAllAnnf.jsp");
								failureView.forward(req, res);
								return;//�{�����_
							}
				                                                      //3.�d�ߧ���,�ǳ����(Send the Success view)	
							req.setAttribute("annVO", annVO);
			                                                                      // ��� 
				 			String url = "/front-end/announcemet/listOneAnnf.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); 
							successView.forward(req, res);
								
			                                        //��L�d�ߥi�઺���~�B�z	
						  }catch (Exception e) {
							errorMsgs.add("�L�k���o���i�s�����:" + e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher("/front-end/announcemet/listAllAnnf.jsp");
							failureView.forward(req, res);
						}
					}
	
				
						
// �ק�� => ��x_listAllAnn.jsp���ШD 
			    if ("getOne_For_Update".equals(action)) {
			    	//���X�s�b�ШD�d�򤤡A�o�e Error
			    	List<String> errorMsgs = new LinkedList<String>();
			    	req.setAttribute("errorMsgs", errorMsgs);
			    	
			    	try {
			          //1.�����ШD�Ѽ�
			    		Integer id = new Integer(req.getParameter("id"));
			    		
			    	 //2.�}�l�d�߸��
			    		AnnService annSvc = new AnnService();
					    AnnVO annVO = annSvc.getOneAnn(id);
					    
					 //3.�d�ߧ���,�ǳ����
					    req.setAttribute("annVO", annVO);
					    String url = "/back-end/announcemet/update_ann_input.jsp";
					    RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						
					//��L�i�઺���~�B�z
			    	}catch (Exception e) {
						errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/announcemet/listAllAnn.jsp");
						failureView.forward(req, res);
					}
				}
			    
				
//��x =>�ק� _�Ӧ�update_ann_input.jsp���ШD
			    if ("update".equals(action)) {
			    	List<String> errorMsgs = new LinkedList<String>();
			    	req.setAttribute("errorMsgs", errorMsgs);
			    	
			    	try {
                                                                               //1.�����ШD�Ѽ� - ��J�榡�����~�B�z
			    		Integer id = new Integer(req.getParameter("id").trim());
			    		
			    		String content = req.getParameter("content");
			    		String contentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,5000}$";
			    		
			    		if (content == null || content.trim().length() == 0) {
							errorMsgs.add("���i���e: �ФŪť�");
						} else if(!content.trim().matches(contentReg)) { 
							errorMsgs.add("���i���e: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
			            }
			    		
			    		java.sql.Date update = null;
			    		
						try {
							update = java.sql.Date.valueOf(req.getParameter("update").trim());
						} catch (IllegalArgumentException e) {
							update=new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("�п�J���!");
						}
						
                                                                                //�Ϥ��B�z�W��
                        InputStream inP = req.getPart("pic").getInputStream();
						 byte[] pic = new byte[inP.available()];
						
						 inP.read(pic);
						 inP.close();
					
						AnnVO annVO = new AnnVO();
						annVO.setId(id);
						annVO.setContent(content);
						annVO.setUpdate(update);
						annVO.setPic(pic);
			    		
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("annVO", annVO); // �t����J�榡���~��annVO����,�]�s�Jreq
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/announcemet/update_ann_input.jsp");
							failureView.forward(req, res);
							return; //�{�����_
						}
						
					//2.�}�l�ק���
						 AnnService annSvc = new AnnService();
						 annVO = annSvc.updateAnn(id, content, update,pic);
			    		
					//3.�ק粒��,�ǳ����
						 req.setAttribute("annVO", annVO);
						 String url = "/back-end/announcemet/listOneAnn.jsp";
						 RequestDispatcher successView = req.getRequestDispatcher(url);
						 successView.forward(req, res);
						 
					// ��L�i�઺���~�B�z 
			    	}catch (Exception e) {
						errorMsgs.add("�ק��ƥ���:"+e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/announcemet/update_ann_input.jsp");
						failureView.forward(req, res);
			         }
			     }
			    
				
//��x =>�s�W��_�Ӧ�addAnn.jsp���ШD 
			    if ("insert".equals(action)) {  
			    	
			    	List<String> errorMsgs = new LinkedList<String>();
			    	req.setAttribute("errorMsgs", errorMsgs);
			    	
			    	try {
			    		//1.�����ШD�Ѽ� - ��J�榡�����~�B�z
			    		String content = req.getParameter("content");
						String contentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,500}$";
						
						if (content == null || content.trim().length() == 0) {
							errorMsgs.add("���i���e: �ФŪť�");
						} else if(!content.trim().matches(contentReg)) { 
							errorMsgs.add("���i���e: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��50����");
			              }
						
						java.sql.Date update = null;
						
						try {
							update = java.sql.Date.valueOf(req.getParameter("update").trim());
						} catch (IllegalArgumentException e) {
							update=new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("�п�ܤ��!");
						}
						                                                        //�Ϥ��W��
						InputStream inP = req.getPart("pic").getInputStream();
						
						byte[] pic = new byte[inP.available()];
						
						 inP.read(pic);
						 inP.close();
						
						 
						AnnVO annVO = new AnnVO();
						annVO.setContent(content);
						annVO.setUpdate(update);
						annVO.setPic(pic);
						
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("annVO", annVO); 
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/announcemet/addAnn.jsp");
							failureView.forward(req, res);
							return;
						}
						
					                                               //2.�}�l�s�W���
						AnnService annSvc = new AnnService();
						annVO = annSvc.addAnn(content, update, pic);
					                                                           //3.�s�W����,�ǳ����
						String url = "/back-end/announcemet/listAllAnn.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); 
						successView.forward(req, res);
						
					                                      //��L�s�W�i�઺���~�B�z
			    	}catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/announcemet/addAnn.jsp");
						failureView.forward(req, res);
					}
			    	
			    }
			    
		  
 //��x =>�R��_������_listAllAnn.jsp�ШD
			    if ("delete".equals(action)) {
			    	
			    	List<String> errorMsgs = new LinkedList<String>();
			    	req.setAttribute("errorMsgs", errorMsgs);
			    	
			    	try {
			    		//1.�����ШD�Ѽ�
			    		Integer id = new Integer(req.getParameter("id"));
			    		
			    		//2.�}�l�R�����3
			    		AnnService annSvc = new AnnService();
						annSvc.deleteAnn(id);
						
						//3.�R������,�ǳ����
						String url = "/back-end/announcemet/listAllAnn.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						
			    	}catch (Exception e) {
						errorMsgs.add("�R����ƥ���:"+e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/announcemet/listAllAnn.jsp");
						failureView.forward(req, res);
			    	
			          }
				
			    }
		 }		
	
	}

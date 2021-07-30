package com.item.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.item.model.*;

@WebServlet("/back-end/Item/ItemServlet")
public class ItemServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
				doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自Item_select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("itemId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/item_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer itemId = null;
				try {
					itemId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("商品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/item_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ItemService itemSvc = new ItemService();
				ItemVO itemVO = itemSvc.getOneItem(itemId);
				if (itemVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/item_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("itemVO", itemVO); // 資料庫取出的itemVO物件,存入req
				String url = "/back-end/item/listOneItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneItem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/item/listOneItem.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		
		
		
		if ("getName_For_Display".equals(action)) { // 來自Item_select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("itemName");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品名稱");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/item_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String itemName = null;
				try {
					itemName = new String(str);
				} catch (Exception e) {
					errorMsgs.add("商品名稱格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/item_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ItemService itemSvc = new ItemService();
				ItemVO itemVO = itemSvc.getOneItem(itemName);
				if (itemVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/item_select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("itemVO", itemVO); // 資料庫取出的itemVO物件,存入req
				String url = "/back-end/Item/listOneItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneItem.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/item/listOneItem.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
		
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllItem.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer itemId = new Integer(req.getParameter("itemId"));

				/***************************2.開始修改資料****************************************/
				ItemService itemSvc = new ItemService();
				ItemVO itemVO = itemSvc.getOneItem(itemId);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("itemVO", itemVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/item/update_item_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_item_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/item/listAllItem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_item_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.print("OK");
			
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer itemId = new Integer(req.getParameter("itemId"));
						
				
				
				Integer itemTypeId = null;
				try {
					itemTypeId = new Integer(req.getParameter("itemTypeId").trim());
					System.out.print(itemTypeId);
				} catch (NumberFormatException e) {
					itemTypeId = 0;
					errorMsgs.add("商品類別請填數字! 1:毛孩食品, 2:毛孩玩具, 3:毛孩傢俱, 4:毛孩衣物, 5:毛孩清潔, 6:毛孩保養");
				} if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/update_item_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}			
				
				
				String itemPetType = req.getParameter("itemPetType");
				System.out.print(itemPetType);
				String itemPetTypeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,1}$";
				if (itemPetType == null || itemPetType.trim().length() == 0) {
					errorMsgs.add("商品寵物類別: 請勿空白");
				} else if(!itemPetType.trim().matches(itemPetTypeReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品寵物類別: 只能是貓或狗");
	            } if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/update_item_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				
				String itemName = req.getParameter("itemName");
				System.out.print(itemName);
				String itemNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,45}$";
				if (itemName == null || itemName.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if(!itemName.trim().matches(itemNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到45之間");
	            } if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/update_item_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				String itemContent = req.getParameter("itemContent").trim();
				System.out.print(itemContent);
				String itemContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,255}$";
				if (itemContent == null || itemContent.trim().length() == 0) {
					errorMsgs.add("商品內容請勿空白");
				} if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/update_item_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				

				Integer itemPrice = null;
				try {
					itemPrice = new Integer(req.getParameter("itemPrice").trim());
					System.out.print(itemPrice);
				} catch (NumberFormatException e) {
					itemPrice = 0;
					errorMsgs.add("商品價格請填數字");
				} if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/update_item_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				
				Integer itemAmount = null;
				try {
					itemAmount = new Integer(req.getParameter("itemAmount").trim());
					System.out.print(itemAmount);
				} catch (NumberFormatException e) {
					itemAmount = 0;
					errorMsgs.add("商品數量請填數字");
				}  if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/update_item_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				
				Integer itemStatus = null;
				try {
					itemStatus = new Integer(req.getParameter("itemStatus").trim());
					System.out.print(itemStatus);
				} catch (NumberFormatException e) {
					itemStatus = 0;
					errorMsgs.add("修改狀態請填1個數字即可, 狀態代號參考  0:草稿, 1:公開, 2:隱藏");
				} if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/update_item_input.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				

				ItemVO itemVO = new ItemVO();
				
				itemVO.setItemId(itemId);
				itemVO.setItemTypeId(itemTypeId);
				itemVO.setItemPetType(itemPetType);
				itemVO.setItemName(itemName);
				itemVO.setItemContent(itemContent);
				itemVO.setItemPrice(itemPrice);
				itemVO.setItemAmount(itemAmount);
				itemVO.setItemStatus(itemStatus);
	


		//		 Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("itemVO", itemVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/update_item_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
			
				
				
				
				/***************************2.開始修改資料*****************************************/
				ItemService itemSvc = new ItemService();
//				itemVO = itemSvc.updateItem(itemId, itemTypeId, itemPetType,itemName, itemContent, itemPrice, itemAmount, itemStatus);
				itemSvc.updateItem(itemId, itemTypeId, itemPetType,itemName, itemContent, itemPrice, itemAmount, itemStatus);
				itemVO = itemSvc.getOneItem(itemId);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("itemVO", itemVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/item/listOneItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneItem.jsp
				successView.forward(req, res);
				

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/item/update_item_input.jsp");
				failureView.forward(req, res);
			}
				

		}

        if ("insert".equals(action)) { // 來自addItem.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer itemTypeId = null;
				try {
					itemTypeId = new Integer(req.getParameter("itemTypeId").trim());
				} catch (NumberFormatException e) {
					itemTypeId = 0;
					errorMsgs.add("商品類別請填數字! 1:毛孩食品, 2:毛孩玩具, 3:毛孩傢俱, 4:毛孩衣物, 5:毛孩清潔, 6:毛孩保養");
				} if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/addItem.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}				
				
				
				String itemPetType = req.getParameter("itemPetType");
				System.out.print(itemPetType);
				String itemPetTypeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,1}$";
				if (itemPetType == null || itemPetType.trim().length() == 0) {
					errorMsgs.add("寵物類別: 請勿空白");
				} else if(!itemPetType.trim().matches(itemPetTypeReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("寵物類別: 只能是貓或狗");
	            } if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/addItem.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}			
				
				String itemName = req.getParameter("itemName");
				String itemNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,45}$";
				if (itemName == null || itemName.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if(!itemName.trim().matches(itemNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到45之間");
	            } if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/addItem.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}			
				
				
				String itemContent = req.getParameter("itemContent").trim();
				String itemContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{10,255}$";
				if (itemContent == null || itemContent.trim().length() == 0) {
					errorMsgs.add("商品內容請勿空白");
	            } if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/addItem.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}			
				

				Integer itemPrice = null;
				try {
					itemPrice = new Integer(req.getParameter("itemPrice").trim());
				} catch (NumberFormatException e) {
					itemPrice = 0;
					errorMsgs.add("商品價格請填數字");
				} if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/addItem.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}			

				
				Integer itemAmount = null;
				try {
					itemAmount = new Integer(req.getParameter("itemAmount").trim());
				} catch (NumberFormatException e) {
					itemAmount = 0;
					errorMsgs.add("商品數量請填數字");
				} if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/item/addItem.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}			

				

				
				
//				java.sql.Date itemUpdate = null;
//				try {
//					itemUpdate = java.sql.Date.valueOf(req.getParameter("itemUpdate").trim());
//				} catch (IllegalArgumentException e) {
//					itemUpdate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請修改日期!");
//				}
				

				ItemVO itemVO = new ItemVO();
				
				itemVO.setItemTypeId(itemTypeId);
				itemVO.setItemPetType(itemPetType);
				itemVO.setItemName(itemName);
				itemVO.setItemContent(itemContent);
				itemVO.setItemPrice(itemPrice);
				itemVO.setItemAmount(itemAmount);
			

				 if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/item/addItem.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}	
				
				/***************************2.開始新增資料***************************************/
				ItemService itemSvc = new ItemService();
				itemVO = itemSvc.addItem(itemTypeId, itemPetType, itemName, itemContent, itemPrice, itemAmount);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/item/listAllItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/item/addItem.jsp");
				failureView.forward(req, res);
				e.printStackTrace();
			}
		}
		
        
        
		if ("listItem_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				
				Map<String, String[]> map = req.getParameterMap();
				
				/***************************2.開始複合查詢***************************************/
				ItemService itemSvc = new ItemService();
				List<ItemVO> list  = itemSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listItem_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/item/listItem_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/item/item_select_page.jsp");
				failureView.forward(req, res);
			}
		}	    
        
	}
}

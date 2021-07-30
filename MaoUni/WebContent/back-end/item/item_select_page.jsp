<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<jsp:useBean id="itemTypeSvc" scope="page" class="com.itemtype.model.ItemTypeService" />
<jsp:useBean id="itemSvc" scope="page" class="com.item.model.ItemService" />

<html style="height: auto;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>商城管理首頁</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu.css"> 
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu2.css"> 




<style>
	.btn{
		background: #e8c497;
      	color: #fff;
	}
	.btn:hover {
      	background: #ffe6c7;
      	color: #fff;
   }
	.pagination>li.active>a {
	  	background: #d6b286;
	    color: #fff;
	}
	.pagination>li>a {
	    color: #d6b286;
	}
	.pagination>li>a:hover {
	  	background: #fff;
	    color: #5c3316;
	}
	.col-md-6 .pagination>li>a,
	.col-md-6 .pagination>li>span {
  	  	border: 1px solid #d6b286;
	}
	.pagination>.active>a:hover{
    	background-color: #e6c195;
    	border: solid 1px #e6c195;
	}
	a.btsp{
		font-size: 35px;
		color: #e8c497;
		font-weight: bold;
		margin-left: -5px;
	}
	a.btsp:hover, a.btsp:active{
		color: #ffe6c7;
		text-decoration: none;
	}
	p.allitemtitle{
		color:#fff;
		font-weight: bold;
	}
	input.details{
		background-color: #bfbfbf;
		color: #fff;
	}
	input.details:hover{
		background-color: #e8c497;
	}
	input.update{
		background-color: #bfbfbf;
		color: #fff;
	}	
	input.update:hover{
		background-color: #e8c497;
	}
	.sontrue{
	background-color: #bfbfbf;
	color: #fff;
	border:5px;
	border-radius:5px;
	}
	.sontrue:hover{
	background-color: #e8c497;
	}

</style>
</head>

<body style="height: auto;"><!-- 步驟二 -->
   <!-- 以下 >>隱藏式菜單_內容 -->
     <header>
         <span class="toggle-button" style="margin-left:-5px;margin-top:-7px;">
             <div class="menu-bar menu-bar-top"></div>
             <div class="menu-bar menu-bar-middle"></div>
             <div class="menu-bar menu-bar-bottom"></div>
         </span>
         <div class="menu-wrap">
             <div class="menu-sidebar" style="margin-top:20px;">
                 <ul class="menu">
                     <li><a href="#">會員資料管理</a></li>
                     <li><a href="#">商品訂單管理</a></li>
                     <li><a href="ItemHomePage.jsp">商城管理</a></li>
                     <li><a href="#">商城客服管理</a></li>
                     <li><a href="#">討論區管理</a></li>
                     <li><a href="#">浪浪找家管理</a></li>
                     <li><a href="#">知識站管理</a></li>
                     <li><a href="#">公告管理</a></li>
                     <li><a href="#">美容師管理</a></li>
                     <li><a href="#">美容預約檢舉管理</a></li>
                 </ul>
             </div>
         </div>
     </header> 
 <!-- 以上 >>隱藏式菜單_內容 --> 
 
 
 
	<!-- id="wrapper"這段是包住整個頁面的大外框，因為有用margin-left，所以目前位置剛好卡在漢堡(隱藏式菜單)的右邊，此行刪掉會跑版 --> 
    <div id="wrapper" style="margin-left: 120px;height: auto;">
        <div class="d-flex flex-column" id="content-wrapper" style="margin-right:5px;">
            
            	 <!-- 以下範圍為最上方包著商城管理以及右方後台人員的區塊, -->
                <nav class="navbar navbar-light navbar-expand bg-white mb-4 topbar static-top">
                    <div class="container-fluid" style="margin-top:23px;margin-left:-6px;">
<!--請看這行最右邊-->  <a class="btsp" href="itemHomePage.jsp">商城管理</a>  <!-- 這行是商城管理的標題，可以自行設定，還有href可以自行設定跳轉的頁面 -->
                       	 <ul class="nav navbar-nav flex-nowrap ml-auto" style="margin-top:-10px;">                         
                           
                            <li class="nav-item dropdown no-arrow" style="margin-top:10px">
                                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#">
                                	<span class="backperson" style="font-size:15px;font-weight:700;margin-right:5px;">後台人員</span>
                                	<img class="border rounded-circle img-profile" style="widht:40px;height:40px;margin-top:-5px;" src="<%=request.getContextPath()%>/resources/images/items/MaoUniICON.png"></a>
                                    <div class="dropdown-menu dropdown-menu-right animated--grow-in">
                                    	<a class="dropdown-item" href="#" style="font-weight:700;color:#646464;">Logout</a>
                                    </div>
	                   			</div>
	                    	</li>
	                    </ul>
	            	</div>
	            </nav>

			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			  <div class="card" style="margin-left:-8px; margin-right: 25px;">
			 
			      <div class="card-header py-3" style="background-color:#e8c497;margin-top:-20px">
			          <p class="allitemtitle" style="margin: 1px;font-size: 20px;">查詢商品資料</p>
			      </div>
			      
			        <div class="card-body" style="height: auto;">
			            <div class="row">
			
			            </div>
			            <div class="table-responsive table mt-2" id="dataTable-1" role="grid" aria-describedby="dataTable_info">
		           							
								<ul>  
								  <li>   
								    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/Item/ItemServlet">
								        
								     <table>   
								        <tr>
									        <td>商品編號：</td>
									        <td><input type="text" name="ITEM_ID" value="1"  placeholder="請輸入商品編號"></td>
								        </tr>
								        <tr>   
									       	<td>商品名稱：</td>
									       	<td><input type="text" name="ITEM_NAME" value="貓咪坐墊"  placeholder="請輸入商品名稱"></td>
								       	</tr>
								       	<tr>
									       	<td>寵物類別：</td>
									       	<td><input type="text" name="ITEM_PET_TYPE" value="貓"  placeholder="請輸入寵物類別"></td>
								       	</tr>
								       	<tr>
									       	<td>商品類別：</td>
									       	<td>
										        <select size="1" name="ITEMT_ID" >
										          <option value="" selected>請選擇商品類別</option>
										         <c:forEach var="itemTypeVO" items="${itemTypeSvc.all}" > 
										          <option value="${itemTypeVO.itemtId}">${itemTypeVO.itemtName}</option>
										         </c:forEach>   
										        </select>
									        </td>
								       	</tr>
								       </table> 
							       			<input class="sontrue" type="submit" value="送出" style="margin-left:275px;margin-top:20px;">
									        <input type="hidden" name="action" value="listItem_ByCompositeQuery">
								     </FORM>
								  			<a href="addItem.jsp">新增商品</a>
								  </li>
								</ul>
						</div> <!-- class="table-responsive" 結尾標籤 -->
				</div> <!-- class="card-body" 結尾標籤 -->			
			</div> <!-- class="card" 結尾標籤 -->	
		</div> <!-- class="d-flex" 結尾標籤 -->
     </div> <!-- Class="Wrapper"的結尾標籤 -->



    <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<!-- 步驟三 -->  
<!-- 隱藏式菜單_連結--> 
<!--底下是jsp 裡面所連結打法可以參考一下-->
    <script src="<%=request.getContextPath()%>/resources/js/hidden_menu3.js"></script> 
    <script src="<%=request.getContextPath()%>/resources/js/hidden_menu4.js"></script>
    
</body>
</html>
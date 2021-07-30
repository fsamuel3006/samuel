<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.item.model.*"%>

<%
  ItemVO itemVO = (ItemVO) request.getAttribute("itemVO"); //ItemServlet.java(Controller), 存入req的itemVO物件
%>

<html style="height: auto;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>hidden_menu</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu.css"> 
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu2.css"> 

<%--以下為匯出至excel表單的功能 --%>
<script>
    function exportTableToExcel(tableID, filename = ''){
    var downloadLink;
    var dataType = 'application/vnd.ms-excel';
    var tableSelect = document.getElementById(tableID);
    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');
    
    // Specify file name
    filename = filename?filename+'.xls':'excel_data.xls';
    
    // Create download link element
    downloadLink = document.createElement("a");
    
    document.body.appendChild(downloadLink);
    
    if(navigator.msSaveOrOpenBlob){
        var blob = new Blob(['\ufeff', tableHTML], {
            type: dataType
        });
        navigator.msSaveOrOpenBlob( blob, filename);
    }else{
        // Create a link to the file
        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;
    
        // Setting the file name
        downloadLink.download = filename;
        
        //triggering the function
        downloadLink.click();
    }
}
</script>
<%--以上為匯出至excel表單的功能 --%>


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
 
 
 
    <div id="wrapper" style="margin-left: 120px;height: auto;">
        <div class="d-flex flex-column" id="content-wrapper" style="margin-right:5px;">
            
            	 <!-- 以下範圍為最上方包著商城管理以及右方後台人員的區塊, -->
                <nav class="navbar navbar-light navbar-expand bg-white mb-4 topbar static-top">
                    <div class="container-fluid" style="margin-top:23px;margin-left:-6px;">
<!--請看這行最右邊-->  <a class="btsp" href="ItemHomePage.jsp">商城管理</a>  <!-- 這行是商城管理的標題，可以自行設定，還有href可以自行設定跳轉的頁面 -->
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
		            <p class="allitemtitle" style="margin: 1px;font-size: 20px;">所有商品資料</p>
		        </div>
			        <div class="card-body" style="height: auto;">
			            <div class="row">
			                <div class="col-md-6 text-nowrap">
			                    <div id="dataTable_length" class="dataTables_length" aria-controls="dataTable" style="margin-left:4px;margin-top:5px;"><label>Show&nbsp;
			                    <select class="form-control form-control-sm custom-select custom-select-sm"><option value="10" selected="">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select>&nbsp;</label>
			                    <button onclick="exportTableToExcel('dataTable')" class="btn" type="button" style="margin-left: 75px;">匯出EXCEL</button>
			                    </div>
			                </div>
			                <div class="col-md-6">
			                    <div class="text-md-right dataTables_filter" id="dataTable_filter"><label><input type="search" class="form-control form-control-sm" aria-controls="dataTable" placeholder="Search" style="margin-top:5px;maring-left:-22px;width:200px;"></label></div>
			                </div>
			            </div>
			            <div class="table-responsive table mt-2" id="dataTable-1" role="grid" aria-describedby="dataTable_info">

							<table class="table my-0" id="dataTable">
							  <thead>
								<tr>
		                            <th style="width: 75px;text-align: center;">商品編號</th>
		                            <th style="width: 75px;text-align: center;">商品類別</th>
		                            <th style="width: 77px;text-align: center;">寵物類別</th>
		                            <th style="width: 109px;text-align: center;">商品名稱</th>
		                            <th style="width: 80px;text-align: center;">商品內容</th>
		                            <th style="width: 80px;text-align: center;">商品價格</th>
		                            <th style="width: 80px;text-align: center;">商品數量</th>
		                            <th style="width: 80px;text-align: center;">商品狀態</th>
		                            <th style="width: 88px;text-align: center;">更新時間</th>
		                            <th style="width: 80px;text-align: center;">修改資料</th>
								</tr>
							  </thead>
				                                
								<tbody>
									
								
									<tr style="width: 70px;text-align: center;">
										<td style="width: 75px;text-align: center;">${itemVO.itemId}</td>
										<c:if test="${itemVO.itemTypeId == '1'}"><td scope="col" class="itemTypeId" style="width: 75px;text-align: center;">毛孩食品</td></c:if>
										<c:if test="${itemVO.itemTypeId == '2'}"><td scope="col" class="itemTypeId" style="width: 75px;text-align: center;">毛孩玩具</td></c:if>
										<c:if test="${itemVO.itemTypeId == '3'}"><td scope="col" class="itemTypeId" style="width: 75px;text-align: center;">毛孩傢俱</td></c:if>
										<c:if test="${itemVO.itemTypeId == '4'}"><td scope="col" class="itemTypeId" style="width: 75px;text-align: center;">毛孩衣物</td></c:if>
										<c:if test="${itemVO.itemTypeId == '5'}"><td scope="col" class="itemTypeId" style="width: 75px;text-align: center;">毛孩清潔</td></c:if>
										<c:if test="${itemVO.itemTypeId == '6'}"><td scope="col" class="itemTypeId" style="width: 75px;text-align: center;">毛孩保養</td></c:if>
										<td style="width: 77px;text-align: center;">${itemVO.itemPetType}</td>
										<td style="width: 109px;text-align: center;">${itemVO.itemName}</td>
										<td style="width: 80px;text-align: center;"><input class=details style="border:5px;border-radius:5px;" type="button" value="詳細內容" onclick="location.href='ItemDetails.jsp'"></td>    <!--${itemVO.itemContent}-->
										<td style="width: 80px;text-align: center;">${itemVO.itemPrice}</td>
										<td style="width: 80px;text-align: center;">${itemVO.itemAmount}</td> 
										<c:if test="${itemVO.itemStatus == '0'}"><td scope="col" class="itemStatus" style="width: 75px;text-align: center;">待上架</td></c:if>
										<c:if test="${itemVO.itemStatus == '1'}"><td scope="col" class="itemStatus" style="width: 75px;text-align: center;">上架中</td></c:if>
										<c:if test="${itemVO.itemStatus == '2'}"><td scope="col" class="itemStatus" style="width: 75px;text-align: center;">已下架</td></c:if>
										<td style="width: 88px;text-align: center;"><fmt:formatDate value="${itemVO.itemUpdate}" pattern="yyyy-MM-dd"/></td>
										<td>	
										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/Item/ItemServlet" style="margin-bottom: 0px;">
										     <input class=update type="submit" value="立即修改" style="border:5px;border-radius:5px;">
										     <input type="hidden" name="itemId"  value="${itemVO.itemId}">
										     <input type="hidden" name="action"	value="getOne_For_Update">
										  </FORM>
										</td>
									</tr>
																		
								</tbody>
							</table>
					</div> <!-- class="table-responsive" 結尾標籤 -->
				</div> <!-- class="card-body" 結尾標籤 -->			
			</div> <!-- class="card" 結尾標籤 -->	
		</div> <!-- class="d-flex" 結尾標籤 -->	


			<div class="row">
				<div class="col-md-6"></div>
				<div class="col-md-6"">
				   <nav class="d-lg-flex justify-content-lg-end dataTables_paginate paging_simple_numbers">
				       <ul class="pagination">
				           <li><a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
				           <li class="active"><a class="page-link" href="#">1</a></li>
				           <li><a class="page-link" href="#">2</a></li>
				           <li><a class="page-link" href="#">3</a></li>
				           <li style="margin-right:25px;"><a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
				       </ul>
				   </nav>
				</div>
        </div> <!-- class="row"的結尾標籤-->
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
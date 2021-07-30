<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.pet.model.*"%>


<%
	PetService memSvc = new PetService();
	List<PetVO> list = memSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>所有毛孩資料</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/bootstrap.min.css">
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
	body{
	overflow-x:hidden; <!-- 此處做整個BODY橫向的隱藏多出的寬度 -->
	}


</style>
</head>

<body style="height: auto;">

     <!-- 以下為隱藏式菜單內容 -->
     <header>
         <span class="toggle-button" style="margin-left:-5px;margin-top:-7px;">
             <div class="menu-bar menu-bar-top"></div>
             <div class="menu-bar menu-bar-middle"></div>
             <div class="menu-bar menu-bar-bottom"></div>
         </span>
         <div class="menu-wrap">
             <div class="menu-sidebar" style="margin-top:20px;">
                 <ul class="menu">
                     <li><a href="<%=request.getContextPath()%>/back-end/member/listAllMember.jsp">會員資料管理</a></li>
                     <li><a href="<%=request.getContextPath()%>/back-end/obuy/obuyAll.jsp">商品訂單管理</a></li>
                     <li><a href="#">商城管理</a></li>
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
     <!-- 以上為隱藏式菜單內容 --> 

    <div id="wrapper" style="margin-left: 120px;height: auto;">
        <div class="d-flex flex-column" id="content-wrapper" style="margin-right:5px;">
            
            	 <!-- 以下範圍為最上方包著商城管理以及右方後台人員的區塊, -->
                <nav class="navbar navbar-light navbar-expand bg-white mb-4 topbar static-top">
                    <div class="container-fluid" style="margin-top:23px;margin-left:-6px;">
<!--請看這行最右邊-->  <a class="btsp" href="item_select_page.jsp">毛孩管理</a>  <!-- 這行是商城管理的標題，可以自行設定，還有href可以自行設定跳轉的頁面 -->
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
				<!-- 以上範圍為最上方包著商城管理以及右方後台人員的區塊, --> 


				<!-- 以下為商城管理下面那個長方形,包含駝色表頭(所有商品資料) + 下面空白區域 -->
			    <div class="card" style="margin-left:-8px; margin-right: 25px;">
			    
			        <div class="card-header py-3" style="background-color:#e8c497;margin-top:-20px">
			            <p class="allitemtitle" style="margin: 1px;font-size: 20px;">所有毛孩資訊</p>
			        </div>
			    
 <table class="table my-0" id="dataTable">
	<tr>
		<th style="width: 77px;text-align: center;font-size:8px;">毛孩編號</th>
		<th style="width: 77px;text-align: center;font-size:8px;">毛主人編號</th>
		<th style="width: 77px;text-align: center;font-size:8px;">毛孩名字</th>
		<th style="width: 77px;text-align: center;font-size:8px;">毛孩種類</th>
		<th style="width: 77px;text-align: center;font-size:8px;">品種</th>
		<th style="width: 77px;text-align: center;font-size:8px;">性別</th>
		<th style="width: 77px;text-align: center;font-size:8px;">年齡</th>
		<th style="width: 77px;text-align: center;font-size:8px;">狀態</th>

	</tr>

		<c:forEach var="petVO" items="${list}" >

			<tr>
				<td style="width: 90px;text-align: center;font-size:3px;">${petVO.petId}</td>
				<td style="width: 90px;text-align: center;font-size:3px;">${petVO.petMemId}</td>
				<td style="width: 90px;text-align: center;font-size:3px;">${petVO.petName}</td>
				<td style="width: 90px;text-align: center;font-size:3px;">${petVO.petSort}</td>
				<td style="width: 90px;text-align: center;font-size:3px;">
				
				<c:if test="${petVO.petVarId == '1'}">英國短毛貓</c:if>
				<c:if test="${petVO.petVarId == '2'}">馬爾濟斯</c:if>
				<c:if test="${petVO.petVarId == '4'}">紅貴賓</c:if>
				</td>
				
				<td style="width: 90px;text-align: center;font-size:3px;">${petVO.petGender}</td>
				<td style="width: 90px;text-align: center;font-size:3px;">${petVO.petAge}</td>
				
				<td style="width: 90px;text-align: center;font-size:3px;">
				
				<c:if test="${petVO.petSurvive == '0'}">健康</c:if>
				<c:if test="${petVO.petSurvive == '1'}">懷念</c:if>
				</td>


				<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do" style="margin-bottom: 0px;">
			     <input class=update type="button" onclick="location.href='<%=request.getContextPath()%>/back-end/pet/petadd.jsp'" value="立即新增" style="border:5px;border-radius:5px;">
			</td>
</form>
<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do" style="margin-bottom: 0px;">
			     <input class=update type="submit" value="立即修改" style="border:5px;border-radius:5px;">
			     <input type="hidden" name="petId"  value="${petVO.petId}">
			     <input type="hidden" name="action"	value="GET_ONE_STMT">
			</td>
			</FORM>
			</tr>
		</c:forEach>
</table>
</table>
		    	
		    	</div> 
				<!-- 以上為商城管理下面那個長方形,包含駝色表頭(所有商品資料) + 下面空白區域 -->


				        
        </div> <!-- class="d-flex flex-column"的結尾標籤-->
	</div> <!-- Class="Wrapper"的結尾標籤 -->


	<!-- 以下為boostrap靜態檔引入,不可少!!! 路徑錯了會跑版-->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script> 
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
    
    <script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
   <script src="<%= request.getContextPath() %>/resources/js/bootstrap.min.js"></script>
	<!-- 以下為隱藏式菜單連結(JSP)--> 
    <script src="<%=request.getContextPath()%>/resources/js/hidden_menu3.js"></script> 
    <script src="<%=request.getContextPath()%>/resources/js/hidden_menu4.js"></script>
    
</body>
</html>
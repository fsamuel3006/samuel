<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.backstage_staff.model.*"%>
<%@ page import="java.util.*"%>

<%
  StaffVO StaffVO = (StaffVO) request.getAttribute("StaffVO"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>hidden_menu</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu.css"> 
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu2.css"> 

<title>新增</title>

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
                     <li><a href="#">會員資料管理</a></li>
                     <li><a href="#">商品訂單管理</a></li>
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
	 <div id="wrapper" style="margin-left: 120px;height: auto;">
        <div class="d-flex flex-column" id="content-wrapper" style="margin-right:5px;">
            
            	 <!-- 以下範圍為最上方包著商城管理以及右方後台人員的區塊, -->
                <nav class="navbar navbar-light navbar-expand bg-white mb-4 topbar static-top">
                    <div class="container-fluid" style="margin-top:23px;margin-left:-6px;">
<!--請看這行最右邊-->  <a class="btsp" href="item_select_page.jsp">後台管理</a>  <!-- 這行是商城管理的標題，可以自行設定，還有href可以自行設定跳轉的頁面 -->
                       	 <ul class="nav navbar-nav flex-nowrap ml-auto" style="margin-top:-10px;">                         
                           
                            <li class="nav-item dropdown no-arrow" style="margin-top:10px">
                                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#">
                                	
                                	<img class="border rounded-circle img-profile" style="widht:40px;height:40px;margin-top:-5px;" src="<%=request.getContextPath()%>/resources/images/items/MaoUniICON.png"></a>
                                    <div class="dropdown-menu dropdown-menu-right animated--grow-in">
                                    	<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/staff/select.jsp" style="font-weight:700;color:#646464;">Logout</a>
                                    </div>
	                   			</div>
	                    	</li>
	                    </ul>
	            	</div>
	            </nav>
</style>
</head>

<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/StaffServlet" name="form1">
<table align="center" cellpadding="10"	border='2'>
	<tr>
		<td>員工ID:</td>
		<td><input type="TEXT" name="id" size="55" 
			 value="<%= (StaffVO==null)? "" : StaffVO.getId()%>" /></td>
	</tr>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="name" size="55"
			 value="<%= (StaffVO==null)? "" : StaffVO.getName()%>" /></td>
	</tr>
	<tr>
		<td>狀態:</td>
		<td><input type="TEXT" name="status" size="55"
			 value="<%= (StaffVO==null)? "" : StaffVO.getStatus()%>" /></td>
	</tr>
	<tr>
		<td>年齡:</td>
		<td><input type="TEXT" name="age" size="55"
			 value="<%= (StaffVO==null)? "" : StaffVO.getAge()%>" /></td>
	</tr>
	<tr>
		<td>學歷:</td>
		<td><input type="TEXT" name="edu" size="55"
			 value="<%= (StaffVO==null)? "" : StaffVO.getEdu()%>" /></td>
	</tr>
	
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="add" size="55"
			 value="<%= (StaffVO==null)? "" : StaffVO.getAdd()%>" /></td>
	</tr>
	
	<tr>
		<td>聯絡人:</td>
		<td><input type="TEXT" name="cont" size="55"
			 value="<%= (StaffVO==null)? "" : StaffVO.getCont()%>" /></td>
	</tr>
	
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="tel" size="55"
			 value="<%= (StaffVO==null)? "" : StaffVO.getTel()%>" /></td>
	</tr>
	<tr>
		<td>新增帳號</td>
		<td><input type="TEXT" name="username" size="55"
			 value="<%= (StaffVO==null)? "" : StaffVO.getUsername()%>" /></td>
	</tr>
	<tr>
		<td>新增密碼</td>
		<td><input type="TEXT" name="password" size="55"
			 value="<%= (StaffVO==null)? "" : StaffVO.getPassword()%>" /></td>
	</tr>
	

	<jsp:useBean id="staffSvc" scope="session" class="com.backstage_staff.model.StaffService"/>


</table>
<br>
<FORM>
<input type="hidden" name="action" value="insert">
<input type="submit" style="text-align:right" value="送出新增" ></FORM>
</body>

<script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script> 
<script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>

<!-- 以下為隱藏式菜單連結(JSP)--> 
<script src="<%=request.getContextPath()%>/resources/js/hidden_menu3.js"></script> 
<script src="<%=request.getContextPath()%>/resources/js/hidden_menu4.js"></script>

</script>
</html>
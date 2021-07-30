<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.backstage_staff.model.*"%>
<%@ page import="com.backstage_authority.model.AuthorityVO"%>
<%@ page import="com.backstage_authority.controller.*"%>
<%@ page import="com.backstage_authority.model.*"%>
<%@ page import="com.SignIn.controller.*"%>


<%
	StaffService StaffSvc = new StaffService();
	List<StaffVO> list = StaffSvc.getAll();
	pageContext.setAttribute("list", list);

	AuthorityService authoritySvc = new AuthorityService();
	List<AuthorityVO> authoritylist = authoritySvc.getAll();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>hidden_menu</title>


<html style="height: auto;"><head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/hidden_menu.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/hidden_menu2.css">
<style>
.btn {
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

.col-md-6 .pagination>li>a, .col-md-6 .pagination>li>span {
	border: 1px solid #d6b286;
}

.pagination>.active>a:hover {
	background-color: #e6c195;
	border: solid 1px #e6c195;
}

a.btsp {
	font-size: 35px;
	color: #e8c497;
	font-weight: bold;
	margin-left: -5px;
}

a.btsp:hover, a.btsp:active {
	color: #ffe6c7;
	text-decoration: none;
}

p.allitemtitle {
	color: #fff;
	font-weight: bold;
}

input.details {
	background-color: #bfbfbf;
	color: #fff;
}

input.details:hover {
	background-color: #e8c497;
}

input.update {
	background-color: #bfbfbf;
	color: #fff;
}

input.update:hover {
	background-color: #e8c497;
}

body {
	overflow-x: hidden;
	<!--
	此處做整個BODY橫向的隱藏多出的寬度
	-->
}
</style>
</head>

<body style="height: auto;">

	<!-- 以下為隱藏式菜單內容 -->
	<header>
		<span class="toggle-button"
			style="margin-left: -5px; margin-top: -7px;">
			<div class="menu-bar menu-bar-top"></div>
			<div class="menu-bar menu-bar-middle"></div>
			<div class="menu-bar menu-bar-bottom"></div>
		</span>
		<div class="menu-wrap">
			<div class="menu-sidebar" style="margin-top: 20px;">
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
					<li><a
						href="<%=request.getContextPath()%>/back-end/staff/backImage.jsp">上一頁</a></li>
				</ul>
			</div>
		</div>
	</header>
	<div id="wrapper" style="margin-left: 120px; height: auto;">
		<div class="d-flex flex-column" id="content-wrapper"
			style="margin-right: 5px;">
			<nav
				class="navbar navbar-light navbar-expand bg-white mb-4 topbar static-top">
				<div class="container-fluid"
					style="margin-top: 23px; margin-left: -6px;">
					<a class="btsp"
						href="<%=request.getContextPath()%>/back-end/staff/backImage.jsp">後台管理</a>
					<ul class="nav navbar-nav flex-nowrap ml-auto"
						style="margin-top: -10px;">

						<li class="nav-item dropdown no-arrow" style="margin-top: 10px">
							<div class="nav-item dropdown no-arrow">
								<a class="dropdown-toggle nav-link" data-toggle="dropdown"
									aria-expanded="false" href="#"> <img
									class="border rounded-circle img-profile"
									style="widht: 40px; height: 40px; margin-top: -5px;"
									src="<%=request.getContextPath()%>/resources/images/items/MaoUniICON.png"></a>
								<div class="dropdown-menu dropdown-menu-right animated--grow-in">
									<a class="dropdown-item"
										href="<%=request.getContextPath()%>/back-end/staff/select.jsp"
										style="font-weight: 700; color: #646464;">Logout</a>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</nav>
			</head>
			<h5 align="center">
				<input type="button" value="查詢權限"
					onclick="window.location.href = '<%=request.getContextPath()%>/back-end/authority/allAuthority.jsp'">
			</h5 align="center">

			<h5 align="center">
				<input type="button" value="新增"
					onclick="window.location.href = '<%=request.getContextPath()%>/back-end/staff/addStaff.jsp'">
			</h5>
			<table align="center" "border:3px #cccccc solid" cellpadding="10"
				border='2'>
				<tr>
					<th>員工編號</th>
					<th>員工姓名</th>
					<th>年齡</th>
					<th>學歷</th>
					<th>住家地址</th>
					<th>聯絡人</th>
					<th>狀態</th>
					<th>電話</th>
					<th>修改</th>
					<th>查詢狀態</th>
				</tr>
				<c:forEach var="staffVO" items="${list}">

					<tr>
						<td>${staffVO.id}</td>
						<td>${staffVO.name}</td>
						<td>${staffVO.age}</td>
						<td>${staffVO.edu}</td>
						<td>${staffVO.add}</td>
						<td>${staffVO.cont}</td>
						<td>${staffVO.status}</td>
						<td>${staffVO.tel}</td>
						</td>

						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/StaffServlet"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改"> <input type="hidden"
									name="id" value="${staffVO.id}"> <input type="hidden"
									name="action" value="UpdateStaff">
							</FORM>
						</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/StaffServlet"
								style="margin-bottom: 0px;">
								<input type="submit" value="查詢狀態"> <input type="hidden"
									name="id" value="${staffVO.id}"> <input type="hidden"
									name="action" value="getOneStaff">
							</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>

			<script
				src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
			<script
				src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>



			<!-- 以下為隱藏式菜單連結(JSP)-->
			<script
				src="<%=request.getContextPath()%>/resources/js/hidden_menu3.js"></script>
			<script
				src="<%=request.getContextPath()%>/resources/js/hidden_menu4.js"></script>
</body>
</html>
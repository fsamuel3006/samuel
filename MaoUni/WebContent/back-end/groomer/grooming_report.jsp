<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.appointment_form.model.*"%>
<%@ page import="com.appointment_form_details.model.*"%>
<%@ page import="com.groomer.model.*"%>
<%@ page import="com.report_grooming.model.*"%>

<jsp:useBean id="apmSvc" scope="page" class="com.appointment_form.model.ApmService"/>
<jsp:useBean id="grepSvc" scope="page" class="com.report_grooming.model.GrepService"/>
<jsp:useBean id="groSvc" scope="page" class="com.groomer.model.GroService"/>

<html style="height: auto;">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>hidden_menu</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu2.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/sweetalert2.css">


<script src="<%= request.getContextPath() %>/resources/js/fontawesome.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/sweetalert2.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>

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

.pass{
  background-color: #EC7357;
  color: #F8F8FF;
  border-radius: 5px;
  margin: 8px 10px;
  padding: 3px;
  margin-top:0;
}


.return{
  background-color: #C89EC4;
  color: #F8F8FF;
  border-radius: 5px;
  margin: 8px 10px;
  padding: 3px;
  margin-top:0;
}

.disabled{
	background-color: #E0E3DA;
}



.table > tbody > tr > td {
     vertical-align: middle;
}







</style>

</head>

<body style="height: auto;">
	<!-- 以下為隱藏式菜單_內容<國任提供> -->
	<header>
		<span class="toggle-button">
			<div class="menu-bar menu-bar-top"></div>
			<div class="menu-bar menu-bar-middle"></div>
			<div class="menu-bar menu-bar-bottom"></div>
		</span>
		<div class="menu-wrap">
			<div class="menu-sidebar" style="margin-top: 46px;">
				<!--上次說改的地方在這，把標題往上移-->
				<ul class="menu">
					<li><a href="#">會員資料管理</a></li>
                     <li><a href="#">商品訂單管理</a></li>
                     <li><a href="#">商城管理</a></li>
                     <li><a href="#">商城客服管理</a></li>
                     <li><a href="#">討論區管理</a></li>
                     <li><a href="#">浪浪找家管理</a></li>
                     <li><a href="#">知識站管理</a></li>
                     <li><a href="#">公告管理</a></li>
                     <li><a href="<%= request.getContextPath() %>/back-end/groomer/groomerList.jsp">美容師管理</a></li>
                     <li><a href="#">美容預約檢舉管理</a></li>
				</ul>
			</div>
		</div>
	</header>
	<!-- 以上為隱藏式菜單_內容<國任提供> -->


	<div id="wrapper"
		style="margin-right: 0px; margin-left: 127px; height: auto;">
		<div class="d-flex flex-column" id="content-wrapper">
			<div id="content" style="height: 100%;">
				<nav
					class="navbar navbar-light navbar-expand bg-white mb-4 topbar static-top">
					<div class="container-fluid">
						<button class="btn btn-link d-md-none rounded-circle mr-3"
							id="sidebarToggleTop" type="button">
							<i class="fas fa-bars"></i>
						</button>

						<!-- 以下為標題"商城管理"，作為回到商城管理首頁的按鈕，可以用在href那邊改成你要的位置，要注意路徑(我這邊只要寫檔名)  -->
						<p
							style="width: 266px; height: 5px; margin-top: 30px; margin-left: -13px; font-weight: bold;">
							<a class="btsp" href="Item_select_page.jsp">美容檢舉管理</a>
						</p>

						<!-- 以下為右上角三個下拉式選單內容，分別為小鈴鐺 / 信箱 / 員工名稱+頭像 ，這部分歡迎更改，因為我也還不確定下拉後該出現什麼選項QQ -->
						<ul class="nav navbar-nav flex-nowrap ml-auto">
							
							<div class="d-none d-sm-block topbar-divider"></div>
							<li class="nav-item dropdown no-arrow" style="margin-top: 4px">
								<div class="nav-item dropdown no-arrow">
									<a class="dropdown-toggle nav-link" data-toggle="dropdown"
										aria-expanded="false" href="#"><span
										class="d-none d-lg-inline mr-2 text-gray-600 small">員工名稱</span><img
										class="border rounded-circle img-profile"
										src="<%=request.getContextPath()%>/resources/images/items/MaoUniICON.png"></a>
									<div
										class="dropdown-menu shadow dropdown-menu-right animated--grow-in">
										<a class="dropdown-item" href="#"><i
											class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Profile</a><a
											class="dropdown-item" href="#"><i
											class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Settings</a>
										<a class="dropdown-item" href="#"><i
											class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Activity
											log</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item" href="#"><i
											class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Logout</a>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</nav>
				<!-- 以上為右上角三個下拉式選單內容，分別為小鈴鐺 / 信箱 / 員工名稱+頭像 ，這部分歡迎更改，因為我也還不確定下拉後該出現什麼選項QQ -->

				<!-- 以下為所有商品資料那一米駝色的橫條 -->
				<div class="container-fluid"
					style="margin-top: 45px; margin-left: -10px;">
					<div class="card" style="margin-left: -13px;">
						<div class="card-header py-3" style="background-color: #e8c497;">
							<p class="allitemtitle" style="margin: 1px; font-size: 20px;">預約檢舉列表</p>
						</div>
						<!-- 以上為所有商品資料那一米駝色的橫條 -->


						<!-- 以下為空白內容，這個位置原本擺放我的列表，大家可以自行增加自己需要的頁面資訊 -->
						<div class="card-body" style="height: 563px;">
							<div class="row">
							<!------------------------ search appointment start --------------------->			
				<form method="get" id="serchForm" >
					<div class="searchGM col-md-12 row mt-0">
						<div class="col-md-12 my-1">
							<select class="form-control condition" name="apmStatus">
								<option value="">ALL</option>
								<option value="0">待審核</option>
								<option value="1">通過</option>
								<option value="2">退回</option>
							</select>
						</div>
						<input class="action" type="hidden" name="groomerId" value="1">
						<input class="action" type="hidden" name="action" value="getAll">
					</div>
				</form>
			</div>
			<!------------------------ search appointment end --------------------->	
							
							
							<div class="table-responsive table mt-2" id="dataTable-1" role="grid" aria-describedby="dataTable_info">
								
								
<section class="showList row">
					<div class="col-12">
						<table class="table table-hover table-sm text-center">
							<thead>
								<tr>
									<th scope="col" style="width:10px;">檢舉單號</th>
									<th scope="col" style="width:20px;">預約單號</th>
									<th scope="col" style="width:80px;">檢舉內容</th>
									<th scope="col" style="width:20px;">狀態</th>
									<th scope="col" style="width:80px;"></th>
								</tr>
							</thead>
							
			<!------------------------ 美容檢舉列表  --------------------->
			
	<% 
		List<GrepVO> list = grepSvc.getAll();
		pageContext.setAttribute("list", list);
	%>

							<tbody class="appointmentList">
	<c:forEach var="grepVO" items="${list}">						
								<tr>
									<td class="rptId">${grepVO.rptId}</td>
									<td class="apmId">${grepVO.apmId}</td>
									<td class="content " style="text-align: left;">${grepVO.content}</td>
									<c:if test="${grepVO.rptStatus == '0'}"><td scope="col" class="rptStatus">待審核</td></c:if>
									<c:if test="${grepVO.rptStatus == '1'}"><td scope="col" class="rptStatus">通過</td></c:if>
									<c:if test="${grepVO.rptStatus == '2'}"><td scope="col" class="rptStatus">退回</td></c:if>
									<td>
									
									<div class="row">
									<form type="POST" class="auditForm col-md-3" >
									<input type="hidden" name="action" value="update">
									<input type="hidden" name="rptId" value="${grepVO.rptId}">
									<input type="hidden" name="rptStatus" value="1">
									<input type="hidden" name="groomerId" value="${grepVO.groomerId}">
									<c:if test="${grepVO.rptStatus == '0'}"> 
										<button type="submit" class="btn pass ">通過</button>
									</c:if>
									<c:if test="${grepVO.rptStatus != '0'}"> 
										<button type="submit" class="btn pass disabled" disabled>通過</button>
									</c:if>
									</form>
									
									<form type="POST" class="auditForm col-md-3">
									<input type="hidden" name="action" value="update">
									<input type="hidden" name="rptId" value="${grepVO.rptId}">
									<input type="hidden" name="rptStatus" value="2">
									<input type="hidden" name="groomerId" value="${grepVO.groomerId}">
									<c:if test="${grepVO.rptStatus == '0'}">
										<button type="submit" class="btn return ">退回</button>
									</c:if>
									<c:if test="${grepVO.rptStatus != '0'}">
										<button type="submit" class="btn return disabled" disabled>退回</button>
									</c:if>
									</form>
									</div>
									</td>
								</tr>	

	</c:forEach>		
							</tobdy>
				
		</div>
	</section>								
								
								</div>
							<!-- 以下是公版的js，路徑要確定是對的 -->
						</div>

						<!-- 以下為右下角的頁數按鈕，可自行增減自己需要的頁數，我沒有用大吳給的page2.file裡面的方法，因為還沒研究怎麼套我們現在這個樣式^__^ -->
						<div class="row">
							<div class="col-md-6"></div>
							<div class="col-md-6">
								<nav
									class="d-lg-flex justify-content-lg-end dataTables_paginate paging_simple_numbers">
									<ul class="pagination">
										<li><a class="page-link" href="#" aria-label="Previous"><span
												aria-hidden="true">«</span></a></li>
										<li class="active"><a class="page-link" href="#">1</a></li>
										<li><a class="page-link" href="#">2</a></li>
										<li><a class="page-link" href="#">3</a></li>
										<li style="margin-right: 25px;"><a class="page-link"
											href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
									</ul>
								</nav>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<script>

$(".auditForm").on("submit", function(e){
	e.preventDefault();
	$.ajax({
		url: "/MaoUni/groomingreport.do",
		type: "POST",
		data: $(this).serialize(),
		success: function(data){
				swal("審核通過！", data, "success").then((result) => {
					window.location.reload();
				});
		},
		error: function(data){
			swal("審核失敗", "檢請重新提交", "error").then((result) => {
				window.location.reload();
			});
		}
	})
	
})


</script>

	<!-- 以下是公版的js，路徑要確定是對的 -->
	<script src="<%=request.getContextPath()%>/resources/bootstrap/jquery/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>


	<!-- 隱藏式菜單_連結<國任提供> -->
	<!-- 底下是隱藏式菜單js，連結打法可以參考一下 -->
	<script src="<%=request.getContextPath()%>/resources/js/hidden_menu3.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/hidden_menu4.js"></script>

</body>

</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groomer.model.*"%>
<%@ page import="java.io.*"%>


<jsp:useBean id="groSvc" scope="page" class="com.groomer.model.GroService" />

<c:if test="${search == null}">
<%
	List<GroVO> list = groSvc.getAll();
	pageContext.setAttribute("list", list);
%>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Groomer List</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu2.css">

<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/fontawesome.js"></script>

<style>
.searchGM {
	width: 80%;
	margin: 20px;
}
.certifyimg {
	width: 100%;
	height: auto;
	justify-content: center;
	margin: 0;
}
i:hover{
	cursor: pointer;
}

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
                     <li><a href="#">美容師管理</a></li>
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
							<a class="btsp" href="Item_select_page.jsp">美容師管理</a>
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

				<%-- 以下為錯誤訊息設置 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
				<%-- 以上為錯誤訊息設置 --%>


				<!-- 以下為所有商品資料那一米駝色的橫條 -->
				<div class="container-fluid"
					style="margin-top: 45px; margin-left: -10px;">
					<div class="card" style="margin-left: -13px;">
						<div class="card-header py-3" style="background-color: #e8c497;">
							<p class="allitemtitle" style="margin: 1px; font-size: 20px;">美容師列表</p>
						</div>
						<!-- 以上為所有商品資料那一米駝色的橫條 -->


						<!-- 以下為空白內容，這個位置原本擺放我的列表，大家可以自行增加自己需要的頁面資訊 -->
						<div class="card-body" style="height: 563px;">
							<div class="row"></div>
							<div class="table-responsive table mt-2" id="dataTable-1"
								role="grid" aria-describedby="dataTable_info">
								
								<main>
	
	<c:if test="${not empty errorMsgs}">
		<c:forEach var="message" items="${errorMsgs}">
	 		<p style="color: red">${message}</p>
		</c:forEach>
	</c:if>
	
		<div class="container">
			
<!--------------------------------------------- searchGroomer ------------------------------------------------------------->
		<form method="post" action="/MaoUni/groomer.do">
			<div class="searchGM row">
				<div class="col-md-3 my-1">
					<input class="form-control" name="groomerId" type="number" min="1" placeholder="美容師編號">
				</div>
				<div class="col-md-3 my-1">
					<input class="form-control" name="gname" type="text" placeholder="姓名">
				</div>
				<div class="col-md-3 my-1">
					<select class="form-control" name="gstatus">
						<option value="all">ALL</option>
						<option value="0">待審核</option>
						<option value="1">通過</option>
						<option value="2">不符資格</option>
						<option value="3">停權</option>
					</select>
				</div>
				<input class="action" type="hidden" name="action" value="getListByCondition">
				<button type="submit" class="btn btn-primary getList ml-4 my-1">
					查詢
				</button>
			</div>
		</form>
<!---------------------------------------------------------------------------------------------------------->
			<div class="groomerList">
				<table class="table table-hover table-sm text-center">
					<thead>
						<tr>
							<th scope="col">編號</th>
							<th scope="col">姓名</th>
							<th scope="col">服務中心點</th>
							<th scope="col">服務範圍(KM)</th>
							<th scope="col">審核文件</th>
							<th scope="col">詳細資訊</th>
							<th scope="col">狀態</th>
						</tr>
					</thead>
					<tbody>
<!--------------------------------------------   JSP動態產生 start  -------------------------------------------->
<c:forEach var="groVO" items="${list}">
							<tr>
								<td>${groVO.groomerId}</td>
								<td>${groVO.gname}</td>
								<td class="text-left pl-md-3">${groVO.center}</td>
								<td>${groVO.grange}</td>
								<td>
	<!--groomerID將透過servlet forward回此頁時攜帶回來，因此在彈出視窗可以getParameter取得id進行該id的審核狀態修改 -->
									<a href="/MaoUni/groomer.do?groomerId=${groVO.groomerId}&action=findPhotosBygmId&gstatus=${groVO.gstatus}">
									<i class="fas fa-user-shield ${groVO.groomerId} btn"></i></a></td>
									
								<td>
								<i class="far fa-address-card btn-primary" data-toggle="modal" data-target="#speModal${groVO.groomerId}">

								</td>
								<td><c:if test="${groVO.gstatus == 0}">待審核</c:if> 
									<c:if test="${groVO.gstatus == 1}">通過</c:if> 
									<c:if test="${groVO.gstatus == 2}">不符資格</c:if> 
									<c:if test="${groVO.gstatus == 3}">停權</c:if></td>
							</tr>

							<!----------------------- speModalModal start---------------------->
					<div class="modal fade" id="speModal${groVO.groomerId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
					  <div class="modal-dialog modal-dialog-centered" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalCenterTitle">詳細資訊</h5>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
					      <div class="modal-body">
					     	 <div class="container-fluid">
					     	 	<div class="row my-3">
					     	 			<div class="col-md-3" style="font-weight: bold">完成服務</div>
					      				<div class="col-md-9 ml-auto">${groVO.reserve}<span> 筆</span></div>
					     	 	</div>
					     	 	<div class="row my-3">
					     	 			<div class="col-md-3" style="font-weight: bold">評價數</div>
					      				<div class="col-md-9 ml-auto">${groVO.com}<span> 筆</span></div>
					     	 	</div>
					     	 	<div class="row my-3">
					     	 			<div class="col-md-3" style="font-weight: bold">評分</div>
					      				<div class="col-md-9 ml-auto"><i class="fa fa-star" style="color: #feee7d;"></i>  <fmt:formatNumber value="${groVO.comg / groVO.com}" pattern="0.0#"/></span></div>
					     	 	</div>
					     	 	<div class="row my-3">
					     	 			<div class="col-md-3" style="font-weight: bold">檢舉</div>
					      				<div class="col-md-9 ml-auto">${groVO.reped}<span> 次</span></div>
					     	 	</div>
					      	  </div>
					      </div>
					      
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					      </div>
					    </div>
					  </div>
					</div>                
                    
						<!----------------------- speModalModal end ---------------------->
              
								
</c:forEach>
<!--------------------------------------------  JSP動態產生 end  -------------------------------------------->
					</tbody>
				</table>


<c:if test="${photos != null}">

                <!-- Modal -->
                <div class="modal fade" id="certifyModal" tabindex="-1" role="dialog"
                  aria-labelledby="certifyModal" aria-hidden="true">
                  <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="myModalLabel">審核文件</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                          <span aria-hidden="true">&times;</span>
                        </button> 
                      </div>
                      
                      <div class="modal-body">
                         
		<!----------------------------  getPhotosBygmId.jsp  start  ------------------------------------>
							<jsp:include page="getPhotosBygmId.jsp"/>
		<!----------------------------  getPhotosBygmId.jsp  end  -------------------------------------->
						  
                       </div>
                        <div class="modal-footer">

						<c:if test="${param.gstatus == 0}"> 
                          	<a href="/MaoUni/groomer.do?groomerId=<%=request.getParameter("groomerId")%>&action=updateStatus&gstatus=1" class="btn btn-success">
                          		審核通過</a>
                          	<a href="/MaoUni/groomer.do?groomerId=<%=request.getParameter("groomerId")%>&action=updateStatus&gstatus=2" class="btn btn-danger">
                          		拒絕申請</a>
                         </c:if>
                         <c:if test="${param.gstatus != 0}"> 
                          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                         </c:if>
                        </div>
                      </div>
                    </div>
                  </div>
     			 <script>
     			 $(window).ready(() => {
     				 $("#certifyModal").modal('show');
     			 })
   			     </script>
           
</c:if>



<c:if test="${upsatesuccess != null}">
			<script>
				$(window).ready(() => {
					alert("Update Success!");
				})
			</script>
</c:if>
               
		</div>
		</div>
	</main>
								
								
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
	
	<footer></footer>			


<script src="<%= request.getContextPath() %>/resources/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>


	<!-- 隱藏式菜單_連結<國任提供> -->
	<!-- 底下是隱藏式菜單js，連結打法可以參考一下 -->
<%-- 	<script src="<%=request.getContextPath()%>/resources/js/hidden_menu3.js"></script> --%>
<%-- 	<script src="<%=request.getContextPath()%>/resources/js/hidden_menu4.js"></script> --%>



</body>
</html>
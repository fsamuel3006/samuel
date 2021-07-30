<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.variety.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    VarietyService varietySvc = new VarietyService();
    List<VarietyVO> list = varietySvc.getAll();
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MaoUni_Variety</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/fonts/fontawesome-all.min.css">
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
         <span class="toggle-button">
             <div class="menu-bar menu-bar-top"></div>
             <div class="menu-bar menu-bar-middle"></div>
             <div class="menu-bar menu-bar-bottom"></div>
         </span>
         <div class="menu-wrap">
             <div class="menu-sidebar" style="margin-top:46px;">
                 <ul class="menu">
                     <li><a href="<%=request.getContextPath()%>/back-end/Member/listAllMember.jsp">會員資料管理</a></li>
                     <li><a href="<%=request.getContextPath()%>/back-end/Obuy/NewObuyAll.jsp">商品訂單管理</a></li>
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
 <!-- 以上 >>隱藏式菜單_內容 --> 
 
 
 
    <div id="wrapper" style="margin-right: 0px;margin-left: 127px;height: auto;">
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content" style="height: 100%;">
                <nav class="navbar navbar-light navbar-expand bg-white mb-4 topbar static-top">
                    <div class="container-fluid"><button class="btn btn-link d-md-none rounded-circle mr-3" id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button>
                        <p style="width: 266px;height: 5px;margin-top:30px;margin-left:-13px;font-weight:bold;"><a class="btsp" href="Item_select_page.jsp">品種資料管理</a></p>
                        <ul class="nav navbar-nav flex-nowrap ml-auto">                         
                            <li class="nav-item dropdown no-arrow mx-1" style="margin-top:10px;">
                                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#"><span class="badge badge-danger badge-counter">3+</span><i class="fas fa-bell fa-fw"></i></a>
                                    <div class="dropdown-menu dropdown-menu-right dropdown-list dropdown-menu-right animated--grow-in">
                                        <h6 class="dropdown-header">alerts center</h6>
                                        <a class="d-flex align-items-center dropdown-item" href="#">
                                            <div class="mr-3">
                                                <div class="bg-primary icon-circle"><i class="fas fa-file-alt text-white"></i></div>
                                            </div>
                                            <div><span class="small text-gray-500">December 12, 2019</span>
                                                <p>A new monthly report is ready to download!</p>
                                            </div>
                                        </a>
                                        <a class="d-flex align-items-center dropdown-item" href="#">
                                            <div class="mr-3">
                                                <div class="bg-success icon-circle"><i class="fas fa-donate text-white"></i></div>
                                            </div>
                                            <div><span class="small text-gray-500">December 7, 2019</span>
                                                <p>$290.29 has been deposited into your account!</p>
                                            </div>
                                        </a>
                                        <a class="d-flex align-items-center dropdown-item" href="#">
                                            <div class="mr-3">
                                                <div class="bg-warning icon-circle"><i class="fas fa-exclamation-triangle text-white"></i></div>
                                            </div>
                                            <div><span class="small text-gray-500">December 2, 2019</span>
                                                <p>Spending Alert: We've noticed unusually high spending for your account.</p>
                                            </div>
                                        </a><a class="text-center dropdown-item small text-gray-500" href="#">Show All Alerts</a></div>
                                </div>
                            </li>
                            <li class="nav-item dropdown no-arrow mx-1" style="margin-top:10px">
                                <div class="nav-item dropdown no-arrow" style="margin-top:;"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#"><i class="fas fa-envelope fa-fw"></i><span class="badge badge-danger badge-counter">7</span></a>
                                    <div class="dropdown-menu dropdown-menu-right dropdown-list dropdown-menu-right animated--grow-in">
                                        <h6 class="dropdown-header">alerts center</h6>
                                        <a class="d-flex align-items-center dropdown-item" href="#">
                                            <div class="dropdown-list-image mr-3"><img class="rounded-circle" src="avatars/avatar4.jpeg">
                                                <div class="bg-success status-indicator"></div>
                                            </div>
                                            <div class="font-weight-bold">
                                                <div class="text-truncate"><span>Hi there! I am wondering if you can help me with a problem I've been having.</span></div>
                                                <p class="small text-gray-500 mb-0">Emily Fowler - 58m</p>
                                            </div>
                                        </a>
                                        <a class="d-flex align-items-center dropdown-item" href="#">
                                            <div class="dropdown-list-image mr-3"><img class="rounded-circle" src="avatars/avatar2.jpeg">
                                                <div class="status-indicator"></div>
                                            </div>
                                            <div class="font-weight-bold">
                                                <div class="text-truncate"><span>I have the photos that you ordered last month!</span></div>
                                                <p class="small text-gray-500 mb-0">Jae Chun - 1d</p>
                                            </div>
                                        </a>
                                        <a class="d-flex align-items-center dropdown-item" href="#">
                                            <div class="dropdown-list-image mr-3"><img class="rounded-circle" src="avatars/avatar3.jpeg">
                                                <div class="bg-warning status-indicator"></div>
                                            </div>
                                            <div class="font-weight-bold">
                                                <div class="text-truncate"><span>Last month's report looks great, I am very happy with the progress so far, keep up the good work!</span></div>
                                                <p class="small text-gray-500 mb-0">Morgan Alvarez - 2d</p>
                                            </div>
                                        </a>
                                        <a class="d-flex align-items-center dropdown-item" href="#">
                                            <div class="dropdown-list-image mr-3"><img class="rounded-circle" src="avatars/avatar5.jpeg">
                                                <div class="bg-success status-indicator"></div>
                                            </div>
                                            <div class="font-weight-bold">
                                                <div class="text-truncate"><span>Am I a good boy? The reason I ask is because someone told me that people say this to all dogs, even if they aren't good...</span></div>
                                                <p class="small text-gray-500 mb-0">Chicken the Dog · 2w</p>
                                            </div>
                                        </a><a class="text-center dropdown-item small text-gray-500" href="#">Show All Alerts</a></div>
                                </div>
                                <div class="shadow dropdown-list dropdown-menu dropdown-menu-right" aria-labelledby="alertsDropdown"></div>
                            </li>
                            <div class="d-none d-sm-block topbar-divider"></div>
                            <li class="nav-item dropdown no-arrow" style="margin-top:4px">
                                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#"><span class="d-none d-lg-inline mr-2 text-gray-600 small">員工名稱</span><img class="border rounded-circle img-profile" src="<%=request.getContextPath()%>/resources/images/items/MaoUniICON.png"></a>
                                    <div class="dropdown-menu shadow dropdown-menu-right animated--grow-in"><a class="dropdown-item" href="#"><i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Profile</a><a class="dropdown-item" href="#"><i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Settings</a>
                                   <a class="dropdown-item" href="#"><i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Activity log</a>
                                <div class="dropdown-divider"></div><a class="dropdown-item" href="#"><i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Logout</a></div>
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

<div class="container-fluid" style="margin-top:45px;margin-left:-10px;">
    <div class="card" style="margin-left: -13px;">
        <div class="card-header py-3" style="background-color:#e8c497;">
            <p class="allitemtitle" style="margin: 1px;font-size: 20px;">所有品種資料</p>
        </div>

        <div class="card-body" style="height: auto;">
            <div class="row">
                <div class="col-md-6 text-nowrap">
                    <div id="dataTable_length" class="dataTables_length" aria-controls="dataTable" style="margin-left:4px;margin-top:5px;"><label>Show&nbsp;
                    <select class="form-control form-control-sm custom-select custom-select-sm"><option value="10" selected="">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select>&nbsp;</label>
                     <input class=update type="submit" onclick="location.href='<%=request.getContextPath()%>/back-end/Pet/PetlistAll.jsp'" value="回到毛孩" style="border:5px;border-radius:5px;height:35px;width:100px;margin-left:21%">

                      <input class=update type="button" onclick="location.href='<%=request.getContextPath()%>/back-end/Pet/PetlistAll.jsp'" value="疾病管理" style="border:5px;border-radius:5px;height:35px;width:100px;margin-left:5%;">
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
                            <th style="width: 100px;text-align: center;font-size:5;">品種編號</th>
                            <th style="width: 100px;text-align: center;font-size:5;">品名</th>
                            <th style="width: 112.5px;text-align: center;font-size:5;">類別</th>
                        </tr>
                    </thead>
                                
					<tbody>	
<%-- 					<%@ include file="page1.file" %>  --%>
<%-- 						<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> --%>

							<c:forEach var="varietyVO" items="${list}" >
							<tr style="width: 70px;text-align: center;">
								<td style="width: 90px;text-align: center;font-size:5">${varietyVO.varId}</td>
								<td style="width: 90px;text-align: center;font-size:5">${varietyVO.varName}</td>
								<td style="width: 100.5px;text-align: center;font-size:5">${varietyVO.varKind}</td>

								<td>
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/variety/variety.do" style="margin-bottom: 0px;">
			    					<input class=update type="submit" class="button" value="立即修改" style="border:5px;border-radius:5px;">
			     					<input type="hidden" name="varId"  value="${varietyVO.varId}">
			     					<input type="hidden" name="action"	value="getOne_For_Update"></FORM>

								  </FORM>
								</td>
								<td>
								  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/variety/variety.do" style="margin-bottom: 0px;">
			    					<input class=update type="submit" class="button" value="立即新增" style="border:5px;border-radius:5px;">
			     					<input type="hidden" name="varId"  value="${varietyVO.varId}">
			     					<input type="hidden" name="action"	value="getOne_For_Update"></FORM>

								  </FORM>
								</td>
							</tr>
						</c:forEach>			
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<!-- 步驟三 --> 
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
</div>
            </div>
        </div>
	</div>

</div>
    <script src="<%=request.getContextPath()%>/resources/bootstrap/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%= request.getContextPath() %>/resources/js/fontawesome.js"></script>
<!-- 步驟三 -->  
<!-- 隱藏式菜單_連結--> 
<!--底下是jsp 裡面所連結打法可以參考一下-->
    <script src="<%=request.getContextPath()%>/resources/js/hidden_menu3.js"></script> 
    <script src="<%=request.getContextPath()%>/resources/js/hidden_menu4.js"></script>
    
</body>
</html>
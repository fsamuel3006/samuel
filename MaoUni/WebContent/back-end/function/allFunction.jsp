<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.backstage_function.model.*"%>


<%
	FunctionService functionSvc = new FunctionService();
	List<FunctionVO> list = functionSvc.getAll();
	pageContext.setAttribute("list",list);
%>


<html style="height: auto;">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>hidden_menu</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu.css"> 
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu2.css"> 



<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

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
	overflow-x:hidden; <!-- ???????????????BODY?????????????????????????????? -->
	}


</style>

</head>

</head>

<body style="height: auto;">

     <!-- ?????????????????????????????? -->
     <header>
         <span class="toggle-button" style="margin-left:-5px;margin-top:-7px;">
             <div class="menu-bar menu-bar-top"></div>
             <div class="menu-bar menu-bar-middle"></div>
             <div class="menu-bar menu-bar-bottom"></div>
         </span>
         <div class="menu-wrap">
             <div class="menu-sidebar" style="margin-top:20px;">
                 <ul class="menu">
                     <li><a href="#">??????????????????</a></li>
                     <li><a href="#">??????????????????</a></li>
                     <li><a href="#">????????????</a></li>
                     <li><a href="#">??????????????????</a></li>
                     <li><a href="#">???????????????</a></li>
                     <li><a href="#">??????????????????</a></li>
                     <li><a href="#">???????????????</a></li>
                     <li><a href="#">????????????</a></li>
                     <li><a href="#">???????????????</a></li>
                     <li><a href="#">????????????????????????</a></li>
                 </ul>
             </div>
         </div>
     </header> 
     <div id="wrapper" style="margin-left: 120px;height: auto;">
        <div class="d-flex flex-column" id="content-wrapper" style="margin-right:5px;">
            
            	 <!-- ???????????????????????????????????????????????????????????????????????????, -->
                <nav class="navbar navbar-light navbar-expand bg-white mb-4 topbar static-top">
                    <div class="container-fluid" style="margin-top:23px;margin-left:-6px;">
<!--?????????????????????-->  <a class="btsp" href="Item_select_page.jsp">????????????</a>  <!-- ????????????????????????????????????????????????????????????href????????????????????????????????? -->
                       	 <ul class="nav navbar-nav flex-nowrap ml-auto" style="margin-top:-10px;">                         
                           
                            <li class="nav-item dropdown no-arrow" style="margin-top:10px">
                                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#">
                                	
                                	<img class="border rounded-circle img-profile" style="widht:40px;height:40px;margin-top:-5px;" src="<%=request.getContextPath()%>/resources/images/items/MaoUniICON.png"></a>
                                    <div class="dropdown-menu dropdown-menu-right animated--grow-in">
                                    	<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/Staff/Select.jsp" style="font-weight:700;color:#646464;">Logout</a>
                                    </div>
	                   			</div>
	                    	</li>
	                    </ul>
	            	</div>
	            </nav>

<%-- ???????????? --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">?????????????????????:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>????????????</th>
		<th>????????????</th>
		<th>??????</th>
		<th>????????????</th>
		
	</tr>
		<c:forEach var="FunctionVO" items="${list}"> 
		<tr>
			<td>${FunctionVO.id}</td>
			<td>${FunctionVO.name}</td>
			
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backstage_function/functionServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="??????"> -->
<%-- 			     <input type="hidden" name="id" value="${FunctionVO.id}"> --%>
<!-- 			     <input type="hidden" name="action" value="insertFunction"></FORM> -->
				
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backstage_function/functionServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="??????">
			     <input type="hidden" name="id" value="${FunctionVO.id}">
			     <input type="hidden" name="action"	value="updateFunction"></FORM>
			</td>
			
			<td>
			   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backstage_function/functionServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="????????????">
			     <input type="hidden" name="id" value="${FunctionVO.id}">
			      <input type="hidden" name="name" value="${FunctionVO.name}">
			     <input type="hidden" name="action" value="getOne_For_Display"></FORM>
			</td>
			
			
			
		</tr>
	</c:forEach>
</table>
 <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script> 
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
    
    

	<!-- ??????????????????????????????(JSP)--> 
    <script src="<%=request.getContextPath()%>/resources/js/hidden_menu3.js"></script> 
    <script src="<%=request.getContextPath()%>/resources/js/hidden_menu4.js"></script>

</body>
</html>
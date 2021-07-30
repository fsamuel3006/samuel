<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.appointment_form.model.*"%>
<%@ page import="com.appointment_form_details.model.*"%>
<%@ page import="com.groomer.model.*"%>

<jsp:useBean id="apmSvc" scope="page" class="com.appointment_form.model.ApmService"/>
<jsp:useBean id="apmdSvc" scope="page" class="com.appointment_form_details.model.ApmdService"/>
<jsp:useBean id="groSvc" scope="page" class="com.groomer.model.GroService"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>毛孩有你 MaoUni</title>

<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- MAIN CSS -->

<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/styleD.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/templatemo-style.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/header.css">
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/sweetalert2.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">    
    
<script src="<%= request.getContextPath() %>/resources/js/fontawesome.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/sweetalert2.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/popper.min.js"></script>

</head>
<style>


/*  input[type=checkbox], input[type=radio] {   */
/*     box-sizing: border-box;   */
/*     padding: 0;   */
/* }   */

.modal-content .left-content input { 
    line-height: 30px; 
    height: 20px; 
    margin-bottom: 0px; 
} 

td, tr{
font-size: 12px;
color: gray;
text-align: center;
}

th{ 
 text-align: center; 
}

.table > tbody > tr > td {
     vertical-align: middle;
}

.showList{
min-height: 500px;
}

.showDeatail{
width: 100%;
heigh: 200px;
}

.svcItem, .pet{
  background-color: #9B8281; 
  border-radius: 20px; 
  margin: 15px 2px;
  color: #F8F8FF;
  padding: 3px 5px;
}

.comment{
  background-color: #77AF9C;
  color: #F8F8FF;
  border-radius: 5px;
  margin: 8px 10px;
  padding: 3px;
  margin-top:0;
}

.detail:hover{
cursor: pointer;
}

.report{
  background-color: #EC7357;
  color: #F8F8FF;
  border-radius: 5px;
  margin: 8px 10px;
  padding: 3px;
  margin-top:0;
}


.cancle{
  background-color: #C89EC4;
  color: #F8F8FF;
  border-radius: 5px;
  margin: 8px 10px;
  padding: 3px;
  margin-top:0;
}

.modal-backdrop {
    z-index: 1;
}


.modal-content .close {
    line-height: 0px;
	right: 0px; 
}


body {
    line-height: 1;
    min-height: 1000px;
    font-size: 18px;
    color: gray;
    font-family: "Microsoft JhengHei";
}

.disabled{
	background-color: #E0E3DA;
}

.table{
	table-layout:fixed; /* bootstrap-table設定colmuns中某列的寬度無效時，需要給整個表設定css屬性 */
	word-break:break-all; word-wrap:break-all; /* 自動換行 */
}

.shopbody{

}

</style>

<body data-spy="scroll" data-target="#navbarNav" data-offset="50">
	<header id="header">
			<div class="icontrue">
	
		  <a href="<%=request.getContextPath()%>/front-end/member/memberpage.jsp" class="iconbth"><i class="fas fa-user fa-1x" style="color:white;font-size:25px;"></i></a>
		  <a href="<%=request.getContextPath()%>/front-end/chatbox/chatbox.jsp" class="iconbth"><i class="fa fa-comments fa-1x" style="color:white;font-size:30px;margin-top:-4px;"></i></a>
		  <a href="#" class="iconbth"><i class="fas fa-envelope fa-1x" style="color:white;font-size:25px;"></i></a>
<!-------------------------------------------- shopping cart ------------------------------------------------>
	      <div class="dropdown ml-auto">
           
            <button class="btn btn-cart btn-sm" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false" >
                <i class="fas fa-shopping-cart fa-2x shoppingCart" style="color:#fff;"></i>
                <span class="badge badge-pill badge-danger totalItems">0</span>
            </button>
           
            <div class="dropdown-menu dropdown-menu-right" style="min-width: 300px" aria-labelledby="dropdownMenuButton">
                <div class="p-3">
                    <table class="table table-sm">
                        <h6>已選擇商品</h6>
                        <tbody class="cartbody" style="color:black;">
                        </tbody>
                    </table>
                    <a href="#" class="btn btn-block btn-primary btn-sm text-white">確認結帳</a>
                </div>
            </div>
        </div>
	   
	        <a href="#" class="iconbth"><i class="fas fa-search fa-1x" style="color:white;font-size:25px;"></i></a>	        	        	
	   
	    </div>
<!---------------------------------------------------------------------------------------------------------------------->	 				
				
				<!--iconu一定要在top-flex之上，不然會被移動條給蓋爆777777-->
			</div>
			<div class="Top-Flex">
				<ul class="LOOP">
					<li class="two"><img src="<%=request.getContextPath()%>/resources/images/Logo/MaoUni2.png" width="" alt=""></li>
				</ul>
			</div>
	
			<div class="shopbody">
			<nav style="margin-bottom:-15px;margin-left:15px;">
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/home/HomePage.jsp" style="font-size:15px;">首　頁</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/shop/shopping_home.jsp" style="font-size:15px;">有你來買</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/forumPost/forumPost_home.jsp" style="font-size:15px;">有你來講</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/member/grooming_home.jsp" style="font-size:15px;">到府美容</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/adopt/adopt_home.jsp" style="font-size:15px;">浪浪找家</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/article/listAllArt_f.jsp" style="font-size:15px;">知識站</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/announcemet/listAllAnnf.jsp" style="font-size:15px;">公告</a></p>
			 	</nav>
			</div>
	</header>
	
<main>
	<section>
		<div class="container">
	
			<!------------------------ search appointment start --------------------->			
				<div class="col-lg-12 col-12 text-center">
		            <h2 class="text-secondary mt-5" data-aos="fade-up" data-aos-delay="200">美容預約紀錄</h2>
		        </div>
<!-- 				<form method="get" id="serchForm" > -->
<!-- 					<div class="searchGM col-md-12 row mt-0"> -->
<!-- 						<div class="col-md-3 my-1"> -->
<!-- 							<select class="form-control condition" name="month"> -->
<!-- 								<option value="" selected>月份</option> -->
<!-- 								<option value="1">01</option> -->
<!-- 								<option value="2">02</option> -->
<!-- 								<option value="3">03</option> -->
<!-- 								<option value="4">04</option> -->
<!-- 								<option value="5">05</option> -->
<!-- 								<option value="6">06</option> -->
<!-- 								<option value="7">07</option> -->
<!-- 								<option value="8">08</option> -->
<!-- 								<option value="9">09</option> -->
<!-- 								<option value="10">10</option> -->
<!-- 								<option value="11">11</option> -->
<!-- 								<option value="12">12</option> -->
<!-- 							</select> -->
<!-- 						</div> -->
<!-- 						<div class="col-md-4 my-1"> -->
<!-- 							<input class="form-control condition" name=apmDate type="date" > -->
<!-- 						</div> -->
<!-- 						<div class="col-md-3 my-1"> -->
<!-- 							<select class="form-control condition" name="apmStatus"> -->
<!-- 								<option value="">ALL</option> -->
<!-- 								<option value="0">待審核</option> -->
<!-- 								<option value="1">拒絕</option> -->
<!-- 								<option value="2">接受</option> -->
<!-- 								<option value="3">完成</option> -->
<!-- 								<option value="4">取消</option> -->
<!-- 							</select> -->
<!-- 						</div> -->
<!-- 						<input class="action" type="hidden" name="groomerId" value="1"> -->
<!-- 						<input class="action" type="hidden" name="action" value="getAll"> -->
<!-- 						<div class="col-md-1 my-1"> -->
<!-- 							<i class=" fas fa-search search mt-4 fa-2x"></i> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</form> -->
			<!------------------------ search appointment end --------------------->	
	<!------------------------------------------------------ appointment list start ------------------------------------------------>			
				<section class="showList row">
					<div class="col-12">
						<table class="table table-hover table-sm text-center">
							<thead>
								<tr>
									<th scope="col" style="width:60px;">美容師</th>
									<th scope="col" style="width:40px;">日期</th>
									<th scope="col" style="width:20px;">時間</th>
									<th scope="col" style="width:80px;">服務</th>
									<th scope="col" style="width:20px;">金額</th>
									<th scope="col" style="width:20px;">狀態</th>
									<th scope="col" style="width:80px;"></th>
								</tr>
							</thead>
							
			<!------------------------ 預約單列表  --------------------->
			
	<% 
		Map<String, String[]> appointment = new LinkedHashMap();
		String[] memId = {"8"};
		appointment.put("memId", memId);
		List<ApmVO> list = apmSvc.getAll(appointment);
		pageContext.setAttribute("list", list);
	%>
	
	
	
							<tbody class="appointmentList">
	<c:forEach var="apmVO" items="${list}">						
								<tr>
									<td class="memId">${groSvc.findByPrimaryKey(apmVO.groomerId).gname}</td>
									<td class="apmDate">${apmVO.apmDate}</td>
									<td class="stime">${apmVO.stimeFormated}</td>
									<td class="svcList">	
										<div class="row ml-2">		
											<c:forEach var="apmdVO" items="${apmdSvc.getByAppointmentId(apmVO.apmId)}">
												<div class="svcItem">${apmdVO.item}</div>
											</c:forEach>
										</div>
									</td>
									<td scope="col-md-3" class="total">${apmVO.total}</td>
									<c:if test="${apmVO.apmStatus == '0'}"><td scope="col" class="apmStatus">待回覆</td></c:if>
									<c:if test="${apmVO.apmStatus == '1'}"><td scope="col" class="apmStatus">失敗</td></c:if>
									<c:if test="${apmVO.apmStatus == '2'}"><td scope="col" class="apmStatus">成功</td></c:if>
									<c:if test="${apmVO.apmStatus == '3'}"><td scope="col" class="apmStatus">完成</td></c:if>
									<c:if test="${apmVO.apmStatus == '4'}"><td scope="col" class="apmStatus">取消</td></c:if>
									<td>
									<div class="row">
									
<!-- 									已評分後不可檢舉  | 預約單狀態為待回覆才可取消  | 未評分才可點選評分 -->
									<c:if test="${apmVO.star == ''}"> 
										<button type="button" class="btn report col-md-2" data-toggle="modal" data-target="#reportForm${apmVO.apmId}">檢舉</button>
									</c:if>
									<c:if test="${apmVO.star != ''}"> 
										<button type="button" class="btn report col-md-2 disabled" disabled>檢舉</button>
									</c:if>
									<c:if test="${apmVO.apmStatus == '0'}">
										<button type="button" class="btn cancle col-md-2" id="${apmVO.apmId}" >取消</button>
									</c:if>
									<c:if test="${apmVO.apmStatus != '0'}">
										<button type="button" class="btn cancle col-md-2 disabled" id="${apmVO.apmId}" disabled>取消</button>
									</c:if>
									<c:if test="${apmVO.star == ''}"> 
										<button type="button" class="btn comment col-md-2" data-toggle="modal" data-target="#commentForm${apmVO.apmId}">評價</button>
									</c:if>
									<c:if test="${apmVO.star != ''}"> 
										<button type="button" class="btn comment col-md-2 disabled"  disabled>評價</button>
									</c:if>
									</div>
									</td>
								</tr>	

<!-- Modal -->
								<div class="modal fade" id="commentForm${apmVO.apmId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLabel">	評價</h5>
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true">&times;</span>
								        </button>
								      </div>
<!----------------------------------------------- Comment Form ----------------------------------------->
								      <div class="modal-body">							      		
										<form method="POST" class="commentForm" enctype="multipart/form-data">
											<input type="hidden" name="action" value="addComment">
									      	<input type="hidden" name="apmId" value="${apmVO.apmId}">
										  	<input type="hidden" name="groomerId" value="${apmVO.groomerId}">
									      	<div class="commentForm">
									      	 	<div class="form-group">
											   		 <label for="star">請給分</label>
											   		 <select class="form-control star" name="star" required>
													      <option>1</option>
													      <option>2</option>
													      <option>3</option>
													      <option>4</option>
													      <option>5</option>
												    </select>
										  		</div>
									      		<textarea name="apmComment" placeholder="有話大聲說！" style="width: 70%; height: 200px; word-wrap: break-word;"></textarea>
												<div class="custom-file mt-3">
												    <input type="file" class="custom-file-input" name="photo" required>
												    <label class="custom-file-label" for="validatedCustomFile">選擇照片...</label>
												</div>
										 	 </div>
										
								      </div>
								      <div class="modal-footer">
								      	<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
								      	<button type="submit" class="btn btn-primary submitForm">送出</button>
								      </div>
								      </form>
								    </div>
								  </div>
								</div>
<!-- Modal -->
								<div class="modal fade" id="reportForm${apmVO.apmId}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLabel">檢舉</h5>
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true">&times;</span>
								        </button>
								      </div>
<!----------------------------------------------- Grept Form ----------------------------------------->
									  <form type="POST" class="reportForm">
								     	  <div class="modal-body">							      		
									      	<div class="reportForm">
									      	 	<div class="form-group">
													<input type="hidden" name="action" value="addGreport">
													<input type="hidden" name="apmId" value="${apmVO.apmId}">
													<input type="hidden" name="memId" value="${apmVO.memId}">
													<input type="hidden" name="groomerId" value="${apmVO.groomerId}">
													<textarea name="content" placeholder="有話大聲說！" style="width: 70%; height: 200px; word-wrap: break-word;" require></textarea>
										  		</div>
										 	 </div>
								     	  </div>
									      <div class="modal-footer">
									      	<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
									      	<button type="submit" class="btn btn-primary submitForm">送出</button>
									      </div>
								      </form>
								    </div>
								  </div>
								</div>

	</c:forEach>		
							</tobdy>
				
		</div>
	</section>

</main>

    <script>
        const sessionId = "${sessionId}";
    </script>
    <script src="<%= request.getContextPath() %>/resources/js/shopping_cart.js"></script>
	


	<!-- SCRIPTS -->
<script src="<%= request.getContextPath() %>/resources/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/grooming_appointment_manage.js"></script>


</body>
</html>
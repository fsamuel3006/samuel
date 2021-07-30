<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.works.model.*"%>
<%@ page import="com.service_list.model.*"%>
<%@ page import="com.groomer.model.*"%>
<%@ page import="com.appointment_form.model.*"%>

<jsp:useBean id="worksSvc" scope="page" class="com.works.model.WorkService"/>
<jsp:useBean id="svcListSvc" scope="page" class="com.service_list.model.SvcListService"/>
<jsp:useBean id="groomerSvc" scope="page" class="com.groomer.model.GroService"/>

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
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/fontawesome-all.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/hero-slider.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/owl-carousel.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/templatemo-style.css">
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/sweetalert2.css"> 
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/jquery-ui.css"> 
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/header.css">
<%-- <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/newheader.css"> --%>

<script src="<%= request.getContextPath() %>/resources/js/fontawesome.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/vendor/modernizr-2.8.3-respond-1.4.2.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/sweetalert2.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/jquery-ui.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/popper.min.js"></script>



</head>
<style>
.table td, .table th {
    padding: .75rem;
    /* vertical-align: top; */
    border-top: 1px solid #dee2e6;
    margin: 5px 0px;
}

/* input[type=checkbox], input[type=radio] { */
/*     box-sizing: border-box; */
/*     padding: 0; */
/* } */

.modal-content .left-content input {
    line-height: 30px;
    height: 20px;
    margin-bottom: 0px;
}

td, tr{
font-size: 2px;
color: gray;
text-align: center;
}

th{
text-align: center;
}

.apminput{
margin: 10px 5px;
}

.shopbody {
    min-height: 45vh;
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
           
            	<button class="btn btn-cart mt-1 " type="button" id="dropdownMenuButton" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false" >
                	<i class="fas fa-shopping-cart fa-2x shoppingCart" style="color:#fff;font-size:25px;"></i>
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
<!-------------------------------------------- shopping cart ------------------------------------------------>	   
	        <a href="#" class="iconbth"><i class="fas fa-search fa-1x" style="color:white;font-size:25px;"></i></a>	        	        	
	   
	    </div>
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



  <section class="popular-places" id="popular">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="section-heading">
                        <h2>Tiere堤柇 寵物SPA</h2>
                    </div>
                </div>
            </div>
            <div class="owl-carousel owl-theme">
 <c:forEach var="workVO" items="${worksSvc.getOneList(1)}">          
                <div class="item popular-item">
                    <div class="thumb">
                        <img src="data:image/jpeg; base64, ${workVO.itemBase64}" alt="作品">
                        <div class="plus-button">
                           <i class="fa fa-heart"></i>
                        </div>
                    </div>
                </div>
</c:forEach>                 
            </div>
        </div>
    </section>

    <section class="our-services" id="services">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="down-services">
                        <div class="row">
                            <div class="col-md-6 col-md-offset-1">
                                <div class="left-content">
                               
<% 
	Map<String, String[]> map = new LinkedHashMap<String, String[]>(); 
	String groomerId = request.getParameter("groomerId");
	String[] list = {groomerId};
	map.put("groomerId", list );
	GroVO groVO = groomerSvc.getAll(map).get(0);
	pageContext.setAttribute("groVO", groVO);
%>                                
                                    <p>${groVO.intro}
<!--                                         Tiere是德文「動物」的意思，在這裡，毛孩兒至上！ -->

<!--                                         每一位毛孩，我們都當自己的孩子疼愛與照護，由堤柇美容的毛孩，全面採用天然無毒洗劑 安心無負擔，重視品質也在乎健康！ -->
<!--                                         還可升級碳酸浴，照護肌膚養護毛髮，面對毛孩需求我們提供更多選擇！ -->

<!--                                         ★『合法營業』：特定寵物業許可證 -->
<!--                                         新北特寵業字第A1070387號 -->
<!--                                         特定寵物長照之家牌證 -->
<!--                                         寵物美容每月公告公休時間 -->
<!--                                         謝謝大家支持 堤柇會繼續努力下去！ -->
                                    </p>
                                </div>
                                
                            </div>
                            <div class="col-md-6">
                                <div class="accordions">
                                    <ul class="accordion">
                                        <li>
                                            <a style="color: gray;">給汪汪的服務</a>
<c:forEach var="svcListVO" items="${svcListSvc.getAll(1)}">                                    
                                            <c:if test="${svcListVO.svcPet == '狗'}">
                                            	<p>${svcListVO.svcItem}</p>
                                            </c:if>
</c:forEach>                                        
                                        </li>
                                        <li>
                                            <a style="color: gray;">給貓貓的服務</a>
<c:forEach var="svcListVO" items="${svcListSvc.getAll(1)}">                                               
                                            <c:if test="${svcListVO.svcPet == '貓'}">
                                            	<p>${svcListVO.svcItem}</p>
                                            </c:if>
</c:forEach>                                             
                                        </li>
                                    </ul> <!-- / accordion -->
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12 col-md-offset-1">
                                    <div class="wrapper">
                                        <!-- Modal button -->
                                        <button id="addtrack" class="modal-btn">加入追蹤</button>
                                        <button id="modBtn" class="modal-btn">立即預約</button>
                                    </div>
                                    <div id="modal" class="modal">
                                        <!-- Modal Content -->
                                        <div class="modal-content">
                                            <div class="close fa fa-close"></div>
                                            <div class="row">
                                                <div class="col-md-8">
                                                    <div class="left-content">
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="section-heading">
                                                                    <span>立即預約</span>
                                                                </div>
                                                            </div>
<form id="apmForm">
                                                            <div class="col-md-6">
                                                                <fieldset>
<!--                                                                 自動代入會員姓名 -->
        <input name="name" type="text" class="form-control apminput" id="name" placeholder="Your name..."required="">
        <input type="hidden" name="memId" value="8"> 
		<input type="hidden" name="groomerId" value="1">
                                                                </fieldset>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <fieldset>
        <select class="form-control pid apminput" name="pid">
			<option value="1">Cookie</option>
			<option value="2">NueNue</option>
		</select>
                                                                </fieldset>
                                                            </div>
                                                            <div class="col-md-12">
                                                            <div class="row align-items-center mx-md-3">
																<table class="table table-sm">
																	<thead>
																		<tr>
																			<th>類型</th>
																			<th>服務</th>
																			<th>服務時間/分鐘</th>
																			<th>價格</th>
																			<th>選擇</th>
																		</tr>
																		
																		
																	</thead>
			<tbody class="showSvcList">
			<!--  AJAX 動態產生 -->

			</tbody>
																</table>
																<fieldset>
<!-- <select class="apmDate apminput" name="schId"> -->
<!-- 	<option selected>選擇日期</option> -->
<!-- 	<!-- 			Ajax 動態產生 --> 
<!-- </select>  -->
<p>Date: <input class="apmDate apminput" type="text" name="apmDate" ></p>

<select class="stime apminput form-control" name="stime">
	<option selected>選擇時間</option>
	<!-- 			Ajax 動態產生 -->
</select> 
<input type="hidden" class="needtime v" value="0">
<input type="hidden" class="etime apminput" name="etime" value="0">
<input type="text" class="total apminput" name="total" value="0">
<input type="text" class="address apminput mb-2" name="address" value="220新北市板橋區萬板路55號" disable>
<input type="hidden" name="action" value="addAppointment">	

																</fieldset> 
															</div>
<!--                                                                 <fieldset> -->
<!--                                                                     <textarea name="message" rows="6" -->
<!--                                                                         class="form-control" id="message" -->
<!--                                                                         placeholder="Your message..." -->
<!--                                                                         required=""></textarea> -->
<!--                                                                 </fieldset> -->
                                                            </div>
                                                            <div class="col-md-12">
                                                                <fieldset>
              <button class="btn submit">預約</button>
                                                                </fieldset>
                                                            </div>
                                                            
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class="right-content">
                                                        <div class="row">
                                                            <div class="col-md-12">
                                                                <div class="content">
                                                                    <div class="section-heading">
                                                                        <span>毛孩資料</span>
                                                                        <h2>妞妞</h2>
                                                                    </div>
                                                                    <ul>
                                                                   		<li>馬爾濟斯</li>
                                                                        <li>10歲</li>
                                                                        <li>妹妹</li>
                                                                        <li>心臟病</li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
</form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                    </div>
                    <!-- </div> -->
                </div>
            </div>
    </section>

<jsp:useBean id="apmSvc" scope="page" class="com.appointment_form.model.ApmService"/>
<%
	Map<String, String[]> apmMap = new LinkedHashMap<String, String[]>(); 
	String[] list2 = {groomerId};
	apmMap.put("groomerId", list2 );
	List<ApmVO> apmList = apmSvc.getAll(apmMap);
	pageContext.setAttribute("apmList", apmList);	
%>

    <section class="featured-places" id="blog">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="section-heading">
                        <h2>我們寵愛過的毛孩們</h2>
                    </div>
                </div>
            </div>
            <div class="row">
<c:forEach var="apmVO" items="${apmList}">      
	<c:if test="${apmVO.star != ''}">        
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="featured-item">
                        <div class="thumb">                      
                            <img src="data:image/jpeg; base64, ${apmVO.photoBase64}" alt="">
                            <div class="overlay-content">
                                <ul>
                                <c:forEach var="i" begin="1" end="${apmVO.star}" step="1">    
                                    <li><i class="fa fa-star fa-1x" style="color: #feee7d;"></i></li>
<!--                                     <li><i class="fa fa-star"></i></li> -->
<!--                                     <li><i class="fa fa-star"></i></li> -->
<!--                                     <li><i class="fa fa-star"></i></li> -->
<!--                                     <li><i class="fa fa-star"></i></li> -->
                                </c:forEach>
                                </ul>
                            </div>
                        </div>
                        <div class="down-content">
                            <h4>${apmVO.pid}</h4>
                            <span>${apmVO.apmDate}</span>
                            <p>${apmVO.apmComment}</p>

                        </div>
                    </div>
                </div>
   	</c:if>
</c:forEach>

            </div>
        </div>
    </section>


    <section class="contact" id="contact">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="section-heading">
                        <h2>服務地區</h2>
                    </div>
                </div>
            </div>

        </div>
        <div id="map">
            <!-- How to change your own map point
                           1. Go to Google Maps
                           2. Click on your location point
                           3. Click "Share" and choose "Embed map" tab
                           4. Copy only URL and paste it within the src="" field below
                    -->

            <iframe
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1197183.8373802372!2d-1.9415093691103689!3d6.781986417238027!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xfdb96f349e85efd%3A0xb8d1e0b88af1f0f5!2sKumasi+Central+Market!5e0!3m2!1sen!2sth!4v1532967884907"
                width="100%" height="500px" frameborder="0" style="border:0" allowfullscreen></iframe>
        </div>

    </section>


</main>





	<script>
		// 此處是縮放導覽列的動態效果，這段請一定要抓到，不然它不會動
		//滑鼠滾動(scroll)就開始觸發
		window.addEventListener("scroll", function() {

			const header = document.querySelector('header');
			header.classList.toggle('sticky', window.scrollY > 0);
		});

		$( ".apmDate" ).datepicker({
			minDate: -0,
			beforeShowDay: function(date){
				  let string = jQuery.datepicker.formatDate("yy-mm-dd", date);
				  console.log(disableDate);
			  	  return [disableDate.indexOf(string) == -1]	
			  },
			  maxDate: "+29D",
			  dateFormat: "yy-mm-dd"
		  });
		

	
	let receiverId = ${groVO.memId};
	const sessionId = "${sessionId}";
		
	</script>
	
    <script src="<%= request.getContextPath() %>/resources/js/shopping_cart.js"></script>
    <script src="<%= request.getContextPath() %>/resources/js/bootstrap.min.js"></script>
    <script src="<%= request.getContextPath() %>/resources/js/datepicker.js"></script>
    <script src="<%= request.getContextPath() %>/resources/js/plugins.js"></script>
    <script src="<%= request.getContextPath() %>/resources/js/main.js"></script>
    <script src="<%= request.getContextPath() %>/resources/js/groomer_appointment.js"></script>
</body>
</html>
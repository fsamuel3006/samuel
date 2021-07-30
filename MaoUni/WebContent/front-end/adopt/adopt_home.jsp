<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adopt_imf.model.*"%>
<%@ page import="com.adopt_mechanism.model.*"%>

<%@ page import="com.variety.model.*"%>
<%@ page import="com.variety.model.VarietyVO"%>


<%
	ImfService ImfSvc = new ImfService();
	List<ImfVO> list = ImfSvc.getAll();
	pageContext.setAttribute("list", list);

	VarietyService varSvc = new VarietyService();
	List<VarietyVO> varlist = varSvc.getAll();
	pageContext.setAttribute("varlist", varlist);
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.5, minimum-scale=0.5, user-scalable=no">
<meta name = "viewport" content ="width=device-width,initial-scale-1.0">
    <!-- <script src="JavaScript 檔案位址.js"></script> -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/newheader.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"> <!-- 左邊這個是右上角購物車下拉式選單使用的BootStrap CSS -->  
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/styleD.css"> <!-- 左邊這個是右上角購物車下拉式選單使用的CSS,有再調過細部效果 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/fontawesome-all.min.css"> <!-- 左邊這個是右上角聊天室ICON的引入 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">  <!-- 左邊這個是右上角會員/信箱/購物車/搜尋ICON使用的fontawesomeCDN,使用靜態檔會跑掉 -->
    
    <script src="<%= request.getContextPath() %>/resources/js/fontawesome.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/sweetalert2.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/popper.min.js"></script>
    <title>MaoUni前端公版</title>
    
<style>
table {
	border: 2px;
	width: 1000px;
	height: 100%;
	margin-left: auto;
	margin-right: auto;
	max-width: 100%;
	display: table;
	background-color: #bbb;
	font-size: 20px;
	font-family: DFKai-sb;
	margin-top: 150px;
}

.Photo {
	height: 280px;
	width: 280px;
}



</style>
</head>

<body>

	
	    <div class="icontrue" style="margin: 10px 10px 0px 0px;">
		
		  <a href="<%=request.getContextPath()%>/front-end/member/memberpage.jsp" class="iconbth"><i class="fas fa-user fa-1x" style="color:white;font-size:25px;"></i></a>
		  <a href="<%=request.getContextPath()%>/front-end/chatbox/chatbox.jsp" class="iconbth"><i class="fa fa-comments fa-1x" style="color:white;font-size:28px;padding:2px;margin-top:-4px;"></i></a>
		  <a href="#" class="iconbth"><i class="fas fa-envelope fa-1x" style="color:white;font-size:25px;margin-right:-4px;"></i></a>
	    
	      <div class="dropdown ml-auto">
           
            <button class="btn btn-cart btn-sm" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false" >
                <i class="fas fa-shopping-cart fa-2x" style="color:#fff;"></i>
                <span class="badge badge-pill badge-danger">9</span>
            </button>
           
            <div class="dropdown-menu dropdown-menu-right" style="min-width: 300px" aria-labelledby="dropdownMenuButton">
                <div class="p-3">
                    <table class="table table-sm">
                        <h6>已選擇商品</h6>
                        <tbody style="color:black;">
                            <tr>
                                <td class="align-middle text-center">
                                    <a href="#removeModal" data-price="1999" data-title="不求人" data-toggle="modal"
                                        data-target="#removeModal"><i class="far fa-trash-alt"></i></a>
                                </td>
                                <td class="align-middle">不求人</td>
                                <td class="align-middle">1件</td>
                                <td class="align-middle text-right">$1999</td>
                            </tr>
                            <tr>
                                <td class="align-middle text-center">
                                    <a href="#removeModal" data-price="999" data-title="一級偽裝帽" data-toggle="modal"
                                        data-target="#removeModal"><i class="far fa-trash-alt "></i></a>
                                </td>
                                <td class="align-middle">一級偽裝帽</td>
                                <td class="align-middle">1件</td>
                                <td class="align-middle text-right">$999</td>
                            </tr>
                        </tbody>
                    </table>
                    <a href="#" class="btn btn-block btn-primary btn-sm text-white">確認結帳</a>
                </div>
            </div>
        </div>
	   
	        <a href="#" class="iconbth"><i class="fas fa-search fa-1x" style="color:white;font-size:25px;"></i></a>	        	        	
	   
	    </div>
	    
		<!--icon一定要在top-flex之上，不然會被移動條給蓋爆777777-->
	
		<div class="Top-Flex">
			<div class="LOOP">
			    	<a class="two" href="<%=request.getContextPath()%>/back-end/listAllItem">
			    	
			    	</a>
			</div>
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
		<div class="Shopping-ul"></div>
	    
	</header>
    

    <!--以上為頭部的html設定位子-->

	<script>
    // 此處是縮放導覽列的動態效果，這段請一定要抓到，不然它不會動
    //滑鼠滾動(scroll)就開始觸發
    window.addEventListener("scroll",function(){

    const header = document.querySelector('header');
    header.classList.toggle('sticky',window.scrollY > 0);
    });
    </script>
    
    <div class="textttt" style=" width: 100%;height: 2000px;">	

<table style="border: 3px #2F0000 dashed;" cellpadding="10" border='1'>
	<tr>
		<th>照片</th>
		<th>動物編號</th>
		<th>動物品種</th>
		<th>所在機構</th>
		<th>名字</th>
		<th>年齡</th>
		<th>身體狀態</th>
		<th>追蹤</th>



	</tr>


	<c:forEach var="ImfVO" items="${list}">
		<tr>
			<td><img class="Photo"
				src="<%=request.getContextPath()%>/ImfPhotoServlet?AI_ID=${ImfVO.id}"></td>
			<td>${ImfVO.id}</td>

			<td><c:forEach var="VarietyVO" items="${varlist}">
					<c:if test="${ImfVO.adopt==VarietyVO.varId}">
	                  ${VarietyVO.varName}
                    </c:if>
				</c:forEach></td>
			<td>${ImfVO.mech2}</td>
			<td>${ImfVO.name}</td>
			<td>${ImfVO.year}</td>
			<td>${ImfVO.sit}</td>

			<td>
			<form class="addTrack">
				<input type="hidden" name="memeId" value="2">
				<input type="hidden" name="petId" value="${ImfVO.id}">
				<input type="hidden" name="action" value="insert">
				<button type="submit">加入追蹤</button>
			</form>	
			</input>
				</FORM>

			</td>
	</c:forEach>

</table>
<footer class="footer">
        <ul class="r-footer">
	        <li>
	            <h2>About</h2>
		        <ul class="box">
			        <li><a class="ff1" href="#">品牌故事</a></li>
			        <li><a class="ff2" href="#">最新文章</a></li>
			        <li><a class="ff3" href="#">公告</a></li>
			        <li><a class="ff4" href="#">FAQ</a></li>
		        </ul>
	        </li>
	
	        <li>
	            <h2>Service</h2>
	            <ul class="box">
		            <li><a class="ff5" href="#">隱私權政策</a></li>
		            <li><a class="ff6" href="#">退換貨政策</a></li>
		            <li><a class="ff7" href="#">付款相關問題</a></li>
		            <li><a class="ff8" href="#">運算相關問題</a></li>
	            </ul>
	        </li>
	
	        <li>
		        <h2>Contact</h2>
		        <ul class="box">
			        <li><a class="ff9" href="#">客服信箱</a></li>
			        <li><a class="ff10" href="#">LINE客服</a></li>
			        <li><a class="ff11" href="#">聯絡電話</a></li>
		        </ul>
	        </li>

	        <li>
	            <h2>Follow</h2>
		        <ul class="box">	        	
		        	<li class="ig"><a class="btn btn-primary" style="color:white;font-size:14px;cursor: pointer;background:none;border:none;"><span class="fab fa-instagram"></span>&nbsp;毛孩有你</a></li>
		        	<li class="fb"><a class="btn btn-primary" style="color:white;font-size:16px;margin-top:-2px;cursor: pointer;background:none;border:none;"><span class="fab fa-facebook-square"></span>&nbsp;MaoUni</a></li>
		        </ul>
	        </li>        
        </ul>
	</footer>
	
    <div class="b-footer">
    <p style="margin-bottom: 10px;">All rights reserved by ©MaoUni 2021 </p>
    </div>	
</body>
<script>
	function highLight() {
		obj = event.srcElement;
		obj.style.color = "#FFFF99";
		obj.style.backgroundColor = "#00FFFF";
	}

	function nocolor() {
		obj = event.srcElement;
		obj.style.color = "black";
		obj.style.backgroundColor = "white";
	}

	
	$(".addTrack").click(function(e){
		e.preventDefault();
		$.ajax({
			url:"/MaoUni/TrackingController",
			type:"post",
			data: $(this).serialize(),
			success: function(data){
				if(data === 0){
					swal("新增失敗！","這個毛孩已經在追蹤名單囉!","warning");
				}else{
					swal("新增成功！","這個毛孩加入追蹤名單囉!","success");
				}
			}
		})
	})
	
	
	
	
</script>
	<script>
        const sessionId = "${sessionId}";
    </script>
    <script src="<%= request.getContextPath() %>/resources/js/shopping_cart.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/cmain.js"></script>
	
	
	<!-- 以下為CDN引入 -->
	<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
    <script src="<%= request.getContextPath() %>/resources/js/popper.min.js"></script>
    <script src="<%= request.getContextPath() %>/resources/js/bootstrap.min.js"></script>
	
	
</body>
</html>

</container>


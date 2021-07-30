<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.message.model.*"%>

<%
	MessageVO messageVO = (MessageVO) request.getAttribute("MessageVO");
%>

<html lang="en">
<head>
<meta charset="UTF-8">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap"
	rel="stylesheet">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.5, minimum-scale=0.5, user-scalable=no">
<meta name="viewport" content="width=device-width,initial-scale-1.0">
<!-- <script src="JavaScript 檔案位址.js"></script> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<!-- 左邊這個是右上角購物車下拉式選單使用的BootStrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/styleD.css">
<!-- 左邊這個是右上角購物車下拉式選單使用的CSS,有再調過細部效果 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/fontawesome-all.min.css">
<!-- 左邊這個是右上角聊天室ICON的引入 -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
<!-- 左邊這個是右上角會員/信箱/購物車/搜尋ICON使用的fontawesomeCDN,使用靜態檔會跑掉 -->

<title>MaoUni前端公版</title>
</head>
<style>






article, aside, details, figcaption, figure, header, hgroup, menu, nav,
	section {
	display: block;
}

body {
	font-size: 18px;
	color: rgb(255, 255, 255);
	font-family: "Microsoft JhengHei";
	margin: 0;
/* 	display: grid; */
}

ol, ul {
	list-style: none;
}

blockquote, q {
	quotes: none;
}

blockquote:before, blockquote:after, q:before, q:after {
	content: '';
	content: none;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
}

* {
	box-sizing: border-box;
	margin: 0px;
	padding: 0px;
}

.Top-Flex { /*這就是上面那條跑來跑去的最開始的底*/
	position: fixed;
	background-color: #c19e74;
	font-weight: bold;
	padding: 0 50px;
	height: 170px;
	width: 100%;
	display: flex;
	align-items: top;
	border-radius: 0px 0px 30px 30px;
	z-index: 2;
}

header {
	position: fixed;
	top: 0px;
	left: 0px;
	width: 100%;
	height: 170px;
	display: flex;
	justify-content: flex-end;
	transition: 1s;
	z-index: 1;
}

header.sticky .Top-Flex { /* 這是下拉後的Top-Flex寬度跟顯示 */
	height: 100px;
	transition: 2.5s;
}

.LOOP {
	display: flex;
	margin-left: 440px;
	margin-top: 3px;
	/*大logo因為看起來沒置中所以是用這個去調到置中的 */
}

header.sticky .two img {
	width: 120px;
	margin-left: 100px; /*小logo用這個移動*/
}

.two img { /*縮小上頭logo時用*/
	width: 300px;
	transition: 1s;
}

/* ------icon區的css------- */
.icontrue {
	display: flex;
	z-index: 5;
	margin-right: 5px;
}

.iconbth { /*給icon間距*/
	margin: 7px;
	z-index: 5;
}

.icontrue img { /*icontrue下的img全縮小*/
	width: 20px;
	border-radius: 4px solid #fff;
	color: #c19e74;
	z-index: 5; /*這個一定要加不然會把上下滑動蓋掉，且無法hover差點氣死*/
}

.iconbth:hover { /*移到icon上時會有半透明的效果*/
	opacity: 0.5;
	z-index: 5;
}

.iconbth1 { /*給icon間距*/
	margin: 7px;
	z-index: 0;
}

.iconbth1:hover { /*移到icon上時會有半透明的效果*/
	opacity: 0.5;
	z-index: 0;
}

/* ------icon區的css結束------- */
.shopbody { /*有你來玩那排的導覽列*/
	position: fixed;
	display: flex;
	font-weight: bold;
	font-size: 13px;
	width: 100%;
	justify-content: center; /*這整排置中*/
	align-items: center; /*讓字在中間的位子*/
	padding-top: 100px;
	z-index: 2;
}

header.sticky .shopbody {
	min-height: 25vh;
	transition: 2s;
}

nav {
	position: relative;
	display: flex;
}

nav a {
	margin: 0 20px;
	/* 字跟字之間的寬度 */
	color: white;
	text-decoration: none; /* 去掉超連結的底線 */
	height: 5px;
	width: 100px;
	/* padding-top: 0px; */
}

/*有你來玩那排的導覽列結束*/
/*只要top的話只需複製上面這些區域*/
/* 以下為FOOTER CSS */
.footer {
	margin: 0;
	display: flex;
	flex-flow: row wrap;
	padding: 50px;
	color: #fff;
	background-color: #c19e74;
	font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
	font-size: 14px;
	position: relative;
	margin-bottom: 10px;
}

.footer>* {
	flex: 1 100%;
}

h2 {
	font-weight: 1000;
	font-size: 35px;
}

.footer ul {
	list-style: none;
	padding-left: 0;
}

.footer li {
	line-height: 2em;
}

.footer a {
	text-decoration: none;
}

.r-footer {
	display: flex;
	text-align: center;
	margin-left: -15px;
}

.box {
	margin-top: 10px;
}

.box a {
	color: #fff;
}

.h-box {
	column-count: 2;
	column-gap: 1.25em;
}

.b-footer {
	text-align: center;
	color: #a0591f;
	font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
	width: 100%;
	background-color: #fff;
}

@media screen and (min-width: 600px) {
	.r-footer>* {
		flex: 1;
	}
	.features {
		flex-grow: 2;
	}
	.DDM>* {
		flex: 1;
	}
}

::placeholder {
	color: #a0591f;
}

:placeholder-shown {
	text-decoration: none;
	border: 1px solid #b89063;
}

:placeholder-shown:hover {
	border-color: #b89063;
}

.ff1:hover {
	color: #f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff2:hover {
	color: #f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff3:hover {
	color: #f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff4:hover {
	color: #f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff5:hover {
	color: #f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff6:hover {
	color: #f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff7:hover {
	color: #f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff8:hover {
	color: #f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff9:hover {
	color: #f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff10:hover {
	color: #f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff11:hover {
	color: #f8e682;
	font-weight: 500;
	font-size: 15px;
}

.btn-primary {
	color: #fff;
	background-color: #d4af81;
	border-color: #d4af81;
}

.btn-primary:hover {
	background-color: #b89063;
	border-color: #b89063;
}

#igbtn:hover {
	color: #f8e682;
	font-size: 15px;
}

#fbbtn:hover {
	color: #f8e682;
	font-size: 15px;
}

.fa-trash-alt {
	color: #a0591f;
}

.fa-trash-alt:hover {
	color: #b89063;
}

.fa-instagram:hover {
	color: #f8e682;
	font-weight: 1000;
	font-size: 20px;
}

.fa-facebook-square:hover {
	color: #f8e682;
	font-weight: 1000;
	font-size: 20px;
}

/* .member1 { */
/* 	margin: auto; */
/* 	margin-top: 300px; */
/* 	margin-left: 600px; */
/* 	border: 1px solid #000; */
/* 	font-size: 25px; */
/* 	width: 40%; */
/* 	height: 1px; */
/* 	padding: 70px 20px 10px 20px; */
/* } */

/* .message-bt { */
/* 	max-width:90px; */ 
/* 	margin: auto; */
/* 	margin-left: 400px; */
/* 	margin-top: 500px; */
/* 	border: 1px solid #000; */
/* 	color: #000000; */
/* 	width: 50%; */
/* 	height: 50px; */
/* 	font-size: 25px; */
/* 	padding: 70px 20px 10px 20px; */
/* } */
.member1{
	width: 100%;
}

.add textarea{
	width: 100%;
}

.add{
	width: 100%;
	color:black;
}

.add td,.add th{
	padding: 5px;
}

.td2{
	width: 20%;
}

.mesWrap{
	margin-top: 230px;
	margin-bottom: 100px;
	margin-left: auto;
	margin-right: auto;
	width: 700px;
	
}

</style>

<body>

	<header>

		<div class="icontrue" style="margin: 10px 10px 0px 0px;">

			<a href="#" class="iconbth"><i class="fas fa-user fa-1x"
				style="color: white; font-size: 25px;"></i></a> <a href="#"
				class="iconbth"><i class="fa fa-comments fa-1x"
				style="color: white; font-size: 28px; padding: 2px; margin-top: -4px;"></i></a>
			<a href="#" class="iconbth"><i class="fas fa-envelope fa-1x"
				style="color: white; font-size: 25px; margin-right: -4px;"></i></a>

			<div class="dropdown ml-auto">

				<button class="btn btn-cart btn-sm" type="button"
					id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">
					<i class="fas fa-shopping-cart fa-2x" style="color: #fff;"></i> <span
						class="badge badge-pill badge-danger">9</span>
				</button>

				<div class="dropdown-menu dropdown-menu-right"
					style="min-width: 300px" aria-labelledby="dropdownMenuButton">
					<div class="p-3">
						<table class="table table-sm">
							<h6>已選擇商品</h6>
							<tbody style="color: black;">
								<tr>
									<td class="align-middle text-center"><a
										href="#removeModal" data-price="1999" data-title="不求人"
										data-toggle="modal" data-target="#removeModal"><i
											class="far fa-trash-alt"></i></a></td>
									<td class="align-middle">不求人</td>
									<td class="align-middle">1件</td>
									<td class="align-middle text-right">$1999</td>
								</tr>
								<tr>
									<td class="align-middle text-center"><a
										href="#removeModal" data-price="999" data-title="一級偽裝帽"
										data-toggle="modal" data-target="#removeModal"><i
											class="far fa-trash-alt "></i></a></td>
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

			<a href="#" class="iconbth"><i class="fas fa-search fa-1x"
				style="color: white; font-size: 25px;"></i></a>

		</div>

		<div class="Top-Flex">
			<div class="LOOP">
				<a class="two"
					href="<%=request.getContextPath()%>/back-end/listAllItem"> </a>
			</div>
		</div>

		<div class="shopbody">
			<nav style="margin-bottom: -15px; margin-left: 15px;">
				<p class="hover-underline-animation">
					<a href="#" style="font-size: 15px;">首 頁</a>
				</p>
				<p class="hover-underline-animation">
					<a href="#" style="font-size: 15px;">有你來買</a>
				</p>
				<p class="hover-underline-animation">
					<a href="#" style="font-size: 15px;">有你來講</a>
				</p>
				<p class="hover-underline-animation">
					<a href="#" style="font-size: 15px;">有你來玩</a>
				</p>
				<p class="hover-underline-animation">
					<a href="#" style="font-size: 15px;">到府美容</a>
				</p>
				<p class="hover-underline-animation">
					<a href="#" style="font-size: 15px;">浪浪找家</a>
				</p>
				<p class="hover-underline-animation">
					<a href="#" style="font-size: 15px;">知識站</a>
				</p>
				<p class="hover-underline-animation">
					<a href="#" style="font-size: 15px;">公告</a>
				</p>
			</nav>
		</div>
		<div class="Shopping-ul"></div>

	</header>


	<!--以上為頭部的html設定位子-->

	<!-- <script>
		// 此處是縮放導覽列的動態效果，這段請一定要抓到，不然它不會動
		//滑鼠滾動(scroll)就開始觸發
		window.addEventListener("scroll", function() {

			const header = document.querySelector('header');
			header.classList.toggle('sticky', window.scrollY > 0);
		});
	</script> -->
		<div class="mesWrap">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/messageServlet" name="form1">

				<table class="add" border="1">					
					<tr>
						<td class="td2">會員編號:</td>
						<td><input type="text" name="memId" class="member1"></td>
					</tr>
					<tr>
						<td class="td2">輸入內容</td>
						<td><textarea class="message-bt" placeholder="請勿輸入不雅字眼" rows="5" id="comment" type="TEXT" name="contain"
								value="${param.contain}" ></textarea></td>
					</tr>
					<tr>
						<td class="td2">客服是否曾聯繫過您？</td>

						<td>
							<select class="status" value:"${param.status}" name="status">
								<option value="0">否</option>
								<option value="1">有</option>
							</select>
						</td>
					</tr>
							
					<jsp:useBean id="MessageSvc" scope="session"
						class="com.message.model.MessageService" />
		
				</table>
				
					<br> <input type="submit" value="送出新增"> </br>
					<input type="hidden" name="action" value="addMessage">
				</FORM>
		</div>




			<!-- 以下為底部FOOTER -->
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
							<li class="ig"><a class="btn btn-primary"
								style="color: white; font-size: 14px; cursor: pointer; background: none; border: none;"><span
									class="fab fa-instagram"></span>&nbsp;毛孩有你</a></li>
							<li class="fb"><a class="btn btn-primary"
								style="color: white; font-size: 16px; margin-top: -2px; cursor: pointer; background: none; border: none;"><span
									class="fab fa-facebook-square"></span>&nbsp;MaoUni</a></li>
						</ul>
					</li>
				</ul>
			</footer>

			<div class="b-footer">
				<p style="margin-bottom: 10px;">All rights reserved by ©MaoUni
					2021</p>
			</div>
			<!-- 以上為底部FOOTER -->




			<!-- 以下為JS引入 -->
			<script src="<%=request.getContextPath()%>/resources/js/cmain.js"></script>


			<!-- 以下為CDN引入 -->
			<script
				src="<%=request.getContextPath()%>/resources/js/jquery_1.12.4.min.js"></script>
			<script
				src="<%=request.getContextPath()%>/resources/js/popper.min.js"></script>
			<script
				src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>


			</script>
</body>
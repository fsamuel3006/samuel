<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>毛孩有你 MaoUni</title>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="author" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<link rel="stylesheet" href="/MaoUni/resources/css/bootstrap.min.css">
<!--     <link rel="stylesheet" href="/MaoUni/resources/css/font-awesome.min.css"> -->


<!-- MAIN CSS -->
<link rel="stylesheet"
	href="/MaoUni/resources/css/tooplate-gymso-style.css">
<script src="https://kit.fontawesome.com/0f58Cfd6ae7.js"
	crossorigin="anonymous"></script>
<!--
Tooplate 2119 Gymso Fitness
https://www.tooplate.com/view/2119-gymso-fitness
-->
<link rel="stylesheet" href="/MaoUni/resources/css/header.css">
</head>
<body data-spy="scroll" data-target="#navbarNav" data-offset="50">
<header>
		<div class="icontrue">

			<a href="#" class="iconbth"><img src="/MaoUni/resources/images/dodge.png"
				border="0"></a> <a href="#" class="iconbth"><img
				src="/MaoUni/resources/images/user.png" border="0"></a> <a href="#"
				class="iconbth"><img src="/MaoUni/resources/images/shopping_cart.png" border="0"></a>
			<a href="#" class="iconbth"><img src="/MaoUni/resources/images/paper_plane.png"
				border="0"></a> <a href="#" class="iconbth"><img
				src="/MaoUni/resources/images/public_access.png" border="0"></a>
			<!--iconu一定要在top-flex之上，不然會被移動條給蓋爆777777-->
		</div>
		<div class="Top-Flex">
			<ul class="LOOP">
				<li class="two"><img src="/MaoUni/resources/images/MaoUni2.png" width="" alt=""></li>
			</ul>
		</div>

		<div class="shopbody">
			<nav>
				<a href="#">首 頁</a> 
				<a href="#">有你來買</a> 
				<a href="#">有你來講</a> 
				<a href="#">有你來玩</a> 
				<a href="#">到府美容</a> 
				<a href="#">浪浪找家</a> 
				<a href="#">友善店家</a> 
				<a href="#">知識站</a>
			</nav>
		</div>
</header>



	<!-- MENU BAR -->
<!-- 	<nav class="navbar navbar-expand-lg fixed-top"> -->
<!-- 		<div class="container"> -->

<!-- 			<a class="navbar-brand" href="index.html">毛孩有你 MaoUni</a> -->

<!-- 			<button class="navbar-toggler" type="button" data-toggle="collapse" -->
<!-- 				data-target="#navbarNav" aria-controls="navbarNav" -->
<!-- 				aria-expanded="false" aria-label="Toggle navigation"> -->
<!-- 				<span class="navbar-toggler-icon"></span> -->
<!-- 			</button> -->

<!-- 			<div class="collapse navbar-collapse" id="navbarNav"> -->
<!-- 				<ul class="navbar-nav ml-lg-auto"> -->
<!-- 					<li class="nav-item"><a href="#home" -->
<!-- 						class="nav-link smoothScroll">Home</a></li> -->

<!-- 					<li class="nav-item"><a href="#about" -->
<!-- 						class="nav-link smoothScroll">About Us</a></li> -->

<!-- 					<li class="nav-item"><a href="#class" -->
<!-- 						class="nav-link smoothScroll">Classes</a></li> -->

<!-- 					<li class="nav-item"><a href="#schedule" -->
<!-- 						class="nav-link smoothScroll">Schedules</a></li> -->

<!-- 					<li class="nav-item"><a href="#contact" -->
<!-- 						class="nav-link smoothScroll">Contact</a></li> -->
<!-- 				</ul> -->

<!-- 				<ul class="social-icon ml-lg-3"> -->
<!-- 					<li><a href="https://fb.com/tooplate" class="fa fa-facebook"></a></li> -->
<!-- 					<li><a href="#" class="fa fa-twitter"></a></li> -->
<!-- 					<li><a href="#" class="fa fa-instagram"></a></li> -->
<!-- 				</ul> -->
<!-- 			</div> -->

<!-- 		</div> -->
<!-- 	</nav> -->



	<!-- ABOUT -->
<!-- 	<section class="about section" id="about"> -->
<!-- 		<!--------------------------------------------- searchSchdule-------------------------------------------------------------> -->
<!-- 		<form method="post" action="/MaoUni/groomer.do"> -->
<!-- 			<div class="searchGM row"> -->
<!-- 				<div class="col-md-2 my-1"> -->
<!-- 					<select class="form-control" name="month"> -->
<!-- 						<option selected>月份</option> -->
<!-- 						<option value="1">01</option> -->
<!-- 						<option value="2">02</option> -->
<!-- 						<option value="3">03</option> -->
<!-- 						<option value="4">04</option> -->
<!-- 						<option value="5">05</option> -->
<!-- 						<option value="6">06</option> -->
<!-- 						<option value="7">07</option> -->
<!-- 						<option value="8">08</option> -->
<!-- 						<option value="9">09</option> -->
<!-- 						<option value="10">10</option> -->
<!-- 						<option value="11">11</option> -->
<!-- 						<option value="12">12</option> -->
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 				<div class="col-md-3 my-1"> -->
<!-- 					<input class="form-control" name="groomerId" type="date"> -->
<!-- 				</div> -->
<!-- 				<div class="col-md-3 my-1"> -->
<!-- 					<select class="form-control" name="apmStatus"> -->
<!-- 						<option value="all">ALL</option> -->
<!-- 						<option value="0">待審核</option> -->
<!-- 						<option value="1">拒絕</option> -->
<!-- 						<option value="2">接受</option> -->
<!-- 						<option value="3">完成</option> -->
<!-- 						<option value="4">取消</option> -->
<!-- 					</select> -->
<!-- 				</div> -->
<!-- 				<input class="action" type="hidden" name="action" -->
<!-- 					value="getListByCondition"> -->
<!-- 				<button type="submit" class="btn btn-primary getList ml-4 my-1"> -->
<!-- 					查詢</i> -->
<!-- 				</button> -->
<!-- 			</div> -->
<!-- 		</form> -->
<!-- 		<!----------------------------------------------------------------------------------------------------------> -->
<!-- 		<div> -->
<!-- 			<table class="table table-hover table-sm text-center"> -->
<!-- 				<thead> -->
<!-- 					<tr> -->
<!-- 						<th scope="col">編號</th> -->
<!-- 						<th scope="col">會員</th> -->
<!-- 						<th scope="col">預約日期</th> -->
<!-- 						<th scope="col">預約時間</th> -->
<!-- 						<th scope="col">預計結束</th> -->
<!-- 						<th scope="col">金額</th> -->
<!-- 						<th scope="col">狀態</th> -->
<!-- 						<th scope="col"></th> -->
<!-- 					</tr> -->
<!-- 				</thead> -->
<!-- 				<tbody class="appointmentList"> -->
<!-- 											ajax 動態產生預約單 -->
<!-- 				</tbody> -->
<!-- 			</table> -->

<!-- 		</div> -->
<!-- 		</div> -->

<!-- 		<!-- Modal --> -->
<!-- 		<div class="modal fade" id="showManage" tabindex="-1" role="dialog" -->
<!-- 			aria-labelledby="exampleModalCenterTitle" aria-hidden="true"> -->
<!-- 			<div class="modal-dialog modal-dialog-centered" role="document"> -->
<!-- 				<div class="modal-content"> -->
<!-- 					<div class="modal-header"> -->
<!-- 						<h5 class="modal-title" id="exampleModalCenterTitle">預約單管理</h5> -->
<!-- 						<button type="button" class="close" data-dismiss="modal" -->
<!-- 							aria-label="Close"> -->
<!-- 							<span aria-hidden="true">&times;</span> -->
<!-- 						</button> -->
<!-- 					</div> -->
<!-- 					<div class="modal-body">...</div> -->
<!-- 					<div class="modal-footer"> -->
<!-- 						<button type="button" class="btn btn-secondary refuse" -->
<!-- 							data-dismiss="modal">拒絕</button> -->
<!-- 											        ajax動態更換 -->
<!-- 											        <button type="button" class="btn btn-secondary cancel" data-dismiss="modal">取消</button> -->
<!-- 						<button type="button" class="btn btn-primary accept">接受</button> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</section> -->




	<!-- SCHEDULE -->
	<section class="schedule section" id="schedule">
		<div class="container">
			<div class="row">

				<div class="col-lg-12 col-12 text-center">
					<!-- <h6 data-aos="fade-up">our weekly GYM schedules</h6> -->

					<h2 class="text-white" data-aos="fade-up" data-aos-delay="200">預約行程</h2>
				</div>

				<div class="col-lg-12 py-5 col-md-12 col-12">
					<div class="guide">
						<span>待審核</span>
					</div>
					<table class="table table-bordered table-responsive schedule-table"
						data-aos="fade-up" data-aos-delay="300">

						<thead class="thead-light">
							<th>一</th>
							<th>二</th>
							<th>三</th>
							<th>四</th>
							<th>五</th>
							<th>六</th>
							<th>日</th>
						</thead>

						<tbody>
							<tr>
								<td>
									<div class="date">
										<strong>1</strong>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>2</strong>
									</div>
									<div class="apmId pending">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>3</strong>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>4</strong>
									</div> <strong>休</strong>
								</td>
								<td>
									<div class="date">
										<strong>5</strong>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId pending">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>6</strong>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId pending">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>7</strong>
									</div>
								</td>
							</tr>

							<tr>
								<td>
									<div class="date">
										<strong>8</strong>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>9</strong>
									</div>
									<div class="apmId pending">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>10</strong>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>11</strong>
									</div> <strong>休</strong>
								</td>
								<td>
									<div class="date">
										<strong>12</strong>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId pending">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>13</strong>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId pending">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>15</strong>
									</div>
								</td>
							</tr>

							<tr>
								<td>
									<div class="date">
										<strong>16</strong>
									</div>
									<div class="apmId ending">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>17</strong>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId pending">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>18</strong>
									</div> <strong>休</strong>
								</td>
								<td>
									<div class="date">
										<strong>19</strong>
									</div>

									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>20</strong>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId pending">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId pending">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>21</strong>
									</div>

								</td>
								<td>
									<div class="date">
										<strong>22</strong>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId pending">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
							</tr>

							<tr>
								<td>
									<div class="date">
										<strong>23</strong>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>24</strong>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId pending">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>25</strong>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId pending">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>26</strong>
									</div> <strong>休</strong>
								</td>
								<td>
									<div class="date">
										<strong>27</strong>
									</div>

								</td>
								<td>
									<div class="date">
										<strong>28</strong>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId pending">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
								</td>
								<td>
									<div class="date">
										<strong>29</strong>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="date">
										<strong>30</strong>
									</div> <strong>休</strong>
								</td>
								<td>
									<div class="date">
										<strong>31</strong>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId pending">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>
									<div class="apmId">
										<span>7:00 - 9:00 </span>
									</div>

								</td>
								<td>
									<div class="date">
										<strong></strong>
									</div>

								</td>
								<td>
									<div class="date">
										<strong></strong>
									</div>
								</td>
								<td>
									<div class="date">
										<strong></strong>
									</div>

								</td>
								<td>
									<div class="date">
										<strong></strong>
									</div>

								</td>
								<td>
									<div class="date">
										<strong></strong>
									</div>
								</td>
							</tr>

						</tbody>
					</table>
				</div>

			</div>
		</div>
	</section>



	<!-- FOOTER -->
	<footer class="site-footer">
		<div class="footer">
			<ul class="Bottom1">
				<li class="About">About us</li>
				<li class="Service">Service</li>
				<li class="Contact">Contact us</li>
				<li class="Follow">Follow us</li>
			</ul>

		</div>
	</footer>

	<!-- Modal -->
	<div class="modal fade" id="membershipForm" tabindex="-1" role="dialog"
		aria-labelledby="membershipFormLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">

			<div class="modal-content">
				<div class="modal-header">

					<h2 class="modal-title" id="membershipFormLabel">Membership
						Form</h2>

					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>

				<div class="modal-body">
					<form class="membership-form webform" role="form">
						<input type="text" class="form-control" name="cf-name"
							placeholder="John Doe"> <input type="email"
							class="form-control" name="cf-email"
							placeholder="Johndoe@gmail.com"> <input type="tel"
							class="form-control" name="cf-phone" placeholder="123-456-7890"
							pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" required>

						<textarea class="form-control" rows="3" name="cf-message"
							placeholder="Additional Message"></textarea>

						<button type="submit" class="form-control" id="submit-button"
							name="submit">Submit Button</button>

						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input"
								id="signup-agree"> <label
								class="custom-control-label text-small text-muted"
								for="signup-agree">I agree to the <a href="#">Terms
									&amp;Conditions</a>
							</label>
						</div>
					</form>
				</div>

				<div class="modal-footer"></div>

			</div>
		</div>
	</div>

	<script>
	$.ajax({
		url: "/MaoUni/appointment.do",
		type: "GET",
		data:{
			groomerId: "1",
			action: "getAll"
		},
		success: function(data){
			console.log(data);
			let obj = JSON.parse(data);
			console.log(obj);
			let html = "";
			for(let i = 0; i < obj.length; i++){
				let apmStatus = "";

				switch (obj[i].apmStatus){
				case 0:
					apmStatus += "待審核";
					break;
				case 1:
					apmStatus += "已拒絕";
					break;
				case 2:
					apmStatus += "已接受";
					break;
				case 3:
					apmStatus += "已完成  ";
					break;
				case 4:
					apmStatus += "已取消";
					break;
				}
				
				let stime = obj[i].stime;
				let etime = obj[i].etime;
				let stimeStr = "";
				let etimeStr = "";
				
				if(stime % 2 === 1 ){
					if(stime < 19)
						stimeStr += `0\${(stime+1)/2}:00`;
					else
						stimeStr += `\${(stime+1)/2}:00`;
				}
				if(stime % 2 === 0 ){
					if(stime < 19)
						stimeStr += `0\${stime/2}:30`;
					else
						stimeStr += `\${stime/2}:30`;
				}
				
				if(etime % 2 === 1 ){
					if(etime < 19)
						etimeStr += `0\${(etime+1)/2}:00`;
					else
						etimeStr += `\${(etime+1)/2}:00`;
				}
				if(etime % 2 === 0 ){
					if(etime < 19)
						etimeStr += `0\${etime/2}:30`;
					else
						etimeStr += `\${etime/2}:30`;
				}
				
				
				html += `
					<tr>
						<td scope="col" class="apmId">\${obj[i].apmId}</td>
						<td scope="col" class="memId">\${obj[i].memId}</td>
						<td scope="col" class="apmDate">\${obj[i].apmDate}</td>
						<td scope="col" class="stime">\${stimeStr}</td>
						<td scope="col" class="etime">\${etimeStr}</td>
						<td scope="col" class="total">\${obj[i].total}</td>
						<td scope="col" class="apmStatus">\${apmStatus}</td>
						<td scope="col" ><i class="far fa-edit manage" type="button" id="\${obj[i].apmId}" data-toggle="modal" data-target="#showManage"></i></td>
					</tr>
				`;
			}
			$(".appointmentList").html(html);
		}
	})
	
	$(document).on("click", ".manage", function(e){
		$("#showManage").modal("show");
		let id = e.target.id;
		$.ajax({
			url: "/MaoUni/appointment.do",
			type: "GET",
			data:{
				apmId: id,
				action: "getAll"
			},
			success: function(data){
				let obj = JSON.parse(data);
				let html = "";
				
				html += `
				
				
				
				
				
				
				
				
				`
				
				
				
// 				$(".modal-body").html(html);
			}
		})
	})
	
	
	
	
	
	</script>

	<script>
		// 此處是縮放導覽列的動態效果，這段請一定要抓到，不然它不會動
		//滑鼠滾動(scroll)就開始觸發
		window.addEventListener("scroll", function() {

			const header = document.querySelector('header');
			header.classList.toggle('sticky', window.scrollY > 0);
		});
	</script>


	<!-- SCRIPTS -->
	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	<!--     <script src="/MaoUni/resources/js/jquery.min.js"></script> -->
	<!--     <script src="/MaoUni/resources/js/bootstrap.min.js"></script> -->
	<!--     <script src="/MaoUni/resources/js/aos.js"></script> -->
	<!--     <script src="/MaoUni/resources/js/smoothscroll.js"></script> -->

</body>
</html>
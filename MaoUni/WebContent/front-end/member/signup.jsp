<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="sgu.css" rel="stylesheet" type="text/css" />
<title>Document</title>

<style>
@import
	url("https://fonts.googleapis.com/css?family=Montserrat:400,600&display=swap")
	;

* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	align-items: center;
	background: #dabfa0;
	color: rgba(0, 0, 0, 0.8);
	display: grid;
	font-family: "Montserrat", sans-serif;
	font-size: 14px;
	font-weight: 400;
	height: 100vh;
	justify-items: center;
	weight: 100vw;
}

.signup-container {
	/* 讓兩個板塊(狗狗跟註冊的,重疊) */
	display: grid;
	grid-template-areas: "left right";
	max-width: 900px;
}

.left-container {
	/* 縮放狗狗的圖片 */
	background: #f0e7dc;
	overflow: hidden;
	padding: 40px 0 0 0;
	position: relative;
	text-align: center;
	width: 300px;
}

.left-container h1 {
	/* 字體 */
	color: rgba(0, 0, 0, 0.8);
	display: inline-block;
	font-size: 24px;
}

.left-container h1 i {
	background: #F7B1AB;
	border-radius: 50%;
	color: #dabfa0;
	font-size: 24px;
	margin-right: 5px;
	padding: 10px;
	transform: rotate(-45deg);
}

.left-container .puppy {
	/* 狗狗的位子 */
	bottom: -5px;
	position: absolute;
	text-align: center;
}

.left-container .puppy:before {
	/* 狗狗調色 */
	background: #f0e7dc;
	content: "";
	height: 100%;
	left: 0;
	opacity: 0.2;
	position: absolute;
	width: 100%;
	z-index: 1;
}

.left-container img {
	/* 狗狗大小縮放 */
	filter: sepia(100%);
	width: 70%;
}

.right-container {
	/* 註冊粉紅底的大小 */
	background: #FFF7E8;
	display: grid;
	grid-template-areas: "." ".";
	width: 500px;
}

.right-container h1:nth-of-type(1) {
	/* 沒動靜 */
	color: rgba(0, 0, 0, 0.8);
	font-size: 28px;
	font-weight: 600;
}

.right-container.set {
	/* display: flex;
  justify-content: space-between; */
	margin: 10px 0;
}

.right-container input {
	/* 整個輸入框的css */
	border: 1px solid rgba(0, 0, 0, 0.1);
	border-radius: 4px;
	height: 38px;
	line-height: 38px;
	padding-left: 5px;
}

.right-container input, .right-container label {
	color: rgba(0, 0, 0, 0.8);
}

.right-container header {
	/* 離狗狗有距離 */
	padding: 40px;
}

.right-container label, .right-container input, .right-container .pets-photo
	{
	width: 176px;
}

.right-container .pets-photo button i {
	color: rgba(0, 0, 0, 0.8);
	font-size: 16px;
}

.right-container footer {
	/* next下的白底 */
	align-items: center;
	background: #fff;
	display: grid;
	padding: 5px 40px;
}

.right-container footer button {
	/* next的設定 */
	border: 1px solid rgba(0, 0, 0, 0.2);
	height: 38px;
	line-height: 38px;
	width: 100px;
	border-radius: 19px;
	font-family: "Montserrat", sans-serif;
}

.right-container footer #back {
	background: #fff;
	transition: 0.2s all ease-in-out;
}

.right-container footer #back:hover {
	background: #171A2B;
	color: white;
}

.right-container footer #next {
	/* 按鈕按顏色的設計 */
	background: #dabfa0;
	border: 1px solid transparent;
	color: #fff;
}

.right-container footer #next:hover {
	background: #171A2B;
}

.pets-name label, .pets-memPassword label, .pets-Idenity label,
	.pets-memAddres label {
	display: block;
	align-items: center;
	margin-bottom: 5px;
}

.pets-birthday label, .pets-gender label, .pets-memEmail label,
	.pets-memPh label {
	display: block;
	margin-bottom: 5px;
}

.radio-container {
	/* 性別欄的設計控制 */
	background: #fff;
	border: 1px solid rgba(0, 0, 0, 0.1);
	border-radius: 4px;
	display: inline-block;
	padding: 5px;
}

.radio-container label {
	/* 性別欄未 */
	background: transparent;
	border: 1px solid transparent;
	border-radius: 2px;
	display: inline-block;
	height: 26px;
	line-height: 26px;
	margin: 0;
	padding: 0;
	text-align: center;
	transition: 0.2s all ease-in-out;
	width: 80px;
}

.radio-container input[type=radio] {
	/* 把按鈕隱藏 */
	display: none;
}

.radio-container input[type=radio]:checked+label {
	background: #dabfa0;
	border: 1px solid rgba(0, 0, 0, 0.1);
}

#next {
	margin-left: 75%;
}
</style>

</head>

<body>
	<div class='signup-container'>
		<div class='left-container'>
			<h1>嗨！毛爸媽</h1>
			<div class='puppy'>
				<img
					src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/38816/image-from-rawpixel-id-542207-jpeg.png'>
			</div>
		</div>
		<div class='right-container'>

			<header>
				<form id="form">
					<div class='set'>
						<div class='pets-name'>
							<label for='pets-name'>姓名</label> <input type="text" id="memName"
								name="memName" value="${param.memName}" required></input>
						</div>
					</div>

					<div class='set'>
						<div class='pets-memPassword'>
							<label for='pets-memPassword'>密碼</label> <input type="password"
								id="memPassword" name="memPassword" value="${param.memPassword}"
								required></input>
						</div>
					</div>

					<div class='set'>

						<div class='pets-Idenity'>
							<label for='pets-Idenity'>身分證</label> <input type="text"
								id="memIdenity" name="memIdenity" value="${param.memIdenity}"
								required pattern="[0-9A-Z]{8,10}"></input>
						</div>

						<div class='pets-memEmail'>
							<label for='pets-memEmail'>郵件信箱</label> <input type="email"
								id="memEmail" name="memEmail" value="${param.memEmail}" required
								onchange="check(this)"><span id="sp"></span></input>
						</div>
						<div class='pets-birthday'>
							<label for='pets-birthday'>生日</label> <input type="text"
								placeholder='YYYY-MM-DD' id="memBirthday" name="memBirthday"
								value="${param.memBirthday}" required></input>
						</div>
					</div>

					<div class='pets-memAddres'>
						<label for='pets-memAddres'>地址</label> <input type="text"
							id="memAddres" name="memAddres" value="${param.memAddres}"
							required></input>
					</div>

					<div class='pets-memPh'>
						<label for='pets-memPh'>電話</label> <input type="text" id="memPh"
							name="memPh" value="${param.memPh}" required></input>
					</div>

					<div class='set'>
						<div class='pets-gender'>
							<label for='pet-gender-female'>性別</label>

								<select size="1" name="memGender" required>
									<option value="">請選擇性別</option>
									<option value="女">女</option>
									<option value="男">男</option>
								</select>

						</div>
					</div>

					<div class='pets-spayed-neutered'>
			</header>
			<footer>

				<div class='set'>
					<input type="hidden" name="action" value="signup"> 
					<input type="submit" value="Signup">
				</div>
				</form>
		</div>
		</footer>
	</div>
	</div>

	<script type="text/javascript">
		function check(input) {
			console.log("xxxxxxxxxxxx")
			var v = input.value;
			console.log(v)
			var xhr = new XMLHttpRequest();//送出HTTP請求，建立物件
			xhr.onreadystatechange = function(ev) {//發送到伺服器後，去負責響應變化，抓取是否200，就是下面if的兩個方法
				console.log("yyyyyyyyyyyyy")
				if (xhr.status == 200 && xhr.readyState == 4){ //伺服器正常且可執行，響應是否就緒

					var s = xhr.responseText; //在請求被發送後，從伺服器返回。
					console.log(s)

					if (s == "0") {

						document.getElementById("sp").innerText = "可以註冊";
						document.getElementById("sp").style.color = "green";

					} else {

						document.getElementById("sp").innerText = "用戶已存在";
						document.getElementById("sp").style.color = "red";
					}

				}

			}
			xhr.open("get","${pageContext.request.contextPath}/check/check.do?memEmail="
							+ v, true);

			xhr.send();

		}

		$("#form").submit(function (e) {
			//form的id的submit
			  e.preventDefault();
			  if ($("#sp").text() === "可以註冊") {
// 		去對身分證欄位旁的顯示數字使否有顯示，才可以進行放行
			    $.ajax({
			      url: "/MaoUni/member/member.do",
			      //要執行的方式的註冊url
			      type: "post",
			      data: $(this).serialize(),
			      success: function (data) {
			        alert("hello 成功啦");
			        window.location.replace("/MaoUni/front-end/home/HomePage.jsp");
			        //轉跳到哪個網址，不然在跳出alert時會被卡在註冊頁面
			      }
			    })
			  } else {
			    alert("請更改信箱啦都說信箱重複了！");
			  }
			})

	</script>

</body>
</html>
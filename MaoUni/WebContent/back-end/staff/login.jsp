<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.backstage_staff.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="chrome">
	<link rel="stylesheet" type="text/css">
	<title>後臺登入</title>
	
	
<!-- 	換成靜態檔 by Esther -->
<!-- 	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
<!-- 	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script> -->
	
	
<style>
	body{
	width:100%;
	height:100%
	padding:0;
	margin:0;
	background-image:url('<%= request.getContextPath()%>/resources/images/kristine.jpg');
	background-size: cover;
	background-attachament: fixed;
	background-position: center;
}
	.login{
	position: fixed;
	margin:0 auto;
	margin-top:30px;
	left: 38%;
	top: 50%
	transform: translate(-50%,-50%);
	background: #c29e74;
	text-align: center;
	color: #FFFFB9	;
	font-family: "微軟正黑體", sans-serif;
	border-radius: 15px;
	width: 25%;
}

.log i{
	font-size:100px;
	margin:20px auto;
}

input [type="text"], input [type="password"]{
	font-size: 16px;
	font-family: "微軟正黑體", sans-serif;
	padding: 5px;
	border-radius: 4px;
	margin:  0 auto;
	display: block;
	width: 100%;
}
button{
	border: 0;
	margin: 20px auto;
	padding: 6px 12px;
	font-size: : 18px;
	font-family: "微軟正黑體", sans-serif;
	color: #3C3C3C	; 
}

@media (max-width: 450px) {
	.login{
		width: 70%;
	}
}
</style>

</head>	

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<body>


<div class="login">
	
	<form id='form' METHOD = "post" ACTION="<%=request.getContextPath()%>/StaffServlet" class="login">
	<h1>登入</h1>
	<i class="fa fa-user-circle-o" aria-hidden="true"></i>
	<h2>帳號</h2>
	<input type="text" name="username"  placeholder="請輸入帳號">
	<h2>密碼</h2>	
	<input type="password" name="password" placeholder="請輸入密碼">
	<br>
	
	<button type="submit" name="action" value="login" onclick="sendData()">登入</button>
	</form>
		
</div>
	
	
</div>
 <script>
 
 $.ajax({
	    url: "../Select.jsp",
	    cache: false,
	    async: false,
	    dataType: 'text',
	    type:'POST',
	    data:{
	        username : $("#username").val(),
	        password : $("#password").val()
	    },
	    error: function(xhr) {
	        alert('請正確輸入');
	    },
	    success: function(data) {
	        if(data == "F") {
	            alert("帳號密碼錯誤，請重新輸入");
	        } else {
	            alert("驗證成功，即將轉入");
	        }
	    }
	});
</script>

</body>
</html>
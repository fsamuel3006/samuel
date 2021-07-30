<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.service_items.model.*"%>


<jsp:useBean id="svcService" scope="request"
	class="com.service_items.model.SvcService" />
<c:if test="${list == null}">
	<%
		List<SvcVO> list = svcService.getAll();
			pageContext.setAttribute("list", list);
	%>
</c:if>


<html>
<head>
<meta charset="UTF-8">
<title>Service Item List</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
	
	
	<link rel="stylesheet" href="./css/index_mobile.css">
<script src="https://kit.fontawesome.com/0f58fd6ae7.js"
	crossorigin="anonymous"></script>
</head>
<style>


</style>
<body>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤: </font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>

	</c:if>

	<main>
	<div class="container">
	<div>
			<form method="post" action="/MaoUni/svc.do" name="form1">

				<div>
					服務項目: <input type="text" name="svcItem">
				</div>
				<div>
					毛孩類型: <select name="svcPet">
						<option value="狗">狗
						<option value="貓">貓
					</select>
				</div>

				<input type="hidden" name="action" value="insert">
				<button type="submit">新增</button>

			</form>
		</div>
		<div>
			<form method="post" action="/MaoUni/svc.do" name="form1">
				<div>
					毛孩類型: <select name="svcPet">
					    <option value="_">ALL
						<option value="狗">狗
						<option value="貓">貓
					</select>
				</div>

				<input type="hidden" name="action" value="findByPet"> 
				<button type="submit">查詢</button>
			</form>
		</div>
		<div>
			<h1>Service List</h1>
			<table>
				<tr>
					<th>編號</th>
					<th>服務項目</th>
					<th>類別</th>
				</tr>
				<c:forEach var="svcVO" items="${list}">
					<tr>
						<td>${svcVO.svcId}</td>
						<td>${svcVO.item}</td>
						<td>${svcVO.pet}</td>

					</tr>

				</c:forEach>


			</table>
		</div>
	</div>
		
	</main>



	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
		integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
		crossorigin="anonymous"></script>

	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyALjOdTPZMiMWQVlR01yYwLZWHAVuhk6_w&callback=initMap"
		async defer></script>

	<script src="/main.js"></script>
</body>
</html>
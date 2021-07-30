<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%@ page import="com.pet.model.*"%>

<%PetVO petVO = (PetVO) request.getAttribute("petVO");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員資料新增</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>

<body>

<table id="table-1">
	<tr><td>
		 <h3>毛孩新增</h3></td><td>
		 <h4><a href="Pet_select_page.jsp"><img src="" width="" height="" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>毛孩新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do">
<table>
	<tr>
		<td>毛主人ID:</td>
		<td><input type="text" name="petMemId" size="" value="${param.petMemId}"/></td>
	</tr>
	<tr>
		<td>毛孩名字:</td>
		<td><input type="text" name="petName" size="" value="${param.petName}"/></td>
	</tr>
	<tr>
		<td>毛孩類別:</td>
		<td><input type="text" name="petSort" size="" value="${param.petSort}"/></td>
	</tr>
	<tr>
		<td>毛孩品種:</td>
		<td><input type="text" name="petVarId" size="" value="${param.petVarId}"/></td>
	</tr>
		<tr>
		<td>性別:</td>
		<td><input type="text" name="petGender" size="1" value="${param.petGender}"/></td>
	</tr>
		<tr>
		<td>年齡:</td>
		<td><input type="text" name="petAge" size="" value="${param.petAge}"/></td>
	</tr>
		<tr>
		<td>狀態:</td>
		<td><input type="text" name="petSurvive" size="" value="${param.petSurvive}"/></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>
</body>

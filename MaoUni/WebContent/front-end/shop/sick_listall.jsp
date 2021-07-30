<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sick_class.model.*"%>


<%
	ClassService cSvc = new ClassService();
	List<ClassVO> list = cSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>所有資料</title>

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

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>所有資料 - listAllMember.jsp</h3>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>毛孩編號</th>
		<th>毛主人編號</th>
		<th>毛孩名字</th>
		<th>毛孩種類</th>
		<th>品種</th>
		<th>性別</th>
		<th>年齡</th>
		<th>狀態</th>

	</tr>

		<c:forEach var="petVO" items="${list}" >

			<tr>
				<td>${petVO.petId}</td>
				<td>${petVO.petMemId}</td>
				<td>${petVO.petName}</td>
				<td>${petVO.petSort}</td>
				<td>${petVO.petVarId}</td>
				<td>${petVO.petGender}</td>
				<td>${petVO.petAge}</td>
				<td>${petVO.petSurvive}</td>

				<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="memId"  value="${petVO.petId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="memId"  value="${petVO.petId}">
			     <input type="hidden" name="action" value="delete"></FORM>


			</tr>
				</c:forEach>

</table>
</body>
</html>
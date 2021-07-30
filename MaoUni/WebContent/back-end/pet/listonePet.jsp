<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pet.model.*"%>
<%
PetVO petVO = (PetVO) request.getAttribute("PetVO"); 
//MemberServlet.java(Concroller), 存入req的VO物件
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>毛孩單一查詢（現在只認主鍵）)</title>

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
	width: 600px;
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
		 <h3>ListOne</h3>
		 
		 <a href="Pet_select_page.jsp">回首頁</a>
	</td></tr>
</table>

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
	<tr>
				<td>${petVO.petId}</td>
				<td>${petVO.petMemId}</td>
				<td>${petVO.petName}</td>
				<td>${petVO.petSort}</td>
				<td>${petVO.petVarId}</td>
				<td>${petVO.petGender}</td>
				<td>${petVO.petAge}</td>
				<td>${petVO.petSurvive}</td>

	</tr>
</table>

</body>
</html>
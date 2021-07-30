<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
<title>單一查詢</title>

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
		 <h3>ListOneMember</h3>
		 
		 <a href="member_select_page.jsp">回首頁</a>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>員工姓名</th>
		<th>會員信箱</th>
		<th>密碼</th>
		<th>身分證</th>
		<th>性別</th>
		<th>電話</th>
		<th>地址</th>
		<th>生日</th>
		<th>身分</th>
		<th>美容預約次數</th>
		<th>會員狀態</th>
		<th>上次更新時間</th>
		<th>修改</th>
	</tr>
	<tr>
				<td>${memberVO.memId}</td>
				<td>${memberVO.memName}</td>
				<td>${memberVO.memEmail}</td>
				<td>${memberVO.memPassword}</td>
				<td>${memberVO.memIdenity}</td>
				<td>${memberVO.memGender}</td>
				<td>${memberVO.memPh}</td>
				<td>${memberVO.memAddres}</td>
				<td>${memberVO.memBirthday}</td>
				<td>${memberVO.memPosition}</td>
				<td>${memberVO.memReserve}</td>
				<td>${memberVO.memSurvive}</td>
				<td>${memberVO.memUpdate}</td>
	</tr>
<td>
				 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="memId"  value="${memberVO.memId}">
			     <input type="hidden" name="action"	value="update">
			     </FORM>
</td>
</table>

</body>
</html>
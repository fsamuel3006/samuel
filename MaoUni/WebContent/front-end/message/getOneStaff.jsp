<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.message.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
MessageVO MessageVO = (MessageVO) request.getAttribute("MessageVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>查詢客服</title>

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
		 <h3>查詢回覆</h3>
		 <h4>查詢回覆</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>內容</th>
		<th>是否讀取</th>
		<th>時間</th>
	</tr>
	<tr>
		<td><%=MessageVO.getMemId()%></td>
		<td><%=MessageVO.getContain()%></td>
		<td><%=MessageVO.getStatus()%></td>
		<td><%=MessageVO.getTime()%></td>
		
	</tr>
</table>

</body>
</html>
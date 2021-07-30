<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.message.model.*"%>
<%@ page import="java.util.*"%>

<%
MessageVO messageVO = (MessageVO) request.getAttribute("MessageVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>回覆訊息</title>

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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>員工資料修改</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/messageServlet" name="updateMessage">
<table>
	<tr>
		<td>訊息ID:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="" size="45" value="<%=messageVO.getId()%>" /></td>

	</tr>
	<tr>
		<td>會員編號</td>
		<td><input type="TEXT" name="" size="45" value="<%=messageVO.getMemId()%>" /></td>
	</tr>
	<tr>
		<td>訊息內容:</td>
		<td><input type="TEXT" name="" size="45"	value="<%=messageVO.getContain()%>" /></td>
	</tr>
	<tr>
		<td>發送日期</td>
		<td><input name="hiredate" id="" type="text" ></td>
	</tr>
	<tr>
		<td>是否已讀</td>
		<td><input type="TEXT" name="" size="45"	value="<%=messageVO.getStatus()%>" /></td>
	</tr>
<%-- 			</c:forEach> --%>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="updateMessage">
<input type="hidden" name="id" value="<%=messageVO.getId()%>">
<input type="submit" value="送出修改"></FORM>
</body>


</html>
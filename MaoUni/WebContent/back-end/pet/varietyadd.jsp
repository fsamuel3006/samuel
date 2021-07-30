<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%@ page import="com.variety.model.*"%>

<%
    VarietyService varSvc = new VarietyService();
    List<VarietyVO> list = varSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>

<form method="post" action="/CFA101G6/svc.do" name="form1">

<div>品種: <input type="text" name="svcItem"></div>
<div>毛孩類型: 
<select name="svcPet">
		<option value="狗">狗
		<option value="貓">貓
</select>
</div>

<input type="hidden" name="action" value="insert">
<input type="submit" value="新增">

</form>


</body>
</html>
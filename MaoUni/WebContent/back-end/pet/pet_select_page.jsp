<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pet.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>pet: Home</h3></td></tr>
</table>

<h3>資料查詢:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listonePet.jsp'>List</a> all .<br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do" >
        <b>輸入毛孩編號:</b>
         
        <input type="text" name="petId">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="GET_ONE_STMT">
    </FORM>
  </li>

  <jsp:useBean id="petSvc" scope="page" class="com.pet.model.PetService"/>
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do" >

         <input type="text" name="petName" >
         <input type="submit" value="送出">
        <input type="hidden" name="action" value="GET_ONE_STMT">
    </FORM>
  </li>
  
  <li>
  
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pet/pet.do">
       <select size="1" name="petName">
 
         <c:forEach var="petName" items="${petSvc.all}" > 
          <option value="${petVO.petName}">${petVO.petId}
         </c:forEach>   
 
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="GET_ONE_STMT">
     </FORM>
  </li>
  
 
</ul>
<h3>新增管理</h3>

<ul>
  <li><a href='Petadd.jsp'>Add</a> a new</li>
</ul>

<h3><font color=orange>全部</font></h3>

<ul>
  <li><a href='PetlistAll.jsp'>List</a> all </li>
</ul>

</body>
</html>
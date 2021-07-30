<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adopt_mechanism.model.*"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>機構查詢</title>

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
   <tr><td><h3>機構查詢</h3><h4></h4></td></tr>
</table>

<p>機構查詢</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='/front-end//Mechamism/SelectMechanism.jsp'></a> 機構查詢  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Mechanism_Servlet" >
        <b>輸入機構ID:</b>
        <input type="text" name="id">
        <input type="hidden" name="action" value="getOneMechanism">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="MechanismSvc" scope="session" class="com.adopt_mechanism.model.MechanismService" />
   
  <li>
     <FORM METHOD="post" ACTION="action" >
       <b>輸入機構名:</b>
       <select size="1" name="name">
         <c:forEach var="MechanismVO" items="${MechanismSvc.all}" > 
          <option value="${MechanismVO.name}">${MechanismVO.name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOneMechanism">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="action" > -->
<!--        <b>選擇機構編號:</b> -->
<!--        <select size="1" name="id"> -->
<%--          <c:forEach var="empVO" items="$MechanismSvc.all}" >  --%>
<%--           <option value="${MechanismVO.empno}">${MechanismVO.ename} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--      </FORM> -->
<!--   </li> -->
<!-- </ul> -->


<h3>員工管理</h3>

<ul>
  <li><a href='getAllMechanism.jsp'>回去主頁</a></li>
</ul>

</body>
</html>
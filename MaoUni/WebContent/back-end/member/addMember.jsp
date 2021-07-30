<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>

<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員後台資料修改</title>
<!-- 隱藏式菜單_連結 -->
 <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu2.css">

   <style>
    .touch_section{
 
 background-image: url("<%=request.getContextPath()%>/resources/images/memberadd.jpg");
  background-size: cover;
  height: 300px;
  position: relative;
  padding: 350px;
 
}

 .get_taital{
	text-align: center;
  position: absolute;
	color: #f7941d;
	font-size: 50pt;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
 }

   .email_box{
    margin-top: 150px;
    max-width:900px;
	    width: 50%;
	    float: left;
	    background-color: #ffffff;
	     border-radius: 20px;
	    height: auto;
      padding: 50px 20px;
      opacity: 0.80;
      text-align:center;

  }

   .form-group {
        margin-bottom: 2rem;
   }
   h4{
    margin-left: 200px;
    font-size: 22px;
   }

   .email-bt {
    max-width:900px;
     margin-left: 200px;
    border: 1px solid #000;
    color: #000000;
    width: 100%;
    height: 55px;
    font-size: 22px;  
    padding: 20px;
    margin-top: 20px;

   }

   .massage-bt {
    max-width:900px;
     margin-left: 200px;
    border: 1px solid #000;
    color: #000000;
    width: 100%;
    height: 200px;
    font-size: 22px;
    padding: 70px 20px 10px 20px;
  }

 .input{

  margin-left: 200px;

 }

button.main_bt {
  background-color: white; 
    color: black; 
    border: 2px solid #D2AD86;
   border-radius: 12px;
    width: 80px;
    height: 40px;
    font-size:15px;
   margin-left: 200px;
   border-radius: 25px;
    
}

button.main_bt:hover{
  background-color: #D2AD86; /* Green */
    color: white;

}
.erroeMasgs{
margin-left: 200px;
}
  
  
 </style>
</head>
<body >

   <!-- 以下 >>隱藏式菜單_內容 -->
     <header>
         <span class="toggle-button">
             <div class="menu-bar menu-bar-top"></div>
             <div class="menu-bar menu-bar-middle"></div>
             <div class="menu-bar menu-bar-bottom"></div>
         </span>
         <div class="menu-wrap">
             <div class="menu-sidebar" style="margin-top:46px;">
                 <ul class="menu">
                     <li><a href="https://tw.yahoo.com/">MaoUni_後台首頁</a></li>
                     <li><a href="<%=request.getContextPath()%>/back-end/Member/listAllMember.jsp">會員資料管理</a></li>
                     <li><a href="#">到府美容管理</a></li>
                     <li><a href="<%=request.getContextPath()%>/back-end/Obuy/NewObuyAll.jsp">商城管理</a></li>
                     <li><a href="#">討論區管理</a></li>
                     <li><a href="#">浪浪找家管理</a></li>
                     <li><a href="#">友善店家管理</a></li>
                     <li><a href="#">知識站管理</a></li>
                     <li><a href="#">公告管理</a></li>
                 </ul>
             </div>
         </div>
     </header> 
 <!-- 以上 >>隱藏式菜單_內容 --> 
 
 
   <div class="touch_section">
        <h1 class="get_taital"><strong>
             <span style="color: #ffffff;">新增會員</span>Add Member</strong></h1>
    </div>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
<table>
	<tr>
		<td>會員姓名:</td>
		<td><input type="text" name="memName" size="" value="${param.memName}"/></td>
	</tr>
	<tr>
		<td>信箱:</td>
		<td><input type="text" name="memEmail" size="" value="${param.memEmail}"/></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="text" name="memPassword" size="" value="${param.memPassword}"/></td>
	</tr>
	<tr>
		<td>身分證:</td>
		<td><input type="text" name="memIdenity" size="" value="${param.memIdenity}"/></td>
	</tr>
		<tr>
		<td>性別:</td>
			<select size="1" name="memGender" required>
				<option value="">請選擇性別</option>
				<option value="女">女</option>
				<option value="男">男</option>
				</select>
		
	</tr>
		<tr>
		<td>電話號碼:</td>
		<td><input type="text" name="memPh" size="" value="${param.memPh}"/></td>
	</tr>
		<tr>
		<td>通訊地址:</td>
		<td><input type="text" name="memAddres" size="" value="${param.memAddres}"/></td>
	</tr>
		<tr>
		<td>生日:</td>
		<td><input type="text" name="memBirthday" size="" value="${param.memBirthday}"/></td>
	</tr>
		<tr>
		<td>身分:</td>
		<td><input type="text" name="memPosition" size="1" value="${param.memPosition}"/></td>
	</tr>
		<tr>
		<td>美容預約次數:</td>
		<td><input type="text" name="memReserve" size="" value="${param.memReserve}"/></td>
	</tr>
		<tr>
		<td>會員狀態:</td>
		<td><input type="text" name="memSurvive" size="1" value="${param.memSurvive}"/></td>
	</tr>


</table>
<br>

<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>
   <script src="<%=request.getContextPath()%>/resources/bootstrap/jquery/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>

<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/bootstrap.min.js"></script>

    <script src="<%=request.getContextPath()%>/resources/js/hidden_menu3.js"></script> 
    <script src="<%=request.getContextPath()%>/resources/js/hidden_menu4.js"></script>


</body>
</html>
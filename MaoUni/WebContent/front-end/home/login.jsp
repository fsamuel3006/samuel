<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page session="false" %>



<%-- <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" /> --%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap" rel="stylesheet">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/resourced/css/login.css" rel="stylesheet" type="text/css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
	
<title>會員登入頁面</title>

<style>

*{

padding:0;
margin: 0;
box-sizing: border-box;
font-family:"poppins";

}
/* 
body{



} */

div.container{

    position: absolute;
    top:50%;
    left: 50%;
    transform: translate(-50%,-50%);
    display: flex;
    flex-direction: row;
    align-items: center;
    background-color: #FFF7E8;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 50px 60px -50px #563F2E;

}

div.container div.myform{

    width: 270px;
    border-radius: 3px;
    margin-right: 30px;
}

div.container div.myform h2{

color:#c29e74;
margin-bottom: 20px;
font-family: monospace;
font-size: 25px;

}

div.container div.myform input{

border:none;
outline: none;
border-radius: 3px;
background-color: #FFF7E8;
width: 100%;
border-bottom:2px solid #563F2E;
margin-bottom: 25px;
padding: 7px 0;
border-radius: 3px;
font-size: 14px;
transition-duration:1s;
}

div.container div.myform button{

color: white;
background-color: #c29e74;
border:none;
outline: none;
border-radius: 3px;
font-size: 14px;
padding: 7px 20px;
font-weight: 500px;
font-family: monospace;
transition-duration:1s;
}


.bt:hover{

    box-shadow: 0px 0px 1px -3px #cc651c;
    /* filter: brightness(2.0); */
    cursor: pointer; 

}

div.container div.myform button:hover{

    font-weight:bold;
    color:#563F2E;
    font-weight:bold;

}

.bt:active{
    opacity: 0.8;
    background-color:#FFF7E8;
}

div.container div.image img{

width: 300px;

}

.ex1 img{

width: 20px;
margin-left: 90%;
vertical-align: bottom;
visibility: hidden;

}

.ex2 img{

    width: 20px;
    margin-left: 90%;
    vertical-align: bottom;
    visibility: hidden;
    }



</style>


<div class="container">
    <div class="myform">
    <div class="redt"></div>
        <form name="myform1" ACTION="<%=request.getContextPath()%>/member/member.do" METHOD="post">
            <h2>LOGIN</h2>
            <input type="text" class="memEmail" placeholder="Email" name="memEmail" id="memEmail" required>
            <input type="password" placeholder="Password" name="memPassword" id="memPassword" required>
            <button type="submit" name="action" value="findByUseremailAndpassword">LOGIN</button>
            <button class="bt" type="button" onclick="location.href='<%=request.getContextPath()%>/front-end/member/Signup.jsp'">SIGNUP</button>
        </form>
        </div>
        <div class="image">
        <img src="${pageContext.request.contextPath}/resources/images/lo.jpg" width="300" height="399.8"/> 
</div>
</div>

</body>
</html>
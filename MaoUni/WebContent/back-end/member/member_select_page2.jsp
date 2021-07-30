<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>

@import url('https://fonts.googleapis.com/css2?family=Rajdhani:wght@300&display=swap');
/* google的字形 */

body,html {

height: 100%;
margin: 0%; 
padding: 0%; 
backgruond-color:black;
}

body{
    /* color: black; */
justify-content: center;
    align-items: center;

	background-image: url('../resources/images/kristine.jpg'); 
    background-size: cover;
    background-position:center ;
    font-family: 'rajdhani',sans-serif;
    

}



.glass{

position:fixed; /*因為會被其他div推著跑，所以要固定元素*/
height: 100%;
width: 100%;
background-image: url('/resources/images/kristine.jpg'); 
background-size: cover;
background-position: center;
 clip-path: inset(10em); /*修剪用 */
filter: blur(5px);/* 模糊 */
display: flex;
justify-content: center;
align-items: center;
border-radius: 3px;
margin-left: ;
}

.drop-shadow{

height: 100%;
width: 100%;
filter: drop-shadow(0 20px 10px rgba(0,0,0,0.5));
display: flex;
justify-content: center;
align-items: center;
z-index:1;

}

.drop-shadow::before{

display: block;
content: "";
position: absolute;
top: 10em;
height: calc(100% - 20em);
width: calc(100% - 20em);
border-left: 1px solid rgba(255, 255, 255, 0.1);
border-top:2px solid rgba(255, 255, 255, 0.2);
border-right:1px solid rgba(255, 255, 255, 0.3);
border-radius: 3px;
}

.drop-shadow > span{

    margin-top:-450px;
    position: absolute;
    align-items: center;
    z-index:5 ;
    color: #fff;
    font-size: 2em;
   letter-spacing: 0.5em;  /* 文字的間距 */
    padding-left: 0.4em;
}

.Qa{

    position:fixed;
    /* display: flex; */
    left: 230px;
    align-content: center;
    /* justify-content: flex-end; */
    z-index:5;
    border-radius: 3px;
}

.Qa button{
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
    letter-spacing: 0.3em;

}

.Qa #text{

    position: relative;
    outline: none;
    border:none;
    border-bottom: 2PX solid #fff;
    color: #fff;
    font-size: 17px;
    padding-top: 12px;
    padding-bottom: 5px;
    background:transparent;
}

.Qa label{

    position: absolute;
    z-index:5;
    top:33%;
    left:4%;
    transform:.5s;
    color: #fff;
    font-size: 18px;
    transition-duration:1s;
}

.Qa #text:focus ~ label,
.Qa #text:valid ~ label{

    top: -10px;
    left: 4%;
    color: wheat;
    font-size: 13px;

}

.Qa span{

    position: absolute;
    bottom: 1%;
    left: 0%;
    height: 2px;
    width: 0%;
    background: wheat;
    transition: .8s;
}

.Qa #text:focus ~ span,
.Qa #text:valid ~ span{
    width: 62.6%;
}


.addall{

    position:fixed; /*因為會被其他div推著跑，所以要固定元素*/
    z-index:5;
    align-items:center;
    /* margin-right: 1.5%; */
    right:320px;
}

.ad{

margin-bottom: 30px;
color: white;
background-color: #c29e74;
border:none;
outline: none;
border-radius: 3px;
font-size: 14px;
padding: 7px 20px;
/* font-weight: 500px; */
font-family: monospace;
transition-duration:1s;
letter-spacing: 0.3em;
}

.listall{

    color: white;
    background-color: #c29e74;
    border:none;
    outline: none;
    border-radius: 3px;
    font-size: 14px;
    padding: 7px 20px;
    /* font-weight: 500px; */
    font-family: monospace;
    transition-duration:1s;
    letter-spacing: 0.3em;

}

.headerDivider { 
    border-left:1px solid white; 
    height:250px; 
    position:absolute; 
    right:50%; 
    top:160px; 
    opacity:0.1;
} 

@media(max-width:980px){
    .glass{

    clip-path: inset(5em);
}
.drop-shadow::before{

    top: 5em;
    width: calc(100% - 10em);
}
}

.drop-shadow >  span{

    font-size: 4em;

}

@media(max-width:640px){
    .drop-shadow>span{
    font-size: 2em;
    }
} 
</style>
<title>會員</title>
</head>

<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>



<body>
    <div class="drop-shadow">
        <div class="glass">
        </div>
        <span>
            Member
        </span>

        	<div class="Qa">

 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" >
            <input type="text" name="memId" id="text" required>
            <label for="text">Enter Text Here</label>
            <button class="bt" type="submit">ENQUIRY</button>
            <span class="focus-border"></span>
             <input type="hidden" name="action" value="findByPrimaryKey">
</FORM>
</div>

 <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

        	<div class="addall">
            <a href='addMember.jsp'><button class="ad" type="submit">MemberAdd</button></a>
            <br>
            <a href='listAllMember.jsp'><button class="listall" type="submit">MemberAll</button></a>
        </div>


        <div class="headerDivider"></div> 

</div>

<!-- <img src="image/nic.png"/> -->

</body>
</html>
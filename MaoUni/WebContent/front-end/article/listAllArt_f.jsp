<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>

<%
     ArtService artSvc = new ArtService();
    List<ArtVO> list = artSvc.getAll();
    pageContext.setAttribute("list",list);
%> 

<jsp:useBean id="arttSvc" scope="page" class="com.article_type.model.ArttService" />   

<!DOCTYPE html>
<!--  知識站_前台_查詢全部  -->
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>知識站文章all - listAllArtf.jsp</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css"  type="text/css"/>

<style>
 body 
 { 
  background-color:#eee 
 } 
/* .wrap */
/* { */
/*   margin:50px auto 0 auto; */
/*   width:100%; */
/*   display:flex; */
/*   align-items:space-around; */
/*   max-width:800px; */
/*   margin-top: 180px; */
/* } */
.tile
{
  width:380px;
  height:380px;
  margin:10px;
  background-color:#99aeff;
  display:inline-block;
  background-size:cover;
  position:relative;
  cursor:pointer;
  transition: all 0.4s ease-out;
  box-shadow: 0px 35px 77px -17px rgba(0,0,0,0.44);
  overflow:hidden;
  color:white;
  font-family:'Roboto';
  margin-top: 20px;
  margin-left:18px;
  
}
.tile img
{
  height:100%;
  width:100%;
  position:absolute;
  top:0;
  left:0;
  z-index:0;
  transition: all 0.4s ease-out;
}
.tile .text
{
/*   z-index:99; */
  position:absolute;
  padding:30px;
  height:calc(100% - 60px);
}
.tile h1
{
 
  font-weight:300;
  margin:0;
  text-shadow: 2px 2px 10px rgba(0,0,0,0.3);
}
.tile h2
{
  font-weight:100;
  margin:20px 0 0 0;
  font-style:italic;
   transform: translateX(200px);
}
.tile p
{
  font-weight:300;
  margin:20px 0 0 0;
  line-height: 25px;
/*   opacity:0; */
  transform: translateX(-200px);
  transition-delay: 0.2s;
}
.animate-text
{
  opacity:0;
  transition: all 0.6s ease-in-out;
}
.tile:hover
{
/*   background-color:#99aeff; */
box-shadow: 0px 35px 77px -17px rgba(0,0,0,0.64);
  transform:scale(1.05);
}
.tile:hover img
{
  opacity: 0.2;
}
.tile:hover .animate-text
{
  transform:translateX(0);
  opacity:1;
}
.dots
{
  position:absolute;
  bottom:20px;
  right:30px;
  margin: 0 auto;
  width:30px;
  height:30px;
  color:currentColor;
  display:flex;
  flex-direction:column;
  align-items:center;
  justify-content:space-around;
  
}

.dots span
{
    width: 5px;
    height:5px;
    background-color: currentColor;
    border-radius: 50%;
    display:block;
  opacity:0;
  transition: transform 0.4s ease-out, opacity 0.5s ease;
  transform: translateY(30px);
 
}

.tile:hover span
{
  opacity:1;
  transform:translateY(0px);
}

.dots span:nth-child(1)
{
   transition-delay: 0.05s;
}
.dots span:nth-child(2)
{
   transition-delay: 0.1s;
}
.dots span:nth-child(3)
{
   transition-delay: 0.15s;
}


@media (max-width: 1200px) {
  .wrap {
   flex-direction: column;
    width:100px;
  }
}

.button{
    background-color: white; 
    color: black; 
    border: 2px solid #D2AD86;
    border-radius: 12px;
    width: 50px;
    transform: translateX(-5px);
}
.button:hover {
    background-color: #D2AD86;
    color: white;
} 
.page{
 margin-top: 180px;
 background-color: #D2AD86;
}
   

</style>

</head>
<body>

<!-- 以下為header ******************************************************************************************* -->

<header>
  <div class="icontrue">

       <a href="<%=request.getContextPath()%>/front-end/member/memberpage.jsp" class="iconbth"><img src="<%=request.getContextPath()%>/resources/images/dodge.png" border="0"></a>
       <a href="<%=request.getContextPath()%>/front-end/chatbox/chatbox.jsp" class="iconbth"><img src="<%=request.getContextPath()%>/resources/images/user.png" border="0"></a>
       <a href="#" class="iconbth"><img src="<%=request.getContextPath()%>/resources/images/shopping_cart.png" border="0"></a>
       <a href="#" class="iconbth"><img src="<%=request.getContextPath()%>/resources/images/paper_plane.png" border="0"></a>
       <a href="#" class="iconbth"><img src="<%=request.getContextPath()%>/resources/images/public_access.png" border="0"></a>
      <!--iconu一定要在top-flex之上，不然會被移動條給蓋爆777777-->
  </div>

<div class="Top-Flex">

<ul class="LOOP">
  <li class="two">
  <img src="<%=request.getContextPath()%>/resources/images/MaoUni2.png" 
        width="" alt=""></li>
</ul>

</div>

  <div class="shopbody">
 <nav style="margin-bottom:-15px;margin-left:15px;">
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/home/HomePage.jsp" style="font-size:15px;">首　頁</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/shop/shopping_home.jsp" style="font-size:15px;">有你來買</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/forumPost/forumPost_home.jsp" style="font-size:15px;">有你來講</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/member/grooming_home.jsp" style="font-size:15px;">到府美容</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/adopt/adopt_home.jsp" style="font-size:15px;">浪浪找家</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/article/listAllArt_f.jsp" style="font-size:15px;">知識站</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/announcemet/listAllAnnf.jsp" style="font-size:15px;">公告</a></p>
			 	</nav>
</header>

<!--以上為header ***********************************************************************-->



<div class="page">
   <%@ include file="page1.file" %> 
   <c:forEach var="artVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
 </div>
 
<!--    <div class="wrap"> -->
      <div class="tile"> 
          <img src="<%=request.getContextPath()%>/ArtImgServlet?id=${artVO.id}" />
            <div class="text">
       <!-- 文章名稱 -->
              <h1>${artVO.name}</h1>
              <!-- 文章分類 -->
                  <h2 class="animate-text"> ${arttSvc.getOneByID(artVO.artt_id).id} - ${arttSvc.getOneByID(artVO.artt_id).name}</h2>
                 <!-- 文章內容 -->       
                        <p class="animate-text">${artVO.contnt} </p>
        <div class="dots">
          <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/art.do" 
                  style="margin-bottom: 0px;">
            <input type="submit" value="Check" class="button">
            <input type="hidden" name="id"  value="${artVO.id}">
            <input type="hidden" name="action"	value="getOne_For_Check"></FORM>
        
           </div>
           </div>
         </div>
<!--     </div> -->

 </c:forEach>
   
   <br>
   <br>
   <br>
   
   <hr>
   
    <%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
   
   <%@ include file="page2.file" %>
   
    <script>
    //             此處是縮放導覽列的動態效果，這段請一定要抓到，不然它不會動
    //             滑鼠滾動(scroll)就開始觸發
            window.addEventListener("scroll",function(){
    
            const header = document.querySelector('header');
            header.classList.toggle('sticky',window.scrollY > 0);
            });
            
    </script>
    <script>
        const sessionId = "${sessionId}";
    </script>
    <script src="<%= request.getContextPath() %>/resources/js/shopping_cart.js"></script>
</body>
</html>
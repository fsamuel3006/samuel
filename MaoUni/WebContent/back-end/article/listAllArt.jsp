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
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>知識站文章all - listAllArt.jsp</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css"  type="text/css"/>

<style>
 h3 {
    text-align:center;
    
   }

  .table1 {
  margin-top: 180px;
  border-collapse: collapse;
  width: 100%;
  font-family:"Lucida Console", "Courier New", monospace;
  max-width: auto; 
  margin-left: auto;
  margin-right: auto;
  padding-top: 0;
  color: black;
}
 .table1 th {
  padding-top: 20px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #D2AD86;   /* 藍色  #008CBA; */
  text-align:center; 
  margin-left: auto; 
  margin-right: auto ;
  font-size:20px;
  color: white;
}

tr:hover {
  background-color:LightGray;
}

th, td {
  padding: 8px;
  text-align:center;
/*   text-align: left; */
  border-bottom: 1px solid #ddd;
}
 .button { 
    background-color: white;  
   color: black;  
    border: 2px solid #D2AD86; 
     border-radius: 12px; 
     width: 80px; 
     height: 50px; 
     font-size:15px; 
 } 

   .button:hover { 
     background-color: #D2AD86; /* Green */ 
     color: white; 
 } 

</style>


</head>
<body >

 <!-- 以下為header ******************************************************************************************* -->

<header>
  <div class="icontrue">

       <a href="#" class="iconbth"><img src="<%=request.getContextPath()%>/resources/images/dodge.png" border="0"></a>
       <a href="#" class="iconbth"><img src="<%=request.getContextPath()%>/resources/images/user.png" border="0"></a>
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
      <nav>
          <!-- <a href="https://tw.yahoo.com/">首　頁</a> -->
          <a href="#">有你來買</a>
          <a href="#">有你來講</a>
          <a href="#">有你來玩</a>
          <a href="#">到府美容</a>
          <a href="#">浪浪找家</a>
          <a href="#">友善店家</a>
          <a href="<%=request.getContextPath()%>/back-end/article/select_page_art.jsp">知識站</a>
          <a href="<%=request.getContextPath()%>/back-end/announcemet/select_page.jsp">公告管理</a>
      </div>
      <div class="Shopping-ul"></div>
  </nav>
</header>

<!--以上為header ***********************************************************************-->

  <table class="table1">
  
      <tr>
        <th>文章圖片</th>
        <th>文章編號</th>
        <th>文章分類</th>
        <th>文章名稱</th>
        <th>文章內容</th>
        <th>發布時間</th>
        <th>修改</th>
        <th>刪除</th>
     </tr>
    <%@ include file="page1.file" %> 
   <c:forEach var="artVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
    
     <tr>
<!-- 文章圖片 -->
       <td><img src="<%=request.getContextPath()%>/ArtImgServlet?id=${artVO.id}" 
                     width="150" height="130"></td>
<!-- 文章編號 -->                     
       <td>${artVO.id}</td>
       
 <!-- 文章分類 -->     
        <td>                   
	      ${arttSvc.getOneByID(artVO.artt_id).id} - ${arttSvc.getOneByID(artVO.artt_id).name}                               
	   </td>
	   
<!-- 文章名稱 -->   
	    <td>${artVO.name}</td>
	    
<!-- 文章內容 -->    
        <td>${artVO.contnt}</td>
        
<!-- 發布時間 -->   
        <td>${artVO.update}</td>
        
<!--      </tr> -->
     
     <td>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/art.do" 
                  style="margin-bottom: 0px;">
            <input type="submit" value="修改" class="button">
            <input type="hidden" name="id"  value="${artVO.id}">
            <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
       </td>

       <td>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/art.do" 
                  style="margin-bottom: 0px;">
          <input type="submit" value="刪除" class="button">
          <input type="hidden" name="id"  value="${artVO.id}">
          <input type="hidden" name="action" value="delete"></FORM>
      </td>
  </tr>
     </c:forEach> 
  </table>
  
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

<br>

  <%@ include file="page2.file" %>
  
   <script>
    //             此處是縮放導覽列的動態效果，這段請一定要抓到，不然它不會動
    //             滑鼠滾動(scroll)就開始觸發
            window.addEventListener("scroll",function(){
    
            const header = document.querySelector('header');
            header.classList.toggle('sticky',window.scrollY > 0);
            });
            
    </script>

</body>
</html>
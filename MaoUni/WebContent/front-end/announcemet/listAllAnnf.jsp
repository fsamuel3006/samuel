<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcemet.model.*"%>

<!-- 查詢全部_公告 -->

<%
    AnnService annSvc = new AnnService();
    List<AnnVO> list = annSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<title>前台_查詢所有資料</title>


<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css" type="text/css"/>

<style>
 span{
    font-family:monospace;
  }

  h3 {
    text-align:center;
    
   }
.table1 {
  margin-top: 180px;
  border-collapse: collapse;
  width: 100%;
  font-family: Microsoft JhengHei;
      /* padding-left: 20px;
          padding-right: 20px;
         padding-bottom: 30px; */
         max-width:1050px;
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
    width: 70px;
    height: 40px;
    font-size:15px;
}

  .button:hover {
    background-color: #D2AD86; /* Green */
    color: white;
}

.content_image{
    display: block;
    margin-left: auto;
    margin-right: auto
}

#pages1{
  /*往右邊移動 */
margin-left: 60px; 

}

#pages {
  padding-top: 20px;
  margin: 0 auto;
  /* 置中 */
  text-align: center;
}

#erroeMasgs{
  
  margin-left: 60px;
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
  <li class="two"><img src="<%=request.getContextPath()%>/resources/images/MaoUni2.png" width="" alt=""></li>
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
      </div>
      <div class="Shopping-ul"></div>
  </nav>
</header>

 </div>  
  </div> 

  <!--以上為頭部的html設定位子-->

<!--以上為header ***********************************************************************-->

<!-- 提示錯誤訊息  -->
 <div id="erroeMasgs"> 
       <c:if test="${not empty errorMsgs}">
	       <font style="color:red">請修正以下錯誤:</font>
	     <ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	     </ul>
       </c:if>
 </div>
 
<!--內容表格  -->
 <table  class="table1">
    <tr>
        <th>照片</th>
		<th>公告編號</th>
		<th>公告內容</th>
		<th>上次修改日期</th>
		<th></th>
	</tr>
 

<div  id="top_pages1">
   <%@ include file="page1.file" %> 
 </div>
   
    <c:forEach var="annVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		  <tr>
		    <td>
		    <div class="content_image">
		      <img src="<%=request.getContextPath()%>/AnnImgServlet?id=${annVO.id}" 
			         width="180" height="150">
			  </div>       
		    </td>
			<td>${annVO.id}</td> 
			<td>${annVO.content}</td>
			<td>${annVO.update}</td>

          <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcemet/ann.do"
			             style="margin-bottom: 0px;">
			     <input type="submit" class="button" value="查看">
			     <input type="hidden" name="id"  value="${annVO.id}">
			     <input type="hidden" name="action"	value="getOne_For_front"></FORM>
			</td>
		</tr>
	</c:forEach>
  </table>   
   <div  id="pages">
        <%@ include file="page2.file" %> 
    </div>          
</span> 
    <script>
        const sessionId = "${sessionId}";
    </script>
    <script src="<%= request.getContextPath() %>/resources/js/shopping_cart.js"></script>
</body>
</html>
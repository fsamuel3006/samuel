<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.article_type.model.*"%>

<%ArtVO artVO = (ArtVO) request.getAttribute("artVO");%>

<%
    ArttService arttSvc = new ArttService();
    ArttVO arttVO = arttSvc.getOneByID(artVO.getArtt_id());
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>後台_單一查詢資料</title>

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
  max-width:auto;
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
<body>

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
	</tr>
	<tr>
	    <td>
	       <img src="<%=request.getContextPath()%>/ArtImgServlet?id=${artVO.getId()}" 
			         width="180" height="150"></td>
        <td><%=artVO.getId()%></td>
        <td><%=artVO.getArtt_id()%>【<%=arttVO.getName()%>】</td>
        <td><%=artVO.getName()%></td>
        <td><%=artVO.getContnt()%></td>
        <td><%=artVO.getUpdate()%></td>
	</tr>
  </table>
  
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
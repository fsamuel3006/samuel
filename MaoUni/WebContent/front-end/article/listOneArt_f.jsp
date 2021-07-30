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
<title>前台_知識站_單一查詢資料</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css"  type="text/css"/>

<style>
  body {
	font-family: 'Roboto Slab', sans-serif;
}

/* Basic */

input, select, textarea {
	outline: none;
}

a:hover {
	color: #FC3;
	text-decoration: none;
}

p {
	font-size: 14px;
	line-height: 25px;
	color: #7a7a7a;
	letter-spacing: 0.2px;
}

h4 {
	font-size: 19px;
	font-weight: 700;
	color: #31323a;
	letter-spacing: 0.25px;
}

img {
	width: 100%;
	overflow: hidden;

}


.i5 {
    max-width:900px;
    margin-left: 90px;
    margin-right: 90px;
    
	margin-top: 170px;
}

.i5 .container-fluid {
	padding-left: 0px;
	padding-right: 0px;
}

.i5 h4 {
	margin: 30px 0px 20px 0px;
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
          <a href="<%=request.getContextPath()%>/front-end/article/listAllArt_f.jsp">知識站</a>
          <a href="<%=request.getContextPath()%>/front-end/announcemet/listAllAnnf.jsp">公告</a>
      </div>
      <div class="Shopping-ul"></div>
   </nav>
</header>

<!--以上為header ***********************************************************************-->

   <section class="i5">
    <div class="container-fluid">
      <img src="<%=request.getContextPath()%>/ArtImgServlet?id=${artVO.getId()}"
             height="450"  alt="">
        <h4>文章名稱</h4>
            <p><%=artVO.getId()%> :<%=artVO.getName()%></p>
        <h4>文章分類</h4>
           <p><%=artVO.getArtt_id()%>【<%=arttVO.getName()%>】</p>
        <h4>文章內容</h4>
            <p><%=artVO.getContnt()%></p>
        <h4>發布時間</h4>   
          <p><%=artVO.getUpdate()%></p>
     </div>
  </section>


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
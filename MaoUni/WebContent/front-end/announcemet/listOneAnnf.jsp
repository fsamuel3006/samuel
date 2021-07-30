<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.announcemet.model.*"%>

<%
  AnnVO annVO = (AnnVO) request.getAttribute("annVO"); 
%>

<!DOCTYPE html>
<html>
<head>
<title>前台_公告查詢單筆資料</title>

<!--  連結外部css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/AnnSelect_pages.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css"  type="text/css"/>

<style>
 .i6{
    max-width:1150px;
    margin-left: 90px;
    margin-right: 90px;
	margin-top: 30px;
  }
.write{
  color: #31323a;
}

.button {
  font-size: 14px;
	font-weight: 700;
	text-transform: uppercase;
  background-color: transparent;
	display: inline-block;
	padding: 8px 20px;
	color: #008CBA;
	border: 2px solid #008CBA;
	transition: all 0.5s;
}

  .button:hover {
    color: white;
	background-color: #008CBA;
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
  <li class="two"><img src="<%=request.getContextPath()%>/resources/images/MaoUni2.png" width="" alt=""></li>
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

<!-- body 內容開始 -->

<section class="i5">
  <div class="container-fluid">
    <img src="<%=request.getContextPath()%>/AnnImgServlet?id=${annVO.getId()}"
         height="450" >
          <h4> 公告Id :
            <p>${annVO.getId()}</p></h4>
   </div>
</section>

<section class="i6">
<div class="container-fluid">
        <h4> 公告內容 :
          <p>${annVO.getContent()}</p>
        </h4>
 </div>
</section>

<section class="i6">
  <div class="container-fluid">
          <h4> 發布日期 :
            <p>${annVO.getUpdate()}</p>
          </h4>
   </div>
  </section>

<!--    Javascript files  -->
    <script>
// 此處是縮放導覽列的動態效果，這段請一定要抓到，不然它不會動
//滑鼠滾動(scroll)就開始觸發
      window.addEventListener("scroll",function(){

      const header = document.querySelector('header');
      header.classList.toggle('sticky',window.scrollY > 0);
      });
      
</script>

</body>
</html>
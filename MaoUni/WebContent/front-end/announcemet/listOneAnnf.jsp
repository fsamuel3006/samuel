<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.announcemet.model.*"%>

<%
  AnnVO annVO = (AnnVO) request.getAttribute("annVO"); 
%>

<!DOCTYPE html>
<html>
<head>
<title>�e�x_���i�d�߳浧���</title>

<!--  �s���~��css -->
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


<!-- �H�U��header ******************************************************************************************* -->

<header>
  <div class="icontrue">

       <a href="#" class="iconbth"><img src="<%=request.getContextPath()%>/resources/images/dodge.png" border="0"></a>
       <a href="#" class="iconbth"><img src="<%=request.getContextPath()%>/resources/images/user.png" border="0"></a>
       <a href="#" class="iconbth"><img src="<%=request.getContextPath()%>/resources/images/shopping_cart.png" border="0"></a>
       <a href="#" class="iconbth"><img src="<%=request.getContextPath()%>/resources/images/paper_plane.png" border="0"></a>
       <a href="#" class="iconbth"><img src="<%=request.getContextPath()%>/resources/images/public_access.png" border="0"></a>
      <!--iconu�@�w�n�btop-flex���W�A���M�|�Q���ʱ����\�z777777-->
  </div>

<div class="Top-Flex">

<ul class="LOOP">
  <li class="two"><img src="<%=request.getContextPath()%>/resources/images/MaoUni2.png" width="" alt=""></li>
</ul>


</div>

  <div class="shopbody">
      <nav>
          <!-- <a href="https://tw.yahoo.com/">���@��</a> -->
          <a href="#">���A�ӶR</a>
          <a href="#">���A����</a>
          <a href="#">���A�Ӫ�</a>
          <a href="#">�쩲���e</a>
          <a href="#">������a</a>
          <a href="#">�͵����a</a>
          <a href="<%=request.getContextPath()%>/front-end/article/listAllArt_f.jsp">���ѯ�</a>
          <a href="<%=request.getContextPath()%>/front-end/announcemet/listAllAnnf.jsp">���i</a>
      </div>
      <div class="Shopping-ul"></div>
  </nav>
</header>

<!--�H�W��header ***********************************************************************-->

<!-- body ���e�}�l -->

<section class="i5">
  <div class="container-fluid">
    <img src="<%=request.getContextPath()%>/AnnImgServlet?id=${annVO.getId()}"
         height="450" >
          <h4> ���iId :
            <p>${annVO.getId()}</p></h4>
   </div>
</section>

<section class="i6">
<div class="container-fluid">
        <h4> ���i���e :
          <p>${annVO.getContent()}</p>
        </h4>
 </div>
</section>

<section class="i6">
  <div class="container-fluid">
          <h4> �o����� :
            <p>${annVO.getUpdate()}</p>
          </h4>
   </div>
  </section>

<!--    Javascript files  -->
    <script>
// ���B�O�Y������C���ʺA�ĪG�A�o�q�Ф@�w�n���A���M�����|��
//�ƹ��u��(scroll)�N�}�lĲ�o
      window.addEventListener("scroll",function(){

      const header = document.querySelector('header');
      header.classList.toggle('sticky',window.scrollY > 0);
      });
      
</script>

</body>
</html>
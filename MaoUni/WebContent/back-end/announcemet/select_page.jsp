<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>後台_公告首頁</title>
 
<!--  連結外部css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/AnnSelect_pages.css" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css"  type="text/css"/>

<style>

hr{
    size: 20px;
    max-width:1150px;
    margin-left: 90px;
    margin-right: 90px;
	/* margin-top: 170px; */
    *margin: 0;
    border: 0;
   color: #DCDCDC;
    background-color: #DCDCDC; 
    height: 1px;
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
	border-radius: 12px;
}

  .button:hover {
    color: white;
	background-color: #008CBA;
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
  <li class="two"><img src="<%=request.getContextPath()%>/resources/images/MaoUni2.png"
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

<!--以上為頭部的html設定位子-->

<!--以上為header ***********************************************************************-->

 


  <!-- body 內容開始 -->
   <section class="i5">
    <div class="container-fluid">
      <img src="<%=request.getContextPath()%>/resources/images/i5.jpg" alt="">
            <h4>Announcemet 公告首頁</h4>
               <div class="primary-button">
                   <a href='<%=request.getContextPath()%>/back-end/announcemet/addAnn.jsp'>新增公告</a>
                </div>
     </div>
     

<!--      左邊照片 -->
  </section>
  <br>
  <hr>
     <section class="left-image">
            <img src="<%=request.getContextPath()%>/resources/images/dog1.jpg" alt="">
        <div class="right">
              <h4> Search 公告資料查詢</h4>
              <div class="primary-button">
                <a href='<%=request.getContextPath()%>/back-end/announcemet/listAllAnn.jsp'>查詢所有資料</a>
              </div>
     <br>
 <br>

              <!-- <div class="primary-button"> -->
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcemet/ann.do" >

                  <div class="write">
                  <b>輸入公告編號 (如1):</b>
                  </div>
                  <br>
                  <input type="text" name="id" style="width:150px;">
                  <input type="hidden" name="action" value="getOne_For_Display">
                  <input type="submit" class="button" value="送出" >
                </FORM>
            
                <jsp:useBean id="annSvc" scope="page" class="com.announcemet.model.AnnService" />

            <br>

                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcemet/ann.do" >
                  <div class="write">
                  <h3>選擇公告編號:</h3>
                </div>
                  <br>
                  <select size="1" style="width:150px;" name="id">
                    <c:forEach var="annVO" items="${annSvc.all}" > 
                     <option value="${annVO.id}">${annVO.id}
                    </c:forEach>   
                  </select>
                  <input type="hidden" name="action" value="getOne_For_Display">
                  <input type="submit" class="button" value="送出">
             <br>
             <br>

                  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcemet/ann.do" >
                    <div class="write">
                    <b>選擇公告內容:</b>  
                  </div>
                    <br>
                    <select size="1" style="width:150px;" name="id">
                      <c:forEach var="annVO" items="${annSvc.all}" > 
                       <option value="${annVO.id}">${annVO.content}
                      </c:forEach>   
                    </select>
                    <input type="hidden" name="action" value="getOne_For_Display">
                    <input type="submit"  class="button" value="送出">
                 </FORM>

  
    </section>
    
    <!-- <%-- 錯誤表列 --%> -->

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
    
<!--     Javascript files -->
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
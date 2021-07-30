<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>後台_文章首頁</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css"  type="text/css"/>

<style>
 h4 {
	font-size: 25px;
    margin-top: 20px;
	font-weight: 700;
	color: #31323a;
	letter-spacing: 0.25px;
  font-family: "Lucida Console", "Courier New", monospace;
}
 p{
  font-size: 19px;
	font-weight: 700;
	color: #31323a;
	letter-spacing: 0.25px;

 }
 hr{
    size: 20px;
    max-width:1200px;
    margin-left: 10px;
    margin-right: 20px;
	 margin-top: 20px;
    *margin: 0;
    border: 0;
   color: #DCDCDC;
    background-color: #DCDCDC;
    height: 1px;
  }

 .centered{

    position: relative;
    max-width:1150px;
    margin-left: 90px;
    margin-right: 90px;
  	margin-top: 250px;
 }

 .centered img { 
    
    width: 100%;
    overflow: hidden;
    height: 400px;
}
  .center {
  font-weight: bold;     /* 字體變粗 */
  position: absolute;

  top: -5%;
  left: 20%;
  transform: translate(-50%, -50%);
  font-size: 50px;
  font-family: "Lucida Console", "Courier New", monospace;
  /* background-color:MediumSeaGreen; */
  color: MediumSeaGreen;
}

.write{
  color: #31323a;
}

.right{
    position: relative;
	width:50%;
    left: 50%;   /*距離左方邊界50%地方置入*/
    margin-top: -33.5%;
  }

.primary-button a,button {
  border-radius: 25px;
	font-size: 14px;
	font-weight: 700;
	text-transform: uppercase;
  background-color: transparent;
	display: inline-block;
	padding: 8px 20px;
	color: #008CBA;
	border: 2px solid #008CBA;
	transition: all 0.5s;
  margin-top: 20px;

}

.primary-button a:hover,
button:hover {
	color: white;
	background-color: #008CBA;
}

.button {
  border-radius: 25px;
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

<!--以上為header ***********************************************************************-->

  <div class="centered">
  <div class="center">後台_知識站首頁</div>
     <img src="<%=request.getContextPath()%>/resources/images/dog9.jpg" alt="Cinque Terre" >

     <h4> SEARCH 後台_查詢文章所有資料</h4>
     <div class="primary-button">
         <a href='<%=request.getContextPath()%>/back-end/article/listAllArt.jsp'>後台查詢文章所有資料</a>
     </div>

 <br>
 
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/art.do" >
        <div class="write">
       <b>輸入文章Id (如1):</b>
        </div>
        <input type="text" name="id">
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" class="button" value="送出">
       </FORM>

 <br>
 
   <jsp:useBean id="artSvc" scope="page" class="com.article.model.ArtService" />
      
       <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/art.do" >
        <div class="write">
             <b>選擇文章Id:</b>
         </div>
         <select size="1" name="id" style="width:165px;">
            <c:forEach var="artVO" items="${artSvc.all}" > 
               <option value="${artVO.id}">${artVO.id}
            </c:forEach>   
         </select>
     <input type="hidden" name="action" value="getOne_For_Display">
     <input type="submit"  class="button" value="送出">
  </FORM>

<br>
  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/art.do" >
    <div class="write">
    <b>選擇文章名稱:</b>
    </div>
     <select size="1" name="id" style="width:165px;">
        <c:forEach var="artVO" items="${artSvc.all}" > 
           <option value="${artVO.id}">${artVO.name}
        </c:forEach>   
      </select>
    <input type="hidden" name="action" value="getOne_For_Display">
    <input type="submit"  class="button" value="送出">
 </FORM>
 
 <hr>
 <br>
 
 <%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
 
 <div class="right">
    <h4> Add 新增文章</h4>

   <div class="primary-button">
      <a href='<%=request.getContextPath()%>/back-end/article/addArt.jsp'>新增文章
            </a>
    </div>   
 </div>
 <br>
</div>
 
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
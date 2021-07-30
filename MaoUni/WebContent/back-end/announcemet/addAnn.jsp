<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.announcemet.model.*"%>

<!-- 新增_公告 -->

<%
  AnnVO annVO = (AnnVO) request.getAttribute("annVO");
 
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增公告資料 - addAnn.jsp</title>

<!-- 隱藏式菜單_連結 -->
 <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu2.css">

<style>
 .touch_section{
 
 background-image: url("<%=request.getContextPath()%>/resources/images/dog3.jpg");
  background-size: cover;
  height: 300px;
  position: relative;
  padding: 350px;
 
}


 .get_taital{
	text-align: center;
  position: absolute;
	color: #f7941d;
	font-size: 50pt;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
 }

   .email_box{
    margin-top: 150px;
    max-width:900px;
	    width: 50%;
	    float: left;
	    background-color: #ffffff;
	     border-radius: 20px;
	    height: auto;
      padding: 50px 20px;
      opacity: 0.80;
      text-align:center;

  }

   .form-group {
        margin-bottom: 2rem;
   }

   .email-bt {
    max-width:900px;
     margin-left: 200px;
    border: 1px solid #000;
    color: #000000;
    width: 100%;
    height: 55px;
    font-size: 22px;  
    padding: 20px;
    margin-top: 20px;

   }

   .massage-bt {
    max-width:900px;
     margin-left: 200px;
    border: 1px solid #000;
    color: #000000;
    width: 100%;
    height: 200px;
    font-size: 22px;
    padding: 70px 20px 10px 20px;
  }

 .input{

  margin-left: 200px;

 }

  
button.main_bt {
  background-color: white; 
    color: black; 
    border: 2px solid #D2AD86;
   border-radius: 12px;
    width: 80px;
    height: 40px;
    font-size:15px;
   margin-left: 200px;
    
}

button.main_bt:hover{
  background-color: #D2AD86; /* Green */
    color: white;

}
</style>

</head>
<body >
<!-- 以下 >>隱藏式菜單_內容 -->
  <header>
    <span class="toggle-button">
        <div class="menu-bar menu-bar-top"></div>
        <div class="menu-bar menu-bar-middle"></div>
        <div class="menu-bar menu-bar-bottom"></div>
    </span>
    <div class="menu-wrap">
        <div class="menu-sidebar">
            <ul class="menu">
                <li><a href="https://tw.yahoo.com/">Home</a></li>
                <li><a href="#">美容照護</a></li>
                <li><a href="#">商城</a></li>
                <li><a href="#">討論區</a></li>
                <li><a href="#">會員資料</a></li>
                <li><a href="<%=request.getContextPath()%>/back-end/article/select_page_art.jsp">知識站管理</a></li>
                <li><a href="<%=request.getContextPath()%>/back-end/announcemet/select_page.jsp">公告管理</a></li>
            </ul>
        </div>
    </div>
</header>
   <!-- 以上 >>隱藏式菜單_內容 --> 
  
  <div class="touch_section">     
       <h1 class="get_taital"><strong>
          <span style="color: #ffffff;">新增公告</span> Add</strong></h1>
  </div>


<%-- 錯誤訊息 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/announcemet/ann.do" enctype='multipart/form-data' name="form1">

 <div class="form-group">
    <input type="text" name="update" id="f_date1" class="email-bt" placeholder="輸入日期EX:2021-01-01" >
  </div>

  <div class="form-group">
    <textarea class="massage-bt" placeholder=" 公告內容 Massage" rows="5" id="comment"  type="TEXT" name="content" 
    value="<%= (annVO==null)? "":annVO.getContent()%>"></textarea>
  </div>
   
  <div class="input"> 
    <p> 請上傳圖片 
        <input type="file" id="pic" name="pic" > 
    </p>
	</div>
<br>

<input type="hidden" name="action" value="insert">
<button type="submit" class="main_bt">送出新增</button>

<!-- 隱藏式菜單_連結 -->
<script src="<%=request.getContextPath()%>/resources/js/hidden_menu3.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/hidden_menu4.js"></script>

</FORM>
</body>


<!-- datetimepicker 之相關設定--------------------------------------------------------------------------------------- -->

<% 
  java.sql.Date update= null;
  try {
	   update = annVO.getUpdate();
   } catch (Exception e) {
	   update = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/jquery.datetimepicker.css" />
<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.datetimepicker.full.js"></script>

  <script>
          $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=update%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
   </script>
   
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
  
 </style>
 
</html>
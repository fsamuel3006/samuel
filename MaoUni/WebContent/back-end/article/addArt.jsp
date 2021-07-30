<%@ page language="java" contentType="text/html; charset=UTF-8"
	     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.article.model.*"%>

<%
  ArtVO artVO = (ArtVO) request.getAttribute("artVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>後台文章資料新增</title>
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
   border-radius: 25px;
    
}

button.main_bt:hover{
  background-color: #D2AD86; /* Green */
    color: white;

}
.erroeMasgs{
margin-left: 200px;
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
        <div class="menu-sidebar" style="margin-top:46px;">
            <ul class="menu">
                <li><a href="https://tw.yahoo.com/">Home</a></li>
                <li><a href="#">美容照護</a></li>
                <li><a href="#">商城</a></li>
                <li><a href="#">討論區</a></li>
                <li><a href="#">會員資料</a></li>
                <li><a href="<%=request.getContextPath()%>/back-end/article/select_page_art.jsp">知識文章管理</a></li>
                <li><a href="<%=request.getContextPath()%>/back-end/announcemet/select_page.jsp">公告管理</a></li>
            </ul>
        </div>
    </div>
</header>
   <!-- 以上 >>隱藏式菜單_內容 --> 
   
    <div class="touch_section">
		  <h1 class="get_taital"><strong>
                 <span style="color: #ffffff;">新增文章</span> Add Article</strong></h1>
    </div>
    	
   <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/article/art.do" enctype='multipart/form-data' 
             name="form1">
             
     <div class="form-group">
         <input type="text" class="email-bt" placeholder="新增文章名稱"  name="name"  
                 value="<%=(artVO==null)? "" : artVO.getName()%>"/>
     </div>    
        
     <div class="form-group">
          <input name="update" id="f_date1" type="text" class="email-bt"
                    placeholder="輸入日期EX:2021-01-01">
     </div>  
     
     <jsp:useBean id="arttSvc" scope="page" class="com.article_type.model.ArttService" /> 
     
     <div class="form-group">
        <select size="1" name="artt_id" class="email-bt" style="height:70px;">
            <c:forEach var="arttVO" items="${arttSvc.all}">
               <option value="${arttVO.id}" ${(artVO.artt_id==arttVO.id)? 'selected':'' } >${arttVO.name}
           </c:forEach>
         </select>
     </div>  
     
     <div class="form-group">
        <textarea class="massage-bt" placeholder=" 新增文章內容 Massage" 
            type="TEXT" name="contnt" value="<%= (artVO==null)? "" : artVO.getContnt()%>"></textarea>
     </div> 
          
          
     <div class="input"> 
        <p> 請上傳圖片<font color=red><b>*</b></font>
             <input type="file" id="pic" name="pic" ></p>
     </div>
 <br> 
           
                    
      <div class="send_btn">
        <input type="hidden" name="action" value="insert">
        <button type="submit" class="main_bt">送出新增</button>
      </div> 
      
      <hr>
      
          <!--   錯誤訊息  -->
       <div class="erroeMasgs">
     <c:if test="${not empty errorMsgs}">
	    <font style="color:red">請修正以下錯誤:</font>
	   <ul>
		 <c:forEach var="message" items="${errorMsgs}">
			  <li style="color:red">${message}</li>
		 </c:forEach>
	  </ul>
    </c:if>  
    </div> 
      
<!-- 隱藏式菜單_連結 -->
<script src="<%=request.getContextPath()%>/resources/js/hidden_menu3.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/hidden_menu4.js"></script> 


   </FORM>
   
</body>

<!--     datetimepicker 之相關設定   -->

<% 
  java.sql.Date update= null;
  try {
	   update = artVO.getUpdate();
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
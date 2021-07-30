<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>



<html style="height: auto;">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>hidden_menu</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu.css"> 
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hidden_menu2.css"> 




<style>
	.btn{
		background: #e8c497;
      	color: #fff;
	}
	.btn:hover {
      	background: #ffe6c7;
      	color: #fff;
   }
	.pagination>li.active>a {
	  	background: #d6b286;
	    color: #fff;
	}
	.pagination>li>a {
	    color: #d6b286;
	}
	.pagination>li>a:hover {
	  	background: #fff;
	    color: #5c3316;
	}
	.col-md-6 .pagination>li>a,
	.col-md-6 .pagination>li>span {
  	  	border: 1px solid #d6b286;
	}
	.pagination>.active>a:hover{
    	background-color: #e6c195;
    	border: solid 1px #e6c195;
	}
	a.btsp{
		font-size: 35px;
		color: #e8c497;
		font-weight: bold;
		margin-left: -5px;
	}
	a.btsp:hover, a.btsp:active{
		color: #ffe6c7;
		text-decoration: none;
	}
	p.allitemtitle{
		color:#fff;
		font-weight: bold;
	}
	input.details{
		background-color: #bfbfbf;
		color: #fff;
	}
	input.details:hover{
		background-color: #e8c497;
	}
	input.update{
		background-color: #bfbfbf;
		color: #fff;
	}	
	input.update:hover{
		background-color: #e8c497;
	}
	body{
	overflow-x:hidden; <!-- 此處做整個BODY橫向的隱藏多出的寬度 -->
	}
	
	
	
	
    .bdleft{  
        transition: box-shadow 1s, background-color 1s,filter 1s;
        text-shadow: black 0.1em 0.1em 0.2em;
        border-radius: 15px;
        width:330px;
        height:230px;
        positon:relative;
        margin: 0px 20px;
    }
    .bdleft:hover{
        box-shadow: 5px 5px 30px 5px rgba(0,0,0,0.5);
        
    }
    .bdleft:active{
        opacity: 0.8;
        background-color: white;
    }

	.bdlefttext{
		position:absolute;
		cursor: pointer;
		color:#fff;
		font-size:33px;
		font-weight:bold;
		letter-spacing:20px;
		display:flex;
		margin-top:160px;
		margin-left:66px;
	}

    .bdleftimg{
    	width:330px;
    	height:230px;
    	border-radius: 15px;
    }


</style>
</head>

<body style="height: auto;">

     <!-- 以下為隱藏式菜單內容 -->
     <header>
         <span class="toggle-button" style="margin-left:-5px;margin-top:-7px;">
             <div class="menu-bar menu-bar-top"></div>
             <div class="menu-bar menu-bar-middle"></div>
             <div class="menu-bar menu-bar-bottom"></div>
         </span>
         <div class="menu-wrap">
             <div class="menu-sidebar" style="margin-top:20px;">
                 <ul class="menu">
                     <li><a href="#">會員資料管理</a></li>
                     <li><a href="#">商品訂單管理</a></li>
                     <li><a href="#">商城管理</a></li>
                     <li><a href="#">商城客服管理</a></li>
                     <li><a href="#">討論區管理</a></li>
                     <li><a href="#">浪浪找家管理</a></li>
                     <li><a href="#">知識站管理</a></li>
                     <li><a href="#">公告管理</a></li>
                     <li><a href="#">美容師管理</a></li>
                     <li><a href="#">美容預約檢舉管理</a></li>
                 </ul>
             </div>
         </div>
     </header> 
     <!-- 以上為隱藏式菜單內容 --> 
 
	 
 	<!-- 以上必須都有,直接複製 -->	 
	 
	 
	 
	 
	 
	 
<!-- 再來是我的個人囉嗦小建議,大家再自行斟酌要不要用下面的範圍 -->	 

<!-- 如果是要做條列式的頁面可以直接複製"全部"的程式碼,刪掉我的註解後直接在"紅框線"標註的空白內容開始做你的版面 -->

<!-- 不過因為是後端的畫面,如果資料較繁複,簡約一點的版面配置能提升使用者體驗; 如果內容不多較簡單,用國任今天分享的方式(背景大圖)會讓版面變得比較豐富好看 --> 
	 
	 
 	<!-- id="wrapper"這段是包住整個頁面的大外框，因為有用margin-left，所以目前位置剛好卡在漢堡(隱藏式菜單)的右邊，此行刪掉會跑版 --> 
    <div id="wrapper" style="margin-left: 120px;height: auto;">
        <div class="d-flex flex-column" id="content-wrapper" style="margin-right:5px;">
            
            	 <!-- 以下範圍為最上方包著商城管理以及右方後台人員的區塊, -->
                <nav class="navbar navbar-light navbar-expand bg-white mb-4 topbar static-top">
                    <div class="container-fluid" style="margin-top:23px;margin-left:-6px;">
<!--請看這行最右邊-->  <a class="btsp" href="">商城管理首頁</a>  <!-- 這行是商城管理的標題，可以自行設定，還有href可以自行設定跳轉的頁面 -->
                       	 <ul class="nav navbar-nav flex-nowrap ml-auto" style="margin-top:-10px;">                         
                           
                            <li class="nav-item dropdown no-arrow" style="margin-top:10px">
                                <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#">
                                	<span class="backperson" style="font-size:15px;font-weight:700;margin-right:5px;">後台人員</span>
                                	<img class="border rounded-circle img-profile" style="widht:40px;height:40px;margin-top:-5px;" src="<%=request.getContextPath()%>/resources/images/items/MaoUniICON.png"></a>
                                    <div class="dropdown-menu dropdown-menu-right animated--grow-in">
                                    	<a class="dropdown-item" href="#" style="font-weight:700;color:#646464;">Logout</a>
                                    </div>
	                   			</div>
	                    	</li>
	                    </ul>
	            	</div>
	            </nav>
				<!-- 以上範圍為最上方包著商城管理以及右方後台人員的區塊, --> 


				<!-- 以下為商城管理下面那個長方形,包含駝色表頭(所有商品資料) + 下面空白區域 -->
			    <div class="belownavbar" style="margin-left:-8px; margin-right: 25px;">
			    
			        
			    
			        <!-- 以下為空白區域(幫大家用紅邊線標示,記得要用的時候去掉) -->
			        <div class="belownavbar-body" style="margin-top:85px;height: 300px;display:flex;flex-wrap:wrap;">
					
					<div class="bdleft">
					<p class="bdlefttext">商品查詢</p>
					<a href="item_select_page.jsp">
					<img class="bdleftimg" src="<%=request.getContextPath()%>/resources/images/pic/dog1.jpg"></img>
					</a>
					</div>
					
					<div class="bdleft">
					<p class="bdlefttext">商品管理</p>
					<a href="listAllItem.jsp">
					<img class="bdleftimg" src="<%=request.getContextPath()%>/resources/images/pic/dog2.jpg"></img>
					</a>
					</div>
					
					<div class="bdleft">
					<p class="bdlefttext">商品訂單</p>
					<a href="#"> <!-- 給思瑤放連結商品訂單的a標籤 -->
					<img class="bdleftimg" src="<%=request.getContextPath()%>/resources/images/pic/dog3.jpg"></img>
					</a>
					</div>
					
				    </div>
				    <!-- 以上為空白區域(幫大家用紅邊線標示,記得要用的時候去掉) -->
		    	
		    	</div> 
				<!-- 以上為商城管理下面那個長方形,包含駝色表頭(所有商品資料) + 下面空白區域 -->









				  
				        
        </div> <!-- class="d-flex flex-column"的結尾標籤-->
	</div> <!-- Class="Wrapper"的結尾標籤 -->







	<!-- 以下必須都有,直接複製 -->


	<!-- 以下為boostrap靜態檔引入,不可少!!! 路徑錯了會跑版-->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script> 
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
    
    

	<!-- 以下為隱藏式菜單連結(JSP)--> 
    <script src="<%=request.getContextPath()%>/resources/js/hidden_menu3.js"></script> 
    <script src="<%=request.getContextPath()%>/resources/js/hidden_menu4.js"></script>
    
</body>
</html>
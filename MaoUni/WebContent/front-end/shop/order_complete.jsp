<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.5, minimum-scale=0.5, user-scalable=no">
<meta name = "viewport" content ="width=device-width,initial-scale-1.0">
    <!-- <script src="JavaScript 檔案位址.js"></script> -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
    <title>MaoUni 商城</title>
	
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"> <!-- 左邊這個是右上角購物車下拉式選單使用的BootStrap CSS -->  
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/styleD.css"> <!-- 左邊這個是右上角購物車下拉式選單使用的CSS,有再調過細部效果 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/fontawesome-all.min.css"> <!-- 左邊這個是右上角聊天室ICON的引入 -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/shop_order.css">
    
<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/sweetalert2.css"> 
<script src="<%= request.getContextPath() %>/resources/js/sweetalert2.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/popper.min.js"></script>

 
</head>


<body>

    <!--以上為頭部的html設定位子-->
<div class="container mt-5">
            <div class="h1 text-center"><strong>訂單完成</strong></div>
            <div class="row justify-content-center mt-3">
                <div class="col-md-8 text-center">
                    <div class="alert alert-success alert-rounded " role="alert">訂單完成</div>
                    <div class="h4">購買資訊</div>
                    <table class="table table-sm my-4">
                        <thead>
                            <tr>
                                <th width="100"></th>
                                <th> 商品名稱</th>
                                <th>數量</th>
                                <th class="text-center" width="120">小計</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="align-middle">
                                    <div class="card p-1 card-bottom">
                                        <img src="https://images.unsplash.com/photo-1447005497901-b3e9ee359928?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=967&q=80"
                                            alt="..." width="80px;">
                                    </div>
                                </td>
                                <td class="align-middle "> 一級偽裝帽</td>
                                <td class="align-middle">1件</td>
                                <td class="align-middle text-right">$999</td>
                            </tr>
                            <tr class="text-right">
                                <td colspan="3"><strong>運費</strong></td>
                                <td><strong>$ 60</strong></td>
                            </tr>
                            <tr class="text-right">
                                <td colspan="3"><strong>合計</strong></td>
                                <td><strong>$ 1059</strong></td>
                            </tr>
                        </tbody>
                    </table>

                    <div class="h4 mt-5">個人資料</div>
                    <table class="table  mt-3 text-left">
                        <tbody>
                            <tr>
                                <th width="200">Email</th>
                                <td>ccc9***@happymail.com</td>
                            </tr>
                            <tr>
                                <th>姓名</th>
                                <td>王*明</td>
                            </tr>
                            <tr>
                                <th>電話</th>
                                <td>0912-345***</td>
                            </tr>
                            <tr>
                                <th>地址</th>
                                <td>台北市晴天路二段321號5樓</td>
                            </tr>
                        </tbody>

                    </table>
                </div>
            </div>
            <div class="text-center mt-4">
                <a href="#" class="btn btn-primary px-5 mb-5">回首頁</a>
            </div>

        </div>



	
	<!-- 以下為FOOTER -->


	
	
    <script>
 // 此處是縮放導覽列的動態效果，這段請一定要抓到，不然它不會動
    //滑鼠滾動(scroll)就開始觸發
    window.addEventListener("scroll",function(){

    const header = document.querySelector('header');
    header.classList.toggle('sticky',window.scrollY > 0);
    });
 
    const sessionId = "${sessionId}";
    </script>
    <script src="<%= request.getContextPath() %>/resources/js/shopping_cart.js"></script>
    	<script src="<%=request.getContextPath()%>/resources/js/cmain.js"></script>
	
	<!-- 以下為CDN引入 -->
	<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
    <script src="<%= request.getContextPath() %>/resources/js/popper.min.js"></script>
    <script src="<%= request.getContextPath() %>/resources/js/bootstrap.min.js"></script>
</body>
</html>
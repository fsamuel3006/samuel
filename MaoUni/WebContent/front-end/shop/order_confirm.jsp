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
            <div class="h1 text-center"><strong>訂單確認</strong></div>

            <div class="row justify-content-center mt-4">
                <div class="col-md-8 text-center">
                    <div class="alert alert-success alert-rounded " role="alert">輸入訂單資料</div>
                    <div class="accordion" id="accordionExample">
                        <div class="card card-bottom">
                            <div class="card-header  d-flex justify-content-between" id="headingOne">
                                <button class="btn btn-link" type="button" data-toggle="collapse"
                                    data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    顯示購物車細節
                                </button>
<form id="orderForm" class="needs-validation" novalidate>
                                <div class="h3 d-inline-block mt-2">
                                    <strong>$ <span class="total">60</span></strong>
                                </div>
                            </div>

                        </div>
                        <div id="collapseOne" class="collapse show " aria-labelledby="headingOne"
                            data-parent="#accordionExample">
                            <table class="table table-sm">
                                <thead>
                                    <tr>
                                        <th width="20"></th>
                                        <th></th>
                                        <th width="100"> 商品名稱</th>
                                        <th class="text-center">數量</th>
                                        <th class="text-center" width="120">小計</th>
                                    </tr>
                                </thead>
                                <tbody class="cartbody">
                                	<!-------------- js 加入 -------------->
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="card text-center my-5 border-0">

                       		<input type="hidden" name="action" value="insertWithco">
                            <input type="hidden" name="oMemId" value="3">
                            <input type="hidden" class="oMoney" name="oMoney" value="">
<!--                             <input type="hidden" class="orderList" name = "orderList" value=""> -->
                             <div class="form-row mb-2">
                                <label for="oPaying">付款方式</label>
                                <select name="oPaying" class="form-control">
                                        <option value="0" selected>貨到付款</option>
                                        <option value="1">ATM轉帳</option>
                                        <option value="2">信用卡</option>
                                    </select>
                            </div>
                            <div class="form-row mb-2">
                                <label for="oSend">送貨方式</label>
                                <select name="oSend" class="form-control">
                                        <option value="0" selected>宅配</option>
                                        <option value="1">便利商店</option>
                                    </select>
                            </div>
                            <div class="form-row">
                                <label for="address">備註</label>
                                <input type="text" class="form-control" name="obuyOther" placeholder="" required>
                            </div>
                            <div class="mt-3 d-flex justify-content-end">
                                <button class="btn btn-secondary mr-2">繼續選購</button>
                                <button type="submit" class="btn btn-primary">送出訂單</button>
                            </div>
</form>

                    </div>
                </div>
            </div>

        </div>



	
	<!-- 以下為FOOTER -->


	
	
    <script>
     const sessionId = "${sessionId}";
    </script>
    <script src="<%= request.getContextPath() %>/resources/js/order_comfirm.js"></script>
	
	<!-- 以下為CDN引入 -->
	<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
    <script src="<%= request.getContextPath() %>/resources/js/popper.min.js"></script>
    <script src="<%= request.getContextPath() %>/resources/js/bootstrap.min.js"></script>
</body>
</html>
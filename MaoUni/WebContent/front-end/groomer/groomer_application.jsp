<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groomer.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Groomer Application</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/groomer_application.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/sweetalert2.css">

<script src="<%= request.getContextPath() %>/resources/js/fontawesome.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/sweetalert2.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤: </font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test="${not empty success}">
		<script>
			alert("新增成功");
		</script>
	</c:if>
	
	<main class="content">

		<section id="form-wrapper" class="row container">
			<form method="post" action="/MaoUni/groomer.do" id="survey-form" class="form-row col-md-7"
				enctype="multipart/form-data">
				<h1 id="title">美容師申請表</h1>
				<div class="col-md-12 row">
					<div class="memId col-md-6">
						<label id="memId-label" for="memId">會員編號</label> 
						<input type="number" id="memId" class="form-control" name="memId" value="1" />
					</div>
					<div class="field-wrapper col-md-6">
						<label id="name-label" for="name">姓名</label> 
						<input type="text" id="name" class="form-control" name="gname" value="王小迷" />
					</div>
					<div class="field-wrapper col-md-8">
						<label id="center-label" for="center">中心位址</label> 
						<input type="text" min="1" placeholder="請輸入中心位址，以計算服務範圍" id="center" class="form-control" required name="center" required />
					</div>
					<div class="field-wrapper col-md-4">
						<label id="range-label" for="range">服務範圍<span style="font-size: 0.7em">(公里)</span></label> 
						<input type="number" min="1" placeholder="請輸入整數" max="122" id="range" class="form-control" name="grange" required />
					</div>
				</div>

				<div class="col-md-12 row">
					<div class="col-md-6">
						<div class="field-wrapper col-md-12 mb-0">
							<label id="license-label">美容師認證書</label>
						</div>
						<div class="field-wrapper col-md-12">
							<input type="file" class="custom-file-input upfile" id="license" name="license" required> <label class="custom-file-label form-control">請上傳檔案...</label>
						</div>
						<!-- DOM操作 上傳後才加入預覽圖片 -->
						<!-- <div class="photo liscensePic mb-3"></div> -->
					</div>
					<div class="col-md-6">
						<div class="field-wrapper col-md-12 mb-0">
							<label id="pcrc-label" for="pcrc">良民證</label>
						</div>
						<div class="field-wrapper col-md-12">
							<input type="file" class="custom-file-input upfile" id="pcrc" name="pcrc" required> <label class="custom-file-label form-control">請上傳檔案...</label>
						</div>
						<!-- DOM操作 上傳後才加入預覽圖片 -->
						<!-- <div class="photo pcrcPic mb-3"></div> -->
					</div>

					<div class="col-md-6">
						<div class="field-wrapper col-md-12 mb-0">
							<label id="fid-label" for="fid">身分證<span style="font-size: 0.7em">(正面)</span></label>
						</div>
						<div class="field-wrapper col-md-12">
							<input type="file" class="custom-file-input upfile" id="fid" name="fid" required> <label class="custom-file-label form-control">請上傳檔案...</label>
						</div>
						<!-- DOM操作 上傳後才加入預覽圖片 -->
						<!-- <div class="photo fidPic mb-3"></div> -->
					</div>
					<div class="col-md-6">
						<div class="field-wrapper col-md-12 mb-0">
							<label id="bid-label" for="bid">身分證<span style="font-size: 0.7em">(反面)</span></label>
						</div>
						<div class="field-wrapper col-md-12">
							<input type="file" class="custom-file-input upfile" id="bid" name="bid" required> <label class="custom-file-label form-control">請上傳檔案...</label>
						</div>
						<!-- DOM操作 上傳後才加入預覽圖片 -->
						<!-- <div class="photo bidPic mb-3"></div> -->
					</div>
				</div>
				<input type="hidden" name="action" value="addGroomer">
				<button type="submit" class="btn btn-primary btn-lg btn-block">送出申請</button>
			</form>
			<div class="preview col-md-5 row justify-content-center align-item-center mt-5"></div>
<!-- 			<div class="img mb-3 col-md-5" ><img src="https://t14.pimg.jp/010/830/504/1/10830504.jpg"></div> -->
		</section>
	</main>
	<footer></footer>
	
	<script>

	function init() {
	  let fnames = "";

	  $(".upfile").change(function (e) {
		  
	    files = e.target.files;

	    if (files !== null) {
	      for (let i = 0; i < files.length; i++) {
	        let file = files[i];
	        if (file.type.indexOf('image') > -1) {
	          fnames = `\${file.name}`
	          let reader = new FileReader();

	          reader.addEventListener('load', function (e) {
	            let result = e.target.result;
	            let show =  `
	                <div class="img col-md-5"><img src="\${result}"></div>
	                `;
	            $(".preview").append(show);
	          });
	          reader.readAsDataURL(file); // 傳入要讀取的檔案，並開始進行讀取(將圖片轉成 Base64)
	        } else {
	          alert('請上傳圖片');
	        }
	      }
	      $(e.target).next().text(fnames);
	    }
	  })

	}

	window.onload = init;
	
	
	</script>


<!--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js" type="text/javascript"></script> -->
<!--     <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>')</script> -->
    <script src="<%= request.getContextPath() %>/resources/js/bootstrap.min.js"></script>

</body>
</html>
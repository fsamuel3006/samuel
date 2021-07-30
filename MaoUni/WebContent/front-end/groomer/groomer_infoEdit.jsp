<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.groomer.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Groomer InfoEdit</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/tooplate-gymso-style.css">
<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/sweetalert2.css">

<script src="<%= request.getContextPath() %>/resources/js/fontawesome.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/sweetalert2.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
<style>

#show-user-avatar {
	border-radius: 50%;
}
.show-item {
	border-radius: 5%;
}
.card {
	height: 250px;
	margin: 10px;
}
.card-body {
	width: 100%;
	align-items: center;
	padding: 0px;
	margin-top: 10px;
}
.stranger, .likes {
	color: #E53A40;
}
.avatar {
	text-align: center;
	width: 200px;
	height: 200px;
}
.avatar-img {
	border-radius: 25px;
	max-width: 180px;
	height: 180px;
	/* 	background: url("") */ no-repeat center 0;
	background-size: cover;
	margin-top: 8px;
}
a{
text-decoration: none;
}

main{
margin-top: 130px;
}

.deleteItem:hover{
cursor: pointer;
}

</style>
</head>
<body>
	<c:if test="${uploadSuccess != null}">
		<script>
			alert("Upload Success!")
		</script>
	</c:if>

	<!-- MENU BAR -->
    <nav class="navbar navbar-expand-lg fixed-top">
        <div class="container">

            <a class="navbar-brand" href="index.html">美容服務管理</a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-lg-auto">
                    <li class="nav-item">
                        <a href="<%= request.getContextPath() %>/front-end/member/grooming_groomerInfo.jsp" class="nav-link smoothScroll">個人主頁</a>
                    </li>
                    <li class="nav-item">
                        <a href="<%= request.getContextPath() %>/front-end/groomer/groomer_infoEdit.jsp" class="nav-link smoothScroll">服務資訊管理</a>
                    </li>
                    <li class="nav-item">
                        <a href="<%= request.getContextPath() %>/front-end/groomer/groomer_appointment_manage.jsp" class="nav-link smoothScroll">預約單管理</a>
                    </li>
                    <li class="nav-item">
                        <a href="<%= request.getContextPath() %>/front-end/groomer/groomer_schedule.jsp" class="nav-link smoothScroll">班表</a>
                    </li>
                </ul>

            </div>

        </div>
    </nav>
	<main>
<!--------------------------------   groomer 表修正項目：名稱、大頭照、預設服務日、預設服務時間、簡介   ------------------------------------->	
		<div class="container">
<jsp:useBean id="groSvc" class="com.groomer.model.GroService"></jsp:useBean>
<% 
	GroVO groVO = groSvc.findByPrimaryKey(1);
	pageContext.setAttribute("groVO", groVO);
%>

		<h2>服務項目</h2>
<!-----------------------------------------------------     svcList start     ---------------------------------------------------------------->		
		<div class="row align-items-center svcList">
			<form style="width: 100%; margin: 0px;">
				<div class="form-row">
					<div class="form-group col-md-2">
						<select class="form-control svcItem_pet" name="svcPet">
							<option selected>寵物類別</option>
							<option value="貓">貓</option>
							<option value="狗">狗</option>
						</select>
					</div>
					<div class="form-group col-md-3">
						<select class="form-control svcItem_item" name="svcItem_item">
							<option selected>服務項目</option>
							<!-- 選擇寵物類別後動態產生搜尋結果 -->
						</select>
					</div>
					<div class="form-group col-md-3">
						<select class="form-control svcTime" name="svcTime">
							<option selected>預估服務時間</option>
							<option value="0">0</option>
							<option value="1">30</option>
							<option value="2">60</option>
							<option value="3">90</option>
							<option value="4">120</option>
							<option value="5">150</option>
							<option value="6">180</option>
							<option value="7">210</option>
							<option value="8">240</option>
							<option value="9">270</option>
							<option value="10">300</option>
							<option value="11">330</option>
							<option value="12">360</option>
							<option value="13">390</option>
							<option value="14">420</option>
							<option value="15">450</option>
							<option value="16">480</option>
						</select>
					</div>
					<div class="form-group col-md-2">
						<input type="number" class="form-control price" name="price" placeholder="價格">
					</div>
					<div class="form-group col-md-2">
						<button type="submit" class="btn btn-info addService">+</button>
					</div>
				</div>
			</form>
		</div>

		<div class="row align-items-center mx-md-3">
			<table class="table table-sm">
				<thead>
					<tr>
						<th scope="col">類型</th>
						<th scope="col">服務</th>
						<th scope="col">預估服務時間</th>
						<th scope="col">價格</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody class="showSvcList">
					<!--  AJAX 動態產生 -->
				</tbody>
			</table>
		</div>
<!-----------------------------------------------------   svcList end   ---------------------------------------------------------------->		
		<hr>
		<h2>作品集</h2>
<!-----------------------------------------------------   works start   ---------------------------------------------------------------->			
		<div class="row works ">
			<button type="button" class="btn btn-info col-md-12 my-5" data-toggle="modal" data-target="#itemUpload">上傳作品</button>
			<div class="modal fade" id="itemUpload" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">作品集上傳</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<form method="post" action="/MaoUni/work.do" enctype="multipart/form-data">
							<div class="modal-body">
								<input type="hidden" name="groomerId" value="${groVO.groomerId }">
								<div class="form-group">
									<div class="field-wrapper col-md-11">
										<input type="file" class="custom-file-input upfile" id="item" name="item" multiple required> 
										<label class="custom-file-label form-control">選擇照片...</label>
									</div>
								</div>
								<hr>
								<div class="form-group">
									<div class="uploadPreview">
										<img src="" alt="">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<input type="hidden" name="action" value="uploadItems">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
								<button type="submit" class="btn btn-primary" name="items">上傳</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- -----------------------------------------   show works   -----------------------------------         -->
		<div class="showWork row">
			<jsp:useBean id="workSvc" class="com.works.model.WorkService"></jsp:useBean>
			<c:forEach var="workVO" items="${workSvc.getOneList(groVO.groomerId)}">
				<div class="col-3">
<!-- 					<button type="button" class="btn card mb-3 mx-2 show-item" style="width: 10rem;"> -->
<%-- 						<img class="card-img-top show-item" data-id="${workVO.wid}" src="data:image/jpeg; base64, ${workVO.itemBase64 }" alt="Card image cap"> --%>
<!-- 						<div class="card-body show-item"> -->
<!-- 						<div class="row"> -->
<%-- 							<div class="card-text show-item" style="font-size: 5px"><fmt:formatDate value="${workVO.wupdate}" pattern="yyyy-MM-dd HH:mm:ss"/></div> --%>
<%-- 							<i class="fas fa-trash deleteItem ml-1" name="${workVO.wid}" style="color: #566270"></i> --%>
<!-- 						</div>	 -->
<!-- 					</div> -->
<!-- 					</button> -->
					
					<div class="card bg-warning text-white style="width: 10rem; height: 200px;"  data-wid="${workVO.wid}">
						  <img class="card-img-top show-item" data-id="${workVO.wid}" src="data:image/jpeg; base64, ${workVO.itemBase64 }" alt="Card image cap" style="width: 100%; max-height: 100%;">
						  <div class="card-img-overlay">
						    <i class="fas fa-trash deleteItem card-title" name="${workVO.wid}" style="color: white"></i>
						    <p class="card-text" style="font-size: 3px; color: white;margin-top: 180px;"><fmt:formatDate value="${workVO.wupdate}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
						  </div>
					</div>
				</div>
			</c:forEach>
		</div>
		
		
		
				
<!-----------------------------------------------------   works end   ---------------------------------------------------------------->
		<hr>
		<h2>服務資訊修改</h2>
		<form method="POST" action="/MaoUni/groomer.do" enctype="multipart/form-data">
				<div class="row align-items-center"   style="background-color: #F0E5DE;color: #7C7877;">
					<div class="col-md-3">
						<div class="avatar">
					<c:if test="${groVO.avatarBase64 != ''}">
						<img src="data:image/jpeg; base64, ${groVO.avatarBase64}" class="img-fluid" alt="avatar">
						</c:if>
						<c:if test="${groVO.avatarBase64 == ''}">
						<img src="https://cdn0.iconfinder.com/data/icons/interface-line-4/48/Cloud_upload_storage-1024.png" class="img-fluid" alt="avatar">
						</c:if>
						</div>
						<div><input type="file" name="avatar" class="upfile" value="${groVO.avatar}"></div>	
					</div>
					<div class="info col-md-9">
						<div class="gname">
							<h4>名稱</h4>
								<input type="text" name="gname" placeholder="請輸入名稱..." value="${groVO.gname}">
						</div>
						
						
						<h4 class="mt-md-2">服務日</h4>
						<div class="schDate col-md-12">
						<table class="table table-sm">
						  <thead>
						    <tr>
						        <th>一</th>
								<th>二</th>
								<th>三</th>
								<th>四</th>
								<th>五</th>
								<th>六</th>
								<th>日</th>
						    </tr>
						  </thead>
						  <tbody>
						    <tr class="workdate ">
						      <td><input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="0" name="schDate"></td>
						      <td><input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="1" name="schDate"></td>
						      <td><input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="2" name="schDate"></td>
						      <td><input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="3" name="schDate"></td>
						      <td><input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="4" name="schDate"></td>
						      <td><input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="5" name="schDate"></td>
						      <td><input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="6" name="schDate"></td>
						    </tr>
						  </tbody>
						</table>
						</div>

						<h4 class="mt-md-2">服務時間</h4>
						<div class="schTime col-md-12">
							<div class="schTime col-md-12">
								<div class="form-row">
									<div class="form-group col-md-4">
										<select id="inputState" class="form-control timeSelect startTime" name="schStartTime"></select>
									</div>
									<p style="width: 50px; text-align: center; margin-top: 23px;">
										~</p>
									<div class="form-group col-md-4">
										<select id="inputState" class="form-control timeSelect endTime" name="schEndTime"></select>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row align-items-center">
				
					<div class="intro col-md-12 px-md-4" style="margin-top: 70px;background-color: #F0E5DE;">
						<div style="text-align: center;"><h4 class="pt-3" style="color: #7C7877;">簡介</h4></div>
						<div>
							<textarea wrap="virtual" style="width: 100%; height: 300px; word-wrap: break-word;" class="my-md-3 introText" name="intro">
${groVO.intro}</textarea>
						</div>
					</div>
					<input type="hidden" name="action" value="updateInfo"> 
					<input type="hidden" name="groomerId" value="${groVO.groomerId}">
					<button type="submit" class="btn btn-info form-control mb-md-3 px-md-3 ">提交</button>
			</form>
			</div>
<!-----------------------------------------------------   groomerEdit form end   ---------------------------------------------------------------->
	</main>
	<footer></footer>
	
	<script>
		let groomerId = "${groVO.groomerId}";
	</script>


<script src="<%= request.getContextPath() %>/resources/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/grooming_appointment_manage.js"></script>
<script src="<%= request.getContextPath() %>/resources/js/groomer_info_edit.js"></script>	


</body>
</html>
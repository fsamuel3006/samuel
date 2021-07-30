<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.groomer.model.*" %>

<<jsp:useBean id="groSvc" scope="page" class="com.groomer.model.GroService"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Groomer InfoEdit</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/tooplate-gymso-style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" /> 
<script src="https://kit.fontawesome.com/0f58fd6ae7.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<style>

.avatar {
	margin-left: 30px;
	text-align: center;
	width: 200px;
	height: 200px;
}
.avatar-img {
	border-radius: 25px;
	max-width: 100%;
	max-height: 150px;
	/* 	background: url("") */ no-repeat center 0;
	background-size: cover;
	margin-top: 8px;
}
.img-fluid {
    max-width: 100%;
    height: 180px;
}

main{
margin-top: 100px;
}

.edit{
background-color: #9DC8C8;
width: 100%;
margin: 20px;
border-radius: 5px;
text-align: center;
font-size: 20px;
padding: 5px;
}

.edit:hover{
cursor: pointer;
}


</style>
</head>
<body>
<%-- 	<c:if test="${uploadSuccess != null}"> --%>
<!-- 		<script> -->
// 			alert("Upload Success!")
<!-- 		</script> -->
<%-- 	</c:if> --%>

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
                        <a href="<%= request.getContextPath() %>/front-end/groomer/groomer_info.jsp" class="nav-link smoothScroll">服務資訊</a>
                    </li>

                    <li class="nav-item">
                        <a href="<%= request.getContextPath() %>/front-end/groomer/groomer_appointment_manage.jsp" class="nav-link smoothScroll">預約單管理</a>
                    </li>

                    <li class="nav-item">
                        <a href="<%= request.getContextPath() %>/front-end/groomer/groomer_schedule.jsp" class="nav-link smoothScroll">班表</a>
                    </li>
                </ul>

<!--                 <ul class="social-icon ml-lg-3"> -->
<!--                     <li><a href="https://fb.com/tooplate" class="fa fa-facebook"></a></li> -->
<!--                     <li><a href="#" class="fa fa-twitter"></a></li> -->
<!--                     <li><a href="#" class="fa fa-instagram"></a></li> -->
<!--                 </ul> -->
            </div>

        </div>
    </nav>
	<main>
<!--------------------------------   groomer 表修正項目：名稱、大頭照、預設服務日、預設服務時間、簡介   ------------------------------------->	
		
<% 
	GroVO groVO = groSvc.findByPrimaryKey(6);
	pageContext.setAttribute("groVO", groVO);
%>
		
		<div class="container" style="background-color: #F0E5DE;">
		<h2>服務資訊</h2>
		<form id="infoEditForm" method="POST" action="/MaoUni/groomer.do" enctype="multipart/form-data">
		
		
				<div class="row align-items-center"   style="background-color: #F0E5DE;color: #7C7877;">
					<div class="col-md-3">
						<div class="avatar"></div>
						<div><input type="file" name="avatar" class="upfile"></div>	
					</div>
					<div class="info col-md-9">
						<div class="gname">
							<h4>名稱</h4>
								<input type="text" name="gname" placeholder="請輸入名稱..." value="${groVO.gname}" required>
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
										<select id="inputState" class="form-control timeSelect startTime" name="schStartTime" required></select>
									</div>
									<p style="width: 50px; text-align: center; margin-top: 23px;">
										~</p>
									<div class="form-group col-md-4">
										<select id="inputState" class="form-control timeSelect endTime" name="schEndTime" required></select>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row align-items-center">
				
					<div class="intro col-md-12 px-md-4" style="margin-top: 50px;background-color: #F0E5DE;">
						<div style="text-align: center;"><h4 class="pt-3" style="color: #7C7877;">簡介</h4></div>
						<div style="border: 1px solid gray;padding: 20px;">
							<p wrap="virtual" style="width: 100%; height: auto; word-wrap: break-word;" class="my-md-3 introText" name="intro">
<textarea wrap="virtual" style="width: 100%; height: 300px; word-wrap: break-word;" class="my-md-3 introText" name="intro">
${groVO.intro}
</textarea>
							</p>
						</div>
					</div>
					<button type="submit" class="btn btn-info form-control mb-md-3 px-md-3 updateInfo">送出</button>
					<input type="hidden" name="action" value="updateInfo"> 
					<input type="hidden" name="groomerId" value="2">
			
				</div>
			</form>
			</div>
<!-----------------------------------------------------   groomerEdit form end   ---------------------------------------------------------------->
	</main>
	<footer></footer>
	
	<script>
	
// 	// 取得預設服務日
// 	let html = "";
// 	let schDate = "${groVO.schDate}";
// 	for(let i = 0; i < schDate.length; i++){
// 		if(schDate.charAt(i) === '0'){
// 			html += "<td></td>";
// 		}else{
// 			html += "<td><i class='fas fa-check'></i></td>";
// 		}
// 	}
	
// 	$(".workdate").html(html);
	
	
// 	// 取得服務時間
// 	let schTime = "${groVO.schTime}";
// 	let stime = schTime.indexOf("1");	// 找到第一個1的index
// 	let etime = schTime.lastIndexOf("1");	// 找到第一個1的index
	
// 	$(".stime").html(timeFormate(stime));
// 	$(".etime").html(timeFormate(etime));


// 	// 將時間indext格式化
//     function timeFormate(timeIndex){
//     	let time = "";
//     	let hour = (timeIndex + 1) / 2;
    	
// 	if (timeIndex % 2 === 1) {
//         if (hour < 10) 
//         	time +=  "0"  + hour + ":00";
//         else 
//        		time += hour + ":00";
        
//       } else {
//         if (hour < 10) 
//         	time +=  "0"  + hour + ":30";
//         else 
//         	time +=  "0"  + hour + ":30";
//       }
// 		return time;
//     }   
	

	  
	</script>
	
	<script>

	function init() {
	  let fnames = "";

	  $(".upfile").change(function (e) {
		  
	    file = e.target.files[0];
	    if (file !== null) {
	        if (file.type.indexOf('image') > -1) {
	          fnames = `\${file.name}`
	          let reader = new FileReader();

	          reader.addEventListener('load', function (e) {
	            let result = e.target.result;
	            let show =  `
	            	<img src="\${result}" class="img-fluid .avatar-img" alt="avatar">
	                `;
	            $(".avatar").html(show);
	          });
	          reader.readAsDataURL(file); // 傳入要讀取的檔案，並開始進行讀取(將圖片轉成 Base64)
	        } else {
	          alert('請上傳圖片');
	        }
	    }
	  })

	}

	window.onload = init;
	
	let url =  "<%= request.getContextPath() %>/resources/front-end/groomer/groomer_infoEdit.jsp"
	
 $(".updateInfo").click(function(e){
	 console.log(url);
	 console.log("${groVO.groomerId}")
	$.ajax({
		url: "/MaoUni/groomer.do",
			type: "POST",
			contentType: false,
			data: {
				groomerId: "${groVO.groomerId}",
				gname: $("[name='gname']").val(),
				schDate: $("[name='schDate']").val(),
				schStartTime: $("[name='schStartTime']").val(),
				schEndTime: $("[name='schEndTime']").val(),
				avatar: $("[name='avatar']").val(),
				intro: $("[name='intro']").val(),
				action: "updateInfo"
			},
			success: function(data){
				console.log(data)
				if(data === 1){
					swal("成功！","班表將會依照您預設的服務時間自動排班！","success");
				}
			}
			})
})

    $(window).ready(() => {
      let time = "";
      let hour = 9;
      for (let i = 17; i <= 39; i++) {
        if (i % 2 === 1) {
          if (hour < 10) {
            time += "<option value=\"" + i + "\">0" + hour + ":00</option>";
          } else {
        	  time += "<option value=\"" + i + "\">" + hour + ":00</option>";
          }
        } else {
          if (hour < 10) {
        	  time += "<option value=\"" + i + "\">0" + hour + ":30</option>";
            hour++;
          } else {
        	  time += "<option value=\"" + i + "\">" + hour + ":30</option>";
            hour++;
          }
        }
      }
      let startTime = document.querySelector(".startTime");
      let endTime = document.querySelector(".endTime");
      startTime.innerHTML = `<option value="" selected>開始...</option>` + time;
      endTime.innerHTML = `<option value="" selected>結束...</option>` + time;
    })	
	
	
	
	
	
	
	
	</script>
	
	
	

</body>
</html>
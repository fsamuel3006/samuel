<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"">
<title>Add Service</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</head>
<body>

<!-- <form method="post" action="/MaoUni/svc.do" name="form1"> -->

<!-- <div>服務項目: <input type="text" name="svcItem"></div> -->
<!-- <div>毛孩類型:  -->
<!-- <select name="svcPet"> -->
<!-- 		<option value="狗">狗 -->
<!-- 		<option value="貓">貓 -->
<!-- </select> -->
<!-- </div> -->

<!-- <input type="hidden" name="action" value="insert"> -->
<!-- <input type="submit" value="新增"> -->





<%-- <jsp:include page="/draft/svcItemList.jsp" flush="true"></jsp:include> --%>

<hr>

<p>Date: <input type="text" id="datepicker"></p>
</form>

  <script>
  
  
  
//   let disableDate = ["2021-07-26","2021-08-01"];
//   $( ".apmDate" ).datepicker({
// 	  beforeShowDay: function(date){
// 		  let string = jQuery.datepicker.formatDate('yy-mm-dd', date)
// 	  	  return [disableDate.indexOf(string) == -1]	
// 	  }
//   });
  
  
  
  
	$.ajax({
		url: "/MaoUni/schedule.do",
		type: "GET",
		data:{
			action: "getByGroomerId",
			groomerId: "1"
		},
		success: function(data){
			let obj = JSON.parse(data);
			 let disableDate = [];
			
			for(let i = 0; i < obj.length; i++){
				let schStatus = obj[i].schStatus;
				
				if(schStatus.indexOf("1") == -1){
					disableDate.push(obj[i].schDate);
				}
				
			}
			 
			  $( "#datepicker" ).datepicker({
				  beforeShowDay: function(date){
					  let string = jQuery.datepicker.formatDate('yy-mm-dd', date)
				  	  return [disableDate.indexOf(string) == -1]	
				  },
				  minDate: -0,
				  maxDate: "+1M"
			  });
			}
		})	
 
  
  

  
  
  
  
  
  
  
  
  
  
  
  
  
  </script>
</body>
</html>
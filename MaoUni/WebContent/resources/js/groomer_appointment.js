
// WebSocket
let MyPoint = "/ApmWS/3";
let host = window.location.host;
let path = window.location.pathname;
let webCtx = path.substring(0, path.indexOf('/', 1));
let endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
let webSocket = new WebSocket(endPointURL);

let self = "3";


// datepicker
let disableDate = [];

function init(){
	connect();
	getDisableDate();
}

function getDisableDate(){
	$.ajax({
		url: "/MaoUni/schedule.do",
		type: "GET",
		data:{
			action: "getByGroomerId",
			groomerId: "1"
		},
		success: function(data){
			let obj = JSON.parse(data);
	
			for(let i = 0; i < obj.length; i++){
				let schStatus = obj[i].schStatus;
				
				if(schStatus.indexOf("1") == -1){
					disableDate.push(obj[i].schDate);
				}
			}
		}
	})	
}
		
		

function connect(){
	
	webSocket.onopen = function(e){
		console.log("Connect Success!");
	}
	
	webSocket.onmessage = function(e){
		let jsonObj = JSON.parse(e.data);
			alert(jsonObj.message);
	}
	
	webSocket.onclose = function(e){
		console.log("Disconnected!");
	}
}


window.onload = init;

window.onunload = function(){
	webSocket.close();
}



// 選擇預約後，自動生成未來30日班表
	
	$("#modBtn").click(function(e){
		$.ajax({
			url: "/MaoUni/schedule.do",
			type: "GET",
			data:{
			action: "autoInsertData"
		  },
		})
	})

	
// 選擇寵物後，依據寵物類別載入可選的服務項目
	
	$(".pid").change(function(e){
		$.ajax({
			url: "/MaoUni/svcList.do",
			type: "GET",
			dataType: "text",
			data: {
				groomerId: "1",
				action: "getSvcList"
			},
			success: function(data){
				let obj = JSON.parse(data);
				let inner = "";
				
				for(let i = 0; i < obj.length; i++){
					let svcPet = obj[i].svcPet;
					// 須修正動態取得pid資訊，為貓或是狗
					if(svcPet === "狗" ){  
					inner += `
						<tr>
					  		<td>${obj[i].svcPet}</td>
		    	        	<td>${obj[i].svcItem}</td>
		    	        	<td data-svctime="${obj[i].svcTime}">${obj[i].svcTime * 30}</td>
		    	        	<td>${obj[i].price}</td>
							<td><input type="checkbox" class="item" name="svcId" value="${obj[i].svcId}"></td>
		    	      	</tr>
					`;
					}
				}
				$(".showSvcList").html(inner);
			}
		})
		
	})
	
// 選擇寵物後，動態產生該美容師可預約日期 (剔除未營業日)	
//	
//	$(".pid").change(function(e){
//		$.ajax({
//			url: "/MaoUni/schedule.do",
//			type: "GET",
//			data:{
//				action: "getByGroomerId",
//				groomerId: "1"
//			},
//			success: function(data){
//				let obj = JSON.parse(data);
//				let apmDateHtml = "<option selected>選擇日期</option>";
//				
//				for(let i = 0; i < obj.length; i++){
//					let schStatus = obj[i].schStatus;
//					
//					if(schStatus.indexOf("1") !== -1){
//						apmDateHtml += `
//							<option value="${obj[i].schId}">${obj[i].schDate}</option>
//							`
//					}
//					
//				}
//				$(".apmDate").html(apmDateHtml);		
//			}
//			
//		})	
//	})
	
//	
//	window.onload = function(){
//		
//
//	}
//
//	
	
	

	
// 動態更新 Total、服務時間

	$(document).on("click", ".showSvcList", function(e){
		$(".apmDate").val("");
		if(e.target.classList.contains("item")){
		$(".etime").val();
		let price = e.target.parentElement.previousElementSibling.innerText;
		let svcTime = e.target.parentElement.previousElementSibling.previousElementSibling.dataset.svctime;
		let count = $(".total").val();
		let needtime = $(".needtime").val();
		
			if(e.target.classList.contains("checked")){
				$(".total").val(parseInt(count) - parseInt(price));
				$(".needtime").val(parseInt(needtime) - parseInt(svcTime));
			}else{
				$(".total").val(parseInt(count) + parseInt(price));
				$(".needtime").val(parseInt(needtime) + parseInt(svcTime));
			}
			$(e.target).toggleClass("checked");
		}
	})
	
	
// 選擇日期後，根據所選服務項目所需花費時間，剔除時間區段不足的時間
	
	$(document).on("change", ".apmDate",function(e){
		$.ajax({
			url: "/MaoUni/schedule.do",
			type: "GET",
			data: {
				apmDate: e.target.value,
				groomerId: "1",
				action: "getSchStatusByDate"
			},
			success: function(data){
				let needtime = parseInt($(".needtime").val());
				let stimeHtml = "<option selected>選擇時間</option>";
				let schStatus = JSON.parse(data).schStatus;
				let count = 0;
				for(let i = 0; i < schStatus.length; i++){
	
					// if(data.charAt(i) !== "1") continue;
					
					// 只顯示stime ~ etime間均為1的時間;
					let interval = schStatus.slice(i, i + needtime);
					
					if((i + needtime) > 41) continue; // etime 超過21:00不顯示
					
					console.log("interval:" + interval);  // check
					
					if(interval.indexOf("0") !== -1 || interval.indexOf("2") !== -1 || interval.indexOf("3") !== -1 || interval.indexOf("4") !== -1 ){
						continue;
					}
					
					if(i % 2 === 1){
						if(i < 19)
							stimeHtml += `<option value="${i}">0${(i+1)/2}:00</option>`;
						else
							stimeHtml += `<option value="${i}">${(i+1)/2}:00</option>`;
					}else {
						if(i < 19)
							stimeHtml += `<option value="${i}">0${(i)/2}:30</option>`;
						else
							stimeHtml += `<option value="${i}">${(i)/2}:30</option>`;
					}
					count++;
					console.log(count)
				}
				if(count === 0){
					swal("您所選擇的日期未有足夠時間服務","請調整服務項目或更改日期","warning");
				}
				$(".stime").html(stimeHtml);
			}
		})
	})
	
	
// 選擇時間後，計算結束時間
	
	$(document).on("change", ".stime", function(e){
		$(".etime").val();							// 將原先時間歸零
		let stime = $(".stime").val();
		let needtime = $(".needtime").val();
		$(".etime").val(parseInt(stime) + parseInt(needtime));
	})
	
	

	
	
	// 送出預約
	
	$(".submit").click(function(){
		$.ajax({
			url: "/MaoUni/appointment.do",
			type: "GET",
			data: $("#apmForm").serialize(),
			success: function(data){
				swal("送出預約單","預約單已送出，請耐心等候美容師回覆","success").then((result) => {
					let jsonObj = {
							"type": "newApm",
							"sender": self,
							"receiver": receiverId,
							"message":"收到一筆新的預約！"
						}
						webSocket.send(JSON.stringify(jsonObj));
					window.location.reload();
				});
			}
		})

	})	

	
	
	
	
	
	
	
	


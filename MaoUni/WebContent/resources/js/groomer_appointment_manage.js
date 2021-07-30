
//WebSocket

let MyPoint = "/ApmWS/1";
let host = window.location.host;
let path = window.location.pathname;
let webCtx = path.substring(0, path.indexOf('/', 1));
let endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
console.log(endPointURL)

let self = "1";
let webSocket;

	window.onload = function(){
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(e){
			console.log("Connect Success!");
		}
		
		webSocket.onmessage = function(e){
			let jsonObj = JSON.parse(e.data);
				alert(jsonObj.message);
		}
		
		webSocket.onclose = function(e){
			console.log("Disconnected!")
		}
	}
	
	window.onunload = function(){
		webSocket.close();
	}	
		

	// 發送訊息
	function sendmessage(){
		let jsonObj = {
				"type": "apmStatusChange",
				"sender": self,
				"receiver": memId,
				"message":"預約單狀態更新！"
			}
		webSocket.send(JSON.stringify(jsonObj));
	}

// 更改預約單狀態 拒絕、接受
	
	$(".svcList").click(function(e){
		e.preventDefault();

		let status = "";
		if(e.target.classList.contains("accept") || e.target.classList.contains("refuse")){
			if(e.target.classList.contains("accept")){
				// 接受預約，需先取得該日班表進行檢查行程是否衝突
				$.ajax({
					url: "/MaoUni/schedule.do", 
					type: "GET",
					data: {
						action: "getSchStatusByDate",
						apmDate: apmDate,
						groomerId: groomerId
					},
					success: function(data){
						let obj = JSON.parse(data);
						let interval = obj.schStatus.slice(stime, etime + 1);
						if(interval.indexOf("0") !== -1 || interval.indexOf("2") !== -1 || interval.indexOf("3") !== -1 ){
							swal("失敗！","此段時間已有行程，請確認班表","error");
							$(".svcList").hide();
							return;
						}
						status += "2";
						
						$.ajax({
							url: "/MaoUni/appointment.do",
							type: "POST",
							data: {
								apmId: $(".apmManage").val(),
								action: "updateStatus",
								apmStatus: status
							},
							success: function(data){
								swal("完成!","班表已更新","success").then((result) => {
									$(".svcList").hide();
									window.location.reload();
									sendmessage();
		                        });
							}
						})
					}
				})
				
				
				
			}else if(e.target.classList.contains("refuse")){
				status += "1";
				$.ajax({
					url: "/MaoUni/appointment.do",
					type: "POST",
					data: {
						apmId: $(".apmManage").val(),
						action: "updateStatus",
						apmStatus: status
					},
					success: function(data){
						swal("完成!","班表已更新","success").then((result)=>{
							$(".svcList").hide();
							sendmessage();
							window.location.reload();
						});
					}
				})
			}
			
		}
	})
	

// 複合查詢預約單

	let serchApmList = [];

	
	$(".search").click(function(e){
		e.preventDefault();		// 如果沒有移除預設，button會自動送出(空表單)，導致立即重新載入頁面
		$(".svcList").hide();
		$.ajax({
			url: "/MaoUni/appointment.do",
			type: "GET",
			data: $("#serchForm").serialize(),
			success: function(data){
				let objs = JSON.parse(data);
				serchApmList = []; // 清空
				serchApmList.push(...objs);

				if(serchApmList.length === 0){
					swal("查無結果","您輸入的條件未找到符合資料","info");
				}
				renderPaginator(serchApmList.length);
				renderApmList(getApmListByPage(1));
				clearOverlays();
				generateMarkers();
			}
		})
	})
	
	
// 顯示預約單明細以進行審核
let memId = "";
let stime = 0;
let etime = 0;
let apmDate = "";
let groomerId = "";
		
	$(document).on("click", ".showList", function(e){
		if(e.target.classList.contains("detail")){
			memId = "";
			stime = 0;
			etime = 0;
			apmDate = "";
			groomerId = "";
			
			memId = parseInt(e.target.dataset.memid);
			stime = parseInt(e.target.dataset.stime);
			etime = parseInt(e.target.dataset.etime);
			apmDate += e.target.dataset.apmdate;
			groomerId += e.target.dataset.groomerid;
			$(".svcList").show();
			$(".targetId").text(`# ${e.target.id}`)
			$.ajax({
				url: "/MaoUni/apmdetails.do",
				type: "GET",
				data: {
					apmId: e.target.id,
					action: "getByAppointmentId"
				},
				success: function(data){
					let obj = JSON.parse(data);
					let showItem = "";
					for(let i = 0; i < obj.length; i++){
						showItem += `<div class="svcItem ">${obj[i].item}</div>`;
					}
					
					if(e.target.dataset.status === '0'){
						showItem += `
							<button type="button" class="btn btn-secondary refuse">拒絕</button>
							<button type="button" class="btn btn-info accept">接受</button>
							`
					}
					
					$(".apmManage").val(e.target.id)
					$(".showDeatail").html(showItem);
				}
			})
		}	
	})
	
	
// 關閉預約單明細
	
	$(".close").click(function(e){
		e.preventDefault();
		$(".svcList").hide();
	})

	
	
// 取消、完成預約
$(".appointmentList").click(function(e){
	let status = "";
	if(e.target.className === "cancle"){
		status += "4";
	}else if(e.target.className === "done"){
		status += "3";
	}else{
		return;
	}
	
	
	$.ajax({
			url: "/MaoUni/appointment.do",
			type: "POST",
			data:{
				apmId: e.target.dataset.apmid,
				action: "updateStatus",
				apmStatus: status
			},
			success: function(data){
				swal("班表已更新!","","success");
				memId = e.target.dataset.memid;
				sendmessage();
				window.location.reload();
			}
		})
	})
	
	
	
	
// 選染頁碼
	
	function renderPaginator(amount){
		const numberOfPages = Math.ceil(amount / APMS_PER_PAGE); // Math.ceil 將無條件進位
		let html = "";
		
		for(let page = 1; page <= numberOfPages; page++){
			html += `<li class="page-item"><a class="page-link" href="#" data-page="${page}">${page}</a></li>`
		}
		$(".pagination").html(html);
	}
	
	
	
// 取得分頁內容
	
	const APMS_PER_PAGE = 5;
	
	function getApmListByPage(page){	
		const startIndex = (page - 1) * APMS_PER_PAGE;
		const data = serchApmList.length ? serchApmList : apmList; // 如果搜尋的陣列裡有資料，則以其作分頁依據
		return data.slice(startIndex, startIndex + APMS_PER_PAGE);
	}
	

// 渲染取得的內容
	
	function renderApmList(apmList){
		let html = "";
		
		for(let i = 0; i < apmList.length; i++){
			let apmStatus = "";
			let buttonGroup = "";

			switch (apmList[i].apmStatus){
			case 0:
				apmStatus += "待審核";
				buttonGroup += `
						<button class="cancle disabled" data-apmid="${apmList[i].apmId}" disabled>取消</button>
						<button class="done disabled" data-apmid="${apmList[i].apmId}" disabled>完成</button>
						<button class="detail" id="${apmList[i].apmId}" data-status="${apmList[i].apmStatus}" 
						data-stime="${apmList[i].stime}" data-etime="${apmList[i].etime}" data-apmdate="${apmList[i].apmDate}" data-groomerid="${apmList[i].groomerId}" data-memid="${apmList[i].memId}">審核</button>
						`
				break;
			case 1:
				apmStatus += "已拒絕";
				buttonGroup += `
					<button class="cancle disabled" data-apmid="${apmList[i].apmId}" disabled>取消</button>
					<button class="done disabled" data-apmid="${apmList[i].apmId}" disabled>完成</button>
					<button class="detail" id="${apmList[i].apmId}" data-status="${apmList[i].apmStatus}">明細</button>
					`
				break;
			case 2:
				apmStatus += "已接受";
				buttonGroup += `
					<button class="cancle" data-apmid="${apmList[i].apmId}" data-memid="${apmList[i].memId}">取消</button>
					<button class="done" data-apmid="${apmList[i].apmId}" data-memid="${apmList[i].memId}">完成</button>
					<button class="detail" id="${apmList[i].apmId}" data-status="${apmList[i].apmStatus}">明細</button>
					`
				break;
			case 3:
				apmStatus += "已完成  ";
				buttonGroup += `
					<button class="cancle disabled" data-apmid="${apmList[i].apmId}" disabled>取消</button>
					<button class="done disabled" data-apmid="${apmList[i].apmId}" disabled>完成</button>
					<button class="detail" id="${apmList[i].apmId}" data-status="${apmList[i].apmStatus}">明細</button>
					`
				break;
			case 4:
				apmStatus += "已取消";
				buttonGroup += `
					<button class="cancle disabled" data-apmid="${apmList[i].apmId}" data-memid="${apmList[i].memId}" disabled>取消</button>
					<button class="done disabled" data-apmid="${apmList[i].apmId}" disabled>完成</button>
					<button class="detail" id="${apmList[i].apmId}" data-status="${apmList[i].apmStatus}">明細</button>
					`
				break;
			}
			
			html += `
				<tr>
					<td scope="col">${apmList[i].apmId}</td>
					<td scope="col">${apmList[i].memId}</td>
					<td scope="col" class="apmDate">${apmList[i].apmDate}</td>
					<td scope="col">${apmList[i].stimeFormated}</td>
					<td scope="col">${apmList[i].etimeFormated}</td>
					<td scope="col">${apmList[i].total}</td>
					<td scope="col">${apmStatus}</td>
					<td scope="col" class="row">
						${buttonGroup}
					</td>
				</tr>
				`;
			
		}
		$(".appointmentList").html(html);
		$(".condition").val("");
	}	
	
	
	
// paginator 事件監聽器
	$(".pagination").click(function(e){
		if(e.target.tagName !== 'A') return // 若點擊的目標非A標籤則結束
		const page = Number(e.target.dataset.page)
		renderApmList(getApmListByPage(page));
	})
	
	
	
	
	
	
// ========================== googleMap ===========================	
// 頁面載入時動態顯示預約單列表
let apmList = [];

//googleMap用
//let dataForMap = [];
//let filterDataForMap = [];
let svgMarker, image, shape;
let geocoder;
	
function init() {

            // 藍色勾勾
            svgMarker = {
                path: "M10.453 14.016l6.563-6.609-1.406-1.406-5.156 5.203-2.063-2.109-1.406 1.406zM12 2.016q2.906 0 4.945 2.039t2.039 4.945q0 1.453-0.727 3.328t-1.758 3.516-2.039 3.070-1.711 2.273l-0.75 0.797q-0.281-0.328-0.75-0.867t-1.688-2.156-2.133-3.141-1.664-3.445-0.75-3.375q0-2.906 2.039-4.945t4.945-2.039z",
                fillColor: "blue",
                fillOpacity: 0.6,
                strokeWeight: 0,
                rotation: 0,
                scale: 2,
                anchor: new google.maps.Point(15, 30),
            };

            // 旗標
            image = {
                url: "https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png",
                // This marker is 20 pixels wide by 32 pixels high.
                size: new google.maps.Size(20, 32),
                // The origin for this image is (0, 0).
                origin: new google.maps.Point(0, 0),
                // The anchor for this image is the base of the flagpole at (0, 32).
                anchor: new google.maps.Point(0, 32),
            };

            shape = {
                coords: [1, 1, 1, 20, 18, 20, 18, 1],
                type: "poly",
            };
            // 呼叫JS的Geolocation API取得經緯度(前提是使用者允許被存取位置)
            // 回傳一個 Geolocation 物件，透過這個物件可以存取Device的位置資訊

            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    pos = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude
                    };

                    let marker = new google.maps.Marker({
                        position: pos,
                        icon: svgMarker,
                        map: map
                    });

                    map.setZoom(13);
                    map.setCenter(pos);

                    let cityCircle = new google.maps.Circle({
                        strokeColor: '#f1c40f', // 線條顏色
                        strokeOpacity: 1, // 線條透明度
                        strokeWeight: 1, // 線條粗度
                        fillColor: '#f1c40f', // 圓形裡填滿的顏色
                        fillOpacity: 0.35, // 圓形裡，填滿顏色的透明度
                        map: map,
                        center: pos, // 中心點
                        radius: 3000 // 半徑
                    });
                })
            } else {
                alert("請先允許存取位置！");
            }
            
//            geocoder = new google.maps.Geocoder();
            // Get FIRST DATA
            getFirstData();
        }
	
		function getFirstData() { // 因為寫在外面，window.init可能會比ajax呼叫晚執行，因此包裝成funciont放入init，確保執行順序
			$.ajax({
				url: "/MaoUni/appointment.do",
				type: "GET",
				data:{			
					groomerId: "1",
					action: "getAll"
				},
				success: function(data){
					let objs = JSON.parse(data);
					apmList.push(...objs);

					renderPaginator(apmList.length);
					renderApmList(getApmListByPage(1));
					generateMarkers();
				}
			});
		}

		let markersArray = [];
		function clearOverlays(){
			for(let i = 0; i < markersArray.length; i++){
				markersArray[i].setMap(null);
				console.log(i);
			}
			markersArray.length = 0;
			console.log(markersArray + "222222")
		}
		
        function generateMarkers() {
        	const dataForMap = serchApmList.length ? serchApmList : apmList; // 如果搜尋的陣列裡有資料，則以其作分頁依據
        	for(let i = 0; i < dataForMap.length; i++){
        		
        		// 產生 marker 物件
        		let marker = new google.maps.Marker({
        			position: { lat: parseFloat(dataForMap[i].lat), lng: parseFloat(dataForMap[i].lng) },
        			icon: image,
        			shape: shape,
        			map: map,
        			animation: google.maps.Animation.DROP, // DROP掉下來、BOUNCE一直彈跳
        			draggable: false // true、false可否拖拉
        		});
        		
        		// 產生 infowindow 物件
        		let infowindow = new google.maps.InfoWindow({
        			content: "<div class='info_title'>會員編號： " + dataForMap[i].memId + "</div>"
        		});
        		// 預設打開 info window
        		// infowindow.open(map, marker);
        		// 當 marker 被點擊時觸發
        		marker.addListener('click', function () {
        			infowindow.open(map, marker);
        		});
        		
        		markersArray.push(marker);
        	}
console.log(markersArray);
        }



        function initMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                center: { lat: 24.978391, lng: 121.268641 },
                zoom: 15,
            });
        }

        window.onload = init;	
	
	


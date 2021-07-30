
// 此處是縮放導覽列的動態效果，這段請一定要抓到，不然它不會動
//滑鼠滾動(scroll)就開始觸發
		window.addEventListener("scroll", function() {

			const header = document.querySelector('header');
			header.classList.toggle('sticky', window.scrollY > 0);
		});


// 評價
		
		$(".commentForm").on("submit", function(e){
			e.preventDefault();
			$.ajax({
				url: "/MaoUni/appointment.do",
				type: "POST",
				data: new FormData(this),
				contentType: false,
				processData: false,
				success: function(data){
					if( data == "1" ){ 
						swal("HI！","已收到您的評價","success").then((result) =>{
							window.location.replace(window.location.href);})
//						setTimeout('window.location.replace(window.location.href)',1000);
					}else{
						swal("失敗", "評價未送出，請重新提交", "error");
						setTimeout('window.location.replace(window.location.href)',1000);
					}
				},
				error: function(data){
					swal("失敗", "評價未送出，請重新提交", "error");
					setTimeout('window.location.replace(window.location.href)',1000);
				}
			})
			
		})
		
		
// 取消
		
		$(".cancle").on("click", function(e){
			$.ajax({
				url: "/MaoUni/appointment.do",
				type: "POST",
				data: {
					apmId: e.target.id,
					sapmStatus: "4",
					action: "cancel"
				},
				success: function(data){
					if(data === "1"){
						swal("您已取消預約", "若需進行服務，請重新填寫預約單", "success").then((result) =>{
							window.location.replace(window.location.href);
						});
					}else{
						swal("oops", "好像哪裡出了問題，請重新送出", "error").then((result) =>{
							window.location.replace(window.location.href);
						});
					}
				}
			})
		})
			
			
// 檢舉	
		
		$(".reportForm").on("submit", function(e){
			e.preventDefault(e);
			$.ajax({
				url: "/MaoUni/groomingreport.do",
				type: "POST",
				data: $(this).serialize(),
				success: function(data){
					if( data == "1" ){ 
						swal("成功！","檢舉已送交於管理員審核","success").then((result) =>{
							window.location.replace(window.location.href);
						});
					}else{
						swal("失敗", "檢舉未送出，請重新提交", "error").then((result) =>{
							window.location.replace(window.location.href);
						});
					}
				},
				error: function(data){
					swal("失敗", "檢舉未送出，請重新提交", "error").then((result) =>{
						window.location.replace(window.location.href);
					});
				}
			})
			
		})

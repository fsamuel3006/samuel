
// 頁面載入時自動產生格式化後的時間

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
    
    
// 頁面載入時自動載入服務列表
    
	$.ajax({
    	  url: "/MaoUni/svcList.do", 
    	  type: "GET",
    	dataType: "text",
    	  data: {
    		  groomerId: groomerId,
    		  action: "getSvcList"
    	  },
    	  success: function(data){
    		  let obj = JSON.parse(data);
    		  let svcList = "";
    		  
    		  for(let j = 0; j < obj.length; j++){
    			  svcList += `
    				  <tr>
    				  	<td>${obj[j].svcPet}</td>
    	    	        <td>${obj[j].svcItem}</td>
    	    	        <td>${obj[j].svcTime * 30}  分鐘</td>
    	    	        <td>${obj[j].price}  元</td>
    	    	        <td><i class="fas fa-trash deleteSvc" name="${obj[j].svcId}" style="color: #566270"></i></td>
    	    	      </tr>
    			  `;
    		  }
    		  $(".showSvcList").html(svcList);
    	  }
      })
  
// 刪除作品照
          
    $(document).on("click",".deleteItem",function(e){
    	let wid = e.target.getAttribute("name");
        $.ajax({
      	  url: "/MaoUni/work.do",
      	  type: "POST",
      	  data: {
      		  wid: wid,
      		  action: "deleteItem"
      	  },
      	  success: function(data){
      		  $(`[data-wid = ${wid}]`).remove();
      	  }
        })
    })
  
    
    
// 選取寵物類別後，代入可選服務項目
          
    $(document).on("change", ".svcItem_pet", function(e){
    	$.ajax({
    		url: "/MaoUni/svc.do",
    		type: "GET",
    		data:{
    			svcPet: $(".svcItem_pet").val(),
    		    action: "findByPet"
    		},
    		success: function(data){
    			let obj = JSON.parse(data);
    			html = "";
    			for(let i = 0; i < obj.length; i++){
    				html += `<option value="${obj[i].svcId}" selected>${obj[i].item}</option>`;	
    			}
    			$(".svcItem_item").html(html);
    		}
    	})
    })
 

// 新增服務項目
    $(document).on("click", ".addService", function(e){
    	console.log(groomerId);
    	$.ajax({
    		url: "/MaoUni/svcList.do",
    		type: "POST",
    		data: {
    			groomerId: groomerId,
    			svcId: $(".svcItem_item").val(),
    			price: $(".price").val(),
    			svcTime: $(".svcTime").val(),
    			action: "addService"
    		},
    		success: function(data){
    			 $(".showSvcList").html(svcList);
    		}
    	})
    })
  
    
    function init() {
  	  
  	  $(".upfile").change(function (e) {
  	    file = e.target.files[0];
  	    if (file !== null) {
  	        if (file.type.indexOf('image') > -1) {
  	          fnames = `${file.name}`
  	          let reader = new FileReader();

  	          reader.addEventListener('load', function (e) {
  	            let result = e.target.result;
  	            let show =  `
  	            	<img src="${result}" class="img-fluid .avatar-img" alt="avatar">
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

    

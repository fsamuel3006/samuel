function init(){
	// ============== 測試用  ==============
	$("#headingOne").click(function(e){
		console.log(e.target);
		$.ajax({
			url: "/MaoUni//shoppingcart.do",
			type: "POST",
			data: {
				action: "init"
			},
			success: function(data){
				getCart();	
			}
		})
	})
	// ===================================
	
	
	$(".cartbody").click(function(e){
		if(e.target.classList.contains("deleteItem")){
			$.ajax({
				url: "/MaoUni//shoppingcart.do",
				type: "POST",
				data: {
					action: "deleteItem",
					itemId: e.target.id,
					sessionId: sessionId
				},
				success: function(data){
					let total = document.querySelector(".total").innerText;
					let count = e.target.parentElement.parentElement.children[4].innerText;
					$(".total").text(total - count);
					
					e.target.parentElement.parentElement.remove();
				}
			})
		}
	})
}



let cart = [];


// 送出訂單
$("#orderForm").on("submit", function(e){
	e.preventDefault();
//	getCart();			// 取得最終的購物車紀錄
//	$(".orderList").val(cart);	// 放入form表單內以一併傳送
//	console.log($(".orderList").val());
	
	$.ajax({
		url: "/MaoUni/buy/obuy.do",
		type: "POST",
		data: $(this).serialize(),
		success: function(data){
			deleteCart();		// 清空redis內購物車value，但仍保存key
			swal("成功！","","success");
//			.then((result) => {
//				window.location.replace("/MaoUni/front-end/shop/order_complete.jsp");
//			});
		}
	})
})



function getCart(){
	$.ajax({
		url: "/MaoUni/shoppingcart.do",
		type: "GET",
		data: {
			action: "getCart",
			sessionId: sessionId
		},
		success: function(data){
			cart.push(data);
			let objs = JSON.parse(data);
			let html = "";
			let total = 60;
			
			for(let i = 0; i < objs.length; i++){
				let obj = objs[i];
				let itemSum = obj.count * obj.itemPrice;
				total += itemSum;
				html += `<tr>
                            <td class="align-middle text-center">
                                <i id="${obj.itemId}" class="far fa-trash-alt deleteItem"></i>
                            </td>
                            <td></td>
                            <td class="align-middle ">${obj.itemName}</td>
                            <td class="align-middle text-center count">${obj.count}</td>
                            <td class="align-middle text-right">${itemSum}</td>
                        </tr>`;
			}
			
			html +=`
				<tr class="text-right">
                    <td colspan="4"><strong>運費</strong></td>
                    <td><strong>$ 60</strong></td>
                </tr>
                <tr class="text-right">
                    <td colspan="4"><strong>合計</strong></td>
                    <td><strong>$ <span class="total">60</span></strong></td>
                </tr>`
			$(".cartbody").html(html);
			$(".total").text(total);
			$(".oMoney").val(total);
		}
	})
}


function deleteCart(){
	$.ajax({
		url: "/MaoUni//shoppingcart.do",
		type: "POST",
		data: {
			action: "deleteCart",
			sessionId: sessionId
		},
		success: function(data){
			// 檢查購物車是否清空
			cart = [];
			getCart();
			console.log("cart: " + cart);
		}
	})
}




window.onload = init;
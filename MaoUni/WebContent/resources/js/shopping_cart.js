function init(){
	// ============== 測試用  ==============
	$(".fa-envelope").click(function(e){
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
	
	
	
	$(".addItem").click(function(e){
		$.ajax({
			url: "/MaoUni//shoppingcart.do",
			type: "POST",
			data: {
				action: "addItem",
				itemId: e.target.id,
				count: 1,
				sessionId: sessionId
			},
			success: function(data){
				let totalItems = $(".totalItems").text();
				totalItems++;
				
				$(".totalItems").text(totalItems);
			}
		})
	})
	

	
	
	$(".shoppingCart").click(function(e){
		getCart();	
	})
	
	
	$(".cartbody").click(function(e){
		console.log(e.target);
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
					let totalItems = $(".totalItems").text();
					let count = e.target.parentElement.parentElement.children[2].innerText;
					$(".totalItems").text(totalItems - count);
					
					e.target.parentElement.parentElement.remove();
				}
			})
		}
	})
}


function getCart(){
	$.ajax({
		url: "/MaoUni//shoppingcart.do",
		type: "GET",
		data: {
			action: "getCart",
			sessionId: sessionId
		},
		success: function(data){
			let objs = JSON.parse(data);
			let html = "";
			let totalItems = 0;
			
			for(let i = 0; i < objs.length; i++){
				let obj = objs[i];
				totalItems += obj.count;
				let itemSum = obj.count * obj.itemPrice;
				html += `<tr>
                            <td class="align-middle text-center">
                                <i id="${obj.itemId}" class="far fa-trash-alt deleteItem"></i>
                            </td>
                            <td class="align-middle">${obj.itemName}</td>
                            <td class="align-middle count">${obj.count}</td>
                            <td class="align-middle text-right">$ ${itemSum}</td>
                        </tr>`;
			}
			$(".cartbody").html(html);
			$(".totalItems").text(totalItems);
		}
	})
}










window.onload = init;
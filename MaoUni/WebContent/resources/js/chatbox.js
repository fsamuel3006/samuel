/*
 
What is the (function() { } )() construct in JavaScript?

It’s an Immediately-Invoked Function Expression, 
or IIFE for short. It executes immediately after it’s created.
It has nothing to do with any event-handler for any events (such as document.onload).
Consider the part within the first pair of parentheses: (function(){})();....it is a regular function expression. 
Then look at the last pair (function(){})();, this is normally added to an expression to call a function; 
in this case, our prior expression.
https://stackoverflow.com/questions/8228281/what-is-the-function-construct-in-javascript 

*/

(function(){
  
  let chat = {
    messageToSend: '',
    receiver: '' , 
    webSocket: null,
    self: '${userName}', // EL
    init: function() {
      this.cacheDOM(); 		// 抓取需要使用的DOM元素
      this.bindEvents();	// 監聽事件
      this.webSocketConnect();
      
    },
    cacheDOM: function() {
      this.$chatHistory = $('.chat-history');	
      this.$peopleList = $('.people-list');	
      this.$button = $('button');
      this.$textarea = $('#message-to-send');
      this.$chatHistoryList =  this.$chatHistory.find('ul'); // chat-history 下的 ul
      this.$friendsList =  this.$peopleList.find('ul'); 
    },
    bindEvents: function() {
    	console.log(this);
      this.$button.on('click', this.addMessage.bind(this));
      this.$textarea.on('keyup', this.addMessageEnter.bind(this));
      this.$peopleList.on('click', this.getReceiver.bind(this));
      $(window).on('unload', this.webSocketDisconnect.bind(this));
    },
    webSocketConnect: function(){
      let MyPoint = "/FriendWS/${userName}";	// EL
	  let host = window.location.host;
	  let path = window.location.pathname;
	  let webCtx = path.substring(0, path.indexOf('/', 1));
	  let endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
	  console.log("endPointURL: " + endPointURL);
		
	  webSocket = new WebSocket(endPointURL);
    	
	  // 開啟連線
	  WebSocket.onopen = function(e){
		  console.log("Connect Success!");
	  };
		  
	  // 收到訊息
	  webSocket.onmessage = function(e){
			let jsonObj = JSON.parse(e.data);
			if("open" === jsonObj.type){	// 有人上線時刷新好友列表
				refreshFriendList(jsonObj);
			}else if("history" === jsonObj.type){
				messagesArea.innerHTML = '';
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				let messages = JSON.parse(jsonObj.message);
				let sender = historyData.sender;
				for(let j = 0; j < messages.length; j++){
					let historyData = JSON.parse(messages[i]);
					let showMsg = histroyData.message;
					render(sender, showMsg);
				}
			}else if ("chat" === jsonObj.type) {
				let messages = JSON.parse(jsonObj.message);
				let sender = historyData.sender;
				render(sender, showMsg);
			}else if("close" === jsonObj.type){		// 有人離線時刷新好友列表
				refreshFriendList(jsonObj);
			}
		};
		
		webSocket.onclose = function(e){
			console.log("Disconnected!");
		};
    },
    render: function(sender, showMsg) {
      this.scrollToBottom();	// 畫面保持在訊息底部
      let template = Handlebars.compile( $("#message-template").html());
      let templateResponse = Handlebars.compile( $("#message-response-template").html());   // responses
      let context = { 
	          messageOutput: showMsg,
	          time: this.getCurrentTime()
	  	  };
      if (this.messageToSend.trim() !== '') {
    	  	sender === self ? this.$chatHistoryList.append(template(context)) : this.$chatHistoryList.append(templateResponse(contextResponse));
	        this.scrollToBottom();
	        this.$textarea.val('');
      }
    },
    refreshFriendList: function(jsonObj){  // 渲染好友列表
    	let friends = jsonObj.users;
    	let templateList = Handlebars.compile($("#list-template").html());
    	
    	for(let i = 0; i < friends.length; i++){
    		if(friends[i] === self){ continue }
    		let contextList = {
        			name: friends[i] // from redis 
//        			statusIcon: jsonObj.status // from redis
        	}
    		this.$friendsList.append(templateList(contextList));
    	}
    },
    getReceiver: function(e){				// 選取接收對象後取得歷史訊息
    	receiver = e.target.dataset.name;
    	let jsonObj = {
    			"type": "history",
    			"sender": self,
    			"receiver": receiver,
    			"message": ""
    		};
    	webSocket.send(JSON.stringify(jsonObj));
    },
    addMessage: function() {
      messageToSend = this.$textarea.val().trim();   
      if (messageToSend === "") {
			alert("Input a message");
			this.$textarea.focus();
		} else if (receiver === "") {
			alert("Choose a friend");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : receiver,
				"message" : messageToSend
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			this.$textarea.focus();
		}     
    },
    addMessageEnter: function(event) {
        // enter was pressed
        if (event.keyCode === 13) {
          this.addMessage();
        }
    },
    scrollToBottom: function() {
       this.$chatHistory.scrollTop(this.$chatHistory[0].scrollHeight);
    },
    getCurrentTime: function() {
      return new Date().toLocaleTimeString().
              replace(/([\d]+:[\d]{2})(:[\d]{2})(.*)/, "$1$3");
    },
    webSocketDisconnect: function(){
    	webSocket.close();
    }
 };
  
  chat.init();
  

  
//  var searchFilter = {
//    options: { valueNames: ['name'] },
//    init: function() {
//      var userList = new List('people-list', this.options);
//      var noItems = $('<li id="no-items-found">No items found</li>');
//      
//      userList.on('updated', function(list) {
//        if (list.matchingItems.length === 0) {
//          $(list.list).append(noItems);
//        } else {
//          noItems.detach();
//        }
//      });
//    }
//  };
  
//  searchFilter.init();
  
})();
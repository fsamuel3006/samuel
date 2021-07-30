<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-tw">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>Chat Box</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/reset.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/chatbox.css">
<script src="<%=request.getContextPath()%>/resources/js/fontawesome.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery_1.12.4.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/handlebars.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/list.min.js"></script>
</head>
<style>
.friend:hover {
	cursor: pointer;
}

.news {
	width: 13px;
	height: 5px;
	background: #E53A40;
	color: white;
	border-radius: 100%;
	padding: 5px;
	margin-left: 50px;
}
</style>
<body>
	<div class="container clearfix">
		<div class="people-list" id="people-list">
			<div class="search">
				<input type="text" placeholder="search" /> <i class="fa fa-search"></i>
			</div>
			<ul class="list">
				<!--       ============ 上線列表 ========== -->
			</ul>
		</div>
		<!--       ============================== -->
		<div class="chat">
			<div class="chat-header clearfix">
				<img
					src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/195612/chat_avatar_01_green.jpg"
					alt="avatar" />

				<div class="chat-about">
					<div class="chat-with"></div>
					<div class="chat-num-messages">start to chat with me!</div>
				</div>
				<i class="fa fa-star"></i>
			</div>
			<!-- end chat-header -->
			<div class="chat-history" id="messagesArea">
				<!--       ============ 訊息窗   ========== -->
				<ul>

				</ul>

			</div>
			<!-- end chat-history -->
			<!--       ============ 輸入欄   ========== -->
			<div class="chat-message clearfix">
				<textarea name="message-to-send" id="message-to-send"
					placeholder="Type your message" rows="3"></textarea>

				<i class="fa fa-file-o"></i> &nbsp;&nbsp;&nbsp; <i
					class="fa fa-file-image-o"></i>

				<button>Send</button>
			</div>
			<!-- end chat-message -->

		</div>
		<!-- end chat -->

	</div>
	<!-- end container -->

	<script id="message-template" type="text/x-handlebars-template">
  <li class="clearfix">
    <div class="message-data align-right">
      <span class="message-data-time" >{{timestamp}}</span> &nbsp; &nbsp;
      <span class="message-data-name" >{{name}}</span> <i class="fa fa-circle me"></i>
    </div>
    <div class="message other-message float-right">
      {{messageOutput}}
    </div>
  </li>
</script>

	<script id="message-response-template"
		type="text/x-handlebars-template">
  <li>
    <div class="message-data">
      <span class="message-data-name"><i class="fa fa-circle online"></i> {{name}}</span>
      <span class="message-data-time">{{timestamp}}</span>
    </div>
    <div class="message my-message">
      {{messageOutput}}
    </div>
  </li>
</script>

	<script id="list-template" type="text/x-handlebars-template">
 <li class="friend clearfix" data-name="{{name}}">
    <img class="friend" data-name="{{name}}" src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/195612/chat_avatar_01.jpg" alt="avatar" />
       <div class="about friend" data-name="{{name}}">
       		<div class="name friend" data-name="{{name}}">{{name}}</div>
      		<div class="status friend" data-name="{{name}}">
        		<i class="fa fa-circle friend online" data-name="{{name}}"></i>	online
      		</div>
       </div>
 </li>
</script>



	<script>
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
		let self = "${userName}"; // EL

		(function() {
			let chat = {
				messageToSend : '',
				receiver : '',
				webSocket : null,
				init : function() {
					this.cacheDOM(); // 抓取需要使用的DOM元素
					this.bindEvents(); // 監聽事件
					this.webSocketConnect();
				},
				cacheDOM : function() {
					this.$chatHistory = $('.chat-history');
					this.$peopleList = $('.people-list');
					this.$button = $('button');
					this.$textarea = $('#message-to-send');
					//       this.$friend =  $('.friend'); 
					this.$chatHistoryList = this.$chatHistory.find('ul'); // chat-history 下的 ul
					this.$friendsList = this.$peopleList.find('ul');
				},
				bindEvents : function() {
					console.log(this);
					this.$button.on('click', this.addMessage.bind(this));
					this.$textarea.on('keyup', this.addMessageEnter.bind(this));
					this.$friendsList.on('click', this.getReceiver.bind(this));
					$(window).on('unload', this.webSocketDisconnect.bind(this));
				},
				webSocketConnect : function() {
					let MyPoint = "/FriendWS/${userName}"; // EL
					let host = window.location.host;
					let path = window.location.pathname;
					let webCtx = path.substring(0, path.indexOf('/', 1));
					let endPointURL = "ws://" + window.location.host + webCtx
							+ MyPoint;
					console.log("endPointURL: " + endPointURL);

					webSocket = new WebSocket(endPointURL);

					// 開啟連線
					webSocket.onopen = function(e) {
						console.log("Connect Success!");
					};

					// 收到訊息
					webSocket.onmessage = function(e) {
						let jsonObj = JSON.parse(e.data);
						console.log(jsonObj)
						if ("open" === jsonObj.type) { // 有人上線時刷新好友列表
							refreshFriendList(jsonObj);
						} else if ("history" === jsonObj.type) {
							// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
							let messages = JSON.parse(jsonObj.message);

							for (let j = 0; j < messages.length; j++) {
								let historyData = JSON.parse(messages[j]);
								let sender = historyData.sender;
								let showMsg = historyData.message;
								let timestamp = historyData.timestamp;
								render(sender, showMsg, timestamp);
							}
						} else if ("chat" === jsonObj.type) {
							let messages = jsonObj.message;
							let sender = jsonObj.sender;
							let timestamp = chat.getCurrentTime();

							// check sender 是否為當前正在聊天的對象
							let chatTarget = $(".chat-with").text();

							if (sender === chatTarget || sender === self) {
								render(sender, messages, timestamp);
							} else {
								let unreadNum = jsonObj.unreadNum; // 非當前聊天對象
								// 跳出提示訊息
								prompNews(sender, unreadNum);

							}
						} else if ("close" === jsonObj.type) { // 有人離線時刷新好友列表
							refreshFriendList(jsonObj);
						}
					};

					webSocket.onclose = function(e) {
						console.log("Disconnected!");
					};

					function render(sender, showMsg, timestamp) {
						chat.scrollToBottom(); // 畫面保持在訊息底部
						let template = Handlebars
								.compile($("#message-template").html());
						let templateResponse = Handlebars.compile($(
								"#message-response-template").html()); // responses
						let context = {
							messageOutput : showMsg,
							timestamp : timestamp,
							name : sender
						};
						sender === self ? chat.$chatHistoryList
								.append(template(context))
								: chat.$chatHistoryList
										.append(templateResponse(context));
						chat.scrollToBottom();
						chat.$textarea.val('');
					}

					// 未讀提示
					function prompNews(sender, unreadNum) {
						let target = $(`li[data-name = \${sender}]`);

						if ($(`li[data-name = \${sender}] .news`).text() === "") {
							let text = "<span class = 'news' data-name = " + sender + ">"
									+ unreadNum + "</span>";
							console.log("yes");
							target.append(text);
						} else {
							console.log("no");
							$(`span[data-name = \${sender}]`).text(unreadNum);
						}
						chat.$textarea.val();
					}

					// 渲染好友列表
					function refreshFriendList(jsonObj) {
						let friends = jsonObj.users;
						let templateList = Handlebars.compile($(
								"#list-template").html());
						chat.$friendsList.html("");

						for (let i = 0; i < friends.length; i++) {
							if (friends[i] === self) {
								continue
							}
							;
							let contextList = {
								name : friends[i]
							// from redis 
							};
							chat.$friendsList.append(templateList(contextList));
						}

					}
				},
				getReceiver : function(e) { // 選取接收對象後取得歷史訊息
					if (!e.target.tagName === "friend") {
						return
					};
					chat.receiver = e.target.dataset.name;
					chat.$chatHistoryList.html("");
					$(`li[data-name = \${chat.receiver}] .news`).remove(); // 並清空未讀提示
					$(".chat-with").text(chat.receiver);
					let jsonObj = {
						"type" : "history",
						"sender" : self,
						"receiver" : chat.receiver,
						"message" : "",
						"timestamp" : "",
						"unreadNum" : ""
					};
					webSocket.send(JSON.stringify(jsonObj));
				},
				addMessage : function() {
					messageToSend = this.$textarea.val().trim();
					console.log(messageToSend);
					if (messageToSend === "") {
						alert("Input a message");
						this.$textarea.focus();
					} else if (this.receiver === "") {
						alert("Choose a friend");
					} else {
						var jsonObj = {
							"type" : "chat",
							"sender" : self,
							"receiver" : this.receiver,
							"message" : messageToSend,
							"timestamp" : chat.getCurrentTime(),
						};
						console.log(JSON.stringify(jsonObj))
						webSocket.send(JSON.stringify(jsonObj));
						this.$textarea.value = "";
						this.$textarea.focus();
					}
				},
				addMessageEnter : function(event) {
					// enter was pressed
					if (event.keyCode === 13) {
						this.addMessage();
					}
				},
				scrollToBottom : function() {
					this.$chatHistory
							.scrollTop(this.$chatHistory[0].scrollHeight);
				},
				getCurrentTime : function() {
					let now = new Date()
					let t = now.toLocaleTimeString().replace(
							/([\d]+:[\d]{2})(:[\d]{2})(.*)/, "$1$3");
					let y = now.getFullYear();
					let m = now.getMonth() + 1;
					let d = now.getDate();
					let str = y + "/" + m + "/" + d + "   " + t;

					return str;
				},
				webSocketDisconnect : function() {
					webSocket.close();
				}
			};

			chat.init();

		})();
	</script>


	<!-- partial -->


	<%-- <script  src="<%= request.getContextPath() %>/resources/js/chatbox.js"></script> --%>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF8"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); 
%>    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@900&display=swap" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.5, minimum-scale=0.5, user-scalable=no">
<meta name = "viewport" content ="width=device-width,initial-scale-1.0">
    <!-- <script src="JavaScript 檔案位址.js"></script> -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/memberpage.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"> <!-- 左邊這個是右上角購物車下拉式選單使用的BootStrap CSS -->  
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/styleD.css"> <!-- 左邊這個是右上角購物車下拉式選單使用的CSS,有再調過細部效果 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/fontawesome-all.min.css"> <!-- 左邊這個是右上角聊天室ICON的引入 -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">  <!-- 左邊這個是右上角會員/信箱/購物車/搜尋ICON使用的fontawesomeCDN,使用靜態檔會跑掉 -->
	<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
	<link rel="stylesheet" href="<%= request.getContextPath()%>/resources/css/sweetalert2.css"> 
	<script src="<%= request.getContextPath() %>/resources/js/sweetalert2.js"></script>
	
<title>Insert title here</title>


<style>

List-style-type ：none/* http://meyerweb.com/eric/tools/css/reset/
v2.0 | 20110126
License: none (public domain)

html, body, div, span, applet, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, abbr, acronym, address, big, cite, code,
del, dfn, em, img, ins, kbd, q, s, samp,
small, strike, strong, sub, sup, tt, var,
b, u, i, center,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, embed,
figure, figcaption, footer, header, hgroup, 
menu, nav, output, ruby, section, summary,
time, mark, audio, video {
    margin: 0;
    padding: 0;
    border: 0;
    font-size: 100%;
    font: inherit;
    vertical-align: baseline;
}
/* HTML5 display-role reset for older browsers */

article, aside, details, figcaption, figure, header, hgroup, menu, nav, section {
    display: block;
}


body {
    font-size: 18px;
    color: rgb(255, 255, 255);
    font-family: "Microsoft JhengHei";
    margin:0;
    display: grid;
}

ol, ul {
    list-style: none;
}

blockquote, q {
    quotes: none;
}

blockquote:before, blockquote:after,
q:before, q:after {
    content: '';
    content: none;
}

table {
    border-collapse: collapse;
    border-spacing: 0;
}

*{

box-sizing: border-box;
margin: 0px;
padding: 0px;
}


.Top-Flex{ /*這就是上面那條跑來跑去的最開始的底*/

position: fixed;
background-color:#c19e74;
font-weight: bold;
padding: 0 50px;
height:170px;
width:100%;
display: flex;
align-items: top;
border-radius: 0px 0px 30px 30px;
z-index:2;

}

header{

    position: fixed;
    top:0px;
    left:0px;
    width: 100%;
    height: 215px;
    display: flex;
    justify-content: flex-end;
    transition: 1s;
    z-index:1;
}

header.sticky .Top-Flex{ /* 這是下拉後的Top-Flex寬度跟顯示 */

    height: 100px;
    transition: 2.5s;
    }

.LOOP{

    display: flex;
    margin-left: 440px;
    margin-top: 3px;
 /*大logo因為看起來沒置中所以是用這個去調到置中的 */

	
}

header.sticky .two img{

    width: 120px;
    margin-left: 100px; /*小logo用這個移動*/

}

.two img{ /*縮小上頭logo時用*/

    width:300px;
    transition: 1s;
}

/* ------icon區的css------- */

.icontrue{

display: flex;
z-index:5;
margin-right:5px;

}

.iconbth{ /*給icon間距*/

margin: 7px;
z-index:5;
}

.icontrue img{ /*icontrue下的img全縮小*/

    width: 20px;
    border-radius: 4px solid #fff;
    color:#c19e74;
    z-index:5; /*這個一定要加不然會把上下滑動蓋掉，且無法hover差點氣死*/
}

.iconbth:hover{ /*移到icon上時會有半透明的效果*/

    opacity: 0.5;
    z-index:5;
}


.iconbth1{ /*給icon間距*/

	margin: 7px;
	z-index:0;
}

.iconbth1:hover{ /*移到icon上時會有半透明的效果*/

    opacity: 0.5;
    z-index:0;
}


/* ------icon區的css結束------- */

.shopbody{ /*有你來玩那排的導覽列*/

    position: fixed;
    display: flex;
    font-weight:bold;
    font-size: 13px;
    width: 100%;
    justify-content: center; /*這整排置中*/
    align-items: center; /*讓字在中間的位子*/
    min-height: 50vh; /* 這一導覽列的高度 */
    z-index:2;
}


header.sticky .shopbody{

    min-height: 25vh; 
    transition: 2s;
}

nav{

    position: relative;
    display: flex;
}

nav a{

    margin: 0 20px; 
    /* 字跟字之間的寬度 */
    color:white ;
    text-decoration: none; /* 去掉超連結的底線 */
    height: 5px;
    width: 100px;
    /* padding-top: 0px; */
    
}

/*有你來玩那排的導覽列結束*/
/*只要top的話只需複製上面這些區域*/


.SearchSorting{

    align-items: center;
    justify-content: center;
    margin: auto;
    border:3px solid #c19063;
    background-color:#fff;
    width: 55%;   /*%數越高，搜尋列的外框越寬 */
    height: 45px;
    color:#666;
    border-style:solid;
    display: flex;
    border-radius: 10px;
    
}

.SearchSorting select{


    margin: -20px 18px;
    padding: auto 5px;
    font-size: 14px;

}







.sliderbody{

    overflow:hidden;

}



.rs{

/* 圓點點的移動設定 */
width: 500%;
height:500px;
display: flex;

}



.rs{ 

/* 隱藏最原本的"圓點點"，讓它不會出現及影響版面，不然會推動整個版面*/
    display: none;
    
}



.navigation-manual{

    /* 讓真正有實際功效的圓點點上移 */
    position: absolute;
    width: 800px;
    margin-top: -40px;
    display: flex;
    justify-content: center; 
    
}

.manual-btn{

    border:2px solid #FFF3DE;
    padding: 5px;
    border-radius: 10px;
    cursor: pointer;
    transition: 1s;
    
}

.manual-btn:not(:last-child){

    margin-right: 40px; /*圓點點的間距*/
    
}

.manual-btn:hover{
    background:#FFF3DE;
}

#radio1:checked~ .first{

    margin-left: 0%;

}

#radio2:checked~ .first{

    margin-left: -20%;

}

#radio3:checked~ .first{

    margin-left: -40%;

}

#radio4:checked~ .first{

    margin-left: -60%;

}

/* css for auyomatic navigation css的導航（導航圖片） */

.navigation-auto{

    position: absolute;
    display: flex;
    width: 800px;
    justify-content: center;
    margin-top: 460px;
}

.navigation-auto div{

    /* navigation-auto下的全部div */
    border: 2px solid #FFF3DE;
    padding: 5px;
    border-radius: 10px;
    transition: 1s;
}

.navigation-auto div:not(:last-child){

    margin-right:40px; 
    
}

#radio1:checked~ .navigation-auto .auto-btn1{

    background: #FFF3DE;

}

#radio2:checked~ .navigation-auto .auto-btn2{

    background: #FFF3DE;

}

#radio3:checked~ .navigation-auto .auto-btn3{

    background: #FFF3DE;

}

#radio4:checked~ .navigation-auto .auto-btn4{

    background: #FFF3DE;

}

<!-- 上方為思瑤版本CSS -->
*{

    padding:0;
    margin: 0;
    box-sizing: border-box;
    font-family:"poppins";
    
    }
    /* 
    body{
    
    
    
    } */

.testtest{

    display: flex;
    justify-content: space-between;
    color: white;

}    


h5{

color:#c29e74 ;

}


.test2{

    margin-top: 30px;
    display: flex;
    justify-content: space-between;
    color: white;

}

.add{

margin-top: 20px;
color: white;

}

.memberupadatebutton{

margin-left: 80%;
margin-bottom: 20px;

}

.memberupadatebutton button{


    color: white;
    background-color: #c29e74;
    border:none;
    outline: none;
    border-radius: 3px;
    font-size: 14px;
    padding: 7px 20px;
    font-weight: 500px;
    font-family: monospace;
    transition-duration:1s;  

}

.memberleft{

display: flex;
flex-direction: column;
/* 讓文字變直的 */
width: 100px;
justify-content: flex-end;
align-items: center;
margin-top: 40%;
margin-left: 5%;

}

.memberleft button{

    color: white;
    background-color: #c29e74;
    border:none;
    outline: none;
    border-radius: 3px;
    font-size: 14px;
    padding: 7px 20px;
    font-weight: 500px;
    font-family: monospace;
    transition-duration:1s;  
    margin-bottom: 7px; 

}

.memberleft button:hover{
    
    font-weight:bold;
    color:#563F2E;


}

/* yao的page */

*{

    padding:0;
    margin: 0;
    box-sizing: border-box;
    font-family:"poppins";
    
    }

    
    div.membercontainer{
    
        position: absolute;
        top: 120%;
        left: 50%;
        transform: translate(-50%,-50%);
        display: flex;
        flex-direction: row;
        align-items: center;
        background-color: #FFF7E8;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 50px 60px -50px #563F2E;
        z-index: -1;
    
    }
    
    div.membercontainer div.switchform{
    
        width: 600px;
        height: 400px;
        border-radius: 3px;
        margin-right: 30px;
        z-index: -1;
    }
    
    div.membercontainer div.switchform h2{
    
    color:#c29e74;
    margin-bottom: 50px;
    font-family: monospace;
    font-size: 25px;
    
    }
    
.membertext{


    color:#c29e74;
    font-family: monospace;
    text-align:center;
    margin-left: 50px;
}

    div.membercontainer div.switchform input{
    
    border:none;
    outline: none;
    border-radius: 3px;
    background-color: #FFF7E8;
    width: 100%;
    border-bottom:2px solid #563F2E;
    margin-bottom: 25px;
    padding: 7px 0;
    border-radius: 3px;
    font-size: 14px;
    transition-duration:1s;
    }
    
 .bt{
    
    color: white;
    background-color: #c29e74;
    border:none;
    outline: none;
    border-radius: 3px;
    font-size: 14px;
    padding: 7px 20px;
    font-weight: 500px;
    font-family: monospace;
    transition-duration:1s;
    align-items: flex-end;
    }

    
   .bt:hover{
    
        font-weight:bold;
        color:#563F2E;
        font-weight:bold;
    
    }
    
    .bt:active{
        opacity: 0.8;
        background-color:#FFF7E8;
    }
    
    div.image img{
    
    z-index: 1;
    width: 290px;
    border-radius: 5px;
    margin-left: 53%;
    margin-bottom:200px;

    }
    
    .memberswitch{
        display: flex;
        flex-direction: column;
        /* 讓文字變直的 */
        width: 100px;
        justify-content: flex-end;
        align-items: center;
        margin-top: 40%;
        margin-left: 5%;
        border-right-style:double;
        border-color: #c5945b;
 
        }
        
a {
            text-decoration:none;
            margin: 5px;
            color: #c29e74;
            font-weight:bold;
}

a:hover{

    color:#865d2e;
    text-decoration:none;
}


/* yao的page結束 */    
/* 以下為FOOTER CSS */


.footer {
margin: 0;
display: flex;
flex-flow: row wrap;
padding: 50px;
color: #fff;
background-color: #c19e74;
font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
font-size: 14px;
position: relative;
margin-bottom: 10px;
}

.footer > * {
flex:  1 100%;
}


h2 {
font-weight: 1000;
font-size: 35px;

}

.footer ul {
list-style: none;
padding-left: 0;
}

.footer li {
line-height: 2em;
}

.footer a {
text-decoration: none;
}



.r-footer {
display: flex;
text-align: center;
margin-left: -15px;
}

.box{
	margin-top:10px;
}

.box a {
color: #fff;
}

.h-box {
column-count: 2;
column-gap: 1.25em;
}

.b-footer {
text-align: center;
color: #a0591f;
font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
width: 100%;
background-color: #fff;

}



@media screen and (min-width: 600px) {
	.r-footer > * {
	flex: 1;
	}
	
	.features {
	flex-grow: 2;
	}
	
	
	.DDM > * {
	flex: 1;
	}
}


::placeholder{
	color: #a0591f;
}

:placeholder-shown{
	text-decoration: none;
	border: 1px solid #b89063;
}
:placeholder-shown:hover{
	border-color: #b89063;
}
 
.ff1:hover{
	color:#f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff2:hover{
	color:#f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff3:hover{
	color:#f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff4:hover{
	color:#f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff5:hover{
	color:#f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff6:hover{
	color:#f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff7:hover{
	color:#f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff8:hover{
	color:#f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff9:hover{
	color:#f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff10:hover{
	color:#f8e682;
	font-weight: 500;
	font-size: 15px;
}

.ff11:hover{
	color:#f8e682;
	font-weight: 500;
	font-size: 15px;
}
 


.btn-primary {
    color: #fff;
    background-color: #d4af81;
    border-color: #d4af81;
}

.btn-primary:hover{
    background-color: #b89063;
    border-color: #b89063;
}

#igbtn:hover{
	color:#f8e682;

	font-size: 15px;
}

#fbbtn:hover{
	color:#f8e682;

	font-size: 15px;
}

.fa-trash-alt{
	color: #a0591f;
}

.fa-trash-alt:hover{
	color: #b89063;
}

.fa-instagram:hover{
	color:#f8e682;
	font-weight: 1000;
	font-size: 20px;
}

.fa-facebook-square:hover{
	color:#f8e682;
	font-weight: 1000;
	font-size: 20px;
}

</style>    
</head>



<!-- 以下為BODY開始 -->




<body>

	<header>
	
	    <div class="icontrue" style="margin: 10px 10px 0px 0px;">
		
		  <a href="<%=request.getContextPath()%>/front-end/member/memberpage.jsp" class="iconbth"><i class="fas fa-user fa-1x" style="color:white;font-size:25px;"></i></a>
		  <a href="<%=request.getContextPath()%>/front-end/chatbox/chatbox.jsp" class="iconbth"><i class="fa fa-comments fa-1x" style="color:white;font-size:28px;padding:2px;margin-top:-4px;"></i></a>
		  <a href="#" class="iconbth"><i class="fas fa-envelope fa-1x" style="color:white;font-size:25px;margin-right:-4px;"></i></a>
	    
	      <div class="dropdown ml-auto">
           
            <button class="btn btn-cart btn-sm" type="button" id="dropdownMenuButton" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false" >
                <i class="fas fa-shopping-cart fa-2x shoppingCart" style="color:#fff;"></i>
                <span class="badge badge-pill badge-danger">9</span>
            </button>
           
            <div class="dropdown-menu dropdown-menu-right" style="min-width: 300px" aria-labelledby="dropdownMenuButton">
                <div class="p-3">
                    <table class="table table-sm">
                        <h6>已選擇商品</h6>
                        <tbody style="color:black;">
                            <tr>
                                <td class="align-middle text-center">
                                    <a href="#removeModal" data-price="1999" data-title="不求人" data-toggle="modal"
                                        data-target="#removeModal"><i class="far fa-trash-alt"></i></a>
                                </td>
                                <td class="align-middle">不求人</td>
                                <td class="align-middle">1件</td>
                                <td class="align-middle text-right">$1999</td>
                            </tr>
                            <tr>
                                <td class="align-middle text-center">
                                    <a href="#removeModal" data-price="999" data-title="一級偽裝帽" data-toggle="modal"
                                        data-target="#removeModal"><i class="far fa-trash-alt "></i></a>
                                </td>
                                <td class="align-middle">一級偽裝帽</td>
                                <td class="align-middle">1件</td>
                                <td class="align-middle text-right">$999</td>
                            </tr>
                        </tbody>
                    </table>
                    <a href="#" class="btn btn-block btn-primary btn-sm text-white">確認結帳</a>
                </div>
            </div>
        </div>
	   
	        <a href="#" class="iconbth"><i class="fas fa-search fa-1x" style="color:white;font-size:25px;"></i></a>	        	        	
	   
	    </div>
	    
		<!--icon一定要在top-flex之上，不然會被移動條給蓋爆777777-->
	
		<div class="Top-Flex">
			<div class="LOOP">
			    	<a class="two" href="<%=request.getContextPath()%>/back-end/listAllItem">
			    	<img class="img-responsive" src="<%=request.getContextPath()%>/resources/images/Logo/MaoUni2.png" width="" alt="">
			    	</a>
			</div>
		</div>
		
		<div class="shopbody">
		    <nav style="margin-bottom:-15px;margin-left:15px;">
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/home/HomePage.jsp" style="font-size:15px;">首　頁</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/shop/shopping_home.jsp" style="font-size:15px;">有你來買</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/forumPost/forumPost_home.jsp" style="font-size:15px;">有你來講</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/member/grooming_home.jsp" style="font-size:15px;">到府美容</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/adopt/adopt_home.jsp" style="font-size:15px;">浪浪找家</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/article/listAllArt_f.jsp" style="font-size:15px;">知識站</a></p>
			       <p class="hover-underline-animation pb-0"><a href="<%= request.getContextPath() %>/front-end/announcemet/listAllAnnf.jsp" style="font-size:15px;">公告</a></p>
			 	</nav>
		</div>
		<div class="Shopping-ul"></div>
	    
	</header>
    

    <!--以上為頭部的html設定位子-->

	<script>
    // 此處是縮放導覽列的動態效果，這段請一定要抓到，不然它不會動
    //滑鼠滾動(scroll)就開始觸發
    window.addEventListener("scroll",function(){

    const header = document.querySelector('header');
    header.classList.toggle('sticky',window.scrollY > 0);
    });
    
	</script>



	<!--滑動top請抓以上為止-->		
		<!-- 以下這個範圍只是為了拉長版面顯示可實作的範圍,可直接在這邊開始做你的版面摟! -->
		
 <div class="memberswitch">
        <a href="<%=request.getContextPath()%>/front-end/member/memberpage.jsp">會員資料</a>
        <a href="<%=request.getContextPath()%>/front-end/member/petpage.jsp">毛孩資料</a>
        <a href="#">訂單紀錄</a>
        <a href="<%=request.getContextPath()%>/front-end/member/grooming_appointment_manage.jsp">預約紀錄</a>
        <a href="/CFA101G6/member/member.do?action=OutUser">登出</a>
</div>

    <div class="membercontainer">
    <form class="form" id="form">
    <div class="switchform">

            <h2>會員資料</h2>
            
            <div class="membertext">
                <table>
             
                <tr>
                <td>快樂肥宅水</td>
                </tr>
            
                <tr>
                <td>hk1345@hotmail.com</td>   
                </tr>
            
                <tr>
                <td>女性</td>
            </tr>
                <td>1999-01-02</td>   
            </tr>
            <tr>
                <td>A1113982337</td>   
                </tr>
             
                <tr>
                <td>0912907998</td>     
                </tr>
                <tr>
                <td>桃園市平鎮區忠孝路106號</td>    
                </tr>
            </div>
            </table>
        </div>
</div>
            <button class="bt" type="submit">Update</button>
            <button class="bt" type="submit">Password</button>
</div>

<div class="image">
  <img src="${pageContext.request.contextPath}/resources/images/valeriia.jpg" width="300" height="399.8"/> 
</div>


	
<%-- <input class=update type="button" onclick="location.href='<%=request.getContextPath()%>/front-end/Home/HomePage.jsp'" value="回首頁" style="border:5px;border-radius:5px;">		 --%>
		<div class="textttt" style=" width: 100%;height: 2000px;">	
		</div>
	
	<!-- 以下為底部FOOTER -->
	<footer class="footer">
        <ul class="r-footer">
	        <li>
	            <h2>About</h2>
		        <ul class="box">
			        <li><a class="ff1" href="#">品牌故事</a></li>
			        <li><a class="ff2" href="#">最新文章</a></li>
			        <li><a class="ff3" href="#">公告</a></li>
			        <li><a class="ff4" href="#">FAQ</a></li>
		        </ul>
	        </li>
	
	        <li>
	            <h2>Service</h2>
	            <ul class="box">
		            <li><a class="ff5" href="#">隱私權政策</a></li>
		            <li><a class="ff6" href="#">退換貨政策</a></li>
		            <li><a class="ff7" href="#">付款相關問題</a></li>
		            <li><a class="ff8" href="#">運算相關問題</a></li>
	            </ul>
	        </li>
	
	        <li>
		        <h2>Contact</h2>
		        <ul class="box">
			        <li><a class="ff9" href="#">客服信箱</a></li>
			        <li><a class="ff10" href="#">LINE客服</a></li>
			        <li><a class="ff11" href="#">聯絡電話</a></li>
		        </ul>
	        </li>

	        <li>
	            <h2>Follow</h2>
		        <ul class="box">	        	
		        	<li class="ig"><a class="btn btn-primary" style="color:white;font-size:14px;cursor: pointer;background:none;border:none;"><span class="fab fa-instagram"></span>&nbsp;毛孩有你</a></li>
		        	<li class="fb"><a class="btn btn-primary" style="color:white;font-size:16px;margin-top:-2px;cursor: pointer;background:none;border:none;"><span class="fab fa-facebook-square"></span>&nbsp;MaoUni</a></li>
		        </ul>
	        </li>        
        </ul>
	</footer>
	
    <div class="b-footer">
    <p style="margin-bottom: 10px;">All rights reserved by ©MaoUni 2021 </p>
    </div>	
	<!-- 以上為底部FOOTER -->
	
	
	
	
	<!-- 以下為JS引入 -->
	<script src="<%=request.getContextPath()%>/resources/js/cmain.js"></script>

	<!-- 以下為CDN引入 -->
	<script src="<%= request.getContextPath() %>/resources/js/jquery_1.12.4.min.js"></script>
    <script src="<%= request.getContextPath() %>/resources/js/popper.min.js"></script>
    <script src="<%= request.getContextPath() %>/resources/js/bootstrap.min.js"></script>
	
	
	<!-- 以下為購物車script程式碼,不可刪除!!! 功能會跑掉!!!! -->
    <script>
        $(document).ready(function () {
            $('#removeModal').on('show.bs.modal', function (event) {
                var btn = $(event.relatedTarget);
                var title = btn.data('title');
                var price = btn.data('price');
                var modal = $(this);
                modal.find('.modal-title').text('確認刪除 ' + title);
                modal.find('.modal-body').text('您要刪除 ' + title + ' $ ' + price + '元 嗎?');
            });
        });
    </script>

<script>

	const sessionId = "${sessionId}";
</script>
<script src="<%= request.getContextPath() %>/resources/js/shopping_cart.js"></script>
    
</body>
</html>

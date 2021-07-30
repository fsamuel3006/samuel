<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Appointment Manage</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/tooplate-gymso-style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />    

<script src="https://kit.fontawesome.com/0f58fd6ae7.js" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
<style>


</style>
</head>

<body>
	<!-- MENU BAR -->
    <nav class="navbar navbar-expand-lg fixed-top">
        <div class="container">

            <a class="navbar-brand" href="index.html">美容服務管理</a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-lg-auto">
                    <li class="nav-item">
                        <a href="<%= request.getContextPath() %>/front-end/member/grooming_groomerInfo.jsp" class="nav-link smoothScroll">個人主頁</a>
                    </li>

                    <li class="nav-item">
                        <a href="<%= request.getContextPath() %>/front-end/groomer/groomer_appointment_manage.jsp" class="nav-link smoothScroll">預約單管理</a>
                    </li>

                    <li class="nav-item">
                        <a href="<%= request.getContextPath() %>/front-end/groomer/groomer_schedule.jsp" class="nav-link smoothScroll">班表</a>
                    </li>
                </ul>

<!--                 <ul class="social-icon ml-lg-3"> -->
<!--                     <li><a href="https://fb.com/tooplate" class="fa fa-facebook"></a></li> -->
<!--                     <li><a href="#" class="fa fa-twitter"></a></li> -->
<!--                     <li><a href="#" class="fa fa-instagram"></a></li> -->
<!--                 </ul> -->
            </div>

        </div>
    </nav>
	<main>
		<c:if test="${not empty errorMsgs}">
			<c:forEach var="message" items="${errorMsgs}">
				<p style="color: red">${message}</p>
			</c:forEach>

		</c:if>


<!------------------------------------------------------ SCHEDULE start ------------------------------------------------>
    <section class="schedule section" id="schedule">
        <div class="container">
            <div class="row">

                <div class="col-lg-12 col-12 text-center">
                    <!-- <h6 data-aos="fade-up">our weekly GYM schedules</h6> -->

                    <h2 class="text-white" data-aos="fade-up" data-aos-delay="200">預約行程</h2>
                </div>

                <div class="col-lg-12 py-5 col-md-12 col-12">
                    <div class="guide"><span>待審核</span></div>
                    <table class="table table-bordered table-responsive schedule-table" data-aos="fade-up"
                        data-aos-delay="300">

                        <thead class="thead-light">
                            <th>一</th>
                            <th>二</th>
                            <th>三</th>
                            <th>四</th>
                            <th>五</th>
                            <th>六</th>
                            <th>日</th>
                        </thead>

                        <tbody>
                            <tr>
                                <td>
                                    <div class="date"><strong>1</strong></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>2</strong></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>3</strong></div>
                                </td>
                                <td>
                                    <div class="date"><strong>4</strong></div>
                                    <strong>休</strong>
                                </td>
                                <td>
                                    <div class="date"><strong>5</strong></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>6</strong></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>7</strong></div>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <div class="date"><strong>8</strong></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>9</strong></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>10</strong></div>
                                </td>
                                <td>
                                    <div class="date"><strong>11</strong></div>
                                    <strong>休</strong>
                                </td>
                                <td>
                                    <div class="date"><strong>12</strong></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>13</strong></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>15</strong></div>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <div class="date"><strong>16</strong></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>17</strong></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>18</strong></div>
                                    <strong>休</strong>
                                </td>
                                <td>
                                    <div class="date"><strong>19</strong></div>

                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>20</strong></div>
                                    <div class="apmId pending"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId pending"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>21</strong></div>

                                </td>
                                <td>
                                    <div class="date"><strong>22</strong></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId pending"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId pending"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <div class="date"><strong>23</strong></div>
                                    <div class="apmId pending"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>24</strong></div>
                                    <div class="apmId pending"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId pending"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>25</strong></div>
                                    <div class="apmId pending"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId pending"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId pending"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>26</strong></div>
                                    <strong>休</strong>
                                </td>
                                <td>
                                    <div class="date"><strong>27</strong></div>

                                </td>
                                <td>
                                    <div class="date"><strong>28</strong></div>
                                    <div class="apmId pending"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId pending"><span>07:00 - 09:00 </span></div>                                    
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong>29</strong></div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="date"><strong>30</strong></div>
                                    <strong>休</strong>
                                </td>
                                <td>
                                    <div class="date"><strong>31</strong></div>
                                    <div class="apmId pending"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId pending"><span>07:00 - 09:00 </span></div>
                                    <div class="apmId"><span>07:00 - 09:00 </span></div>
                                </td>
                                <td>
                                    <div class="date"><strong></strong></div>

                                </td>
                                <td>
                                    <div class="date"><strong></strong></div>
                                </td>
                                <td>
                                    <div class="date"><strong></strong></div>

                                </td>
                                <td>
                                    <div class="date"><strong></strong></div>

                                </td>
                                <td>
                                    <div class="date"><strong></strong></div>
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </section>	
<!------------------------------------------------------ SCHEDULE end ------------------------------------------------>




























	
<!-- Modal 暫無用到-->
					<div class="modal fade" id="showManage" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
					  <div class="modal-dialog modal-dialog-centered" role="document">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="exampleModalCenterTitle">預約單管理</h5>
					        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					          <span aria-hidden="true">&times;</span>
					        </button>
					      </div>
					      <div class="modal-body">
					        ...
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-secondary refuse">拒絕</button>
<!-- 					        ajax動態更換 -->
<!-- 					        <button type="button" class="btn btn-secondary cancel" data-dismiss="modal">取消</button> -->
					        <button type="button" class="btn btn-primary accept">接受</button>
					        <input type="hidden" class="apmManage" name="apmId" value="">
					      </div>
					    </div>
					  </div>
					</div>
	</main>
	<footer></footer>
	

	<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
		integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
		crossorigin="anonymous"></script>

</body>
</html>
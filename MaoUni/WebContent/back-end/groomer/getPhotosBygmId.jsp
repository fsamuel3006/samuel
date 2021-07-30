<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.groomer.model.*"%>
<%@ page import="java.io.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container-fluid">
		<div class="row mb-3">
			<h5>美容師證書</h5>
			<img class="certifyimg license justify-content-xl-center"
				src="data:image/jpeg;base64, ${photos.license}" alt="no file">
		</div>

		<div class="row mb-3">
			<h5>良民證</h5>
			<img class="certifyimg" pcrc
				src="data:image/jpeg;base64, ${photos.pcrc}" alt="no file">
		</div>

		<div class="row mb-3">
			<h5>身分證(正面)</h5>
			<img class="certifyimg" fid
				src="data:image/jpeg;base64, ${photos.fid}" alt="no file">
		</div>

		<div class="row mb-3">
			<h5>身分證(反面)</h5>
			<img class="certifyimg" bid
				src="data:image/jpeg;base64, ${photos.bid}" alt="no file">
		</div>
	</div>
</body>
</html>
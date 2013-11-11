
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DreamHost | Home</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
</head>

<style>
.carousel-inner {
	height: 500px;
}

.item-img {
	margin-left: auto;
	margin-right: auto;
	min-height: 500px;
	max-height: 500px;
}
</style>

<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="main-body">
		<div class="container">
			<div class="panel panel-default main">
				<div class="panel-body">
					<center>
						<div class="row" style="margin-left: auto; margin-right: auto;">
							<img src="res/img/back.png" />
						</div>
					</center>
					<div class="panel-footer">
						<div class="row">
							<div class="col-lg-4">
								<h2>Files preview</h2>
								<p>You can view files before download. We support images,
									audio and video preview.</p>
							</div>
							<div class="col-lg-4">
								<h2>Intuitive interface</h2>
								<p>With our drag and drop technology you can easy manage
									files. Its look like in you OS.</p>
							</div>
							<div class="col-lg-4">
								<h2>Reasonable prices</h2>
								<p>You pay only for space that your use. We have different
									tariffs for you needs. Always you can change you choose with
									saving money.</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
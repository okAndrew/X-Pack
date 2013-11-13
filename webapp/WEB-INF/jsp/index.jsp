
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
								<h2>
									<fmt:message key="Files_preview" bundle="${lang}" />
								</h2>
								<p>
									<fmt:message
										key="You_can_view_files_before_download._We_support_images,_audio_and_video_preview."
										bundle="${lang}" />
								</p>
							</div>
							<div class="col-lg-4">
								<h2>
									<fmt:message key="Intuitive_interface" bundle="${lang}" />
								</h2>
								<p>
									<fmt:message
										key="With_our_drag_and_drop_technology_you_can_easy_manage_files._Its_look_like_in_you_OS."
										bundle="${lang}" />
								</p>
							</div>
							<div class="col-lg-4">
								<h2>
									<fmt:message key="Reasonable_prices" bundle="${lang}" />
								</h2>
								<p>
									<fmt:message
										key="You_pay_only_for_space_that_your_use._We_have_different_tariffs_for_you_needs._Always_you_can_change_you_choose_with_saving_money."
										bundle="${lang}" />
								</p>
								<p>
									<a
										href="https://docs.google.com/document/d/1CyTUTKSyt-FXI0g_afYl5MjQlGNLXUuRiMC8UvSa3Ig/edit?usp=sharing">Get
										Dreamhost API doc</a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
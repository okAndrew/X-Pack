
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
	<div id="carousel-home-generic" class="carousel slide"
		data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-home-generic" data-slide-to="0"
				class="active"></li>
			<li data-target="#carousel-home-generic" data-slide-to="1"></li>
			<li data-target="#carousel-home-generic" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner">
			<div class="item active">
				<img class="item-img" src="res/img/home/1.png" alt="...">
				<div class="carousel-caption">
					<h3>Best file hosting in the world</h3>
					<p>free space... data privacy... low prices...</p>
				</div>
			</div>
			<div class="item">
				<img class="item-img" src="res/img/home/2.jpg" alt="...">
				<div class="carousel-caption">
					<h3>Easy file sharing</h3>
					<p>Upload, set public and share link for file download</p>
				</div>
			</div>
			<div class="item">
				<img class="item-img" src="res/img/home/3.jpg" alt="...">
				<div class="carousel-caption">
					<h3>Easy files browsing</h3>
					<p></p>
				</div>
			</div>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-home-generic"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"></span>
		</a> <a class="right carousel-control" href="#carousel-home-generic"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right"></span>
		</a>
	</div>
	<div class="main-body">
		<div class="container">
			<div class="row">
				<div class="col-lg-4">
					<h2>Web Hosting</h2>
					<p>Cost-effective web hosting for WordPress bloggers, website
						designers, e-commerce and small business.</p>
					<p>
						<a class="btn btn-default" href="#">View details &raquo;</a>
					</p>
				</div>
				<div class="col-lg-4">
					<h2>Dedicated Hosting</h2>
					<p>>High-performance, business class web hosting services that
						are ideal for enterprise or advanced users.</p>
					<p>
						<a class="btn btn-default" href="#">View details &raquo;</a>
					</p>
				</div>
				<div class="col-lg-4">
					<h2>Virtual Private Server</h2>
					<p>Web hosting resources allocated only to you, for improved
						performance and scalability as your website grows.</p>
					<p>
						<a class="btn btn-default" href="#">View details &raquo;</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<body>
	<jsp:include page="jsp/menu.jsp"></jsp:include>
	<div class="jumbotron">
		<div class="container">
			<h1>Hello, Team!</h1>
			<p>At DreamHost we’ve been happily hosting our clients’ dreams (and websites) since April 1997. That was when four Computer Science undergraduates at Harvey Mudd College in Claremont, CA launched this company with no capital apart from a single Pentium 100 web server (Destro was her name), using shared bandwidth on a T1 line that a friend gave us at no cost. By necessity we had to be frugal, but even with our less than ample resources we always did our best to provide a quality service at a reasonable price.</p>
			<p>
				<a class="btn btn-primary btn-lg">Learn more &raquo;</a>
			</p>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-4">
				<h2>Web Hosting</h2>
				<p>Cost-effective web hosting for WordPress bloggers, website designers, e-commerce and small business.</p>
				<p>
					<a class="btn btn-default" href="#">View details &raquo;</a>
				</p>
			</div>
			<div class="col-lg-4">
				<h2>Dedicated Hosting</h2>
				<p>>High-performance, business class web hosting services that are ideal for enterprise or advanced users.</p>
				<p>
					<a class="btn btn-default" href="#">View details &raquo;</a>
				</p>
			</div>
			<div class="col-lg-4">
				<h2>Virtual Private Server</h2>
				<p>Web hosting resources allocated only to you, for improved performance and scalability as your website grows.</p>
				<p>
					<a class="btn btn-default" href="#">View details &raquo;</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
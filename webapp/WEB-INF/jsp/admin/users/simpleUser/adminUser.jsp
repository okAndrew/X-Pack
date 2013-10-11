<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="images/favicon.png">

<title>Dream Host</title>

<!-- Bootstrap core CSS -->
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="res/js/bootstrap.js"></script>

<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/signui.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="adminUserHeader.jsp"></jsp:include>
	<div class="container">
		<!-- Static navbar -->
		<div class="navbar navbar-default">
			<div class="navbar-collapse collapse">
				<table class="table">
					<tbody>
						<tr>
							<td>User id</td>
							<!-- add placeholder!!!!!! -->
							<td>${user.id}</td>
						</tr>
						<tr>
							<td>User login</td>
							<td>${user.login}</td>
						</tr>
						<tr>
							<td>Email</td>
							<td>${user.email}</td>
						</tr>
						<tr>
							<td>Tariff</td>
							<td>${user.idTariff}</td>
						</tr>
						<tr>
							<td>Capacity</td>
							<td>${user.capacity}</td>
						</tr>
						<tr>
							<td>Token</td>
							<td>${user.token}</td>
						</tr>

					</tbody>
				</table>

				<a data-toggle="modal" href="#myModal">Edit</a>
			</div>
			
			<!--/.nav-collapse -->
		</div>
		<jsp:include page="modalAdminUser.jsp"></jsp:include>
	</div>
	<!-- /container -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

</body>
</html>
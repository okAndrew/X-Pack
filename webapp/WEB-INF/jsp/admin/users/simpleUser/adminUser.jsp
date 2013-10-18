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
	<div class="user-info">
		<!-- Panel -->
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">User info</div>
			<form action="deleteUsers" method="post" id="#deleteusers">
				<div class="panel-body">
					<!-- Table -->

					<table class="table zebra-striped table-hover">
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
							<tr>
								<td>Activation</td>
								<td>${user.isActivated}</td>
							</tr>
							<tr>
								<td>Role</td>
								<td>${user.idRole}</td>
							</tr>
						</tbody>
					</table>

					<ul class="nav nav-pills">
						<li><a data-toggle="modal" href="#myModal">Edit</a></li>
					</ul>
				</div>
			</form>
		</div>
		<jsp:include page="modalAdminUser.jsp"></jsp:include>
	</div>

</body>
</html>
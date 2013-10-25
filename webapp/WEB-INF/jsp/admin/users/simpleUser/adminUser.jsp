<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
			<div class="panel-heading">
				<fmt:message key="User_info" bundle="${lang}" />
			</div>
			<form action="deleteUsers" method="post" id="#deleteusers">
				<div class="panel-body">
					<!-- Table -->

					<table class="table zebra-striped table-hover">
						<tbody>
							<tr>
								<td><fmt:message key="User_id" bundle="${lang}" /></td>
								<!-- add placeholder!!!!!! -->
								<td>${user.id}</td>
							</tr>
							<tr>
								<td><fmt:message key="User_login" bundle="${lang}" /></td>
								<td>${user.login}</td>
							</tr>
							<tr>
								<td><fmt:message key="Email" bundle="${lang}" /></td>
								<td>${user.email}</td>
							</tr>
							<tr>
								<td><fmt:message key="Tariff" bundle="${lang}" /></td>
								<td>${user.idTariff}</td>
							</tr>
							<tr>
								<td><fmt:message key="Capacity" bundle="${lang}" /></td>
								<td>${user.capacity}</td>
							</tr>
							<tr>
								<td><fmt:message key="Activation" bundle="${lang}" /></td>
								<td>${user.isActivated}</td>
							</tr>
							<tr>
								<td><fmt:message key="Role" bundle="${lang}" /></td>
								<td>${user.role}</td>
							</tr>
						</tbody>
					</table>

					<ul class="nav nav-pills">
						<li><a data-toggle="modal" href="#myModal"><fmt:message
									key="Edit" bundle="${lang}" /></a></li>
					</ul>
				</div>
			</form>
		</div>
		<jsp:include page="modalAdminUser.jsp"></jsp:include>
	</div>

</body>
</html>
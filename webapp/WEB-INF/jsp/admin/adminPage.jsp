<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.epam.lab.controller.web.listeners.UserOnlineListener"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Sign in</title>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="res/js/bootstrap.js"></script>


<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/signui.css" rel="stylesheet" />

<style type="text/css">
.Container {
	padding-top: 70px;
	max-width: 1200px;
	margin: auto;
}
</style>
</head>

<body>
	<jsp:include page="menu/menuAdmin.jsp"></jsp:include>
<%
    UserOnlineListener counter = (UserOnlineListener) session
            .getAttribute("counter");
%>

	<div class="Container">
		<!-- Panel -->
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">General statistics</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="col-sm-6">
						<!-- Panel -->
						<div class="panel panel-default">
							<!-- Default panel contents -->
							<div class="panel-heading">Users</div>
							<table class="table zebra-striped table-hover">
								<tbody>
									<tr>
										<td>All users</td>
										<td>value</td>
									</tr>
									<tr>
										<td>Online users</td>
										<td><%= counter.getActiveSessionNumber() %></td>
									</tr>
								</tbody>
							</table>
							<table class="table zebra-striped table-hover">
								<tbody>
									<tr>
										<td><b>Activity</b></td>
									</tr>
									<tr>
										<td>Active users</td>
										<td>value</td>
									</tr>
									<tr>
										<td>Passive users</td>
										<td>value</td>
									</tr>
								</tbody>
							</table>
							<table class="table zebra-striped table-hover">
								<tbody>
									<tr>
										<td><b>Tariffs</b></td>
									</tr>
									<tr>
										<td>Simple users</td>
										<td>value</td>
									</tr>
									<tr>
										<td>Gold users</td>
										<td>value</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<!-- Panel -->
					<div class="panel panel-default">
						<!-- Default panel contents -->
						<div class="panel-heading">Files</div>

						hello admin
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
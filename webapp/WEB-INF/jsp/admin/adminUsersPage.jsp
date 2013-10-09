<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Sign in</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/signui.css" rel="stylesheet" />
<style type="text/css">
body {
	padding-top: 70px;
	max-width: 1200px;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>

<body>
	<jsp:include page="menu/menuAdmin.jsp"></jsp:include>

	<div class="Container">
		<!-- Panel -->
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">Users</div>
			<div class="panel-body">
				<ul class="nav nav-pills">
					<li class="active"><a href="#">Add</a></li>
					<li><a href="#">Delete</a></li>
					<li><a href="#">Sort</a></li>
				</ul>
			</div>

			<!-- Table -->
			<c:if test="${users != null}">
				<table class="table zebra-striped table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Login</th>
							<th>Email</th>
							<th>Password</th>
							<th>Capacity</th>
							<th>Tariffs</th>
							<th>Details</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${users}">
							<tr>
								<td>${user.id}</td>
								<td>{user.login}</td>
								<td>${user.email}</td>
								<td>${user.password}</td>
								<td>${user.capacity}</td>
								<td>${user.idTariff}</td>
								<td>view more</td>
								
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</c:if>
		</div>
	</div>

</body>
</html>
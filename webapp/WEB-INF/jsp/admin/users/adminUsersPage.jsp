<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Users</title>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="res/js/bootstrap.js"></script>

<script>
	function toggle(source) {
		checkboxes = document.getElementsByName('checkUser');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}
</script>

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
	<jsp:include page="../menu/menuAdmin.jsp"></jsp:include>

	<div class="Container">
		<!-- Panel -->
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<fmt:message key="Users" bundle="${lang}" />
			</div>

			<form action="employeeControllerUsers" method="post">
				<div class="panel-body">
					<ul class="nav nav-pills">
						<li><button type="button" class="btn btn-default"
								data-toggle="modal" data-target="#addUserModal">
								<fmt:message key="Add" bundle="${lang}" />
							</button></li>
						<li><button type="submit" class="btn btn-default"
								name="action" value="delete">
								<fmt:message key="Delete" bundle="${lang}" />
							</button></li>
						<li><button type="submit" class="btn btn-default"
								name="action" value="activated">
								<fmt:message key="Activate" bundle="${lang}" />
							</button></li>
						<li><button type="submit" class="btn btn-default"
								name="action" value="baned">
								<fmt:message key="Baned" bundle="${lang}" />
							</button></li>
						<li><button type="submit" class="btn btn-default"
								name="action" value="sendEmailUsers">
								<fmt:message key="Send_email" bundle="${lang}" />
							</button></li>
					</ul>

					<!-- Table -->
					<c:if test="${users != null}">
						<table class="table zebra-striped table-hover">
							<thead>
								<tr>
									<th><input type="checkbox" onClick="toggle(this)" /> <fmt:message
											key="All" bundle="${lang}" /></th>
									<th><fmt:message key="Id" bundle="${lang}" /></th>
									<th><fmt:message key="Login" bundle="${lang}" /></th>
									<th><fmt:message key="Email" bundle="${lang}" /></th>
									<th><fmt:message key="Password" bundle="${lang}" /></th>
									<th><fmt:message key="Capacity" bundle="${lang}" /></th>
									<th><fmt:message key="Tariffs" bundle="${lang}" /></th>
									<th><fmt:message key="Type" bundle="${lang}" /></th>
									<th><fmt:message key="Activated" bundle="${lang}" /></th>
									<th><fmt:message key="Details" bundle="${lang}" /></th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="user" items="${users}">
									<tr>
										<td><input type="checkbox" name="checkUser"
											value="${user.id}"></td>
										<td>${user.id}</td>
										<td>${user.login}</td>
										<td>${user.email}</td>
										<td>${user.password}</td>
										<td>${user.capacity}</td>
										<td>${user.idTariff}</td>
										<td>${user.role}</td>
										<td>${user.isActivated}</td>
										<td><a href="adminUser?userid=${user.id}"><fmt:message
													key="View_more" bundle="${lang}" />...</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<c:if test="${message != null}">
						<div class="alert alert-block">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<h4>Warning!</h4>
							<h5>${message}</h5>
						</div>
					</c:if>
				</div>
			</form>
			<jsp:include page="addUserModalPage.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>
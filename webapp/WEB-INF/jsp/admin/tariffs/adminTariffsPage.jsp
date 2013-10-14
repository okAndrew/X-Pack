<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Tariffs</title>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="res/js/bootstrap.js"></script>

<script>
	function toggle(source) {
		checkboxes = document.getElementsByName('checkTariff');
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
			<div class="panel-heading">Tariffs</div>
			<form action="tariffsController" method="post">
				<div class="panel-body">
					<ul class="nav nav-pills">
						<li><a data-toggle="modal" href="#addTariffModal">Add</a></li>
						<li><a href="#">Sort</a></li>
						<li><button type="submit" class="btn btn-default">Delete</button></li>
					</ul>

					<!-- Table -->
					<c:if test="${tariffs != null}">
						<table class="table zebra-striped table-hover">
							<thead>
								<tr>
									<th><input type="checkbox" onClick="toggle(this)" /> All</th>
									<th>Id</th>
									<th>Name</th>
									<th>Max Capacity</th>
									<th>Edit</th>
								</tr>
							</thead>

							<tbody>

								<c:forEach var="tariff" items="${tariffs}">
									<tr>
										<td><input type="checkbox" name="checkTariff"
											value="${tariff.id}"></td>
										<td>${tariff.id}</td>
										<td>${tariff.name}</td>
										<td>${tariff.maxCapacity}</td>
										<td><a href="adminUser?userid=${user.id}">edit</a></td>
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
		</div>
	</div>
	<jsp:include page="addTariffModalPage.jsp"></jsp:include>
</body>
</html>
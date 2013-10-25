<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<link rel="stylesheet" href="res/css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="res/css/style.css" rel="stylesheet" />
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
	<jsp:include page="addUserModalPage.jsp"></jsp:include>
	<jsp:include page="noDeleteUsersModal.jsp"></jsp:include>

	<div class="Container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<fmt:message key="Users" bundle="${lang}" />
			</div>

			<div class="panel-body">
				<form action="employeeControllerUsers" method="post">
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
								<fmt:message key="Ban" bundle="${lang}" />
							</button></li>
						<li><button type="submit" class="btn btn-default"
								name="action" value="sendEmailUsers">
								<fmt:message key="Send_email" bundle="${lang}" />
							</button></li>
						<li><input id="filter" type="text" class="form-control"
							placeholder="Search"></li>
					</ul>
					<c:if test="${message != null}">
						<div class="alert alert-block">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<h4>Warning!</h4>
							<h5>${message}</h5>
						</div>
					</c:if>
					
					<c:if test="${noDeletedUsers != null}">
						<script>$('#noDeleteUsersModal').modal('show')</script>
					</c:if>
					
					<jsp:include page="tableUsers.jsp"></jsp:include>
				</form>

			</div>
		</div>
	</div>
</body>
</html>
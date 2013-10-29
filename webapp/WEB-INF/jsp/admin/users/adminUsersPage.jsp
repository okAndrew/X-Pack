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
<script type="text/javascript" src="res/js/utils.js"></script>

<link rel="stylesheet" href="res/css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="res/css/style.css" rel="stylesheet" />

<style type="text/css">
div.alert {
	width: auto;
	margin-top: 15px;
}

form {
	
}

span.glyphicon.glyphicon-sort {
	font-size: 8pt;
	text-align: center;
}

table thead tr th {
	cursor: pointer;
}

.blue {
	color: #428BCA;
}

table {
	margin-top: 20px;
}

table th,table td {
	overflow: hidden;
}
</style>

</head>

<body>
	<jsp:include page="../../menu.jsp"></jsp:include>
	<jsp:include page="addUserModalPage.jsp"></jsp:include>
	<c:if test="${messageAddUser != null}">
		<script>
			$('#addUserModal').modal('show');
		</script>
	</c:if>


	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<c:if test="${message != null}">
					<div class="alert alert-warning">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<h4>Warning</h4>
						<h5>Message</h5>
					</div>
				</c:if>
				<form action="employeeControllerUsers" method="post">
					<jsp:include page="sendEmailModalPage.jsp"></jsp:include>
					<div class="btn-group">
						<button type="button" class="btn btn-default" data-toggle="modal"
							data-target="#addUserModal">
							<fmt:message key="Add" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="delete">
							<fmt:message key="Delete" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="restore">Restore</button>
						<button type="submit" class="btn btn-default" name="action"
							value="activated">
							<fmt:message key="Activate" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="baned">
							<fmt:message key="Ban" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" data-toggle="modal"
							data-target="#sendEmailModal">
							<fmt:message key="Send_email" bundle="${lang}" />
						</button>
					</div>

					<table class="table zebra-striped table-hover table-condensed"
						id="tablesorter">
						<thead>
							<tr>
								<th><input type="checkbox" onClick="toggle(this)" /></th>
								<th><fmt:message key="Id" bundle="${lang}" /></th>
								<th><fmt:message key="Login" bundle="${lang}" /></th>
								<th><fmt:message key="Email" bundle="${lang}" /></th>
								<th><fmt:message key="Password" bundle="${lang}" /></th>
								<th><fmt:message key="Capacity" bundle="${lang}" /></th>
								<th><fmt:message key="Tariffs" bundle="${lang}" /></th>
								<th><fmt:message key="Type" bundle="${lang}" /></th>
								<th><fmt:message key="Activated" bundle="${lang}" /></th>
								<th>Baned</th>
								<th></th>
							</tr>
						</thead>
						<tbody class="avoid-sort">
							<c:forEach var="user" items="${users}">
								<tr>
									<td class="{sorter: false}"><input type="checkbox"
										name="checkUser" value="${user.id}"></td>
									<td>${user.id}</td>
									<td>${user.login}</td>
									<td>${user.email}</td>
									<td>${user.password}</td>
									<td><script>document.write(bytesToSize(${user.capacity}));</script></td>
									<td>${user.idTariff}</td>
									<td>${user.role}</td>
									<td>${user.isActivated}</td>
									<td>${user.isBanned}</td>
									<td><a href="adminUser?userid=${user.id}"> <span
											class="glyphicon glyphicon-eye-open blue"></span></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
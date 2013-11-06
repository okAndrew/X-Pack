<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://dreamhost.com/jsp/tags/" prefix="dream"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DreamHost(Administrator) | Users</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="res/js/bootstrap.js"></script>
<script src="res/js/utils.js"></script>

<script type="text/javascript" src="res/js/utils.js"></script>
<link rel="stylesheet" href="res/css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="res/css/style.css" rel="stylesheet" />

</head>
<body>
	<jsp:include page="../../../menu.jsp"></jsp:include>
	<jsp:include page="modalAdminUser.jsp"></jsp:include>
	<c:if test="${message != null}">
		<script>
			$('#myModal').modal('show')
		</script>
	</c:if>
	<div class="container">
		<div class="panel panel-default main">
		<div class="panel-heading"><jsp:include
					page="adminUserHeader.jsp"></jsp:include></div>
			<div class="panel-body">
			
				<form action="adminSimpleEmployeeController">
					<table class="table zebra-striped table-hover table-condensed"
						id="tablesorter">
						<tbody class="avoid-sort">
							<tr>
								<td><fmt:message key="User_id" bundle="${lang}" /></td>
								<td>${adminUser.id}</td>
							</tr>
							<tr>
								<td><fmt:message key="User_login" bundle="${lang}" /></td>
								<td>${adminUser.login}</td>
							</tr>
							<tr>
								<td><fmt:message key="Email" bundle="${lang}" /></td>
								<td>${adminUser.email}</td>
							</tr>
							<tr>
								<td><fmt:message key="Tariff" bundle="${lang}" /></td>
								<td>${adminUser.idTariff}</td>
							</tr>
							<tr>
								<td><fmt:message key="Capacity" bundle="${lang}" /></td>
								<td><dream:formatSize value="${adminUser.capacity}" /></td>
							</tr>
							<tr>
								<td><fmt:message key="Activation" bundle="${lang}" /></td>
								<td>${adminUser.isActivated}</td>
							</tr>
							<tr>
								<td><fmt:message key="Banned" bundle="${lang}" /></td>
								<td>${adminUser.isBanned}</td>
							</tr>
							<tr>
								<td><fmt:message key="Role" bundle="${lang}" /></td>
								<td>${adminUser.role}</td>
							</tr>
						</tbody>
					</table>
					<button type="button" class="btn btn-default" data-toggle="modal"
						data-target="#myModal">
						<fmt:message key="Edit" bundle="${lang}" />
					</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
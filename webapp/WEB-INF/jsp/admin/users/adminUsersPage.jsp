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
<script src="res/js/bootstrap.js"></script>
<script type="text/javascript" src="res/js/utils.js"></script>

<link rel="stylesheet" href="res/css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="res/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="res/css/adminuserpage.css" rel="stylesheet" />

<script type="text/javascript">
	var page = checkPage("${param.page}");
	var perPage = checkCount("${param.count}");
	var orderBy = checkOrderBy("${param.orderby}");
	var sort = checkSort("${param.sop}");
	var pageCount = Math.ceil(parseInt("${usersCount}") / perPage);
</script>

</head>
<body onload="render();">
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
				<form action="employeeControllerUsers" method="post">
					<div class="btn-group">
						<button type="button" class="btn btn-default" data-toggle="modal" data-target="#addUserModal"> <fmt:message key="Add" bundle="${lang}" /> </button>
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
						<button type="button" class="btn btn-default" data-toggle="modal"
							data-target="#sendEmailModal">
							<fmt:message key="Send_email" bundle="${lang}" />
						</button>
					</div>

					<c:if test="${message != null}">
						<div class="alert alert-warning">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<h4>Warning</h4>
							<h5>${message}</h5>
						</div>
					</c:if>

					<table class="table zebra-striped table-hover table-condensed"
						id="tablesorter">
						<thead>
							<tr>
								<th><input type="checkbox" onClick="toggle(this)" /></th>
								<th onclick="changeOrderBy('id');"><fmt:message key="Id"
										bundle="${lang}" /></th>
								<th onclick="changeOrderBy('login');"><fmt:message
										key="Login" bundle="${lang}" /></th>
								<th onclick="changeOrderBy('email');"><fmt:message
										key="Email" bundle="${lang}" /></th>
								<th onclick="changeOrderBy('capacity');"><fmt:message
										key="Capacity" bundle="${lang}" /></th>
								<th onclick="changeOrderBy('id_tariff');"><fmt:message
										key="Tariffs" bundle="${lang}" /></th>
								<th onclick="changeOrderBy('id_role');"><fmt:message
										key="Type" bundle="${lang}" /></th>
								<th onclick="changeOrderBy('is_activated');"><fmt:message
										key="Activated" bundle="${lang}" /></th>
								<th onclick="changeOrderBy('is_banned');">Baned</th>
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
					<jsp:include page="sendEmailModalPage.jsp"></jsp:include>
				</form>
				<div class="paginator-main">
					<div class="page-pages">
						<ul id="paginator" class="pagination">
						</ul>
					</div>
					<div class="page-option">
						<div class="btn-group">
							<button type="button" class="btn btn-default">Rows</button>
							<button type="button" class="btn btn-default dropdown-toggle"
								data-toggle="dropdown">
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a onclick="changePerPage(10);">10</a></li>
								<li><a onclick="changePerPage(20);">20</a></li>
								<li><a onclick="changePerPage(50);">50</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
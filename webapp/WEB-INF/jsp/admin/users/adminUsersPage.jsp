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
<script type="text/javascript" src="res/js/jquery.tablesorter.pager.js"></script>
<script src="res/js/bootstrap.js"></script>

<script type="text/javascript" src="res/js/jquery-latest.js"></script>

<script type="text/javascript" src="res/js/jquery.tablesorter.js"></script>
<script type="text/javascript"
	src="res/js/jquery.dataTables.columnFilter.js"></script>

<link rel="stylesheet" href="res/css/styleTable.css" type="text/css"
	media="print, projection, screen" />

<script>
	function toggle(source) {
		checkboxes = document.getElementsByName('checkUser');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}
</script>


<script type="text/javascript">
	$(document).ready(function() {
		$("#tablesorter-demo").tablesorter({
			widthFixed : true,
			widgets : [ 'zebra' ]
		}).tablesorterPager({
			container : $("#pager")
		});
	});
</script>



<script type="text/javascript">
	$(document).ready(function() {
		$('#tablesorter-demo').dataTable({
			"aoColumnDefs" : [ {
				"bSortable" : false,
				"aTargets" : [ 1 ]
			} ]
		});
	});
</script>


<script type="text/javascript">
	$(document).ready(function() {
		$("#tablesorter-demo").tablesorter({
			sortList : [ [ 0, 0 ], [ 2, 0 ] ]
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#tablesorter-demo').dataTable().columnFilter({
			aoColumns : [ {
				sSelector : "#searchTable"
			}, {
				sSelector : "#searchTable"
			}, {
				sSelector : "#searchTable"
			} ]
		});
	});
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
								<fmt:message key="Baned" bundle="${lang}" />
							</button></li>
						<li><button type="submit" class="btn btn-default"
								name="action" value="sendEmailUsers">
								<fmt:message key="Send_email" bundle="${lang}" />
							</button></li>
						<li><input id="searchTable" type="text" class="form-control"
							placeholder="Search"></li>
					</ul>

					<table id="tablesorter-demo" class="tablesorter">
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
				</form>
				<div id="pager" class="pager">
					<form>
						<img src="../addons/pager/icons/first.png" class="first" /> <img
							src="../addons/pager/icons/prev.png" class="prev" /> <input
							type="text" class="pagedisplay" /> <img
							src="../addons/pager/icons/next.png" class="next" /> <img
							src="../addons/pager/icons/last.png" class="last" /> <select
							class="pagesize">
							<option selected="selected" value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
							<option value="40">40</option>
						</select>
					</form>
				</div>
				<c:if test="${message != null}">
					<div class="alert alert-block">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<h4>Warning!</h4>
						<h5>${message}</h5>
					</div>
				</c:if>

			</div>
		</div>
	</div>
</body>
</html>
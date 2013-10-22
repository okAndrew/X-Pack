<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
<input type="hidden" name="jspName" value="adminPage">
	<div class="Container" id="load_me">
		<!-- Panel -->
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading"><fmt:message key="General_statistics" bundle="${lang}" /></div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="col-sm-6">
						<!-- Panel -->
						<div class="panel panel-default">
							<!-- Default panel contents -->
							<div class="panel-heading"><fmt:message key="Users" bundle="${lang}" /></div>
							<table class="table zebra-striped table-hover">
								<tbody>
									<tr>
										<td>All users</td>
										<td>${countAllUsers}</td>
									</tr>
									<tr>
										<td>Online users</td>
										<td>${countUsers}</td>
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
										<td><b><fmt:message key="Tariffs" bundle="${lang}" /></b></td>
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
						<div class="panel-heading"><fmt:message key="Files" bundle="${lang}" /></div>

						hello admin
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var auto_refresh = setInterval(function() {
			$('#load_me').load('adminPage').fadeIn("slow");
		}, 10000);
	</script>
</body>
</html>
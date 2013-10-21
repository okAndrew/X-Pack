<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Logging</title>

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
	<jsp:include page="../menu/menuAdmin.jsp"></jsp:include>

	<div class="Container">
		<!-- Panel -->
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">Logging</div>

			<div class="panel-body">
				<ul class="nav nav-pills">
					<li><button type="submit" class="btn btn-default"
							name="action" value="error">Error</button></li>
					<li><button type="submit" class="btn btn-default"
							name="action" value="warning">Warning</button></li>
					<li><button type="submit" class="btn btn-default"
							name="action" value="info">Info</button></li>
					<li><button type="submit" class="btn btn-default"
							name="action" value="debug">Debug</button></li>
				</ul>

				<!-- Table -->
				<c:if test="${logs != null}">
					<table class="table zebra-striped table-hover">
						<thead>
							<tr>
								<th><input type="checkbox" onClick="toggle(this)" /> All</th>
								<th>Id</th>
								<th>Date</th>
								<th>Logger</th>
								<th>Level</th>
								<th>Message</th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="log" items="${logs}">
								<c:choose>
									<c:when test="${log.level == 'ERROR'}">
										<tr class="danger">
									</c:when>
									<c:when test="${log.level == 'WARNING'}">
										<tr class="warning">
									</c:when>
									<c:when test="${log.level == 'DEBUG'}">
										<tr class="success">
									</c:when>
									<c:otherwise>
										<tr class="active">
									</c:otherwise>
								</c:choose>
								<td><input type="checkbox" name="checkLog"
									value="${log.id}"></td>
								<td>${log.id}</td>
								<td>${log.datetime}</td>
								<td>${log.logger}</td>
								<td>${log.level}</td>
								<td>${log.message}</td>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
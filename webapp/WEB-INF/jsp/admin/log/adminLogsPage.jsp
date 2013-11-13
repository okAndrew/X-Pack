<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Logs</title>

<link rel="stylesheet" href="res/css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="res/css/style.css" rel="stylesheet" />

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="res/js/bootstrap.js"></script>
<script type="text/javascript" src="res/js/utils.js"></script>
</head>

<body onload="render();">
	<jsp:include page="../../menu.jsp"></jsp:include>

	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<c:if test="${message != null }">
					<div class="alert alert-warning">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<h4>
							<fmt:message key="Warning" bundle="${lang}" />
						</h4>
						<h5>
							<fmt:message key="${message}" bundle="${lang}" />
						</h5>
					</div>
				</c:if>
				<table class="table zebra-striped table-hover table-condensed"
					id="tablesorter">
					<thead>
						<tr>
							<th onclick="changeOrderBy('id');"><fmt:message key="Id"
									bundle="${lang}" /></th>
							<th onclick="changeOrderBy('date_time');"><fmt:message
									key="Date" bundle="${lang}" /></th>
							<th onclick="changeOrderBy('logger');"><fmt:message
									key="Logger" bundle="${lang}" /></th>
							<th onclick="changeOrderBy('lvl');"><fmt:message key="Level"
									bundle="${lang}" /></th>
							<th onclick="changeOrderBy('msg');"><fmt:message
									key="Message" bundle="${lang}" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="log" items="${logs}">
							<tr>
								<td>${log.id}</td>
								<td>${log.datetime}</td>
								<td style="max-width: 400px;">${log.logger}</td>
								<td>${log.level}</td>
								<td style="max-width: 300px;">${log.message}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="paginator-main">
					<div class="page-pages">
						<ul id="paginator" class="pagination">
						</ul>
					</div>
					<div class="page-option">
						<div class="btn-group">
							<button type="button" class="btn btn-default">
								<fmt:message key="Rows" bundle="${lang}" />
							</button>
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
	<script type="text/javascript">
		var page = checkPage("${param.page}");
		var perPage = checkCount("${param.count}");
		var orderBy = checkOrderBy("${param.orderby}");
		var sort = checkSort("${param.sop}");
		var pageCount = Math.ceil(parseInt("${logsCount}") / perPage);
		var linkVar = "adminLogsPage";

		document.getElementById("menu_logs").className = "active";
	</script>
</body>
</html>
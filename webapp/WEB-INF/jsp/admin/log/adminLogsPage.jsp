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

<script type="text/javascript" src="res/js/jquery-latest.js"></script>

<script type="text/javascript" src="res/js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="res/js/jquery.tablesorter.filer.js"></script>
<script type="text/javascript" src="res/js/jquery.tablesorter.pager.js"></script>

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

<script>
	function toggle(source) {
		checkboxes = document.getElementsByName('checkLog');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}
</script>

<script type="text/javascript">
	$("tablesorter").ready(function() {
		$("#tablesorter").tablesorter({
			headers : {
				0 : {
					sorter : false
				},
				10 : {
					sorter : false
				}
			},
			widthFixed : true,
		}).tablesorterPager({
			container : $("#pager"),
			positionFixed : false
		}).tablesorterFilter({
			filterContainer : $("#filter"),
			filterColumns : [ 1, 2, 3, 4, 5, 6 ],
			filterCaseSensitive : false
		});
	});
</script>

</head>

<body>
	<jsp:include page="../../menu.jsp"></jsp:include>

	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<form action="adminLogsEmployeeController" method="post">
					<div class="btn-group">
						<button type="button" class="btn btn-default" name="action"
							value="all">
							<fmt:message key="All" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="error">
							<fmt:message key="Error" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="warning">
							<fmt:message key="Warning" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="info">
							<fmt:message key="Info" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="debug">
							<fmt:message key="Debug" bundle="${lang}" />
						</button>
						<button type="button" class="btn btn-default" name="action"
							value="delete">
							<fmt:message key="Delete" bundle="${lang}" />
						</button>
						<button type="button" class="btn btn-default" name="action"
							value="clear">
							<fmt:message key="Clear_history" bundle="${lang}" />
						</button>
					</div>
					<div class="alert alert-warning">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<h4>Warning</h4>
						<h5>Message</h5>
					</div>
					<!-- table -->
					<table id="tablesorter" class="tablesorter">
						<thead>
							<tr>
								<th><input type="checkbox" onClick="toggle(this)" /> <fmt:message
										key="All" bundle="${lang}" /></th>
								<th><fmt:message key="Id" bundle="${lang}" /></th>
								<th><fmt:message key="Date" bundle="${lang}" /></th>
								<th><fmt:message key="Logger" bundle="${lang}" /></th>
								<th><fmt:message key="Level" bundle="${lang}" /></th>
								<th><fmt:message key="Message" bundle="${lang}" /></th>
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
						<tfoot>
							<tr id="pager" class="pager">
								<td><img src="res/img/table/first.png" class="first" /> <img
									src="res/img/table/prev.png" class="prev" /> <input
									type="text" class="pagedisplay" /> <img
									src="res/img/table/next.png" class="next" /> <img
									src="res/img/table/last.png" class="last" /> <select
									class="pagesize">
										<option selected="selected" value="10">10</option>
										<option value="20">20</option>
										<option value="30">30</option>
										<option value="40">40</option>
										<option value="50">50</option>
										<option value="100">100</option>
								</select></td>
							</tr>
						</tfoot>
					</table>
					<!-- /table -->
				</form>
			</div>
		</div>
	</div>
</body>
</html>
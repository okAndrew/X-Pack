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

<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<script type="text/javascript" src="res/js/utils.js"></script>
<link rel="stylesheet" href="res/css/styleTable.css" type="text/css" />

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
						<button type="submit" class="btn btn-default" name="action"
							value="delete">
							<fmt:message key="Delete" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="clear">
							<fmt:message key="Clear_history" bundle="${lang}" />
						</button>
					</div>
					<c:if test="${message != null }">
						<div class="alert alert-warning">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<h4>Warning</h4>
							<h5>${message}</h5>
						</div>
					</c:if>
					<div>
						<jsp:include page="tableLogs.jsp"></jsp:include>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
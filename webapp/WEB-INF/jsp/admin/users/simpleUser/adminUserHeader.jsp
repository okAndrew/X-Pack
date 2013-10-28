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
	<jsp:include page="../../../menu.jsp"></jsp:include>
	<jsp:include page="modalAdminUser.jsp"></jsp:include>
	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<form action="adminSimpleEmployeeController">
					<div class="btn-group">
						<button type="submit" class="btn btn-default" name="action"
							value="adminUserInfo">
							<fmt:message key="Info" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="adminUserFiles">Files</button>
						<button type="submit" class="btn btn-default" name="action"
							value="adminUserPayments">
							<fmt:message key="Payments" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="adminUserActivity">
							<fmt:message key="Activity" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="adminUserTraffic">
							<fmt:message key="Traffic" bundle="${lang}" />
						</button>
					</div>

				</form>
			</div>
		</div>
	</div>
</body>
</html>
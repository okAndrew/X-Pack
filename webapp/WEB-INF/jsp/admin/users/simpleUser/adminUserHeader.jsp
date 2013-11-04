<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
<script type="text/javascript" src="res/js/utils.js"></script>
<link rel="stylesheet" href="res/css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="res/css/style.css" rel="stylesheet" />

</head>
<body>
	<jsp:include page="../../../menu.jsp"></jsp:include>
	<jsp:include page="modalAdminUser.jsp"></jsp:include>
	<div class="container">
		<div >
			<div class="panel-body">
				<form action="userEmployeeController" method="post">
					<div class="btn-group">
						<button type="submit" class="btn btn-default" name="action"
							value="adminUserInfo">
							<fmt:message key="Info" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="adminUserFiles"><fmt:message key="Files" bundle="${lang}" /></button>
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
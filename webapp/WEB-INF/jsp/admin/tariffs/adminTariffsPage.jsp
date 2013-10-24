<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Tariffs</title>

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
			<div class="panel-heading"><fmt:message key="Tariffs" bundle="${lang}" /></div>
			<form action="employeeControllerTariffs" method="post">
				<div class="panel-body">

					<ul class="nav nav-pills">
						<li><button data-toggle="modal" data-target="#addTariffModal"
								class="btn btn-default"><fmt:message key="Add" bundle="${lang}" /></button></li>
						<li><button type="submit" class="btn btn-default"
								name="action" value="isActivate">
								<fmt:message key="Activate" bundle="${lang}" />
							</button></li>

						<li><button type="submit" class="btn btn-default"
								name="action" value="isDelete"><fmt:message key="Delete" bundle="${lang}" /></button></li>


					</ul>

					<jsp:include page="tableTariffs.jsp"></jsp:include>						

					<c:if test="${message != null}">
						<div class="alert alert-block">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<h4>Warning!</h4>
							<h5>${message}</h5>
						</div>
					</c:if>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="addTariffModalPage.jsp"></jsp:include>
	<jsp:include page="editTariffModalPage.jsp"></jsp:include>
</body>
</html>
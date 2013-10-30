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
	var pageCount = Math.ceil(parseInt("${tariffsCount}") / perPage);
	var linkVar = "adminUsersPage";
</script>

</head>

<body onload="render();">
	<jsp:include page="../../menu.jsp"></jsp:include>

	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<form action="employeeControllerTariffs" method="post">
					<jsp:include page="addTariffModalPage.jsp"></jsp:include>
					<jsp:include page="editTariffModalPage.jsp"></jsp:include>
					<div class="btn-group">
						<button data-toggle="modal" data-target="#addTariffModal"
							class="btn btn-default">
							<fmt:message key="Add" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="isActivate">
							<fmt:message key="Activate" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="isDelete">
							<fmt:message key="Delete" bundle="${lang}" />
						</button>
					</div>
					<c:if test="${message != null}">
						<div class="alert alert-warning">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<h4>Warning</h4>
							<h5>${message}</h5>
						</div>
					</c:if>
					<jsp:include page="tableTariffs.jsp"></jsp:include>
				</form>
				<jsp:include page="../../paginator.jsp"></jsp:include>
			</div>
		</div>
	</div>
</body>
</html>
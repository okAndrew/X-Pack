<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Simple User</title>

<script type="text/javascript" src="res/js/jquery-1.10.2.min.js"></script>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<script type="text/javascript" src="res/js/utils.js"></script>

<style type="text/css">
.Container {
	padding-top: 30px;
	max-width: 1200px;
	margin: auto;
}

.button-reset1.btn.btn-default,.button-reset2.btn.btn-default {
	margin: 10px;
}
</style>
</head>
<body>
	<jsp:include page="../../../menu.jsp"></jsp:include>
	<div class="Container">
		<!-- Panel -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="btn-group">
					<a href="adminUser?page=adminUserInfo" class="btn btn-default">
						<fmt:message key="Info" bundle="${lang}" />
					</a> <a href="adminUser?page=adminUserFiles" class="btn btn-default">
						<fmt:message key="Files" bundle="${lang}" />
					</a> <a href="adminUser?page=adminUserPayments" class="btn btn-default">
						<fmt:message key="Payments" bundle="${lang}" />
					</a> <a href="adminUser?page=adminUserActivity" class="btn btn-default">
						<fmt:message key="Activity" bundle="${lang}" />
					</a> <a href="adminUser?page=adminUserTraffic" class="btn btn-default">
						<fmt:message key="Traffic" bundle="${lang}" />
					</a>
				</div>
			</div>
			<div class="panel-body">
				<div id="dynamicArea">
					<c:choose>
						<c:when test="${param.page == 'adminUserInfo'}">
							<jsp:include page="adminUserInfo.jsp"></jsp:include>
						</c:when>
						<c:when test="${param.page == 'adminUserFiles'}">
							<jsp:include page="files/adminUserFiles.jsp"></jsp:include>
						</c:when>
						<c:when test="${param.page == 'adminUserPayments'}">
							<jsp:include page="adminUserPayments.jsp"></jsp:include>
						</c:when>
						<c:when test="${param.page == 'paymentsByDate'}">
							<jsp:include page="adminUserPayments.jsp"></jsp:include>
						</c:when>
						<c:when test="${param.page == 'adminUserActivity'}">
							<jsp:include page="adminUserActivity.jsp"></jsp:include>
						</c:when>
						<c:when test="${param.page == 'adminUserTraffic'}">
							<jsp:include page="adminUserTraffic.jsp"></jsp:include>
						</c:when>
						<c:otherwise>
							<jsp:include page="adminUserInfo.jsp"></jsp:include>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Logging</title>

<script type="text/javascript" src="res/js/jquery-1.10.2.min.js"></script>

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
	<jsp:include page="../../menu.jsp"></jsp:include>

	<div class="Container">
		<!-- Panel -->
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<fmt:message key="Log" bundle="${lang}" />
			</div>

			<div class="panel-body">
				<ul class="nav nav-pills">
					<li><a href="adminLogsPage?page=all"> <fmt:message
								key="All" bundle="${lang}" />
					</a></li>

					<li><a href="adminLogsPage?page=error"><fmt:message
								key="Error" bundle="${lang}" /></a></li>

					<li><a href="adminLogsPage?page=warning"><fmt:message
								key="Warning" bundle="${lang}" /></a></li>

					<li><a href="adminLogsPage?page=info"><fmt:message
					
								key="Info" bundle="${lang}" /></a></li>

					<li><a href="adminLogsPage?page=debug"><fmt:message
								key="Debug" bundle="${lang}" /></a></li>
					<!-- 
					<li><button type="button" class="btn btn-default"
							data-target="adminLogsEmployeeController" name="action"
							value="delete">
							<fmt:message key="Delete" bundle="${lang}" />
						</button></li>
					<li><button type="button" class="btn btn-default"
							data-target="adminLogsEmployeeController" name=action
							value="clear">
							<fmt:message key="Clear_history" bundle="${lang}" />
						</button></li>
 -->
				</ul>

				<div id="dynamicArea">
					<c:choose>
						<c:when test="${param.page == 'all'}">
							<jsp:include page="logsAll.jsp"></jsp:include>
						</c:when>
						<c:when test="${param.page == 'error'}">
							<jsp:include page="logsError.jsp"></jsp:include>
						</c:when>
						<c:when test="${param.page == 'warning'}">
							<jsp:include page="logsWarning.jsp"></jsp:include>
						</c:when>
						<c:when test="${param.page == 'info'}">
							<jsp:include page="logsInfo.jsp"></jsp:include>
						</c:when>
						<c:when test="${param.page == 'debug'}">
							<jsp:include page="logsDebug.jsp"></jsp:include>
						</c:when>
					</c:choose>
					<!-- 
					<jsp:include page="tableLogs.jsp"></jsp:include></div>
 -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Statistics</title>

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

	<div class="code prettyprint">
		<pre class="code prettyprint brush: js"></pre>
	</div>
	<jsp:include page="../../menu.jsp"></jsp:include>

	<div class="Container">
		<!-- Panel -->
		<div class="panel panel-default">
			<!-- Default panel contents -->

			<div class="panel-heading">
				<fmt:message key="Statistics" bundle="${lang}" />
			</div>
			<div class="panel-body">
				<ul class="nav nav-pills">
					<li><a href="adminStatisticsPage?page=users"
						class="btn btn-default"> <fmt:message key="Users"
								bundle="${lang}" />
					</a></li>
					<li><a href="adminStatisticsPage?page=files"
						class="btn btn-default"> <fmt:message key="Files"
								bundle="${lang}" />
					</a></li>
					<li><a href="adminStatisticsPage?page=server"
						class="btn btn-default"> <fmt:message key="Server"
								bundle="${lang}" />
					</a></li>
				</ul>
				<h1 hidden="false">'${freeSpace}'</h1>
				<h1 hidden="false">'${totalSpace}'</h1>
				<div id="dynamicArea">
					<c:choose>
						<c:when test="${param.page == 'users'}">
							<jsp:include page="statisticsUsers.jsp"></jsp:include>
						</c:when>
						<c:when test="${param.page == 'files'}">
							<jsp:include page="statisticsFiles.jsp"></jsp:include>
						</c:when>
						<c:when test="${param.page == 'server'}">
							<jsp:include page="statisticsServer.jsp"></jsp:include>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
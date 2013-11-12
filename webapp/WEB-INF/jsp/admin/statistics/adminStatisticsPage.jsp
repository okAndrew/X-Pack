<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Statistics</title>

<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="res/js/jquery-1.10.2.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<script type="text/javascript" src="res/js/utils.js"></script>

<style type="text/css">
.Container {
	padding-top: 30px;
	max-width: 1200px;
	margin: auto;
}

.center {
	margin-left: auto;
	margin-right: auto;
	height: 350px;
	width: 700px;
}

.centerDiv {
	margin-left: auto;
	margin-right: auto;
}

.button-reset1.btn.btn-default,.button-reset2.btn.btn-default {
	margin: 10px;
}
</style>

<script type="text/javascript">
window.onload = function() {
	var el = document.getElementById("menu_statistics");
    el.className="active";
};
</script>
</head>
<body>
	<!--<div class="code prettyprint">
		<pre class="code prettyprint brush: js"></pre>
	</div>  -->

	<jsp:include page="../../menu.jsp"></jsp:include>

	<div class="Container">
		<!-- Panel -->
		<div class="panel panel-default">
			<!-- Default panel contents -->

			<div class="panel-heading">
				<!--<fmt:message key="Statistics" bundle="${lang}" />-->
				<div class="btn-group">
					<a href="adminStatisticsPage?page=users" class="btn btn-default">
						<fmt:message key="Users" bundle="${lang}" />
					</a> <a href="adminStatisticsPage?page=files" class="btn btn-default">
						<fmt:message key="Files" bundle="${lang}" />
					</a> <a href="adminStatisticsPage?page=server" class="btn btn-default">
						<fmt:message key="Server" bundle="${lang}" />
					</a>
				</div>
			</div>
			<div class="panel-body">

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
						<c:otherwise>
							<div style="margin-left: auto; margin-right: auto;">
								<div class="row-fluid">
									<div class="col-xs-4">
										<img src="res/img/statistics.png">
									</div>
									<div class="col-xs-4">
										<img src="res/img/statisticsFiles.png">
									</div>
									<div class="col-xs-4">
										<img src="res/img/statistics3.png">
									</div>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DreamHost | Settings</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/settings.css" rel="stylesheet" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<script src="res/js/string_en.js"></script>
<c:choose>
	<c:when
		test="${(sessionScope.sessLocale=='uk')||(sessionScope.sessLocale=='uk_UA')}">
		<script src="res/js/string_uk.js"></script>
	</c:when>
	<c:when
		test="${(sessionScope.sessLocale=='ru')||(sessionScope.sessLocale=='ru_RU')}">
		<script src="res/js/string_ru.js"></script>
	</c:when>
</c:choose>
<script src="res/js/settings.js"></script>
<script src="res/js/utils.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu.jsp"></jsp:include>
	<jsp:include page="settings/modalemail.jsp"></jsp:include>
	<jsp:include page="settings/modalpassword.jsp"></jsp:include>

	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-3">
						<ul class="nav nav-pills nav-stacked">
							<li class="active"><a href="#tabs1-pane1" data-toggle="tab"><fmt:message
										key="Personal_information" bundle="${lang}" /></a></li>
						</ul>
					</div>
					<div class="col-md-9">
						<div class="tab-content">
							<div class="tab-pane active" id="tabs1-pane1">
								<jsp:include page="settings/basic.jsp"></jsp:include>
							</div>
							<div class="tab-pane" id="tabs1-pane2">
								<jsp:include page="settings/tariffs.jsp"></jsp:include>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
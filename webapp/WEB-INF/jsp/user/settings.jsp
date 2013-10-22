
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost | Settings</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<script src="res/js/settings.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu.jsp"></jsp:include>
	<jsp:include page="settings/modalemail.jsp"></jsp:include>
	<jsp:include page="settings/modalpassword.jsp"></jsp:include>

	<div class="container">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-3">
						<ul class="nav nav-pills nav-stacked">
							<li class="active"><a href="#tabs1-pane1" data-toggle="tab">Personal
									information</a></li>
							<li><a href="#tabs1-pane2" data-toggle="tab">Service</a></li>
							<li><a href="#tabs1-pane3" data-toggle="tab">...</a></li>
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
							<div class="tab-pane" id="tabs1-pane3">
								<p>Other</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel-footer">Footer</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Team</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/team.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<script src="res/js/team.js"></script>
<style type="text/css">
.panel-body {
	margin-right: 15px;
	margin-left: 15px;
}
.panel-person {
	border: 1px solid #DDD;
	margin: 15px;
	border-radius: 3px;
	min-height: 400px;
	max-height: 400px;
}
</style>
</head>

<body onload="initTeamPage()">
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<div class="row" id="row"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		document.getElementById("menu_about").className = "active";
	</script>
</body>
</html>
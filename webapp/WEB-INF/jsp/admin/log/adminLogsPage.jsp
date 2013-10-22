<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Logging</title>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="res/js/bootstrap.js"></script>

<script>
	function loadXMLDoc(action) {
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("dynamicArea").innerHTML = xmlhttp.responseText;
			}
		}
		xmlhttp.open("GET", "adminLogsEmployeeController?action="
				+ action.value, true);
		xmlhttp.send();
	}
</script>

<script>
	function toggle(source) {
		checkboxes = document.getElementsByName('checkLog');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}
</script>

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
			<div class="panel-heading">Logging</div>

			<div class="panel-body">
				<ul class="nav nav-pills">
					<li><button type="submit" onclick="loadXMLDoc(this)"
							class="btn btn-default" value="all">All</button></li>
					<li><button type="submit" onclick="loadXMLDoc(this)"
							class="btn btn-default" value="error">Error</button></li>
					<li><button type="submit" onclick="loadXMLDoc(this)"
							class="btn btn-default" value="warning">Warning</button></li>
					<li><button type="submit" onclick="loadXMLDoc(this)"
							class="btn btn-default" value="info">Info</button></li>
					<li><button type="submit" onclick="loadXMLDoc(this)"
							class="btn btn-default" value="debug">Debug</button></li>
					<li><button type="button" class="btn btn-default"
							data-target="adminLogsEmployeeController" name="action"
							value="delete">Delete</button></li>
					<li><button type="button" class="btn btn-default"
							data-target="adminLogsEmployeeController" name=action
							value="clear">Clear history</button></li>
				</ul>

				<!-- Table -->
				<table class="table zebra-striped table-hover">
					<thead>
						<tr>
							<th><input type="checkbox" onClick="toggle(this)" /> All</th>
							<th>Id</th>
							<th>Date</th>
							<th>Logger</th>
							<th>Level</th>
							<th>Message</th>
						</tr>
					</thead>
					<tbody id="dynamicArea"><jsp:include page="logsList.jsp"></jsp:include></tbody>

				</table>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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

<script type="text/javascript" src="res/js/jquery-latest.js"></script>

<script type="text/javascript" src="res/js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="res/js/jquery.tablesorter.filer.js"></script>
<script type="text/javascript" src="res/js/jquery.tablesorter.pager.js"></script>


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
				ready();
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

<script type="text/javascript">
	$("tablesorter").ready(function() {
		$("#tablesorter").tablesorter({
			headers : {
				0 : {
					sorter : false
				},
			},
			widthFixed : true,
		}).tablesorterPager({
			container : $("#pager"),
			positionFixed : false
		}).tablesorterFilter({
			filterContainer : $("#filter"),
			filterColumns : [ 1, 2, 3, 4, 5, 6 ],
			filterCaseSensitive : false
		});
	});
</script>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/signui.css" rel="stylesheet" />
<link rel="stylesheet" href="res/css/styleTable.css" type="text/css" />

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
					<li><button type="submit" onclick="loadXMLDoc(this)"
							class="btn btn-default" value="all">
							<fmt:message key="All" bundle="${lang}" />
						</button></li>
					<li><button type="submit" onclick="loadXMLDoc(this)"
							class="btn btn-default" value="error">
							<fmt:message key="Error" bundle="${lang}" />
						</button></li>
					<li><button type="submit" onclick="loadXMLDoc(this)"
							class="btn btn-default" value="warning">
							<fmt:message key="Warning" bundle="${lang}" />
						</button></li>
					<li><button type="submit" onclick="loadXMLDoc(this)"
							class="btn btn-default" value="info">
							<fmt:message key="Info" bundle="${lang}" />
						</button></li>
					<li><button type="submit" onclick="loadXMLDoc(this)"
							class="btn btn-default" value="debug">
							<fmt:message key="Debug" bundle="${lang}" />
						</button></li>

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

				</ul>

				<div id="dynamicArea"><jsp:include page="tableLogs.jsp"></jsp:include></div>
<!-- 				
<!-- 				Table -->
<!-- 				<table class="table zebra-striped table-hover"> -->
<!-- 					<thead> -->
<!-- 						<tr> -->
<%-- 							<th><input type="checkbox" onClick="toggle(this)" /> <fmt:message --%>
<%-- 									key="All" bundle="${lang}" /></th> --%>
<%-- 							<th><fmt:message key="Id" bundle="${lang}" /></th> --%>
<%-- 							<th><fmt:message key="Date" bundle="${lang}" /></th> --%>
<%-- 							<th><fmt:message key="Logger" bundle="${lang}" /></th> --%>
<%-- 							<th><fmt:message key="Level" bundle="${lang}" /></th> --%>
<%-- 							<th><fmt:message key="Message" bundle="${lang}" /></th> --%>
<!-- 						</tr> -->
<!-- 					</thead> -->
<%-- 					<tbody id="dynamicArea"><jsp:include page="logsList.jsp"></jsp:include></tbody> --%>

<!-- 				</table> -->
<!-- 				-->	
			</div>
		</div>
	</div>
</body>
</html>
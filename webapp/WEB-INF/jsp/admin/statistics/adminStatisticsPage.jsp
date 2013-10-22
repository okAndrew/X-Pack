<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Statistics</title>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="res/js/bootstrap.js"></script>
<script type="text/javascript" src="res/js/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="res/js/jqplot.pieRenderer.min.js"></script>

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
		xmlhttp.open("GET", "adminStatisticEmployeeController?action="
				+ action.value, true);
		xmlhttp.send();
	}
</script>

<script type="text/javascript">
	$(function() {
		var free = ${freeSpace};
		var employed = ${totalSpace}-free;
		line1 = [ [ "Free space " + free + "GB", free ],
				[ "Employed space" + employed + "GB", employed ] ];
		$.jqplot("example", [ line1 ], {
			title : "Space",
			grid : {
				gridLineColor : '#cccccc',
				background : '#ffffff',
				borderColor : '#000000',
				borderWidth : 2.0,
				shadow : true,
				shadowWidth : 3,
				shadowDepth : 3
			},
			seriesColors : [ "#33FF33", "#FF0000" ],
			seriesDefaults : {
				renderer : $.jqplot.PieRenderer,
				rendererOptions : {
					showDataLabels : true,
					sliceMargin : 8,
					diameter : 200,
					fill : true,
					shadowDepth : 5,
				}
			},

			legend : {
				show : true,
				location : 'e',
				marginTop : '15px'
			}
		});
	});
</script>


<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/signui.css" rel="stylesheet" />
<link href="res/css/jquery.jqplot.css" rel="stylesheet" />

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
			<div class="panel-heading">Statistics</div>
			<div class="panel-body">
			<ul class="nav nav-pills">
				<li><button type="submit" onclick="loadXMLDoc(this)"
						class="btn btn-default" value="users">Users</button></li>
				<li><button type="submit" onclick="loadXMLDoc(this)"
						class="btn btn-default" value="files">Files</button></li>
				<li><button type="submit" onclick="loadXMLDoc(this)"
						class="btn btn-default" value="server">Server</button></li>
			</ul>
			
			<div id="dynamicArea">
				<jsp:include page="files.jsp"></jsp:include>
				<jsp:include page="users.jsp"></jsp:include>
			</div>
			</div>
		</div>
	</div>
</body>
</html>
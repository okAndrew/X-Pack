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
<script type="text/javascript" src="res/js/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="res/js/jqplot.pieRenderer.min.js"></script>
<script type="text/javascript"
	src="res/js/jqplot.canvasTextRenderer.min.js"></script>
<script type="text/javascript"
	src="res/js/jqplot.canvasAxisLabelRenderer.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<script type="text/javascript"
	src="res/js/jqplot.dateAxisRenderer.min.js"></script>
<script type="text/javascript" src="res/js/jqplot.jqplot.cursor.min.js"></script>
<link href="res/css/jquery.jqplot.css" rel="stylesheet" />
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
				plotGist();
				Hello();
			}
		}
		xmlhttp.open("GET", "adminStatisticEmployeeController?action="
				+ action.value, true);
		xmlhttp.send();
	}

	function plotGist() {
		var free = '${freeSpace}';
		var employed = '${totalSpace}'-free;
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
	};

	function Hello() {
		var line1 = [ [ '2008-06-30', 4 ], [ '2008-7-30 8:00AM', 100 ],
						[ '2008-8-30 8:00AM', 5.7 ], [ '2008-9-30 8:00AM', 9 ],
						[ '2008-11-30 8:00AM', 8.2 ], [ '2008-12-30 8:00AM', 8.2 ],
						[ '2009-01-30 8:00AM', 8.2 ], [ '2009-02-30 8:00AM', 8.2 ] ];
				var plot2 = $.jqplot('chart2', [ line1 ], {
					title : 'Visitors by period:',
					gridPadding : {
						right : 35
					},
					axes : {
						xaxis : {
							renderer : $.jqplot.DateAxisRenderer,
							tickOptions : {
								formatString : '%b %#d, %y'
							},
							min : 'May 30, 2008',
							tickInterval : '1 month'
						}
					},
					series : [ {
						lineWidth : 2,
						markerOptions : {
							style : 'round'
						}
					} ]
				});
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
					<li><button type="submit" onclick="loadXMLDoc(this)"
							class="btn btn-default" value="users">
							<fmt:message key="Users" bundle="${lang}" />
						</button></li>
					<li><button type="submit" onclick="loadXMLDoc(this);"
							class="btn btn-default" value="files">
							<fmt:message key="Files" bundle="${lang}" />
						</button></li>
					<li><button type="submit" onclick="loadXMLDoc(this)"
							class="btn btn-default" value="server">
							<fmt:message key="Server" bundle="${lang}" />
						</button></li>

				</ul>
				<h1 hidden="false">'${freeSpace}'</h1>
				<h1 hidden="false">'${totalSpace}'</h1>
				<div id="dynamicArea"></div>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Files</title>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>

<script type="text/javascript" src="res/js/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="res/js/jqplot.pieRenderer.min.js"></script>

<script src="res/js/bootstrap.js"></script>


<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/signui.css" rel="stylesheet" />
<link href="res/css/jquery.jqplot.css" rel="stylesheet" />


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
			<div class="panel-heading"><fmt:message key="Files" bundle="${lang}" /></div>
			<div class="panel-body">
				<div id="example"></div>
				<div>
					<c:if test="${types != null}">
						<table class="table zebra-striped table-hover">
							<thead>
								<tr>
									<td>#</td>
									<td><fmt:message key="Type" bundle="${lang}" /></td>
									<td><fmt:message key="Size" bundle="${lang}" />(MB)</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="type" items="${types}">
									<tr>
										<td></td>
										<td>${type.type}</td>
										<td>${type.size/1024/1024}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>


				</div>
			</div>
		</div>
	</div>
</body>
</html>
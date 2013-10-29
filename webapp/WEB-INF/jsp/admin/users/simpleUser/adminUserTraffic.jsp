<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Users</title>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="res/js/bootstrap.js"></script>
<script type="text/javascript" src="res/js/utils.js"></script>

<link rel="stylesheet" href="res/css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="res/css/style.css" rel="stylesheet" />

<style type="text/css">
div.alert {
	width: auto;
	margin-top: 15px;
}

form {
	
}

span.glyphicon.glyphicon-sort {
	font-size: 8pt;
	text-align: center;
}

table thead tr th {
	cursor: pointer;
}

.blue {
	color: #428BCA;
}

table {
	margin-top: 20px;
}

table th,table td {
	overflow: hidden;
}

.button-reset {
	margin: 30px;
	margin-left: 90px;
}
</style>
</head>
<body>
	<jsp:include page="../../../menu.jsp"></jsp:include>
	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<jsp:include page="adminUserHeader.jsp"></jsp:include>
				<div class="col-xs-6">
					<div id="chart1" style="height: 350px; width: 550px;"></div>
					<button class="button-reset">Reset Zoom</button>
				</div>

				<div class="col-xs-6">
					<div id="chart2" style="height: 350px; width: 550px;"></div>
					<button class="button-reset">Reset Zoom</button>
				</div>


				<div>
					<table class="table zebra-striped table-hover">
						<tbody>
							<tr>
								<td>Download last month</td>
								<td><script>document.write(bytesToSize(${downlUserLastMonth}));</script></td>
							</tr>
							<tr>
								<td>Upload last month</td>
								<td><script>document.write(bytesToSize(${uploadUserLastMonts}));</script></td>
							</tr>
							<tr>
								<td>Download last week</td>
								<td><script>document.write(bytesToSize(${downlUserLastWeek}));</script></td>
							</tr>
							<tr>
								<td>Upload last week</td>
								<td><script>document.write(bytesToSize(${uploadUserLastWeek}));</script></td>

							</tr>
							<tr>
								<td>Download last day</td>
								<td><script>document.write(bytesToSize(${downlUserLastDay}));</script></td>
							</tr>
							<tr>
								<td>Upload last day</td>
								<td><script>document.write(bytesToSize(${uploadUserLastDay}));</script></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="res/js/jquery.jqplot.min.js"></script>
	<script type="text/javascript"
		src="res/js/jqplot.dateAxisRenderer.min.js"></script>
	<script type="text/javascript" src="res/js/jqplot.cursor.min.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							// Our ajax data renderer which here retrieves a text file.
							// it could contact any source and pull data, however.
							// The options argument isn't used in this renderer.
							var ajaxDataRenderer = function(url, plot, options) {
								var ret = null;
								$.ajax({
									// have to use synchronous here, else the function 
									// will return before the data is fetched
									async : false,
									url : url,
									dataType : "json",
									success : function(data) {
										ret = data;
									},
									error : function(xhr, ajaxOptions,
											thrownError) {
										alert('responseText: '
												+ xhr.responseText
												+ '    Error: ' + thrownError);
									}
								});
								return ret;
							};

							// The url for our json data
							var jsonurl = "Data4UserTraffic";
							var jsonurl2 = "Data4UserStatisticsUpload";

							var plot1 = $.jqplot('chart1', jsonurl,	{
												title : "Load server(Download)",
												dataRenderer : ajaxDataRenderer,
												dataRendererOptions : {
													unusedOptionalUrl : jsonurl
												},
												axes : {
													xaxis : {
														renderer : $.jqplot.DateAxisRenderer,
														rendererOptions : {
															tickRenderer : $.jqplot.CanvasAxisTickRenderer
														},
														min : 'October 1, 2013',
														tickOptions : {
															formatString : '%b %#d, %y'
														}
													}
												},
												axesDefault : {
													useSeriesColor : true
												},
												cursor : {
													show : true,
													zoom : true,
													showTooltip : false
												}
											});

							var plot1 = $.jqplot('chart2', jsonurl2, {
												title : "Load server(Upload)",
												dataRenderer : ajaxDataRenderer,
												dataRendererOptions : {
													unusedOptionalUrl : jsonurl2
												},
												axes : {
													xaxis : {
														renderer : $.jqplot.DateAxisRenderer,
														rendererOptions : {
															tickRenderer : $.jqplot.CanvasAxisTickRenderer
														},
														min : 'October 1, 2013',
														tickOptions : {
															formatString : '%b %#d, %y'
														}
													}
												},
												axesDefault : {
													useSeriesColor : true
												},
												cursor : {
													show : true,
													zoom : true,
													showTooltip : false
												}
											});

							$('.button-reset').click(function() {
								plot1.resetZoom()
							});
						});
	</script>
</body>
</html>
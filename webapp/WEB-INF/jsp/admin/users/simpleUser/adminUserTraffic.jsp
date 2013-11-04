<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

</head>
<body>
	<jsp:include page="../../../menu.jsp"></jsp:include>
	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<jsp:include page="adminUserHeader.jsp"></jsp:include>
				<div class="col-xs-6">
					<div id="chart1" style="height: 350px; width: 550px;"></div>
					<button class="button-reset1">
						<fmt:message key="Reset_Zoom" bundle="${lang}" />
					</button>
				</div>

				<div class="col-xs-6">
						<div id="chart2" style="height: 350px; width: 550px;"></div>
						<button class="button-reset2">
							<fmt:message key="Reset_Zoom" bundle="${lang}" />
						</button>
					</div>
				</div>

				<div>
					<table class="table zebra-striped table-hover">
						<tbody>
							<tr>
								<td><fmt:message key="Download_last_month" bundle="${lang}" /></td>
								<td><script>document.write(bytesToSize(${downlUserLastMonth}));</script></td>
							</tr>
							<tr>
								<td><fmt:message key="Upload_last_month" bundle="${lang}" /></td>
								<td><script>document.write(bytesToSize(${uploadUserLastMonth}));</script></td>
							</tr>
							<tr>
								<td><fmt:message key="Download_last_week" bundle="${lang}" /></td>
								<td><script>document.write(bytesToSize(${downlUserLastWeek}));</script></td>
							</tr>
							<tr>
								<td><fmt:message key="Upload_last_week" bundle="${lang}" /></td>
								<td><script>document.write(bytesToSize(${uploadUserLastWeek}));</script></td>

							</tr>
							<tr>
								<td><fmt:message key="Download_last_day" bundle="${lang}" /></td>
								<td><script>document.write(bytesToSize(${downlUserLastDay}));</script></td>
							</tr>
							<tr>
								<td><fmt:message key="Upload_last_day" bundle="${lang}" /></td>
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
							var ajaxDataRenderer = function(url, plot, options) {
								var ret = null;
								$.ajax({
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
							<fmt:message key="Load_server_Download" var="download"/>
							var msgdownload = "${download}";
							var plot1 = $.jqplot('chart1', jsonurl,	{
												title : msgdownload,
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
															formatString : '%d/%m/%Y'
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
							<fmt:message key="Load_server_Upload" var="upload"/>
							var msgupload = "${upload}";
							var plot2 = $.jqplot('chart2', jsonurl2, {
												title : msgupload,
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
															formatString : '%d/%m/%Y'
																
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

							$('.button-reset1').click(function() {
								plot1.resetZoom();
							});
							$('.button-reset2').click(function() {
								plot2.resetZoom();
							});
							
						});
	</script>
</body>
</html>
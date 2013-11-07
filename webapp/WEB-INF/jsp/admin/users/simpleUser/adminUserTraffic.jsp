<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<script type="text/javascript" src="res/js/utils.js"></script>
<div class="col-xs-6">
	<div id="chart1" style="height: 330px; width: 500px;"></div>
	<button class="button-reset1 btn btn-default">
		<fmt:message key="Reset_Zoom" bundle="${lang}" />
	</button>
</div>

<div class="col-xs-6">
	<div id="chart2" style="height: 330px; width: 500px;"></div>
	<button class="button-reset2 btn btn-default">
		<fmt:message key="Reset_Zoom" bundle="${lang}" />
	</button>
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
<link href="res/css/jquery.jqplot.min.css" rel="stylesheet" />
<style type="text/css">
.button-reset {
	margin: 30px;
	margin-left: 90px;
}
</style>
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

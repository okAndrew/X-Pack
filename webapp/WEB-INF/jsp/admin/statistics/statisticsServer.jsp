<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://dreamhost.com/jsp/tags/" prefix="dream"%>

<script type="text/javascript" src="res/js/utils.js"></script>

<div class="col-xs-6">
<div id="chart1" style="height: 350px; width: 550px;"></div>
<button class="button-reset1">Reset Zoom</button>
</div>

<div class="col-xs-6">
<div id="chart2" style="height: 350px; width: 550px;"></div>
<button class="button-reset2">Reset Zoom</button>
</div>


<div>
	<table class="table zebra-striped table-hover">
		<tbody>
			<tr>
				<td>Download last mounth</td>
				<td><dream:formatSize value="${downlLastMounts}"/></td>
			</tr>
			<tr>
				<td>Upload last mounts</td>
				<td><dream:formatSize value="${uploadLastMounts}"/></td>
			</tr>
			<tr>
				<td>Download last week</td>
				<td><dream:formatSize value="${downlLastWeek}"/></td>
			</tr>
			<tr>
				<td>Upload last week</td>
				<td><dream:formatSize value="${uploadLastWeek}"/></td>
			</tr>
			<tr>
				<td>Download last day</td>
				<td><dream:formatSize value="${downlLastDay}"/></td>
			</tr>
			<tr>
				<td>Upload last day</td>
				<td><dream:formatSize value="${uploadLastDay}"/></td>
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
	$(document).ready(
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
						error : function(xhr, ajaxOptions, thrownError) {
							alert('responseText: ' + xhr.responseText
									+ '    Error: ' + thrownError);
						}
					});
					return ret;
				};

				// The url for our json data
				var jsonurlD = "Data4StatisticTrafficDownload";
				var jsonurlU = "Data4StatisticTrafficUpload";

				var plot1 = $.jqplot('chart1', jsonurlD,{
					title : "Load server(Download)",
					dataRenderer : ajaxDataRenderer,
					dataRendererOptions : {
						unusedOptionalUrl : jsonurlD
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
						showTooltip : false,
					}
				});
				
				var plot2 = $.jqplot('chart2', jsonurlU,{
					title : "Load server(Upload)",
					dataRenderer : ajaxDataRenderer,
					dataRendererOptions : {
						unusedOptionalUrl : jsonurlU
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
							},
						}
					},
					axesDefault : {
						useSeriesColor : true
					},
					cursor : {
						show : true,
						zoom : true,
						showTooltip : false,
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
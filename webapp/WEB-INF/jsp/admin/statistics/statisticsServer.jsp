<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


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
				<td>Download last mounth</td>
				<td>${downlLastMounts}</td>
			</tr>
			<tr>
				<td>Upload last mounts</td>
				<td>${uploadLastMounts}</td>
			</tr>
			<tr>
				<td>Download last week</td>
				<td>${downlLastWeek}</td>
			</tr>
			<tr>
				<td>Upload last week</td>
				<td>${uploadLastWeek}</td>
			</tr>
			<tr>
				<td>Download last day</td>
				<td>${downlLastDay}</td>
			</tr>
			<tr>
				<td>Upload last day</td>
				<td>${uploadLastDay}</td>
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
				var jsonurl = "Data4StatisticTraffic";
				var jsonurl2 = "Data4StatisticTrafficUpload";

				var plot1 = $.jqplot('chart1', jsonurl,{
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
				
				var plot1 = $.jqplot('chart2', jsonurl2,{
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
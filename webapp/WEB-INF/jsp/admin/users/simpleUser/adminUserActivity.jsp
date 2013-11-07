<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="res/js/utils.js"></script>

<style type="text/css">
.Statisticstable {
	margin-top: 120px;
	float: right;
	height: 350px;
	width: 350px;
}
</style>

<div class="rowSpace">
	<div class="row-fluid">
		<div class="col-xs-7">
			<div id="chart1"></div>
			<button class="button-reset  btn btn-default">
				<fmt:message key="Reset_Zoom" bundle="${lang}" />
			</button>
		</div>
	</div>
	<div class="col-xs-4">
		<div class=Statisticstable>
			<table class="table zebra-striped table-hover">
				<tbody>
					<tr>
						<td><fmt:message key="Average_time_of_sessions"
								bundle="${lang}" /></td>
						<td>${avarageSession}</td>
					</tr>
					<tr>
						<td><fmt:message key="Average_number_visits_per_day"
								bundle="${lang}" /></td>
						<td>${visitsByUserId}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
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
				var ajaxDataRenderer = function(url, plot, options) {
					var ret = null;
					$.ajax({
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
				var jsonurl = "adminUserActivityGraph";
				<fmt:message key="Visitors" var="name"/>
				var msg = "${name}";
				var plot1 = $.jqplot('chart1', jsonurl, {
					title : msg,
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://dreamhost.com/jsp/tags/" prefix="dream"%>

<div id="chart1" class="center"></div>
<button class="button-reset btn btn-default" >
	<fmt:message key="Reset_Zoom" bundle="${lang}" />
</button>
<div id="example"></div>

<div>
	<table class="table zebra-striped table-hover">
		<tbody>
			<tr>
				<td><fmt:message key="All_users" bundle="${lang}" /></td>
				<td>${countAllUsers}</td>
			</tr>
			<tr>
				<td><fmt:message key="Online_users" bundle="${lang}" /></td>
				<td>${countUsers}</td>
			</tr>
			<tr>
				<td><fmt:message key="Online_logged_users" bundle="${lang}" /></td>
				<td>${countUsersLogged}</td>
			</tr>
			<tr>
				<td><fmt:message key="Visited_on_last_day_/_logged"
						bundle="${lang}" /></td>
				<td>${visitorsByDay}/${loggedVisitorsByDay}</td>
			</tr>
			<tr>
				<td><fmt:message key="Visited_on_last_week_/_logged"
						bundle="${lang}" /></td>
				<td>${visitorsByWeek}/${loggedVisitorsByWeek}</td>
			</tr>
			<tr>
				<td><fmt:message key="Visited_on_last_month_/_logged"
						bundle="${lang}" /></td>
				<td>${visitorsByMonth}/${loggedVisitorsByMonth}</td>
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
<script type="text/javascript" src="res/js/jqplot.pieRenderer.min.js"></script>
<script type="text/javascript" src="res/js/jqplot.cursor.min.js"></script>
<script type="text/javascript">
	$(document).ready(function timedRefresh() {
		setTimeout("location.reload(true);", 100000);
	});
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
				var jsonurl = "Data4StatisticUsers";
				<fmt:message key="Visitors" var="visitors" bundle="${lang}"/>
				var visitors = "${visitors}";
				var plot1 = $.jqplot('chart1', jsonurl, {
					title : visitors,
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
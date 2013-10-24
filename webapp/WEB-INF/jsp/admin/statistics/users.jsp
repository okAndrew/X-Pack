<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="res/js/jqplot.canvasTextRenderer.min.js"></script>
<script type="text/javascript" src="res/js/jqplot.canvasAxisLabelRenderer.min.js"></script>
<link href="res/css/jquery.jqplot.css" rel="stylesheet" />
<script>
$(document).ready(function(){
	$jqplot.config.enablePlugins = true;
  var plot2 = $.jqplot ('chart2', [[3,7,9,1,4,6,8,2,5]], {
      // Give the plot a title.
      title: 'Plot With Options',
      // You can specify options for all axes on the plot at once with
      // the axesDefaults object.  Here, we're using a canvas renderer
      // to draw the axis label which allows rotated text.
      axesDefaults: {
        labelRenderer: $.jqplot.CanvasAxisLabelRenderer
      },
      // An axes object holds options for all axes.
      // Allowable axes are xaxis, x2axis, yaxis, y2axis, y3axis, ...
      // Up to 9 y axes are supported.
      axes: {
        // options for each axis are specified in seperate option objects.
        xaxis: {
          label: "X Axis",
          // Turn off "padding".  This will allow data point to lie on the
          // edges of the grid.  Default padding is 1.2 and will keep all
          // points inside the bounds of the grid.
          pad: 0
        },
        yaxis: {
          label: "Y Axis"
        }
      }
    });
});
</script>
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
	

			<td>Visited on last day</td>
		</tr>
		
		<tr>
			<td>Visited to last week</td>
		</tr>
		
		<tr>
			<td>Visited to last mounts</td>
		</tr>

	</tbody>
</table>
<div id="chart2"></div>

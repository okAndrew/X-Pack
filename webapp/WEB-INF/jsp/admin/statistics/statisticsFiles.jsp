<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://dreamhost.com/jsp/tags/" prefix="dream"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="res/js/utils.js"></script>
<div id="example"></div>

<table class="table zebra-striped table-hover">
	<thead>
		<tr>
			<td>#</td>
			<td><fmt:message key="Type" bundle="${lang}" /></td>
			<td><fmt:message key="Size" bundle="${lang}" /></td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="type" items="${types}">
			<tr>
				<td></td>
				<td>${type.type}</td>
				<td><dream:formatSize value="${type.size}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<link href="res/css/jquery.jqplot.css" rel="stylesheet" />
<script type="text/javascript" src="res/js/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="res/js/jqplot.pieRenderer.min.js"></script>

<script type="text/javascript">
	
	$(document)
			.ready(

					function plotGist() {
						<fmt:message key="Free_space" var="freespace" bundle="${lang}"/>
						var freespace = "${freespace}";
						<fmt:message key="Employed_space" var="employedspace" bundle="${lang}"/>
						var employedspace = "${employedspace}";
						var free = ${freeSpace}	;
						var employed = ${totalSpace}-free;
						line1 = [
								[ freespace + " - " + free + "GB", free ],
								[ employedspace + " - " + employed + "GB",
										employed ] ];
						<fmt:message key="Space" var="space" bundle="${lang}"/>
						var space = "${space}";
						$.jqplot("example", [ line1 ], {
							title : space,
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

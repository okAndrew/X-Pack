<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="example"></div>

<table class="table zebra-striped table-hover">
	<thead>
		<tr>
			<td>#</td>
			<td>Type</td>
			<td>Size(MB)</td>
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
<link href="res/css/jquery.jqplot.css" rel="stylesheet" />
<script type="text/javascript" src="res/js/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="res/js/jqplot.pieRenderer.min.js"></script>

<script type="text/javascript">
	$(document).ready(
			function plotGist() {
				var free = ${freeSpace};
				var employed = ${totalSpace} - free;
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>

<script type="text/javascript" src="res/js/jquery-latest.js"></script>

<script type="text/javascript" src="res/js/jquery.tablesorter.js"></script>
<script type="text/javascript" src="res/js/jquery.tablesorter.filer.js"></script>
<script type="text/javascript" src="res/js/jquery.tablesorter.pager.js"></script>

<script>
	function toggle(source) {
		checkboxes = document.getElementsByName('checkTariff');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}
</script>

<script type="text/javascript">
	$("tablesorter").ready(function() {
		$("#tablesorter").tablesorter({
			headers : {
				0 : {
					sorter : false
				},
				8 : {
					sorter : false
				}
			},
			widthFixed : true,
		}).tablesorterPager({
			container : $("#pager"),
			positionFixed : false
		}).tablesorterFilter({
			filterContainer : $("#filter"),
			filterColumns : [ 1, 2, 3, 4, 5, 6 ],
			filterCaseSensitive : false
		});
	});
</script>

<link rel="stylesheet" href="res/css/styleTable.css" type="text/css" />

<table id="tablesorter" class="tablesorter">
	<thead>
		<tr>
			<th><input type="checkbox" onClick="toggle(this)" /> <fmt:message
					key="All" bundle="${lang}" /></th>
			<th><fmt:message key="Id" bundle="${lang}" /></th>
			<th><fmt:message key="Name" bundle="${lang}" /></th>
			<th><fmt:message key="Max_Capacity" bundle="${lang}" /></th>
			<th><fmt:message key="Price" bundle="${lang}" /></th>
			<th><fmt:message key="Position" bundle="${lang}" /></th>
			<th><fmt:message key="Description" bundle="${lang}" /></th>
			<th><fmt:message key="isDelete" bundle="${lang}" /></th>
			<th><fmt:message key="Edit" bundle="${lang}" /></th>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="tariff" items="${tariffs}">
			<tr>
				<td><input type="checkbox" name="checkTariff"
					value="${tariff.id}"></td>
				<td>${tariff.id}</td>
				<td>${tariff.name}</td>
				<td>${tariff.maxCapacity}</td>
				<td>${tariff.price}</td>
				<td>${tariff.position}</td>
				<td>${tariff.description}</td>
				<td>${tariff.isDelete}</td>
				<td><a data-toggle="modal" data-target="#editTariffModal">edit</a></td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr id="pager" class="pager">
			<td><img src="res/img/table/first.png" class="first" /> <img
				src="res/img/table/prev.png" class="prev" /> <input type="text"
				class="pagedisplay" /> <img src="res/img/table/next.png"
				class="next" /> <img src="res/img/table/last.png" class="last" />
				<select class="pagesize">
					<option selected="selected" value="10">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="40">40</option>
					<option value="50">50</option>
			</select></td>
		</tr>
	</tfoot>
</table>
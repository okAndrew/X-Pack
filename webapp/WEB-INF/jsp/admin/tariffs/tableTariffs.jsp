<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://dreamhost.com/jsp/tags/" prefix="dream"%>

<script>
	function toggle(source) {
		checkboxes = document.getElementsByName('checkTariff');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}
</script>
<script>
function checkBoxes(sourse){
	toggle(sourse);
	checkboxesStatus();
}
</script>

<table class="table zebra-striped table-hover table-condensed">
	<thead>
		<tr>
			<th><input type="checkbox" onClick="checkBoxes(this)" /> <fmt:message
					key="All" bundle="${lang}" /></th>
			<th onclick="changeOrderBy('id');"><fmt:message key="Id"
					bundle="${lang}" /></th>
			<th onclick="changeOrderBy('name');"><fmt:message key="Name"
					bundle="${lang}" /></th>
			<th onclick="changeOrderBy('max_capacity');"><fmt:message
					key="Max_Capacity" bundle="${lang}" /></th>
			<th onclick="changeOrderBy('price');"><fmt:message key="Price"
					bundle="${lang}" /></th>
			<th onclick="changeOrderBy('position');"><fmt:message
					key="Position" bundle="${lang}" /></th>
			<th onclick="changeOrderBy('description');"><fmt:message
					key="Description" bundle="${lang}" /></th>
			<th onclick="changeOrderBy('is_delete');"><fmt:message
					key="isDelete" bundle="${lang}" /></th>
			<th><fmt:message key="Edit" bundle="${lang}" /></th>
		</tr>
	</thead>

	<tbody>
		<c:forEach var="tariff" items="${tariffs}">
			<tr>
				<td><input type="checkbox" name="checkTariff"
					value="${tariff.id}" onclick="checkboxesStatus()"></td>
				<td>${tariff.id}</td>
				<td>${tariff.name}</td>
				<td><dream:formatSize value="${tariff.maxCapacity}" /></td>
				<td>${tariff.price}</td>
				<td>${tariff.position}</td>
				<td>${tariff.description}</td>
				<td>${tariff.isDelete}</td>
				<td><a data-toggle="modal" data-target="#editTariffModal">
						<span class="glyphicon glyphicon-eye-open blue"></span>
				</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
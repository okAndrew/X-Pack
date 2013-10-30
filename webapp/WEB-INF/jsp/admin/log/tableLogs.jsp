<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	function toggle(source) {
		checkboxes = document.getElementsByName('checkLog');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}
</script>

<table id="tablesorter" class="table zebra-striped table-hover table-condensed">
	<thead>
		<tr>
			<th onclick="changeOrderBy('id');"><fmt:message key="Id" bundle="${lang}" /></th>
			<th onclick="changeOrderBy('date_time');"><fmt:message key="Date" bundle="${lang}" /></th>
			<th onclick="changeOrderBy('logger');"><fmt:message key="Logger" bundle="${lang}" /></th>
			<th onclick="changeOrderBy('lvl');"><fmt:message key="Level" bundle="${lang}" /></th>
			<th onclick="changeOrderBy('msg');"><fmt:message key="Message" bundle="${lang}" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="log" items="${logs}">
		<tr>
			<td>${log.id}</td>
			<td>${log.datetime}</td>
			<td>${log.logger}</td>
			<td>${log.level}</td>
			<td>${log.message}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

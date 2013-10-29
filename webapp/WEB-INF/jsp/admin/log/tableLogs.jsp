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
			<th><input type="checkbox" onClick="toggle(this)" /> <fmt:message
					key="All" bundle="${lang}" /></th>
			<th><fmt:message key="Id" bundle="${lang}" /></th>
			<th><fmt:message key="Date" bundle="${lang}" /></th>
			<th><fmt:message key="Logger" bundle="${lang}" /></th>
			<th><fmt:message key="Level" bundle="${lang}" /></th>
			<th><fmt:message key="Message" bundle="${lang}" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="log" items="${logs}">
			<c:choose>
				<c:when test="${log.level == 'ERROR'}">
					<tr class="danger">
				</c:when>
				<c:when test="${log.level == 'WARNING'}">
					<tr class="warning">
				</c:when>
				<c:when test="${log.level == 'DEBUG'}">
					<tr class="success">
				</c:when>
				<c:otherwise>
					<tr class="active">
				</c:otherwise>
			</c:choose>
			<td><input type="checkbox" name="checkLog" value="${log.id}"></td>
			<td>${log.id}</td>
			<td>${log.datetime}</td>
			<td>${log.logger}</td>
			<td>${log.level}</td>
			<td>${log.message}</td>
		</c:forEach>
	</tbody>
</table>

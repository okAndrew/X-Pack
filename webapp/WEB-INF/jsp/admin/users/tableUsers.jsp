<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://dreamhost.com/jsp/tags/" prefix="dream"%>

<script>
	function toggle(source) {
		checkboxes = document.getElementsByName('checkUser');
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

<table class="table zebra-striped table-hover table-condensed"
	id="tablesorter">
	<thead>
		<tr>
			<th><input type="checkbox" onClick="checkBoxes(this)" /></th>
			<th onclick="changeOrderBy('id');"><fmt:message key="Id"
					bundle="${lang}" /></th>
			<th onclick="changeOrderBy('login');"><fmt:message key="Login"
					bundle="${lang}" /></th>
			<th onclick="changeOrderBy('email');"><fmt:message key="Email"
					bundle="${lang}" /></th>
			<th onclick="changeOrderBy('capacity');"><fmt:message
					key="Capacity" bundle="${lang}" /></th>
			<th onclick="changeOrderBy('id_tariff');"><fmt:message
					key="Tariffs" bundle="${lang}" /></th>
			<th onclick="changeOrderBy('id_role');"><fmt:message key="Type"
					bundle="${lang}" /></th>
			<th onclick="changeOrderBy('is_activated');"><fmt:message
					key="Activated" bundle="${lang}" /></th>
			<th onclick="changeOrderBy('is_banned');">Baned</th>
			<th></th>
		</tr>
	</thead>
	<tbody class="avoid-sort">
		<c:forEach var="user" items="${users}">
			<tr>
				<td><input type="checkbox" name="checkUser" value="${user.id}" onclick="checkboxesStatus()"></td>
				<td>${user.id}</td>
				<td>${user.login}</td>
				<td>${user.email}</td>
				<td><dream:formatSize value="${user.capacity}" /></td>
				<td>${tariffsNames[user.idTariff].name}</td>
				<td>${user.role}</td>
				<td>${user.isActivated}</td>
				<td>${user.isBanned}</td>
				<td><a href="adminUser?userid=${user.id}"> <span
						class="glyphicon glyphicon-eye-open blue"></span></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
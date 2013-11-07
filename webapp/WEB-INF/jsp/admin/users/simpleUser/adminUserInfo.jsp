<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://dreamhost.com/jsp/tags/" prefix="dream"%>

<script type="text/javascript" src="res/js/utils.js"></script>

<jsp:include page="modalAdminUser.jsp"></jsp:include>
<c:if test="${message != null}">
	<script>
		$('#myModal').modal('show')
	</script>
</c:if>

<form action="adminSimpleEmployeeController">
	<table class="table zebra-striped table-hover table-condensed"
		id="tablesorter">
		<tbody class="avoid-sort">
			<tr>
				<td><fmt:message key="User_id" bundle="${lang}" /></td>
				<td>${adminUser.id}</td>
			</tr>
			<tr>
				<td><fmt:message key="User_login" bundle="${lang}" /></td>
				<td>${adminUser.login}</td>
			</tr>
			<tr>
				<td><fmt:message key="Email" bundle="${lang}" /></td>
				<td>${adminUser.email}</td>
			</tr>
			<tr>
				<td><fmt:message key="Tariff" bundle="${lang}" /></td>
				<td>${adminUser.idTariff}</td>
			</tr>
			<tr>
				<td><fmt:message key="Capacity" bundle="${lang}" /></td>
				<td><dream:formatSize value="${adminUser.capacity}" /></td>
			</tr>
			<tr>
				<td><fmt:message key="Activation" bundle="${lang}" /></td>
				<td>${adminUser.isActivated}</td>
			</tr>
			<tr>
				<td><fmt:message key="Banned" bundle="${lang}" /></td>
				<td>${adminUser.isBanned}</td>
			</tr>
			<tr>
				<td><fmt:message key="Role" bundle="${lang}" /></td>
				<td>${adminUser.role}</td>
			</tr>
		</tbody>
	</table>
	<button type="button" class="btn btn-default" data-toggle="modal"
		data-target="#myModal">
		<fmt:message key="Edit" bundle="${lang}" />
	</button>
</form>

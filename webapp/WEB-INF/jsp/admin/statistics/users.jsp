<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	</tbody>
</table>
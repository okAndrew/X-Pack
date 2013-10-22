<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div id="example"></div>
<div>
		<table class="table zebra-striped table-hover">
			<thead>
				<tr>
					<td>#</td>
					<td><fmt:message key="Type" bundle="${lang}" /></td>
					<td><fmt:message key="Size" bundle="${lang}" />(MB)</td>
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
</div>
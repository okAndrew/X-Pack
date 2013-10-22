<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
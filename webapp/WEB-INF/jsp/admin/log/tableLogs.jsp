<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<table id="tablesorter" class="tablesorter">
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

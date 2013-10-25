<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="example"></div>

<table class="table zebra-striped table-hover">
	<thead>
		<tr>
			<td>#</td>
			<td>Type</td>
			<td>Size(MB)</td>
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
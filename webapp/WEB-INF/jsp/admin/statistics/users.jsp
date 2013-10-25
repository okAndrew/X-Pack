<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

 <div id="chart2" style="height: 400px; width: 600px;"></div> 
 <div id="example"></div>
<body>
	<div>
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
				<tr>
					<td><fmt:message key="Online_logged_users" bundle="${lang}" /></td>
					<td>${countUsersLogged}</td>
				</tr>
				<tr>
					<td>Visited on last day / logged</td>
					<td>${visitorsByDay}/${loggedVisitorsByDay}</td>
				</tr>
				<tr>
					<td>Visited to last week / logged</td>
					<td>${visitorsByWeek}/${loggedVisitorsByWeek}</td>
				</tr>
				<tr>
					<td>Visited to last month / logged</td>
					<td>${visitorsByMonth}/${loggedVisitorsByMonth}</td>
				</tr>

			</tbody>
		</table>
	</div>
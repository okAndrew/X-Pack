<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script  src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js" type="text/javascript"></script>
<script type="text/javascript" src="res/js/jquery-latest.js"></script>


<script>
	function toggle(source) {
		checkboxes = document.getElementsByName('checkUser');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}
</script>

<script type="text/javascript">
	$("tablesorter").ready(function() {
		$("#tablesorter").tablesorter({
			headers : {
				0 : {
					sorter : false
				},
				10 : {
					sorter : false
				}
			},
			widthFixed : true,
			})
		.tablesorterPager({container: $("#pager"), positionFixed: false}).
		tablesorterFilter({ filterContainer: $("#filter"),
            filterColumns: [1, 2, 3, 4, 5, 6],
            filterCaseSensitive: false
        });
	});
</script>

<link rel="stylesheet" href="res/css/styleTable.css" type="text/css"/>

					<table id="tablesorter" class="tablesorter">
						<thead>
							<tr>
								<th><input type="checkbox"
									onClick="toggle(this)" /> <fmt:message key="All"
										bundle="${lang}" /></th>
								<th><fmt:message key="Id" bundle="${lang}" /></th>
								<th><fmt:message key="Login" bundle="${lang}" /></th>
								<th><fmt:message key="Email" bundle="${lang}" /></th>
								<th><fmt:message key="Password" bundle="${lang}" /></th>
								<th><fmt:message key="Capacity" bundle="${lang}" /></th>
								<th><fmt:message key="Tariffs" bundle="${lang}" /></th>
								<th><fmt:message key="Type" bundle="${lang}" /></th>
								<th><fmt:message key="Activated" bundle="${lang}" /></th>
								<th>Baned</th>
								<th><fmt:message key="Details" bundle="${lang}" /></th>
							</tr>
						</thead>

						<tbody class="avoid-sort">
							<c:forEach var="user" items="${users}">
								<tr>
									<td class="{sorter: false}"><input type="checkbox"
										name="checkUser" value="${user.id}"></td>
									<td>${user.id}</td>
									<td>${user.login}</td>
									<td>${user.email}</td>
									<td>${user.password}</td>
									<td>${user.capacity}</td>
									<td>${user.idTariff}</td>
									<td>${user.role}</td>
									<td>${user.isActivated}</td>
									<td>${user.isBanned}</td>
									<td><a href="adminUser?userid=${user.id}"><fmt:message
												key="View_more" bundle="${lang}" />...</a></td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr id="pager" class="pager">
								<td><img
									src="res/img/table/first.png" class="first" /> <img
									src="res/img/table/prev.png" class="prev" /> <input
									type="text" class="pagedisplay" /> <img
									src="res/img/table/next.png" class="next" /> <img
									src="res/img/table/last.png" class="last" /> <select
									class="pagesize">
										<option selected="selected" value="10">10</option>
										<option value="20">20</option>
										<option value="30">30</option>
										<option value="40">40</option>
										<option value="50">50</option>
								</select></td>
							</tr>
						</tfoot>
					</table>
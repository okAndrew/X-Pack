<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>DreamHost(Administrator) | Users</title>

	<script	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js" type="text/javascript"></script>
	<script src="res/js/bootstrap.js"></script>
	<script type="text/javascript" src="res/js/utils.js"></script>
	
	<link rel="stylesheet" href="res/css/bootstrap.css" rel="stylesheet" />
	<link rel="stylesheet" href="res/css/style.css" rel="stylesheet" />
	<style type="text/css">
		div.alert {
			width: auto;
			margin-top: 15px;
		}
		span.glyphicon.glyphicon-sort {
			font-size: 8pt;
			text-align: center;
		}
		table thead tr th {
			cursor: pointer;
		}
		.blue {
			color: #428BCA;
		}
		table {
			margin-top: 20px;
		}
		table th, table td {
			overflow: hidden;
		}
		.paginator {
		border: 3px dotted red;
			width: 500px;
		}
	</style>
	
	<script type="text/javascript">
		var page = checkPage("${param.page}");
		var perPage = checkCount("${param.count}");
		var orderBy = checkOrderBy("${param.orderby}");
		var sort = checkSort("${param.sop}");
		
		var pageCount = Math.ceil(parseInt("${usersCount}") / perPage);
		
		document.write("page: " + page + ",<br>count: " + perPage + ",<br>order by: " + orderBy + 
			",<br>sort: " + sort + ",<br>page count: " + pageCount + ",<br>users count: ${usersCount}");
		
		function checkPage(page) {
			var temp = parseInt(page);
			if (temp != NaN && temp >= 0) {
				return temp;
			}
			
			return 0;
		}
		
		function changePerPage(param) {
			perPage = 123;
			window.location.href = generateLink(page);
		}
		
		function checkCount(perPage) {
			var temp = parseInt(perPage);
			if (temp != NaN && temp > 0 && temp < 100) {
				return temp;
			}
			
			return 10;
		}
		
		function checkOrderBy(orderBy) {
			var temp = new String(orderBy).toLocaleLowerCase();
			if (temp != "") {
				return orderBy;
			}
			
			return "id";
		}
		
		function checkSort(sort) {
			var temp = new String(sort).toLocaleLowerCase();
			if (temp == "asc" || temp == "desc") {
				return temp;
			}
			
			return "asc";
		}
		
		function changeSort(sort) {
			var temp = new String(sort);
			if (sort == "asc") {
				temp = "desc";
			} else {
				temp = "asc";
			}
			
			return temp;
		}
		
		function changeOrderBy(order) {
			sort = changeSort(sort);
			orderBy = order;
			window.location.href = generateLink(page);
		}
		
		function render() {
			var link; "<a href=" + generateLink(i) +">";
			var pageStart = page - 2;
			var pageEnd = page + 2;
			
			if (pageStart < 0) {
				pageStart = 0;
			}
			
			if (pageEnd > pageCount - 1) {
				pageEnd = pageCount - 1;
			}
			
			if (page != 0) {
				link = "<li><a href=" + generateLink(i);
				link += "><<</a></li>";
				$('#paginator').append(link);
			}
			
			if (page != 0) {
				link = "<li><a href=" + generateLink(page - 1);
				link += "><</a></li>";
				$('#paginator').append(link);
			}
			
			for (var i = pageStart, j = i + 1; i <= pageEnd; i++, j++) {
				if (page == i) {
					link = "<li class='active'>";
				} else {
					link = "<li>";
				}
				link += "<a href=" + generateLink(i);
				link += ">" + j + "</a></li>";

				if (page == i) {
					$('#paginator').append(link);
				} else {
					$('#paginator').append(link);
				}
			}
			
			if (page != pageCount - 1) {
				link = "<li><a href=" + generateLink(page + 1);
				link += ">></a></li>";
				$('#paginator').append(link);
			}
			
			if (page != pageCount - 1) {
				link = "<li><a href=" + generateLink(pageCount - 1);
				link += ">>></a></li>";
				$('#paginator').append(link);
			}
		}
		
		function generateLink(p) {
			var link = "http://localhost:8080/dreamhost/adminUsersPage?page=" + p;
			link += "&count=" + perPage;
			link += "&orderby=" + orderBy;
			link += "&sop=" + sort;
			
			return link;
		}
	</script>
	
</head>

<body onload="render();">
	<jsp:include page="../../menu.jsp"></jsp:include>
	<jsp:include page="addUserModalPage.jsp"></jsp:include>
	<c:if test="${messageAddUser != null}">
		<script>
			$('#addUserModal').modal('show');
		</script>
	</c:if>
	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<form action="employeeControllerUsers" method="post">
					<div class="btn-group">
						<button type="button" class="btn btn-default" data-toggle="modal" data-target="#addUserModal"> <fmt:message key="Add" bundle="${lang}" /> </button>
						<button type="submit" class="btn btn-default" name="action" value="delete">	<fmt:message key="Delete" bundle="${lang}" /> </button>
						<button type="submit" class="btn btn-default" name="action" value="restore">Restore</button>
						<button type="submit" class="btn btn-default" name="action" value="activated"> <fmt:message key="Activate" bundle="${lang}" /> </button>
						<button type="submit" class="btn btn-default" name="action" value="baned"> <fmt:message key="Ban" bundle="${lang}" /> </button>
						<button type="submit" class="btn btn-default" name="action" value="sendEmailUsers"> <fmt:message key="Send_email" bundle="${lang}" /> </button>
					</div>
					
					<c:if test="${temp != null}">
						<div class="alert alert-warning">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<h4>Warning</h4>
							<h5>Message</h5>
						</div>
					</c:if>
					
					<table class="table zebra-striped table-hover table-condensed"  id="tablesorter">
						<thead>
							<tr>
								<th> <input type="checkbox" onClick="toggle(this)" /> </th>
								<th onclick="changeOrderBy('id');"> <fmt:message key="Id" bundle="${lang}" /> </th>
								<th onclick="changeOrderBy('login');"> <fmt:message key="Login" bundle="${lang}" /> </th>
								<th onclick="changeOrderBy('email');"> <fmt:message key="Email" bundle="${lang}" /> </th>
								<th onclick="changeOrderBy('password');"> <fmt:message key="Password" bundle="${lang}" /> </th>
								<th onclick="changeOrderBy('capacity');"> <fmt:message key="Capacity" bundle="${lang}" /> </th>
								<th onclick="changeOrderBy('id_tariff');"> <fmt:message key="Tariffs" bundle="${lang}" /> </th>
								<th onclick="changeOrderBy('id_role');"> <fmt:message key="Type" bundle="${lang}" /> </th>
								<th onclick="changeOrderBy('is_activated');"> <fmt:message key="Activated" bundle="${lang}" /> </th>
								<th onclick="changeOrderBy('is_banned');"> Baned </th>
								<th >${usersCount}</th>
							</tr>
						</thead>
						<tbody class="avoid-sort">
							<c:forEach var="user" items="${users}">
							<tr>
								<td class="{sorter: false}"><input type="checkbox" name="checkUser" value="${user.id}"></td>
								<td>${user.id}</td>
								<td>${user.login}</td>
								<td>${user.email}</td>
								<td>${user.password}</td>
								<td><script>document.write(bytesToSize(${user.capacity}));</script></td>
								<td>${user.idTariff}</td>
								<td>${user.role}</td>
								<td>${user.isActivated}</td>
								<td>${user.isBanned}</td>
								<td><a href="adminUser?userid=${user.id}"> <span class="glyphicon glyphicon-eye-open blue"></span></a></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
				<div class="paginator">
					<ul id="paginator" class="pagination">
					</ul>
					<div class="btn-group">
						<button type="button" class="btn btn-primary">Rows</button>
						<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="" onclick="changePerPage(10);">10</a></li>
							<li><a href="" onclick="changePerPage(20);">20</a></li>
							<li><a href="" onclick="changePerPage(50);">50</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="images/favicon.png">

<title>Dream Host</title>

<!-- Bootstrap core CSS -->
<link href="res/css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="res/css/navbar.css" rel="stylesheet">
<link href="res/css/style.css" rel="stylesheet" />

<script src="res/js/html5shiv.js"></script>
<script src="res/js/respond.min.js"></script>
<link
	href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
<script type="text/javascript">
	function validateForm() {
		var p1 = document.forms["paybyDate"]["startDate"].value;
		if (p1 == "") {
			setMessage("Fields cannot be empty", errorinfo);
			return false;
		}

		return true;
	}

	function setMessage(message, block) {
		block.style.display = "block";
		block.innerHTML = message;
	}
</script>
</head>

<body>
	<script type="text/javascript"
		src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
		
	</script>
	<script type="text/javascript"
		src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
		
	</script>
	<script type="text/javascript"
		src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
		
	</script>
	<script type="text/javascript"
		src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js">
		
	</script>
	<jsp:include page="adminUserHeader.jsp"></jsp:include>
	<div class="payments-admin-user">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<fmt:message key="Payments" bundle="${lang}" />
			</div>
			<h2 id="tables-condensed"></h2>
			<form action="paymentsByDate" method="post" name="paybyDate"
				onsubmit="return validateForm()">
				<div class="panel-body">
					<button type="submit" class="btn btn-default" name="action"
						value="delete">
						<fmt:message key="Show_payments_for_period" bundle="${lang}" />
					</button>
					<div class="well">
						<input type="date" name="startDate"> <input type="date"
							name="endDate">
					</div>

				</div>
				<table class="table table-condensed">
					<thead>
						<tr>
							<th><fmt:message key="Payment_id" bundle="${lang}" /></th>
							<th><fmt:message key="Description" bundle="${lang}" /></th>
							<th><fmt:message key="Status" bundle="${lang}" /></th>
							<th><fmt:message key="Date" bundle="${lang}" /></th>
							<th><fmt:message key="Avaliable" bundle="${lang}" /></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${listPayments==null}">
							<a href="adminUserPayments"><fmt:message
									key="Back_to_all_payments" bundle="${lang}" /></a>
						</c:if>
						<c:forEach items="${listPayments}" var="payment">
							<tr>
								<td>${payment.id}</td>
								<td>${payment.description}</td>
								<td>${payment.status}</td>
								<td>${payment.date}</td>
								<td>${payment.available}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</div>
</body>
</html>



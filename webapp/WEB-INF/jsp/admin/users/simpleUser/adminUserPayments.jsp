<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

	<script type="text/javascript">
		$(function() {
			$('#datetimepicker4').datetimepicker({
				pickTime : false
			});
		});
	</script>
	<script type="text/javascript">
		$(function() {
			$('#datetimepicker5').datetimepicker({
				pickTime : false

			})
		});
	</script>
	<jsp:include page="adminUserHeader.jsp"></jsp:include>
	<div class="payments-admin-user">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">Payments</div>
			<h2 id="tables-condensed"></h2>
			<form action="adminUserDeleteFile" method="post">
				<div class="panel-body">
					<a href="#">Show payments for period</a>
					<div class="well">
						<div id="datetimepicker4" class="input-append">
							<input data-format="yyyy-MM-dd" type="text"></input> <span
								class="add-on"> <i data-time-icon="icon-time"
								data-date-icon="icon-calendar"> </i>
							</span>
						</div>
						<div id="datetimepicker5" class="input-append">
							<input data-format="yyyy-MM-dd" type="text"></input> <span
								class="add-on"> <i data-time-icon="icon-time"
								data-date-icon="icon-calendar"> </i>
							</span>
						</div>
					</div>

				</div>
				<table class="table table-condensed">
					<thead>
						<tr>
							<th>Payment id</th>
							<th>Description</th>
							<th>Status</th>
							<th>Date</th>
							<th>Avaliable</th>
						</tr>
					</thead>
					<tbody>
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





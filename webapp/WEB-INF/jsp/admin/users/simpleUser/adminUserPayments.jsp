<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="images/favicon.png">

<title>Dream Host</title>
<script src="res/js/bootstrap.js"></script>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/signui.css" rel="stylesheet" />
<link href="res/css/datepicker.css" rel="stylesheet" />
<style>
body {
	padding-top: 40px;
}
</style>
</head>
<body>
	<jsp:include page="adminUserHeader.jsp"></jsp:include>
	<div class="payments-admin-user">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<fmt:message key="Payments" bundle="${lang}" />
			</div>
			<form action="paymentsByDate" method="post" name="paybyDate">
				<div class="panel-body">
					<div class="well">
						<div class="input">
							<div class="input-append date">
								<input data-date-format="yyyy-mm-dd" type="text" class="span2"
									name="startDate" id="dpd1"
									placeholder="<fmt:message key="DateCreated" bundle="${lang}" />">
								<input data-date-format="yyyy-mm-dd" type="text" class="span2"
									name="endDate"
									placeholder="<fmt:message key="DateEnd" bundle="${lang}" />"
									id="dpd2">
								<button type="submit" class="btn btn-default">
									<fmt:message key="Show_payments_for_period" bundle="${lang}" />
								</button>
							</div>
						</div>
					</div>
					<c:if test="${message != null}">
						<div class="errorinfo">${message}</div>
					</c:if>
					<c:if test="${messagePeriod != null}">
						<div class="info">${messagePeriod}</div>
					</c:if>
				</div>
				<c:set var="headerRef" value="${header}" />
				<c:if test="${notFullList}">
					<a href="adminUserPayments"><fmt:message key="AllPayments"
							bundle="${lang}" /></a>
				</c:if>
				<table class="table table-condensed">
					<thead>
						<tr>
							<th><fmt:message key="Payment_id" bundle="${lang}" /></th>
							<th><fmt:message key="Tariff" bundle="${lang}" /></th>
							<th><fmt:message key="DateCreated" bundle="${lang}" /></th>
							<th><fmt:message key="DateEnd" bundle="${lang}" /></th>
							<th><fmt:message key="Price" bundle="${lang}" /></th>
							<th><fmt:message key="Status" bundle="${lang}" /></th>
							<th><fmt:message key="Avaliable" bundle="${lang}" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listPayments}" var="payment">
							<tr>
								<td>${payment.id}</td>
								<td>${payment.tariff}</td>
								<td><fmt:formatDate value="${payment.dateCreated}" /></td>
								<td><fmt:formatDate value="${payment.dateEnd}" /></td>
								<td>${payment.price}</td>
								<td>${payment.status}</td>
								<td>${payment.available}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery-1.7.min.js"></script>
	<script src="res/js/bootstrap-datepicker.js"></script>
	
	<script>
		var nowTemp = new Date();
		var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp
				.getDate(), 0, 0, 0, 0);

		var checkin = $('#dpd1').datepicker({
			defaultDate : now,
			onRender : function(date) {
				return date.valueOf();
			}

		}).on('changeDate', function(ev) {
			if (ev.date.valueOf() > checkout.date.valueOf()) {
				var newDate = new Date(ev.date);
				newDate.setDate(newDate.getDate() + 1);
				checkout.setValue(newDate);
			}
			checkin.hide();
			$('#dpd2')[0].focus();
		}).data('datepicker');

		var checkout = $('#dpd2').datepicker({
			onRender : function(date) {
				return date.valueOf();
			}
		}).on('changeDate', function(ev) {
			checkout.hide();
		}).data('datepicker');
	</script>https://gist.github.com/nareshv/3169847
</body>
</html>
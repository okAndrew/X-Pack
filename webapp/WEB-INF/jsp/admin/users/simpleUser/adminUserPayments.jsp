<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DreamHost(Administrator) | Users</title>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>
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

table th,table td {
	overflow: hidden;
}
</style>

</head>
<body>
	<jsp:include page="../../../menu.jsp"></jsp:include>

	<c:if test="${message != null}">
		<script>
			$('#myModal').modal('show')
		</script>
	</c:if>
	<div class="container">
		<div class="panel panel-default main">
		<div class="panel-heading"><jsp:include
					page="adminUserHeader.jsp"></jsp:include></div>
			<div class="panel-body">
				<form action="userEmployeeController" method="post">
					<div class="btn-toolbar pull-right">
						<c:if test="${notFullList}">
							<button type="submit" class="btn btn-default" name="action"
								value="adminUserPayments">
								<fmt:message key="Back_to_all_payments" bundle="${lang}" />
							</button>
						</c:if>
					</div>
				</form>
				<div class="rowSpace">
					<c:if test="${messagePeriod != null&&listPayments.size()==0}">
						<div><fmt:message key="${messagePeriod}" bundle="${lang}" /></div>
					</c:if>
				</div>
				<c:choose>
					<c:when test="${listPayments.size()>0}">
						<form action="userEmployeeController" method="post"
							name="paybyDate">
							<div class="panel-body">
								<div class="well">
									<div class="input">
										<div class="input-append date">
											<input data-date-format="yyyy-mm-dd" type="text"
												class="span2" name="startDate" id="dpd1"
												placeholder="<fmt:message key="DateCreated" bundle="${lang}" />">
											<input data-date-format="yyyy-mm-dd" type="text"
												class="span2" name="endDate"
												placeholder="<fmt:message key="DateEnd" bundle="${lang}" />"
												id="dpd2">
											<button type="submit" class="btn btn-default" name="action"
												value="paymentsByDate">
												<fmt:message key="Show_payments_for_period" bundle="${lang}" />
											</button>
										</div>
									</div>
								</div>

								<c:if test="${message != null}">
									<div class="alert alert-danger"><fmt:message key="${message}" bundle="${lang}" /></div>
								</c:if>
								<div class="rowSpace">
									<c:if test="${messagePeriod != null}">
										<div><fmt:message key="${messagePeriod}" bundle="${lang}" /></div>
									</c:if>
								</div>
							</div>
							<c:set var="headerRef" value="${header}" />

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
					</c:when>
					<c:otherwise>
						<div class="well">
							<fmt:message key="There_is_no_payments_yet" bundle="${lang}" />
						</div>
					</c:otherwise>
				</c:choose>

			</div>
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
	</script>


</body>
</html>
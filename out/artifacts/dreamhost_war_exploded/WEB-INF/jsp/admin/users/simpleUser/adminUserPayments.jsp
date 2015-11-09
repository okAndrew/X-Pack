<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="res/js/utils.js"></script>
<script src="res/js/bootstrap.js"></script>
<link href="res/css/datepicker.css" rel="stylesheet" />
<c:if test="${message != null}">
	<script>
		$('#myModal').modal('show')
	</script>
</c:if>

<form action="adminUser">
	<div class="panel-body">
	<c:if test="${listPayments.size()>0}">
			<div class="input">
				<div class="input-append date">
					<div class="form-group col-lg-2">
						<input data-date-format="yyyy-mm-dd" type="text"
							class="form-control span2" name="startDate" id="dpd1"
							placeholder="<fmt:message key="DateCreated" bundle="${lang}" />">
					</div>
					<div class="form-group col-lg-2">
						<input data-date-format="yyyy-mm-dd" type="text"
							class="form-control span2" name="endDate"
							placeholder="<fmt:message key="DateEnd" bundle="${lang}" />"
							id="dpd2">
					</div>
					<button type="submit" class="btn btn-default" name="page"
						value="paymentsByDate" id="button" disabled="disabled">
						<fmt:message key="Show_payments_for_period" bundle="${lang}" />
					</button>
					<c:if test="${notFullList}">
						<button type="submit" class="btn btn-default" name="page"
							value="adminUserPayments">
							<fmt:message key="Back_to_all_payments" bundle="${lang}" />
						</button>
					</c:if>
				</div>
			</div>
		</c:if>

		<c:if test="${message != null}">
			<div class="alert alert-danger">
				<fmt:message key="${message}" bundle="${lang}" />
			</div>
		</c:if>
		<div>
			<c:if test="${messagePeriod != null}">
				<div>
					<fmt:message key="${messagePeriod}" bundle="${lang}" />
					<fmt:formatDate value="${startperiod}" />
					-
					<fmt:formatDate value="${endperiod}" />
				</div>
			</c:if>
		</div>
	</div>
	<c:set var="headerRef" value="${header}" />
	<c:choose>
		<c:when test="${listPayments.size()>0}">
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
		</c:when>
		<c:otherwise>
			<div class="well">
				<fmt:message key="There_is_no_payments_yet" bundle="${lang}" />
			</div>
		</c:otherwise>
	</c:choose>
</form>


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
		checkout.setValue(checkin.date.valueOf()+1);
		checkin.hide();
		$('#dpd2')[0].focus();
		$('#button').prop('disabled', false);
	}).data('datepicker');

	var checkout = $('#dpd2').datepicker({
		onRender : function(date) {
			return date.valueOf()<= checkin.date.valueOf() ? 'disabled' : '';
		}
	}).on('changeDate', function(ev) {
		checkout.hide();
		
	}).data('datepicker');
	
</script>

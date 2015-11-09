<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="modal fade" id="createPay" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="Pay" bundle="${lang}" />
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-info"><fmt:message key="Choose_the_time_of_duration_for_your_tariff" bundle="${lang}" /></div>
				<form action="CreatePaymentServlet" method="post" id="form_pay"
					class="form-horizontal">
					<div class="form-group">
						<input type="text" id="tariffId" name="tariffId" hidden="yes" />
						<div class="col-lg-4">
							<span class="label label-primary"><fmt:message
									key="Months" bundle="${lang}" /></span> <select class="form-control"
								name="months">
								<option>1</option>
								<option>3</option>
								<option>6</option>
								<option>12</option>
							</select>
						</div>
					</div>
					<br>
				</form>
				<p><fmt:message key="You_can't_buy_your_tariff_duration_more_than_one_year" bundle="${lang}" /></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="Close" bundle="${lang}" />
				</button>
				<input type="submit" class="btn btn-primary" form="form_pay"
					value=<fmt:message key="Pay" bundle="${lang}" /> />
			</div>
		</div>
	</div>
</div>
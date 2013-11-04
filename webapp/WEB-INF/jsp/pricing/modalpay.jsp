<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="modal fade" id="createPay" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="Pay" bundle="${lang}" />
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-info">
					Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ultrices nibh vitae erat lobortis posuere. Donec dictum suscipit semper. In tincidunt commodo mauris at interdum. Integer vel dolor turpis. Etiam vitae eros diam. Aliquam in.
				</div>
				<form action="CreatePaymentServlet" method="post" id="form_pay">
					<div class="form-group">
						<input type="text" id="tariffId" name="tariffId" hidden="yes" />
						<label><fmt:message key="Months" bundle="${lang}" /></label>
						<select name="months">
							<option>1</option>
							<option>3</option>
							<option>6</option>
							<option>12</option>
						</select>
					</div>
					<p>
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ultrices nibh vitae erat lobortis posuere. Donec dictum suscipit semper. In tincidunt commodo mauris at interdum. Integer vel dolor turpis. Etiam vitae eros diam. Aliquam in.
					</p>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="Close" bundle="${lang}" />
				</button>
				<input type="submit" form="form_pay" value=<fmt:message key="Send" bundle="${lang}" /> />
				<button class="btn btn-primary" id="form_pay_submit"><fmt:message key="Pay" bundle="${lang}" /></button>
			</div>
		</div>
	</div>
</div>
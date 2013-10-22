<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="modal fade" id="editEmail" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="Change_email" bundle="${lang}" />
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-info">
					<fmt:message
						key="Enter_new_email_address_and_secret_code_from_you_old_email_address._To_take_a_secret_code_click_on_'Send_secret_code'"
						bundle="${lang}" />
				</div>
				<div class="form-group">
					<div class="form-group">
						<label><fmt:message key="Old_email_address"
								bundle="${lang}" /></label>
						<div class="input-group">
							<input type="text" class="form-control" placeholder="email"
								value="${sessionScope.user.email}" disabled="disabled">
							<span class="input-group-btn">
								<button class="btn btn-primary" type="button" id="sendCode">
									<fmt:message key="Send_secret_code" bundle="${lang}" />

								</button>
							</span>
						</div>
					</div>
				</div>
				<form id="editEmailForm" method="post" action="EditEmailServlet">
					<div class="form-group">
						<label><fmt:message key="New_email_address"
								bundle="${lang}" /></label> <input name="email" type="email"
							class="form-control" placeholder="New email address"
							id="newEmailAddress">
					</div>
					<div class="form-group">
						<label><fmt:message key="Secret_code" bundle="${lang}" /></label>
						<input name="secretCode" type="text" class="form-control"
							placeholder="Secret code" id="secretCodeInput">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="Close" bundle="${lang}" />

				</button>
				<input type="submit" class="btn btn-primary" id="submitEmailForm"
					value="Send" />
			</div>
		</div>
	</div>
</div>
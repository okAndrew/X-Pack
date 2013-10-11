<div class="modal fade" id="editEmail" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">Change email</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-info">Enter new email address and
					secret code from you old email address. To take a secret code click
					on "Send secret code"</div>
				<div class="form-group">
					<div class="form-group">
						<label>Old email address</label>
						<div class="input-group">
							<input type="text" class="form-control" placeholder="email"
								value="${sessionScope.user.email}" disabled="disabled">
							<span class="input-group-btn">
								<button class="btn btn-primary" type="button" id="sendCode">Send
									secret code</button>
							</span>
						</div>
					</div>
				</div>
				<form id="editEmailForm" method="post" action="EditEmailServlet">
					<div class="form-group">
						<label>New email address</label> <input name="email" type="email"
							class="form-control" placeholder="New email address"
							id="newEmailAddress">
					</div>
					<div class="form-group">
						<label>Secret code</label> <input name="secretCode" type="text"
							class="form-control" placeholder="Secret code"
							id="secretCodeInput">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<input type="submit" class="btn btn-primary" id="submitEmailForm"
					value="Send" />
			</div>
		</div>
	</div>
</div>
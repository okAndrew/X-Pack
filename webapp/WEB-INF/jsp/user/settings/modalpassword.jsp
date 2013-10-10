<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal fade" id="editPassword" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="" method="post" onsubmit="return validatePasswords()"
				name="form-change-password" id="form-change-password">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Change password</h4>
				</div>
				<div class="modal-body">
					<div id="errorinfo" class="alert alert-danger"
						style="display: none;">
						<c:if test="${message != null}">
							${message}
						</c:if>
					</div>
					<div class="form-group">
						<label>Enter your current password</label> <input type="password"
							class="form-control" placeholder="Old password" name="old_pass">
					</div>
					<div class="form-group">
						<label>Choose a new password</label> <input type="password"
							class="form-control" placeholder="New password" name="new_pass">
					</div>
					<div class="form-group">
						<label>Confirm your new password</label> <input type="password"
							class="form-control" placeholder="Confirm new password"
							name="new_pass_r">
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary" onclick="validatePasswords()">Save</button>
				</div>
			</form>
		</div>
	</div>
</div>
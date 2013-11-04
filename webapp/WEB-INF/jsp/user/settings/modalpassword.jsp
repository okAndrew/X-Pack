<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="modal fade" id="editPassword" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="ChangePasswordServlet" method="post"
				onsubmit="return validatePasswords()" name="form-change-password"
				id="form-change-password">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">
						<fmt:message key="Change_password" bundle="${lang}" />
					</h4>
				</div>
				<div class="modal-body">
					<div id="errorinfomodal" class="alert alert-danger" style="display: none;">
					</div>
					<div class="form-group">
						<label><fmt:message key="Enter_your_current_password"
								bundle="${lang}" /></label> <input type="password" class="form-control"
							placeholder="Old password" name="old_pass">
					</div>
					<div class="form-group">
						<label><fmt:message key="Choose_a_new_password"
								bundle="${lang}" /></label> <input type="password" class="form-control"
							placeholder="New password" name="password">
					</div>
					<div class="form-group">
						<label><fmt:message key="Confirm_your_new_password"
								bundle="${lang}" /></label> <input type="password" class="form-control"
							placeholder="Confirm new password" name="password_retype">
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<fmt:message key="Close" bundle="${lang}" />
					</button>
					<button type="submit" class="btn btn-primary"
						onclick="validatePasswords()">
						<fmt:message key="Save" bundle="${lang}" />
					</button>
				</div>
			</form>
		</div>
	</div>
</div>
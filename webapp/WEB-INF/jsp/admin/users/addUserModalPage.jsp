<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="res/js/modal.js"></script>
<script src="res/js/bootstrap.js"></script>

<link href="res/css/style.css" rel="stylesheet" />

<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title"><fmt:message key="Add_new_user" bundle="${lang}" /></h4>
			</div>
			<div class="modal-body">
				<div class="container">
					<!-- Static navbar -->
					<div class="navbar navbar-default">
						<div class="navbar-collapse collapse">
							<form action="employeeControllerUsers" method="post">
								<div id="errorinfo" class="alert alert-danger"
									style="display: none;">
									<c:if test="${message != null}">
											${message}
									</c:if>
								</div>
								<table class="table">
									<tbody>
										<tr>
											<td><fmt:message key="Login" bundle="${lang}" /></td>
											<td><input type="text" name="login"
												class="form-control first" placeholder="Login"
												autofocus="autofocus" /></td>
										</tr>

										<tr>
											<td><fmt:message key="Email" bundle="${lang}" /></td>
											<td><input type="email" name="email"
												class="form-control midle" placeholder="Email" /></td>
										</tr>

										<tr>
											<td><fmt:message key="Password" bundle="${lang}" /></td>
											<td><input type="text" name="password"
												class="form-control midle" placeholder="Password" /></td>
										</tr>

										<tr>
											<td><fmt:message key="Repeat_password" bundle="${lang}" /></td>
											<td><input type="text" name="passwordVerify"
												class="form-control last" placeholder="Password" /></td>
										</tr>
									</tbody>
								</table>
								<button type="submit" class="btn btn-primary" name="action"
									value="add"><fmt:message key="Add_new_user" bundle="${lang}" /></button>
							</form>
						</div>
						<!--/.nav-collapse -->
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="Close" bundle="${lang}" /></button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
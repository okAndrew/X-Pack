<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">Add new user</h4>
			</div>
			<div class="modal-body">
				<div class="container">
					<!-- Static navbar -->
					<div class="navbar navbar-default">
						<div class="navbar-collapse collapse">
							<form action="addUser" method="post">
								<div id="errorinfo" class="alert alert-danger"
									style="display: none;">
									<c:if test="${message != null}">
											${message}
									</c:if>
								</div>
								<table class="table">
									<tbody>
										<tr>
											<td>Login</td>
											<td><input type="text" name="login"
												class="form-control first" placeholder="Login"
												autofocus="autofocus" /></td>
										</tr>

										<tr>
											<td>Email</td>
											<td><input type="email" name="email"
												class="form-control midle" placeholder="Email" /></td>
										</tr>

										<tr>
											<td>Password</td>
											<td><input type="text" name="password"
												class="form-control midle" placeholder="Password" /></td>
										</tr>

										<tr>
											<td>Repeat password</td>
											<td><input type="text" name="passwordVerify"
												class="form-control last" placeholder="Password" /></td>
										</tr>
									</tbody>
								</table>
								<button type="submit" class="btn btn-primary">Add new
									user</button>
							</form>
						</div>
						<!--/.nav-collapse -->
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
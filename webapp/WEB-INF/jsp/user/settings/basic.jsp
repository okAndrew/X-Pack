<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-md-8">
		<h3>Basic info</h3>
		<p>
			Tariff: <strong>Looser tariff</strong>
		</p>
		<p>
			Free space: <strong>194.45Mb</strong>
		</p>
		<h3>Edit profile</h3>
		<div class="panel panel-default">
			<div class="panel-body">
				<div id="settingsErrorDiv" class="alert alert-danger"
					style="display: none;">
					<c:if test="${message != null}">
						${message}
					</c:if>
				</div>
				<form action="EditUserLoginServlet" method="post">
					<div class="form-group">
						<input type="email" name="email"
							value="${sessionScope.user.email}" hidden /> <label>Login</label>
						<div class="input-group">
							<input id="inputLogin" type="text" class="form-control"
								placeholder="Login" value="${sessionScope.user.login}"
								name="login"> <span class="input-group-btn">
								<button class="btn btn-primary" type="submit"
									onclick="return checkChangeLoginForm()">Edit</button>
							</span>
						</div>
					</div>
				</form>


				<div class="form-group">
					<a data-toggle="modal" href="#editEmail">Change email</a>
				</div>
				<div class="form-group">
					<a data-toggle="modal" href="#editPassword"
						onclick="formPassReset()">Change password</a>
				</div>
			</div>
		</div>
	</div>
</div>
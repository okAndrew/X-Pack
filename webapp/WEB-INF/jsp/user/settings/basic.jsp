<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="row">
	<div class="col-md-8">
		<h3><fmt:message key="Basic_info" bundle="${lang}" /></h3>
		<p>
			<fmt:message key="Tariff" bundle="${lang}" />
			: <strong>${tariff.name}</strong>
		</p>
		<p>
			<fmt:message key="Free_space" bundle="${lang}" />
			: <strong>
			<script type="text/javascript">
				document.write(bytesToSize(${tariff.maxCapacity - user.capacity}));
			</script>
			</strong>
		</p>
		<h3>
			<fmt:message key="Edit_profile" bundle="${lang}" />
		</h3>
		<div class="panel panel-default">
			<div class="panel-body">
				<c:if test="${message != null}">
					<div id="errorinfo" class="alert alert-danger">
						<fmt:message key="${message}" bundle="${lang}" />
					</div>
				</c:if>
				<form action="EditUserLoginServlet" method="post">
					<div class="form-group">
						<input type="email" name="email" value="${user.email}"
							hidden="yes" /> <label><fmt:message key="Login"
								bundle="${lang}" /></label>
						<div class="input-group">
							<input id="inputLogin" type="text" class="form-control"
								placeholder="Login" value="${user.login}" name="login">
							<span class="input-group-btn">
								<button class="btn btn-primary" type="submit"
									onclick="return checkChangeLoginForm()">
									<fmt:message key="Edit" bundle="${lang}" />
								</button>
							</span>
						</div>
					</div>
				</form>
				<c:if test="${editLoginError != null}">
					<div class="alert alert-danger">
						<p><fmt:message key="${editLoginError}" bundle="${lang}" /></p>
					</div>
				</c:if>
				<form action="EditEmailServlet" method="post">
					<div class="form-group">
						<input type="email" name="oldEmail" value="${user.email}"
							hidden="yes" /> <label><fmt:message key="Email"
								bundle="${lang}" /></label>
						<div class="input-group">
							<input id="email" type="newEmail" class="form-control"
								placeholder="New email" name="newEmail"> <span
								class="input-group-btn">
								<button class="btn btn-primary" type="submit">
									<fmt:message key="Edit" bundle="${lang}" />
								</button>
							</span>
						</div>
					</div>
				</form>
				<c:if test="${editEmailError != null}">
					<div class="alert alert-danger" >
						<p><fmt:message key="${editEmailError}" bundle="${lang}" /></p>
					</div>
				</c:if>
				<!-- <div class="form-group">
					<a data-toggle="modal" href="#editEmail"><fmt:message
							key="Change_email" bundle="${lang}" /></a>
				</div> -->
				<div class="form-group">
					<a data-toggle="modal" href="#editPassword"
						onclick="formPassReset()"><fmt:message key="Change_password"
							bundle="${lang}" /></a>
				</div>
			</div>
		</div>
	</div>
</div>
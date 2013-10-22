<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="row">
	<div class="col-md-8">
		<h3>Basic info</h3>
		<c:forEach var="tariff" items="${tariffs}">
			<c:if test="${sessionScope.user.idTariff == tariff.id}">
				<p>
					<fmt:message key="Tariff" bundle="${lang}" />
					: <strong>${tariff.name} (${tariff.maxCapacity}Mb)</strong>
				</p>
			</c:if>
		</c:forEach>

		<p>
			<fmt:message key="Free_space" bundle="${lang}" />
			: <strong>194.45Mb</strong>
		</p>
		<h3>
			<fmt:message key="Edit_profile" bundle="${lang}" />
		</h3>
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
							value="${sessionScope.user.email}" hidden="yes" /> <label><fmt:message
								key="Login" bundle="${lang}" /></label>
						<div class="input-group">
							<input id="inputLogin" type="text" class="form-control"
								placeholder="Login" value="${sessionScope.user.login}"
								name="login"> <span class="input-group-btn">
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
						<p>${editLoginError}</p>
					</div>
				</c:if>
				<div class="form-group">
					<a data-toggle="modal" href="#editEmail"><fmt:message
							key="Change_email" bundle="${lang}" /></a>
				</div>
				<div class="form-group">
					<a data-toggle="modal" href="#editPassword"
						onclick="formPassReset()"><fmt:message key="Change_password"
							bundle="${lang}" /></a>
				</div>
			</div>
		</div>
	</div>
</div>
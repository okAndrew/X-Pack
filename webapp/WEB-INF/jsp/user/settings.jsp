<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost | Settings</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<script src="res/js/settings.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu.jsp"></jsp:include>
	<jsp:include page="settings/modalemail.jsp"></jsp:include>
	<jsp:include page="settings/modalpassword.jsp"></jsp:include>

	<div class="container">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-3">
						<ul class="nav nav-pills nav-stacked">
							<li class="active"><a href="#tabs1-pane1" data-toggle="tab">Personal
									information</a></li>
							<li><a href="#tabs1-pane2" data-toggle="tab">Service</a></li>
							<li><a href="#tabs1-pane3" data-toggle="tab">...</a></li>
						</ul>
					</div>
					<div class="col-md-6">
						<div class="tab-content">
							<div class="tab-pane active" id="tabs1-pane1">
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

										<!-- EDIT LOGIN FORM -->
										<form action="EditUserLoginServlet" method="post">
											<div class="form-group">
												<input type="email" name="email" value="${sessionScope.user.email}"
													hidden />
												<label>Login</label>
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
							<div class="tab-pane" id="tabs1-pane2">
								<jsp:include page="settings/tariffs.jsp"></jsp:include>
							</div>
							<div class="tab-pane" id="tabs1-pane3">
								<p>Other</p>
							</div>
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
			<div class="panel-footer">Footer</div>
		</div>
	</div>
</body>
</html>
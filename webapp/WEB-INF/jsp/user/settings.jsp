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
<script type="text/javascript">
	function validatePasswords() {
		var p1 = ${sessionScope.user.password};
		var p2 = document.forms["form-signin"]["old_pass"].value;
		var p3 = document.forms["form-signin"]["new_pass"].value;
		var p4 = document.forms["form-signin"]["new_pass_r"].value;
		var errorinfo = document.getElementById("errorinfo");
		
		errorinfo.style.display = "none";
		
		if(p1 != p2) {
			setMessage("Old password incorrect", errorinfo);
			return false;
		}
		
		if (p1 == "" || p2 == "" || p3 == "" || p3 == "") {
			setMessage("Fields cannot be empty", errorinfo);
	  		return false;
		}
		
		if(p3 != p4) {
			setMessage("New passwords are different", errorinfo);
			return false;
		}
		
		return true;
	}
	
	function setMessage(message, block) {
		block.style.display = "block";
		block.innerHTML = message;
	}
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu.jsp"></jsp:include>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="row">
					<div class="col-md-3">
						<ul class="nav nav-pills nav-stacked">
							<li class="active"><a href="#tabs1-pane1" data-toggle="tab">Personal
									informations</a></li>
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
										<form role="form">
											<div class="form-group">
												<label>Login</label>
												<div class="input-group">
													<input type="text" class="form-control" placeholder="Login"
														value="${sessionScope.user.login}"> <span
														class="input-group-btn">
														<button class="btn btn-primary" type="submit">Edit</button>
													</span>
												</div>
											</div>
										</form>
										<div class="form-group">
											<a data-toggle="modal" href="#editEmail">Change email</a>
										</div>
										<div class="form-group">
											<a data-toggle="modal" href="#editPassword">Change
												password</a>
										</div>
										<div class="modal fade" id="editEmail" tabindex="-1"
											role="dialog" aria-labelledby="myModalLabel"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;</button>
														<h4 class="modal-title">Change email</h4>
													</div>
													<div class="modal-body">
														<div class="alert alert-info">Enter new email
															address and secret code from you old email address. To
															take a secret code click on "Send secret code"</div>
														<form>
															<div class="form-group">
																<div class="form-group">
																	<label>Old email address</label>
																	<div class="input-group">
																		<input type="text" class="form-control"
																			placeholder="email"
																			value="${sessionScope.user.email}"
																			disabled="disabled"> <span
																			class="input-group-btn">
																			<button class="btn btn-primary" type="submit">Send
																				secret code</button>
																		</span>
																	</div>
																</div>
																<label>New email address</label> <input type="email"
																	class="form-control" placeholder="New email address">
															</div>
															<div class="form-group">
																<label>Secret code</label> <input type="text"
																	class="form-control" placeholder="Secret code">
															</div>
														</form>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Close</button>
														<button type="button" class="btn btn-primary">Save</button>
													</div>
												</div>
											</div>
										</div>
										<div class="modal fade" id="editPassword" tabindex="-1"
											role="dialog" aria-labelledby="myModalLabel"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<form action="" method="post"
														onsubmit="return validatePasswords()" name="form-signin">
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
																<label>Enter your current password</label> <input
																	type="password" class="form-control"
																	placeholder="Old password" name="old_pass">
															</div>
															<div class="form-group">
																<label>Choose a new password</label> <input
																	type="password" class="form-control"
																	placeholder="New password" name="new_pass">
															</div>
															<div class="form-group">
																<label>Confirm your new password</label> <input
																	type="password" class="form-control"
																	placeholder="Confirm new password" name="new_pass_r">
															</div>
														</div>

														<div class="modal-footer">
															<button type="button" class="btn btn-default"
																data-dismiss="modal">Close</button>
															<button type="submit" class="btn btn-primary">Save</button>
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane" id="tabs1-pane2">
								<p>Tariffs</p>
							</div>
							<div class="tab-pane" id="tabs1-pane3">
								<p>Other</p>
							</div>
						</div>
					</div>
					<div class="col-md-3"></div>
				</div>
			</div>
			<div class="panel-footer">Foolter</div>
		</div>
	</div>
</body>
</html>
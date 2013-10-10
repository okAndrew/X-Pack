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
											<a data-toggle="modal" href="#editPassword"
												onclick="formPassReset()">Change password</a>
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
	<script type="text/javascript">
		var $email = "${sessionScope.user.email}";
		$("#sendCode").click(function() {
			$.ajax({
				type : "POST",
				data : {
					email : "savruksergiy@gmail.com"
				},
				url : "http://localhost:8080/dreamhost/editEmailForm"
			});
		});
	</script>
	<script type="text/javascript">
		var newEmailAddress = document.getElementById("newEmailAddress");
		var secretCodeInput = document.getElementById("secretCodeInput");

		$('#submitEmailForm').click(function() {
			$.ajax({
				type : "POST",
				data : {
					email : newEmailAddress,
					code : secretCodeInput
				},
				url : "http://localhost:8080/dreamhost/EditEmailServlet"
			});
		});
	</script>
	<script type="text/javascript">
		function validatePasswords() {
			var p1 = $
			{
				sessionScope.user.password
			}
			;
			var p2 = document.forms["form-change-password"]["old_pass"].value;
			var p3 = document.forms["form-change-password"]["new_pass"].value;
			var p4 = document.forms["form-change-password"]["new_pass_r"].value;
			var errorinfo = document.getElementById("errorinfo");

			if (p1 != p2) {
				setMessage("Old password incorrect", errorinfo);
				return false;
			}

			if (p1 == "" || p2 == "" || p3 == "" || p3 == "") {
				setMessage("Fields cannot be empty", errorinfo);
				return false;
			}

			if (p3 != p4) {
				setMessage("New passwords are different", errorinfo);
				return false;
			}

			return true;
		}

		function setMessage(message, block) {
			block.style.display = "block";
			block.innerHTML = message;
		}

		function formPassReset() {
			document.getElementById("errorinfo").innerHTML = "";
			document.getElementById("errorinfo").style.display = "none";
			document.getElementById("form-change-password").reset();
		}
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/signui.css" rel="stylesheet" />
<script type="text/javascript">
	function validateForm() {
		var p1 = document.forms["updateForm"]["userLogin"].value;
		var email = document.forms["updateForm"]["userEmail"].value;
		var atpos = email.indexOf("@");
		var dotpos = email.lastIndexOf(".");
		
		if (atpos < 1 || dotpos < atpos+2 || dotpos + 2 > email.length) {
			setMessage("Not a valid e-mail address", errorinfo);
			return false;
		}
		if (p1 == "") {
			setMessage("Fields cannot be empty", errorinfo);
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
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">
						<fmt:message key="Update_user" bundle="${lang}" />
					</h4>
				</div>

				<div class="modal-body">
					<div class="container">
						<!-- Static navbar -->
						<div class="navbar navbar-default">
							<div>
								<form action="updateUser" name="updateForm" method="post"
									onsubmit="return validateForm()">
									<div id="errorinfo" class="alert alert-danger"style="display:${(message != null) ? 'block' : 'none'}">
										<c:if test="${message != null}">
											${message}
										</c:if>
									</div>
									<table class="table">
										<tbody>
											<tr>
												<td><fmt:message key="User_id" bundle="${lang}" /></td>
												<td><input type="text" name="userIdHolder"
													class="form-control first" value="${user.id}"
													autofocus="autofocus" readonly></td>
											</tr>
											<tr>
												<td><fmt:message key="Login" bundle="${lang}" /></td>
												<td><input type="text" name="userLogin"
													class="form-control first" value="${user.login}"
													autofocus="autofocus"></td>
											</tr>
											<tr>
												<td><fmt:message key="Email" bundle="${lang}" /></td>
												<td><input type="text" name="userEmail" id="userEmail"
													class="form-control first" value="${user.email}"
													autofocus="autofocus" /></td>
												<!--  onkeyup="check(this.value, '${user.id}');">
												<div id="mydiv"></div>-->
											</tr>
											<tr>
												<td><fmt:message key="Tariff" bundle="${lang}" /></td>
												<td><input type="text" name="userIdTariff"
													class="form-control first" value="${user.idTariff}"
													autofocus="autofocus" readonly></td>
											</tr>
											<tr>
												<td><fmt:message key="Capacity" bundle="${lang}" /></td>
												<td><input type="text" class="form-control first"
													value="${user.capacity}" autofocus="autofocus" disabled></td>
											</tr>

											<tr>
												<td><fmt:message key="Activation" bundle="${lang}" /></td>
												<td><input type="text" name="userActivation"
													id="userActivation" class="form-control last"
													value="${user.isActivated}" autofocus="autofocus"></td>
											</tr>
											<tr>
												<td><fmt:message key="Banned" bundle="${lang}" /></td>
												<td><input type="text" name="userBanned"
													id="userBanned" class="form-control last"
													value="${user.isBanned}" autofocus="autofocus"></td>
											</tr>
											<tr>
												<td><fmt:message key="Role" bundle="${lang}" /></td>
												<td><input type="text" name="userRole" id="userRole"
													class="form-control last" value="${user.role}"
													autofocus="autofocus"></td>
											</tr>

										</tbody>
									</table>
									<button type="submit" class="btn btn-primary ">
										<fmt:message key="Save_changes" bundle="${lang}" />
									</button>
								</form>

							</div>
							<!--/.nav-collapse -->
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<fmt:message key="Close" bundle="${lang}" />
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


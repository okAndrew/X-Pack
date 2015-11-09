<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://dreamhost.com/jsp/tags/" prefix="dream"%>

<script type="text/javascript" src="res/js/modal.js"></script>
<script src="res/js/bootstrap.js"></script>
<link href="res/css/style.css" rel="stylesheet" />

<script type="text/javascript">
	function validateForm() {
		var p1 = document.forms["updateForm"]["userLogin"].value;
		var email = document.forms["updateForm"]["userEmail"].value;
		var atpos = email.indexOf("@");
		var dotpos = email.lastIndexOf(".");
		
		if (atpos < 1 || dotpos < atpos+2 || dotpos + 2 > email.length) {
			<fmt:message key="Not_a_valid_e-mail_address" var="email" bundle="${lang}"/>
			var email = "${email}";
			setMessage(email, errorinfo);
			return false;
		}
		if (p1 == "") {
			<fmt:message key="Fields_cannot_be_empty" var="fields" bundle="${lang}"/>
			var fields = "${fields}";
			setMessage(fields, errorinfo);
	  		return false;
		}
		return true;
	}
	function setMessage(message, block) {
		block.style.display = "block";
		block.innerHTML = message;
	}
	</script>
<div class="modal fade" id="myModal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="Update_user" bundle="${lang}" />
				</h4>
			</div>
			<div class="modal-body" >
				<div class="container">
					<form action="adminUser">
						<div class="navbar navbar-default">
							<c:if test="${message != null}">
								<div id="errorinfo" class="alert alert-danger"
									style="display:${(message != null) ? 'block' : 'none'}">
									<fmt:message key="${message}" bundle="${lang}" />
								</div>
							</c:if>
							<table class="table">
								<tbody>
									<tr>
										<td><fmt:message key="User_id" bundle="${lang}" /></td>
										<td><input type="text" name="userIdHolder"
											class="form-control first" value="${adminUser.id}"
											autofocus="autofocus" readonly></td>
									</tr>
									<tr>
										<td><fmt:message key="Login" bundle="${lang}" /></td>
										<td><input type="text" name="userLogin"
											class="form-control first" value="${adminUser.login}"
											autofocus="autofocus"></td>
									</tr>
									<tr>
										<td><fmt:message key="Email" bundle="${lang}" /></td>
										<td><input type="text" name="userEmail" id="userEmail"
											class="form-control first" value="${adminUser.email}"
											autofocus="autofocus" /></td>
									</tr>
									<tr>
										<td><fmt:message key="Tariff" bundle="${lang}" /></td>
										<td><input type="text" name="userIdTariff"
											class="form-control first" value="${adminUser.idTariff}"
											autofocus="autofocus" readonly></td>
									</tr>
									<tr>
										<td><fmt:message key="Capacity" bundle="${lang}" /></td>
										<td><input type="text" class="form-control first"
											value='<dream:formatSize value="${adminUser.capacity}" />'
											autofocus="autofocus" disabled></td>
									</tr>

									<tr>
										<td><fmt:message key="Activation" bundle="${lang}" /></td>
										<td><input type="text" name="userActivation"
											id="userActivation" class="form-control last"
											value="${adminUser.isActivated}" autofocus="autofocus"></td>
									</tr>
									<tr>
										<td><fmt:message key="Banned" bundle="${lang}" /></td>
										<td><input type="text" name="userBanned" id="userBanned"
											class="form-control last" value="${adminUser.isBanned}"
											autofocus="autofocus"></td>
									</tr>
									<tr>
										<td><fmt:message key="Role" bundle="${lang}" /></td>
										<td><input type="text" name="userRole" id="userRole"
											class="form-control last" value="${adminUser.role}"
											autofocus="autofocus"></td>
									</tr>
								</tbody>
							</table>
						</div>
						<button type="button" class="btn btn-default pull-right"
							data-dismiss="modal">
							<fmt:message key="Close" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-primary pull-right"
							name="page" value="updateUser">
							<fmt:message key="Save_changes" bundle="${lang}" />
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

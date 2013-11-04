<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DreamHost | Sign in</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/signui.css" rel="stylesheet" />
<script type="text/javascript">
	function validateForm() {
		var email = document.forms["form-signup"]["email"].value;
		var atpos = email.indexOf("@");
		var dotpos = email.lastIndexOf(".");
		
		if (atpos < 1 || dotpos < atpos+2 || dotpos + 2 > email.length) {
			setMessage("Not a valid e-mail address", errorinfo);
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
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="container">
		<form action="SendRestorePass" method="post" class="form-signin" name="form-signup" onsubmit="return validateForm()">
			<h2 class="form-signin-heading"> <fmt:message key="Please_sign_in" bundle="${lang}" /> </h2>
			<div id="errorinfo" class="alert alert-danger" style="display: none;">
				<c:if test="${message != null}">
					<fmt:message key="${message}" bundle="${lang}" />
				</c:if>
			</div>
			<c:if test="${message != null}">
				<div class="errorinfo"><fmt:message key="${message}" bundle="${lang}" /></div>
			</c:if>
			<input id="emailInput" type="text" name="email"	class="form-control first last" placeholder="Email" autofocus="autofocus">
			<button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="Send" bundle="${lang}" /></button>
		</form>
	</div>
</body>
</html>
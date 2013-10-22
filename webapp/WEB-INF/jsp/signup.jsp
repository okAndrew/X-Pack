<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>DreamHost | Sign up</title>
	
	<link href="res/css/bootstrap.css" rel="stylesheet" />
	<link href="res/css/style.css" rel="stylesheet" />
	<link href="res/css/signui.css" rel="stylesheet" />
	<script type="text/javascript">
	function mainFunc() {
		alert("on load();");
	}
	function validateForm() {
		var p1 = document.forms["form-signin"]["password"].value;
		var p2 = document.forms["form-signin"]["passwordVerify"].value;
		var login = document.forms["form-signin"]["login"].value;
		var email = document.forms["form-signin"]["email"].value;
		var errorinfo = document.getElementById("errorinfo");
		var atpos = email.indexOf("@");
		var dotpos = email.lastIndexOf(".");
		
		errorinfo.style.display = "none";
		
		if (atpos < 1 || dotpos < atpos+2 || dotpos + 2 > email.length) {
			setMessage("Not a valid e-mail address", errorinfo);
			return false;
		}
		
		if (p1 == "" || p2 == "" || login == "") {
			setMessage("Fields cannot be empty", errorinfo);
	  		return false;
		}
		
		if(p1 != p2) {
			setMessage("Passwords are different", errorinfo);
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
		<form action="signup" method="post" class="form-signin" name="form-signin" onsubmit="return validateForm()">
			<h2 class="form-signin-heading"><fmt:message key="New_to_DreamHost_Sign_up" bundle="${lang}" /></h2>
			<div id="errorinfo" class="alert alert-danger" style="display: none;">
				<c:if test="${message != null}">
					${message}
				</c:if>
			</div>
			<input type="text" name="login" class="form-control first" placeholder="Login" autofocus />
			<input type="text" name="email" class="form-control middle" placeholder="Email address" />
			<input type="password" name="password" class="form-control middle" placeholder="Password" />
			<input type="password" name="passwordVerify" class="form-control last" placeholder="Password" />
			<button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="signup" bundle="${lang}" /></button>
  		</form>
 </div>
</body>
</html>



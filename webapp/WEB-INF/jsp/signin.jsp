<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost | Sign in</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/signui.css" rel="stylesheet" />
	<script type="text/javascript">
	function validateForm() {
		var p1 = document.forms["form-signup"]["password"].value;
		var email = document.forms["form-signup"]["email"].value;
		var atpos = email.indexOf("@");
		var dotpos = email.lastIndexOf(".");
		
		if (atpos < 1 || dotpos < atpos+2 || dotpos + 2 > email.length) {
			alert("Not a valid e-mail address");
			return false;
		}
		
		if (p1 == "") {
	  		alert("Fields cannot be empty");
	  		return false;
		}
		
		return true;
	}
	</script>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="container">
		<form action="signin" method="post" class="form-signin" name="form-signup" onsubmit="return validateForm()">
			<h2 class="form-signin-heading">Please sign in</h2>
			<c:if test="${message != null}">
				<div class="errorinfo">${message}</div>
			</c:if>
			<input type="text" name="email" class="form-control first" placeholder="Email" autofocus="autofocus">
  			<input type="password" name="password" class="form-control last" placeholder="Password">
  			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost(Administrator) | Users</title>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<script type="text/javascript" src="res/js/utils.js"></script>

<link rel="stylesheet" href="res/css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="res/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="res/css/adminuserpage.css" rel="stylesheet" />

<script type="text/javascript">
	var page = checkPage("${param.page}");
	var perPage = checkCount("${param.count}");
	var orderBy = checkOrderBy("${param.orderby}");
	var sort = checkSort("${param.sop}");
	var pageCount = Math.ceil(parseInt("${usersCount}") / perPage);
	var linkVar = "adminUsersPage";
</script>

<script type="text/javascript">
	$("#addNewUserSubmit").click(function() {
		alert("test");
		$("#addNewUser").submit();
	});
</script>

</head>
<body onload="render();">
	<jsp:include page="../../menu.jsp"></jsp:include>
	

	<c:if test="${messageAddUser != null}">
		<script>
			$('#addUserModal').modal('show');
		</script>
	</c:if>

	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<form action="employeeControllerUsers" method="post">
					<div class="btn-group">
						<button type="button" class="btn btn-default" data-toggle="modal" data-target="#addUserModal"> <fmt:message key="Add" bundle="${lang}" /> </button>
						<button type="submit" class="btn btn-default" name="action"
							value="delete">
							<fmt:message key="Delete" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="restore">Restore</button>
						<button type="submit" class="btn btn-default" name="action"
							value="activated">
							<fmt:message key="Activate" bundle="${lang}" />
						</button>
						<button type="submit" class="btn btn-default" name="action"
							value="baned">
							<fmt:message key="Ban" bundle="${lang}" />
						</button>
						<button type="button" class="btn btn-default" data-toggle="modal"
							data-target="#sendEmailModal">
							<fmt:message key="Send_email" bundle="${lang}" />
						</button>
					</div>

					<c:if test="${message != null}">
						<div class="alert alert-warning">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<h4>Warning</h4>
							<h5>${message}</h5>
						</div>
					</c:if>
					<jsp:include page="tableUsers.jsp"></jsp:include>
					<jsp:include page="addUserModalPage.jsp"></jsp:include>					
					<jsp:include page="sendEmailModalPage.jsp"></jsp:include>
				</form>
				<jsp:include page="../../paginator.jsp"></jsp:include>
			</div>
		</div>
	</div>
</body>
</html>
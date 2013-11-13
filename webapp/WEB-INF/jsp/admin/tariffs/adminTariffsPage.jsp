<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DreamHost(Administrator) | Tariffs</title>

<link rel="stylesheet" href="res/css/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="res/css/style.css" rel="stylesheet" />

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<script type="text/javascript" src="res/js/utils.js"></script>

<script type="text/javascript">
	var page = checkPage("${param.page}");
	var perPage = checkCount("${param.count}");
	var orderBy = checkOrderBy("${param.orderby}");
	var sort = checkSort("${param.sop}");
	var pageCount = Math.ceil(parseInt("${tariffsCount}") / perPage);
	var linkVar = "adminTariffsPage";
</script>

<script>
function checkboxesStatus() {
	  var checkboxes = document.getElementsByName('checkTariff');
	  for ( var i = 0, n = checkboxes.length; i < n; i++) {
	   if (checkboxes[i].checked === true) {
		     $('#isActivate').prop('disabled', false);
		     $('#isDelete').prop('disabled', false);
	    return;
	   }
	  }
	     $('#isActivate').prop('disabled', true);
	     $('#isDelete').prop('disabled', true);
	 }
</script>

<script type="text/javascript">
window.onload = function() {
	var el = document.getElementById("menu_tariffs");
    el.className="active";
};
</script>

</head>
<body onload="render();">
	<jsp:include page="../../menu.jsp"></jsp:include>

	<c:if test="${addTarMessage != null}">
		<script>
			$('#addTariffModal').modal('show');
		</script>
	</c:if>

	<c:if test="${editTarMessage != null}">
		<script>
			$('#editTariffModal').modal('show');
		</script>
	</c:if>

	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<form action="employeeControllerTariffs" method="post">
					<div class="btn-group">
						<button data-toggle="modal" data-target="#addTariffModal"
							class="btn btn-default">
							<fmt:message key="Add" bundle="${lang}" />
						</button>
						<button disabled="disabled" type="submit" class="btn btn-default" id="isActivate" name="action"
							value="isActivate">
							<fmt:message key="Activate" bundle="${lang}" />
						</button>
						<button disabled="disabled" type="submit" class="btn btn-default" id="isDelete" name="action"
							value="isDelete">
							<fmt:message key="Delete" bundle="${lang}" />
						</button>
					</div>
					<c:if test="${message != null}">
						<div class="alert alert-warning">
							<button type="button" class="close" data-dismiss="alert">&times;</button>
							<h4><fmt:message key="Warning" bundle="${lang}" /></h4>
							<h5><fmt:message key="${message}" bundle="${lang}" /></h5>
						</div>
					</c:if>
					<jsp:include page="tableTariffs.jsp"></jsp:include>
					<jsp:include page="addTariffModalPage.jsp"></jsp:include>
					<jsp:include page="editTariffModalPage.jsp"></jsp:include>
				</form>
				<jsp:include page="../../paginator.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		document.getElementById("menu_tariffs").className = "active";
	</script>
</body>
</html>
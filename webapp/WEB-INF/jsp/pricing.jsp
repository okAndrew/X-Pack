<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DreamHost | Pricing</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="container">
		<div class="panel panel-default">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<c:forEach var="tariff" items="${tariffs}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4>${tariff.name} - ${tariff.maxCapacity}Mb</h4>
							</div>
							<div class="panel-body">
								<p>${tariff.description}</p>
								<h4>${tariff.price}$</h4>
								<form action="CreatePaymentServlet" method="post">
									<input type="text" name="id" value="${sessionScope.user.id}"
										hidden /> <input type="text" name="tariff"
										value="${tariff.id}" hidden />
									<c:choose>
										<c:when test="${sessionScope.user.idTariff == tariff.id}">
											<button type="submit" class="btn btn-success"
												disabled="disabled">Buy it now</button>
										</c:when>
										<c:otherwise>
											<button type="submit" class="btn btn-success">Buy it
												now</button>
										</c:otherwise>
									</c:choose>
								</form>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="col-md-3"></div>
			</div>
		</div>
	</div>
</body>
</html>
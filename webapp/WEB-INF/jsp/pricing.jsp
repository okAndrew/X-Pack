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
<link href="res/css/pricing.css" rel="stylesheet" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<script type="text/javascript">
window.onload = function() {
	var el = document.getElementById("menu_pricing");
    el.className="active";
};
function set(targetElementId, id) {
	document.getElementById(targetElementId).setAttribute('value',id);
}
$("#form_pay_submit").click( function() {
    $('#form_pay').submit();
});
</script>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<jsp:include page="pricing/modalpay.jsp"></jsp:include>
	<div class="container">
		<div class="panel panel-default main">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<c:forEach var="tariff" items="${tariffs}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4>
									${tariff.name} - ${tariff.maxCapacity}Mb
									<c:if test="${currentTariff.id == tariff.id}">(current)</c:if>
								</h4>
							</div>
							<div class="panel-body">
								<p>${tariff.description}</p>
								<form action="CreatePaymentServlet" method="post">
									<input type="text" name="id" value="${sessionScope.userid}" hidden />
									<input type="text" name="tariff" value="${tariff.id}" hidden />
									<c:choose>
										<c:when test="${currentTariff.id == tariff.id}">
											<a data-toggle="modal" href="#createPay" class="btn btn-success pull-right" onclick="set('tariffId', ${tariff.id})">
												Continue more
											</a>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${currentTariff.price >= tariff.price}">
													<c:choose>
														<c:when test="${daysLeft > 0}">
															<a class="btn btn-success pull-right" disabled="disabled">
																${daysLeft} days left
															</a>
														</c:when>
														<c:otherwise>${tariff.price}$</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<a data-toggle="modal" href="#createPay" class="btn btn-success pull-right"  onclick="set('tariffId', ${tariff.id})">
														<c:choose>
															<c:when test="${savedCash > 0}">${tariff.price}$ - <strong>${savedCash}$ (saved cash)</strong></c:when>
															<c:otherwise>${tariff.price}$</c:otherwise>
														</c:choose>
													</a>
												</c:otherwise>
											</c:choose>
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-md-8">
		<c:forEach var="tariff" items="${tariffs}">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>${tariff.name} - ${tariff.maxCapacity}Mb</h4>
				</div>
				<div class="panel-body">
					<p>${tariff.description}</p>
					<h4>${tariff.price}$</h4>
					<c:choose>
						<c:when test="${sessionScope.user.idTariff == tariff.id}">
							<button type="button" class="btn btn-success" disabled="disabled">Buy it now</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="btn btn-success">Buy it now</button>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
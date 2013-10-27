<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css">
.header {
	padding-top: 20px;
	max-width: 1000px;
	margin: auto;
}
</style>
<div class="header">
	<div class="navbar navbar-default navbar-inverse">
		<fmt:setLocale value="${sessionScope.sessLocale}" scope="session" />
		<c:if test="${sessionScope.sessLocale == null}">
			<fmt:setLocale value="${pageContext.request.locale}" scope="session" />
		</c:if>
		<fmt:setBundle basename="locale.messages" var="lang" scope="session" />
		<div class="container">
			<div class="collapse navbar-collapse">

				<c:if test="${sessionScope.userid != null}">
					<ul class="nav navbar-nav">
						<li class=""><a href="adminUserInfo"><fmt:message
									key="Info" bundle="${lang}" /></a></li>
						<li class=""><a href="adminUserFiles"><fmt:message
									key="Files" bundle="${lang}" /></a></li>
						<li class=""><a href="adminUserPayments"><fmt:message
									key="Payments" bundle="${lang}" /></a></li>
						<li class=""><a href="adminUserActivity">Activity</a></li>
						<li class=""><a href="adminUserTraffic">Traffic</a></li>
					</ul>
				</c:if>
			</div>
		</div>
	</div>
</div>
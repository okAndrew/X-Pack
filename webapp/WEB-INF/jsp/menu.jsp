<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
.collapse.navbar-collapse {
	height: 50px;
}

.navbar-brand {
	padding-top: 5px;
	padding-bottom: 0px;
}

/* .navbar-brand:HOVER { */
/* 	background-image: url('res/img/LOGO.png'); */
/* } */

.nav.navbar-nav>li>a {
	padding-top: 22px;
	height: 60px;
}

.navbar-form.navbar-right {
	margin-top: 13px;
}

#dream-logo {
	height: 50px;
}
</style>

<div class="navbar navbar-fixed-top navbar-inverse">

	<fmt:setLocale value="${sessionScope.sessLocale}" scope="session" />
	<c:if test="${sessionScope.sessLocale==null}">
		<fmt:setLocale value="${pageContext.request.locale}" scope="session" />
	</c:if>
	<fmt:setBundle basename="locale.messages" var="lang" scope="session" />
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="homepage"><img id="dream-logo"
				src="res/img/dream-logo.png"></a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<c:if test="${sessionScope.userLogin != null}">
					<li id="menu_myspace"><a href="userpage"> <fmt:message
								key="My_space" bundle="${lang}" /></a></li>
					<li id="menu_pricing"><a href="pricing"> <fmt:message
								key="Pricing" bundle="${lang}" /></a></li>
				</c:if>
				<c:if test="${sessionScope.userRole == 'ADMIN'}">
					<li class=""><a href="adminUsersPage"><fmt:message
								key="Users" bundle="${lang}" /></a></li>
					<li class=""><a href="adminTariffsPage"><fmt:message
								key="Tariffs" bundle="${lang}" /></a></li>
					<li class=""><a href="adminStatisticsPage"><fmt:message
								key="Statistics" bundle="${lang}" /></a></li>
					<li class=""><a href="adminLogsPage"><fmt:message
								key="Logger" bundle="${lang}" /></a></li>
				</c:if>
				<li class="dropdown" id="menu_about"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"><fmt:message
							key="About" bundle="${lang}" /> <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="about"><fmt:message key="Project"
									bundle="${lang}" /></a></li>
						<li><a href="team"><fmt:message key="Team"
									bundle="${lang}" /></a></li>
					</ul></li>
			</ul>

			<div class="navbar-form navbar-right">
				<c:if test="${sessionScope.userLogin == null}">
					<div class="form-group">
						<a href="signup" class="btn btn-primary"><fmt:message
								key="signup" bundle="${lang}" /></a>
					</div>
					<div class="form-group">
						<a href="signin" class="btn btn-primary"> <fmt:message
								key="signin" bundle="${lang}" />
						</a>
					</div>
				</c:if>

				<c:if test="${sessionScope.userLogin != null}">
					<div class="btn-group">
						<button type="button" class="btn btn-default">${sessionScope.userLogin}</button>
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown">
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="settings"><fmt:message key="Settings"
										bundle="${lang}" /></a></li>
							<li class="divider"></li>
							<li><a href="signout"><fmt:message key="signout"
										bundle="${lang}" /></a></li>
						</ul>
					</div>
				</c:if>

				<div class="btn-group">
					<img src="${currentLanguage.pathImage}" data-toggle="dropdown">
					<div class="dropdown-menu pull-right">
						<c:forEach items="${languages}" var="language">
							<li><a href="locale?language=${language.defaulLocale}"><img
									src="${language.pathImage}">${language.name}</a></li>
						</c:forEach>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>


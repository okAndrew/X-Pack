<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="navbar navbar-fixed-top navbar-inverse">
	<fmt:setLocale value="${sessionScope.sessLocale}" scope="session" />
	<c:if test="${sessionScope.sessLocale == null}">
		<fmt:setLocale value="${pageContext.request.locale}" scope="session" />
	</c:if>
	<fmt:setBundle basename="locale.messages" var="lang" scope="session" />
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="homepage">DreamHost</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<c:if test="${sessionScope.userLogin != null}">
					<li id="menu_myspace"><a href="userpage">
						<fmt:message key="My_space" bundle="${lang}" /></a>
					</li>
				</c:if>
				<li id="menu_about"><a href=""><fmt:message key="About" bundle="${lang}" /></a></li>
				<li id="menu_home"><a href=""><fmt:message key="Home" bundle="${lang}" /></a></li>
				<li id="menu_pricing"><a href="pricing"><fmt:message key="Pricing"
							bundle="${lang}" /></a></li>
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
							<li><a href="settings">Settings</a></li>
							<li class="divider"></li>
							<li><a href="signout"><fmt:message key="signout"
										bundle="${lang}" /></a></li>
						</ul>
					</div>
				</c:if>

				<a href="locale?language=en_US"><img
					src="res/img/flags/United-States-Flag-icon.png"></a> <a
					href="locale?language=uk_UA"><img
					src="res/img/flags/Ukraine-Flag-icon.png"></a> <a
					href="locale?language=fr_FR"><img
					src="res/img/flags/France-Flag-icon.png"></a> <a
					href="locale?language=de_DE"><img
					src="res/img/flags/Germany-Flag-icon.png"></a>

			</div>
		</div>
	</div>
</div>


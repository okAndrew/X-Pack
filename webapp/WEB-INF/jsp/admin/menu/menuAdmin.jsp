<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="navbar navbar-fixed-top navbar-inverse">
	<fmt:setLocale value="${sessionScope.sessLocale}" scope="session" />
	<c:if test="${sessionScope.sessLocale == null}">
		<fmt:setLocale value="${pageContext.request.locale}" scope="session" />
	</c:if>
	<fmt:setBundle basename="locale.messages" var="lang"
		scope="session" />
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="homepage">DreamHost</a>
		</div>
		<div class="collapse navbar-collapse">

			<c:if test="${sessionScope.userid != null}">
				<ul class="nav navbar-nav">
					<li class=""><a href="userpage"><fmt:message key="My_space" bundle="${lang}" /></a></li>
					<li class=""><a href="adminUsersPage"><fmt:message key="Users" bundle="${lang}" /></a></li>
					<li class=""><a href="adminFilesPage"><fmt:message key="Files" bundle="${lang}" /></a></li>
					<li class=""><a href="adminTariffsPage"><fmt:message key="Tariffs" bundle="${lang}" /></a></li>
					<li class=""><a href="adminStatisticsPage"><fmt:message key="Statistics" bundle="${lang}" /></a></li>
					<li class=""><a href="adminLoggingPage"><fmt:message key="Log" bundle="${lang}" /></a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="adminPage"><fmt:message key="To_main_menu" bundle="${lang}" /></a></li>
				</ul>
			</c:if>

			<div class="navbar-form navbar-right">
				<c:if test="${sessionScope.userid == null}">
					<div class="form-group">
						<a href="signin" class="btn btn-success"><fmt:message
								key="signin" bundle="${lang}" /></a>
					</div>
				</c:if>

				<c:if test="${sessionScope.userid != null}">
					<div class="form-group">
						<a href="signout" class="btn btn-success"> <fmt:message
								key="signout" bundle="${lang}" />
						</a>
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
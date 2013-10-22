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
					<li class=""><a href="userpage">My Space</a></li>
					<li class=""><a href="adminUsersPage">Users</a></li>
					<li class=""><a href="#">Files</a></li>
					<li class=""><a href="adminTariffsPage">Tariffs</a></li>
					<li class=""><a href="adminStatisticsPage">Statistics</a></li>
					<li class=""><a href="adminLogsPage">Logging</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="adminPage">To main menu</a></li>
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
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Locale "%>

<div class="navbar navbar-fixed-top navbar-inverse">
	<%
		Locale l = request.getLocale();
		Object s = session.getAttribute("sessLocale");
	%>
	
	<fmt:setLocale value="<%=s%>" scope="session" />
	<c:if test="${sessionScope.sessLocale == null}">
		<fmt:setLocale value="<%=l%>" scope="session" />
	</c:if>
	<fmt:setBundle basename="com.epam.lab.resources.messages" var="lang"
		scope="session" />
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="homepage">DreamHost</a>
		</div>
		<div class="collapse navbar-collapse">

			<c:if test="${sessionScope.login != null}">
				<ul class="nav navbar-nav">
					<li class="active"><a href="adminUsersPage">Users</a></li>
					<li class="active"><a href="#">Files</a></li>
					<li class="active"><a href="#">Tariffs</a></li>
					<li class="active"><a href="#">Admins</a></li>
				</ul>
			</c:if>

			<div class="navbar-form navbar-right">
				<c:if test="${sessionScope.login == null}">
					<div class="form-group">
						<a href="signInAdmin" class="btn btn-success"><fmt:message
								key="signin" bundle="${lang}" /></a>
					</div>
				</c:if>

				<c:if test="${sessionScope.login != null}">
					<div class="form-group">
						<a href="#" class="btn btn-success"> <fmt:message
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
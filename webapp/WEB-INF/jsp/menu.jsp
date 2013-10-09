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
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
			</ul>
			
			<div class="navbar-form navbar-right">
				<c:if test="${sessionScope.user == null}">
				<div class="form-group">
					<a href="signup" class="btn btn-primary"><fmt:message
							key="signup" bundle="${lang}" /></a>
				</div>
				<div class="form-group">
					<a href="signin" class="btn btn-primary">
						<fmt:message key="signin" bundle="${lang}" />
					</a>
				</div>
				</c:if>
				
				<c:if test="${sessionScope.user != null}">
				<div class="btn-group">
					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						${sessionScope.user} <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li><a href="#">Separated link</a></li>
					</ul>
				</div>
				<div class="form-group">
					<a href="signout" class="btn btn-primary">
						<fmt:message key="signout" bundle="${lang}" />
					</a>
				</div>
				</c:if>
				<!--
				<a href="locale?language=en_US"><img
					src="res/img/flags/United-States-Flag-icon.png"></a> <a
					href="locale?language=uk_UA"><img
					src="res/img/flags/Ukraine-Flag-icon.png"></a> <a
					href="locale?language=fr_FR"><img
					src="res/img/flags/France-Flag-icon.png"></a> <a
					href="locale?language=de_DE"><img
					src="res/img/flags/Germany-Flag-icon.png"></a>
				 -->
			</div>
		</div>
	</div>
</div>


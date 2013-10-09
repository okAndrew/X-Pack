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
			<!--<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
			</ul>
			 -->
			<div class="navbar-form navbar-right">
				<div class="form-group">
					<a href="signup" class="btn btn-primary"><fmt:message
							key="signup" bundle="${lang}" /></a>
				</div>
			</div>
		</div>
	</div>
</div>

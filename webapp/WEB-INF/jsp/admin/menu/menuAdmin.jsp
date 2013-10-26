<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-inverse" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">Dreamhost</a>
	</div>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav">
			<c:if test="${sessionScope.userid != null}">
				<ul class="nav navbar-nav">
					<li class=""><a href="adminUserInfo"><fmt:message key="Info" bundle="${lang}" /></a></li>
					<li class=""><a href="adminUserFiles"><fmt:message key="Files" bundle="${lang}" /></a></li>
					<li class=""><a href="adminUserPayments"><fmt:message key="Payments" bundle="${lang}" /></a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="adminUsersPage"><fmt:message key="To_main_menu" bundle="${lang}" /></a></li>
				</ul>
			</c:if>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="userpage">Back</a></li>
		</ul>
	</div>
	<!-- /.navbar-collapse -->
</nav>
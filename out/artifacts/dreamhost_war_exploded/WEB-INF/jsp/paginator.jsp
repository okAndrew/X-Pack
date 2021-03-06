<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="paginator-main">
	<div class="page-pages">
		<ul id="paginator" class="pagination">
		</ul>
	</div>
	<div class="page-option">
		<div class="btn-group">
			<button type="button" class="btn btn-default">
				<fmt:message key="Rows" bundle="${lang}" />
			</button>
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown">
				<span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a onclick="changePerPage(10);">10</a></li>
				<li><a onclick="changePerPage(20);">20</a></li>
				<li><a onclick="changePerPage(50);">50</a></li>
			</ul>
		</div>
	</div>
</div>
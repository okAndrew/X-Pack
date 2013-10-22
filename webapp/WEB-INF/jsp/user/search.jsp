<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost | User Menu</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/signui.css" rel="stylesheet" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<style type="text/css">
body {
	padding-top: 70px;
}
</style>
</head>
<body>
	<jsp:include page="..//menu.jsp"></jsp:include>
	<div class="container">
		<div class="btn-toolbar">
			<div class="input-group" style="width: 300px;">
				<form action="search" method="post">
					<input name="searchtext" type="text" class="form-control">
					<span class="input-group-btn">
						<button class="btn btn-default" type="submit"><fmt:message key="Search" bundle="${lang}" /></button>
					</span>
				</form>

			</div>
		</div>

		<div class="bs-example">
			<form action="downloadfiles" method="get">
				<button type="submit" class="btn btn-default"><fmt:message key="Download" bundle="${lang}" /></button>
				<table class="table table-condensed table-hover table-bordered">
					<tbody>

						<c:forEach items="${folders}" var="folder">
							<tr>
								<td><label class="checkbox-inline"> <input
										type="checkbox" name="folders" value="${folder.id}">
								</label></td>
								<td><a href="userfoldernav?folderId=${folder.id}">${folder.name}</a>
							</tr>
						</c:forEach>
						<c:forEach items="${files}" var="file">
							<tr>

								<td><label class="checkbox-inline"> <input
										type="checkbox" name="files" value="${file.id}">
								</label></td>
								<td><a href="downloadfile?fileId=${file.id}">${file.nameIncome}</a></td>
								<td><c:out value="${file.date}" /></td>
								<td><c:out value="${file.size}" /></td>
								<td><c:out value="${file.type}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
			<form action="userpage" method="get">
				<button type="submit" class="btn btn-default"><fmt:message key="Return_to_userpage" bundle="${lang}" /></button>
			</form>
		</div>
	</div>
</body>
</html>
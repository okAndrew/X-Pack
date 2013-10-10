<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DreamHost | User Menu</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/signui.css" rel="stylesheet" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> 
<style type="text/css">
body {
	padding-top: 70px;
}
</style>
</head>
<body>
	<jsp:include page="..//menu.jsp"></jsp:include>
<body>
	<div class="container">
		<ul class="nav nav-pills">
			<li class="active"><a href="#">New Folder</a></li>
			<li><form method="post" action="upload"
					enctype="multipart/form-data">
					<input type="file"> <input type="submit" value="Upload">
				</form>
			<li>
				<div class="input-group" style="max-width: 300px;">
					<input type="text" class="form-control"> <span
						class="input-group-btn">
						<button class="btn btn-default" type="button">Search</button>
					</span>
				</div>
			</li>
		</ul>
	</div>
	<form method="post" action="DeleteFile">
		<div class="btn-group">

			<button type="submit" class="btn btn-default" name="Delete">Delete</button>
			<button type="button" class="btn btn-default" name="Download">Download</button>
			<div class="errorinfo">${message}</div>
		</div>
		<h2 id="tables-condensed"></h2>
		<div class="bs-example" style="max-width: 600px;">
			<table class="table table-condensed">
				<thead>
					<tr>
						<th>#</th>
						<th>Name</th>
						<th>Date</th>
						<th>Size</th>
						<th>Type</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${files}" var="files">
						<tr>
							<td><label class="checkbox-inline"> <input
									type="checkbox" id="inlineCheckbox2" name="files"
									value="${files.id}">
							</label></td>
							<td><c:out value="${files.name}" /></td>
							<td><c:out value="${files.date}" /></td>
							<td><c:out value="${files.size}" /></td>
							<td><c:out value="${files.type}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>
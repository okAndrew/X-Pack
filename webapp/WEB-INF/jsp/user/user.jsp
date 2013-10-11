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
<body>
	<div class="container">
		<ul class="nav nav-pills">
			<li class="active"><a data-toggle="modal"
				href="#createFolderModal" class="btn btn-primary btn-lg">Create
					Folder</a></li>
			<li><form method="post" action="upload"
					enctype="multipart/form-data">
					<input type="hidden" name="folderId" value="${currentFolder.id}">
					<input type="file" multiple name="fileName"> <input
						type="submit" value="Upload">
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
	<div class="btn-group">
		<button type="button" class="btn btn-default" name="Download">Download</button>
	</div>
	<h2 id="tables-condensed"></h2>
	<div class="bs-example" style="max-width: 600px;">
		<table class="table table-condensed">

			<tbody>
				<c:if test="${currentFolder.idUpper!=0}">
					<a href="userfoldernav?folderId=${currentFolder.idUpper}">Up</a>
				</c:if>
				<form action="DeleteFile" method="get">
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
							<td><c:out value="${file.nameIncome}" /></td>
							<td><c:out value="${file.date}" /></td>
							<td><c:out value="${file.size}" /></td>
							<td><c:out value="${file.type}" /></td>
						</tr>
					</c:forEach>
					<button type="submit" class="btn btn-default">Delete</button>
				</form>
			</tbody>
		</table>
	</div>

	<form action="createfolder" method="post">
		<div class="modal fade" id="createFolderModal" tabindex="-1"
			role="dialog" aria-labelledby="createFolderModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">Create Folder</h4>
					</div>
					<div class="modal-body">
						<input type="text" name="folderName" placeholder="folder name">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Create</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</form>
</body>
</html>
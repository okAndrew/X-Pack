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
	<div class="container">
		<div class="btn-toolbar">
			<div class="btn-group">
				<a data-toggle="modal" role="button" class="btn btn-primary"
					href="#createFolderModal">Create Folder</a>
			</div>
			<div class="btn-group">
				<a data-toggle="modal" role="button" class="btn btn-primary"
					href="#uploadFormModal">Upload</a>
			</div>
			<div class="btn-group pull-right">
				<div class="input-group" style="width: 300px;">
					<form action="search" method="post">
						<input name="searchtext" type="text" class="form-control">
						<span class="input-group-btn">
							<button class="btn btn-default" type="submit">Search</button>
						</span>
					</form>
				</div>
			</div>
		</div>

		<div class="bs-example">
			<form action="downloadfiles" method="get">
				<button type="submit" class="btn btn-default">Download</button>
				<table class="table table-condensed table-hover table-bordered">
					<tbody>
						<c:if test="${currentFolder.idUpper!=0}">
							<a href="userfoldernav?folderId=${currentFolder.idUpper}">Up</a>
						</c:if>
						<!-- 				<form action="deletefile" method="get"> -->

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
						<!-- 					<button type="submit" class="btn btn-default">Delete</button> -->
					</tbody>
				</table>
			</form>
		</div>

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
			</div>
		</div>
	</form>

	<form method="post" action="upload" enctype="multipart/form-data">
		<div class="modal fade" id="uploadFormModal" tabindex="-1"
			role="dialog" aria-labelledby="uploadFormModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">Upload Files</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" name="folderId" value="${currentFolder.id}">
						<input type="file" multiple name="fileName">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Upload</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
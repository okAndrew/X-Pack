<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="images/favicon.png">

<title>Dream Host</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="res/js/bootstrap.js"></script>

<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/signui.css" rel="stylesheet" />


</head>
<body>
	<jsp:include page="adminUserHeader.jsp"></jsp:include>

	<div class="files-admin-user">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">Files</div>

			<h2 id="tables-condensed"></h2>
			<form action="adminUserDeleteFile" method="post">
				<div class="panel-body">
					<ul class="nav nav-pills">
						<li><button type="submit" class="btn btn-default">Delete</button></li>
						<li><div class="input-group" style="max-width: 300px;">
								<input type="text" class="form-control"> <span
									class="input-group-btn">
									<button class="btn btn-default" type="button">Search</button>
								</span>
							</div></li>
					</ul>
				</div>
				<table class="table table-condensed">

					<tbody>
						<c:if test="${currentFolder.idUpper!=0}">
							<a href="userfoldernav?folderId=${currentFolder.idUpper}">Up</a>
						</c:if>
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
								<td><c:out value="${file.path}" /></td>
							</tr>
						</c:forEach>


					</tbody>
				</table>

			</form>
		</div>
	</div>

<!-- Don't need this -->
	<div class="bs-example" style="max-width: 600px;"></div>
	<!-- Don't need this? -->
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
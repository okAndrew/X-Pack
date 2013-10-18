<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DreamHost | User Menu</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/signui.css" rel="stylesheet" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<script>
	function toggle(source) {
		checkboxes = document.getElementsByName('folders');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
		checkboxes = document.getElementsByName('files');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}

	function set(targetElementId, id) {
		document.getElementById(targetElementId).setAttribute('value',id);
	}
	function getCurFolderId(){
		document.getElementById("folderidmove").getAttribute("value");
	}
</script>
<style type="text/css">
body {
	padding-top: 70px;
}
</style>
</head>
<body>
	<jsp:include page="..//menu.jsp"></jsp:include>
	<div class="container">
		<div class="panel panel-default">
			<form action="usercontroller" method="post">
				<div class="panel-heading">File Storage</div>
				<div class="btn-toolbar" style="padding: 20px">
					<div class="btn-group">
						<a data-toggle="modal" role="button" class="btn btn-primary"
							href="#createFolderModal">Create Folder</a>
					</div>
					<div class="btn-group">
						<a data-toggle="modal" role="button" class="btn btn-primary"
							href="#uploadFormModal">Upload</a>
					</div>
					<div class="btn-group">
						<button type="submit" name="download" class="btn btn-primary">Download</button>
					</div>
					<div class="btn-group">
						<button type="submit" name="delete" class="btn btn-primary">Delete</button>
					</div>
					<div class="btn-group pull-right">
						<div class="input-group" style="width: 300px;">
							<input name="searchtext" type="text" class="form-control"
								placeholder="search"> <span class="input-group-btn">
								<button class="btn btn-primary" name="search" type="submit">Search</button>
							</span>
						</div>
					</div>
				</div>
				<c:if test="${message!=null}">
					<div class="alert alert-danger">${message}</div>
				</c:if>
				<div class="panel-body">
					<table class="table zebra-striped table-hover">
						<thead>
							<tr>
								<th width="5%"><input type="checkbox"
									onClick="toggle(this)" /> All</th>
								<th width="35%">Name</th>
								<th width="15%">Date</th>
								<th width="10%">Size</th>
								<th width="35%">Type</th>
								<th></th><th></th><th></th><th></th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${currentFolder.idUpper!=0}">
								<tr>
									<td></td>
									<td colspan="8"><a
											href="userfoldernav?folderid=${currentFolder.idUpper}"
											style="font-size: 20px;"><span
										class="glyphicon glyphicon-chevron-up"></span></a></td>
								</tr>
							</c:if>
							<c:forEach items="${folders}" var="folder">
								<tr>
									<td><label class="checkbox-inline"> <input
											type="checkbox" name="folders" value="${folder.id}">
									</label></td>
									<td><span class="glyphicon glyphicon-folder-open"></span>&nbsp;&nbsp;
										<a href="userfoldernav?folderid=${folder.id}">${folder.name}</a>
									</td>
									<td><c:out value="${folder.date}" /></td>
									<td><c:out value="${folder.size}" /></td>
									<td><c:out value="Folder" /></td>
									<td><a data-toggle="modal" role="button"
										class="btn btn-primary" href="#EditFolderModal"
										onclick="set('folderidedit', ${folder.id})">Edit</a>
									<td>
									<td>
										<div class="btn-group">
											<a data-toggle="modal" role="button" class="btn btn-primary"
												href="#DeleteFolderModal"
												onclick="set('folderiddelete', ${folder.id})">Delete</a>
										</div>
									</td>
									<td>
										<div class="btn-group">
											<a data-toggle="modal" role="button" class="btn btn-primary"
												href="#MoveFolderModal"
												onclick="set('folderidmove', ${folder.id})">Move</a>
										</div>
									</td>
								</tr>
							</c:forEach>
							<c:forEach items="${files}" var="file">
								<tr>
									<td><label class="checkbox-inline"> <input
											type="checkbox" name="files" value="${file.id}">
									</label></td>
									<td><span class="glyphicon glyphicon-file"></span>&nbsp;&nbsp;<a
										href="download?fileid=${file.id}">${file.nameIncome}</a></td>
									<td><c:out value="${file.date}" /></td>
									<td><c:out value="${file.size}" /></td>
									<td><c:out value="${file.type}" /></td>
									<td><div class="btn-group">
											<a data-toggle="modal" role="button" class="btn btn-primary"
												href="#EditFileModal"
												onclick="set('fileidedit', ${file.id})">Edit</a>
										</div>
									<td>
									<td>
										<div class="btn-group">
											<a data-toggle="modal" role="button" class="btn btn-primary"
												href="#DeleteFileModal"
												onclick="set('fileiddelete', ${file.id})">Delete</a>
										</div>
									</td>
									<td>
										<div class="btn-group">
											<a data-toggle="modal" role="button" class="btn btn-primary"
												href="#MoveFileModal"
												onclick="set('fileidmove', ${file.id})">Move</a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</form>
		</div>
	</div>

	<form action="move" method="post">
		<div class="modal fade" id="MoveFolderModal" tabindex="-1"
			role="dialog" aria-labelledby="MoveFolderModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h3 id="MoveFolderModalLabel">Moving Confirmation</h3>
					</div>
					<div class="modal-body">
						<input type="hidden" id="folderidmove" name="folderidmove">
						<p>Please select folder to move</p>
						<c:forEach items="${folders}" var="folder">
						<c:if test="${folder.id!=getCurFolderId.call()}">
								<input type="radio" name="folderidtarget" value="${folder.id}"> ${folder.name}<br>
								</c:if>
						</c:forEach>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-success">Move</button>
					</div>
				</div>
			</div>
		</div>
	</form>

	<form action="move" method="post">
		<div class="modal fade" id="MoveFileModal" tabindex="-1" role="dialog"
			aria-labelledby="MoveFileModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h3 id="MoveFileModalLabel">Moving Confirmation</h3>
					</div>
					<div class="modal-body">
						<input type="hidden" id="fileidmove" name="fileidmove">
						<p>Please select folder to move</p>
						<c:forEach items="${folders}" var="folder">
						<c:if test="${folder.id!=getCurFolderId.call()}">
								<input type="radio" name="folderidtarget" value="${folder.id}"> ${folder.name}<br>
								</c:if>
						</c:forEach>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-success">Move</button>
					</div>
				</div>
			</div>
		</div>
	</form>

	<form action="delete" method="post">
		<div class="modal fade" id="DeleteFolderModal" tabindex="-1"
			role="dialog" aria-labelledby="DeleteFolderModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h3 id="DeleteFolderModalLabel">Delete Confirmation</h3>
					</div>
					<div class="modal-body">
						<input type="hidden" id="folderiddelete" name="folderid">
						<p class="error-text">Are you sure you want to delete this
							folder?</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<button type="submit" name="deletesingle" class="btn btn-danger">Delete</button>
					</div>
				</div>
			</div>
		</div>
	</form>

	<form action="delete" method="post">
		<div class="modal fade" id="DeleteFileModal" tabindex="-1"
			role="dialog" aria-labelledby="DeleteFileModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h3 id="DeleteFileModalLabel">Delete Confirmation</h3>
					</div>
					<div class="modal-body">
						<input type="hidden" id="fileiddelete" name="fileid">
						<p class="error-text">Are you sure you want to delete this
							file?</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						<button type="submit" name="deletesingle" class="btn btn-danger">Delete</button>
					</div>
				</div>
			</div>
		</div>
	</form>

	<form action="useredit" method="post">
		<div class="modal fade" id="EditFolderModal" tabindex="-1"
			role="dialog" aria-labelledby="EditFolderModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">Edit</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" id="folderidedit" name="folderid"> <input
							type="text" name="foldername" placeholder="folder name">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Edit</button>
					</div>
				</div>
			</div>
		</div>
	</form>

	<form action="useredit" method="post">
		<div class="modal fade" id="EditFileModal" tabindex="-1" role="dialog"
			aria-labelledby="EditFileModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title">Edit</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" id="fileidedit" name="fileid"> <input
							type="text" name="filename" placeholder="filename">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Edit</button>
					</div>
				</div>
			</div>
		</div>
	</form>


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
						<input type="text" name="foldername" placeholder="folder name">
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
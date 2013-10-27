<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DreamHost | User Menu</title>
<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/myspace.css" rel="stylesheet" />
<link href="res/css/treeshow.css" rel="stylesheet" />
<link href="res/css/dropzone/dropzone.css" rel="stylesheet" />
<style type="text/css">
.dropzone {
	margin-left: auto;
	margin-right: auto;
	width: 85%;
	min-height: 150px;
}
</style>
<script src="res/js/dropzone.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<script src="res/js/TreeMenu.js" type="text/javascript"></script>
<script>
	function toggle(source) {
		var checkboxes = document.getElementsByName('folders');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
		var checkboxes = document.getElementsByName('files');
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
	function checkboxesStatus(source) {
		var checkboxes = document.getElementsByName('folders');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			if (checkboxes[i].checked === true) {
				document.getElementById('download').removeAttribute('disabled');
				document.getElementById('delete').removeAttribute('disabled');
				document.getElementById('move').removeAttribute('disabled');
				return;
			}
		}
		var checkboxes = document.getElementsByName('files');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			if (checkboxes[i].checked === true) {
				document.getElementById('download').removeAttribute('disabled');
				document.getElementById('delete').removeAttribute('disabled');
				document.getElementById('move').removeAttribute('disabled');
				return;
			}
		}
		document.getElementById('download').setAttribute('disabled','disabled');
		document.getElementById('delete').setAttribute('disabled','disabled');
		document.getElementById('move').setAttribute('disabled','disabled');
	}

	function getCheckboxes(source) {
		
	}
</script>
</head>
<body>
	<jsp:include page="..//menu.jsp"></jsp:include>
	<jsp:include page="myspace/modelcreatefolder.jsp"></jsp:include>
	<jsp:include page="myspace/modeledit.jsp"></jsp:include>
	<jsp:include page="myspace/modeldelete.jsp"></jsp:include>
	<jsp:include page="myspace/modelmove.jsp"></jsp:include>

	<c:choose>
		<c:when test="${isbanned }">
			<div class="alert alert-danger">
				<fmt:message key="You_are_banned" bundle="${lang}" />
			</div>
		</c:when>
		<c:otherwise>
			<form action="upload" class="dropzone">
				<div class="fallback">
					<input name="file" type="file" multiple />
				</div>
			</form>
		</c:otherwise>
	</c:choose>
	<form action="usercontroller" method="post">
		<div class="container">
			<div class="panel panel-default main">
				<div class="panel-body">
					<nav class="navbar navbar-default controlmenu" role="navigation">
						<div class="collapse navbar-collapse controlmenu">
							<div class="btn-group">
								<a href="#createFolderModal" data-toggle="modal" role="button"
									class="btn btn-default"><fmt:message key="Create_folder"
										bundle="${lang}" /></a>
								<button type="submit" name="download" class="btn btn-default"
									disabled="disabled" id="download">
									<fmt:message key="Download" bundle="${lang}" />
								</button>
								<button type="submit" name="delete" class="btn btn-default"
									disabled="disabled" id="delete">
									<fmt:message key="Delete" bundle="${lang}" />
								</button>
								<a href="#MoveModal" data-toggle="modal" role="button"
									class="btn btn-default" disabled="disabled" id="move"><fmt:message
										key="Move" bundle="${lang}" /></a>
							</div>
						</div>
					</nav>

					<ol class="breadcrumb">
						<c:forEach items="${folderpath}" var="folder">
							<li><a href="userfoldernav?folderid=${folder.id}">${folder.name}</a></li>
						</c:forEach>
					</ol>

					<table class="table zebra-striped table-hover table-condensed">
						<thead>
							<tr>
								<th style="width: 30px;"><input type="checkbox"
									onClick="toggle(this); checkboxesStatus(this)" /></th>
								<th style="width: 100%;"><fmt:message key="Name"
										bundle="${lang}" /></th>
								<th style="width: 120px;"><fmt:message key="Date"
										bundle="${lang}" /></th>
								<th style="width: 100px;"><fmt:message key="Size"
										bundle="${lang}" /></th>
								<th style="width: 120px"><fmt:message key="Type"
										bundle="${lang}" /></th>
								<th style="width: 70px;"></th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${currentFolder.idUpper!=0}">
								<tr>
									<td></td>
									<td colspan="7"><a
										href="userfoldernav?folderid=${currentFolder.idUpper}"
										style="font-size: 20px;"><span
											class="glyphicon glyphicon-chevron-up"></span></a></td>
									<td></td>
								</tr>
							</c:if>
							<c:forEach items="${folders}" var="folder">
								<tr>
									<td><input type="checkbox" name="folders"
										value="${folder.id}" onclick="checkboxesStatus(this)" /></td>
									<td><span class="glyphicon glyphicon-folder-open"></span><a
										href="userfoldernav?folderid=${folder.id}" class=""><b>
												${folder.name}</b></a></td>
									<td><fmt:formatDate value="${folder.date}" /></td>
									<td><c:out value="${folder.size}" /></td>
									<td><c:out value="folder" /></td>
									<td>
										<div class="btn-group">
											<a data-toggle="modal" role="button" href="#EditModal"
												onclick="set('folderidedit', ${folder.id})"> <span
												class="glyphicon glyphicon-pencil"></span>
											</a>
										</div>
										<div class="btn-group">
											<a data-toggle="modal" role="button" href="#DeleteModal"
												onclick="set('folderiddelete', ${folder.id})"> <span
												class="glyphicon glyphicon-trash"></span>
											</a>
										</div>
										<div class="btn-group">
											<a data-toggle="modal" role="button" href="#MoveModal"
												onclick="set('folderidmove', ${folder.id})"> <span
												class="glyphicon glyphicon-move"></span>
											</a>
										</div>
									</td>
								</tr>
							</c:forEach>
							<c:forEach items="${files}" var="file">
								<tr>
									<td><label class="checkbox-inline"><input
											type="checkbox" name="files" value="${file.id}"
											onclick="checkboxesStatus(this)" /></label></td>
									<td><span class="glyphicon glyphicon-file"></span><a
										href="download?fileid=${file.id}">${file.nameIncome}</a></td>
									<td><fmt:formatDate value="${file.date}" /></td>
									<td><c:out value="${file.size}" /></td>
									<td><c:out value="${file.type}" /></td>
									<td>
										<div class="btn-group">
											<a data-toggle="modal" role="button" href="#EditModal"
												onclick="set('fileidedit', ${file.id})"> <span
												class="glyphicon glyphicon-pencil"></span>
											</a>
										</div>
										<div class="btn-group">
											<a data-toggle="modal" role="button" href="#DeleteModal"
												onclick="set('fileiddelete', ${file.id})"> <span
												class="glyphicon glyphicon-trash"></span>
											</a>
										</div>
										<div class="btn-group">
											<a data-toggle="modal" role="button" href="#MoveModal"
												onclick="set('fileidmove', ${file.id})"> <span
												class="glyphicon glyphicon-move"></span>
											</a>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
		</div>
	</form>

</body>
</html>
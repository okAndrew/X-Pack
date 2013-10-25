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
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<script src="res/js/TreeMenu.js" type="text/javascript"></script>
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
</head>
<body>
	<jsp:include page="..//menu.jsp"></jsp:include>
	<jsp:include page="myspace/modelcreatefolder.jsp"></jsp:include>
	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<nav class="navbar navbar-default controlmenu" role="navigation">
					<div class="collapse navbar-collapse controlmenu">
						<div class="btn-group">
							<a href="#createFolderModal" data-toggle="modal" role="button" class="btn btn-default"><fmt:message key="Create_folder" bundle="${lang}" /></a>
							<a href="#uploadFormModal" data-toggle="modal" role="button" class="btn btn-default"><fmt:message key="Upload" bundle="${lang}" /></a>
							<button type="submit" name="download" class="btn btn-default"><fmt:message key="Download" bundle="${lang}" /></button>
							<button type="submit" name="delete" class="btn btn-default"><fmt:message key="Delete" bundle="${lang}" /></button>
							<button type="submit" name="move" class="btn btn-default"><fmt:message key="Move" bundle="${lang}" /></button>
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
							<th style="width: 30px;"><input type="checkbox" onClick="toggle(this)" /></th>
							<th style="width: 100%;"><fmt:message key="Name" bundle="${lang}" /></th>
							<th style="width: 120px;"><fmt:message key="Date" bundle="${lang}" /></th>
							<th style="width: 100px;"><fmt:message key="Size" bundle="${lang}" /></th>
							<th style="width: 120px"><fmt:message key="Type" bundle="${lang}" /></th>
							<th style="width: 70px;"></th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${currentFolder.idUpper!=0}">
							<tr>
								<td></td>
								<td colspan="7">
									<a href="userfoldernav?folderid=${currentFolder.idUpper}" style="font-size: 20px;"><span class="glyphicon glyphicon-chevron-up"></span></a>
								</td>
								<td></td>
							</tr>
						</c:if>
						<c:forEach items="${folders}" var="folder">
						<tr>
							<td><input type="checkbox" name="folders" value="${folder.id}" /></td>
							<td><span class="glyphicon glyphicon-folder-open"></span><a href="userfoldernav?folderid=${folder.id}" class=""><b> ${folder.name}</b></a></td>
							<td><fmt:formatDate value="${folder.date}" /></td>
							<td><c:out value="${folder.size}" /></td>
							<td><c:out value="folder" /></td>
							<td>
                            	<div class="btn-group">
									<a data-toggle="modal" role="button" href="#EditFileModal" onclick="set('fileidedit', ${file.id})">
										<span class="glyphicon glyphicon-pencil"></span>
									</a>
								</div>
								<div class="btn-group">
									<a data-toggle="modal" role="button" href="#DeleteFileModal" onclick="set('fileiddelete', ${file.id})">
										<span class="glyphicon glyphicon-trash"></span>
									</a>
								</div>
								<div class="btn-group">
									<a data-toggle="modal" role="button" href="#MoveFileModal" onclick="set('fileidmove', ${file.id})">
										<span class="glyphicon glyphicon-move"></span>
									</a>
								</div>
							</td>
						</tr>
						</c:forEach>
						<c:forEach items="${files}" var="file">
						<tr>
                        	<td><label class="checkbox-inline"><input type="checkbox" name="files" value="${file.id}" /></label></td>
							<td><span class="glyphicon glyphicon-file"></span><a href="download?fileid=${file.id}">${file.nameIncome}</a></td>
							<td><c:out value="${file.date}" /></td>
                            <td><c:out value="${file.size}" /></td>
                            <td><c:out value="${file.type}" /></td>
                            <td>
                            	<div class="btn-group">
									<a data-toggle="modal" role="button" href="#EditFileModal" onclick="set('fileidedit', ${file.id})">
										<span class="glyphicon glyphicon-pencil"></span>
									</a>
								</div>
								<div class="btn-group">
									<a data-toggle="modal" role="button" href="#DeleteFileModal" onclick="set('fileiddelete', ${file.id})">
										<span class="glyphicon glyphicon-trash"></span>
									</a>
								</div>
								<div class="btn-group">
									<a data-toggle="modal" role="button" href="#MoveFileModal" onclick="set('fileidmove', ${file.id})">
										<span class="glyphicon glyphicon-move"></span>
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
	
	
	
	<div class="container">
                <div class="panel panel-default">
                        <form action="usercontroller" method="post">
                                <div class="panel-heading">
                                        <fmt:message key="File_storage" bundle="${lang}" />
                                </div>
                                <div class="btn-toolbar" style="padding: 20px">
                                        <div class="btn-group">
                                                <a data-toggle="modal" role="button" class="btn btn-primary"
                                                        href="#createFolderModal"><fmt:message key="Create_folder"
                                                                bundle="${lang}" /></a>
                                        </div>
                                        <div class="btn-group">
                                                <a data-toggle="modal" role="button" class="btn btn-primary"
                                                        href="#uploadFormModal"><fmt:message key="Upload"
                                                                bundle="${lang}" /></a>
                                        </div>
                                        <div class="btn-group">
                                                <button type="submit" name="download" class="btn btn-primary">
                                                        <fmt:message key="Download" bundle="${lang}" />
                                                </button>
                                        </div>
                                        <div class="btn-group">
                                                <button type="submit" name="delete" class="btn btn-primary">
                                                        <fmt:message key="Delete" bundle="${lang}" />
                                                </button>
                                        </div>
                                        <div class="btn-group">
                                                <button type="submit" name="move" class="btn btn-primary">
                                                        <fmt:message key="Move" bundle="${lang}" />
                                                </button>
                                        </div>
                                        <div class="btn-group pull-right">
                                                <div class="input-group" style="width: 300px;">
                                                        <input name="searchtext" type="text" class="form-control"
                                                                placeholder="search"> <span class="input-group-btn">
                                                                <button class="btn btn-primary" name="search" type="submit">
                                                                        <fmt:message key="Search" bundle="${lang}" />
                                                                </button>
                                                        </span>
                                                </div>
                                        </div>
                                        <c:if test="${message!=null}">
                                                <div class="alert alert-danger">${message}</div>
                                        </c:if>
                                </div>
                                <div class="panel-body">
                                        <table class="table zebra-striped table-hover">
                                                <thead>
                                                        <c:forEach items="${folderpath}" var="folder">
                                                                <a href="userfoldernav?folderid=${folder.id}">${folder.name}</a> /</th>
                                                        </c:forEach>
                                                        <tr>
                                                                <th width="5%"><input type="checkbox"
                                                                        onClick="toggle(this)" /> <fmt:message key="All"
                                                                                bundle="${lang}" /></th>
                                                                <th width="35%"><fmt:message key="Name" bundle="${lang}" /></th>
                                                                <th width="15%"><fmt:message key="Date" bundle="${lang}" /></th>
                                                                <th width="10%"><fmt:message key="Size" bundle="${lang}" /></th>
                                                                <th width="35%"><fmt:message key="Type" bundle="${lang}" /></th>
                                                                <th></th>
                                                                <th></th>
                                                                <th></th>
                                                                <th></th>
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
                                                                        <td><fmt:formatDate value="${folder.date}"/></td>
                                                                        <td><c:out value="${folder.size}" /></td>
                                                                        <td><c:out value="Folder" /></td>
                                                                        <td><a data-toggle="modal" role="button"
                                                                                class="btn btn-primary" href="#EditFolderModal"
                                                                                onclick="set('folderidedit', ${folder.id})"><fmt:message
                                                                                                key="Edit" bundle="${lang}" /></a>
                                                                        <td>
                                                                        <td>
                                                                                <div class="btn-group">
                                                                                        <a data-toggle="modal" role="button" class="btn btn-primary"
                                                                                                href="#DeleteFolderModal"
                                                                                                onclick="set('folderiddelete', ${folder.id})"><fmt:message
                                                                                                        key="Delete" bundle="${lang}" /></a>
                                                                                </div>
                                                                        </td>
                                                                        <td>
                                                                                <div class="btn-group">
                                                                                        <a data-toggle="modal" role="button" class="btn btn-primary"
                                                                                                href="#MoveFolderModal"
                                                                                                onclick="set('folderidmove', ${folder.id})"><fmt:message
                                                                                                        key="Move" bundle="${lang}" /></a>
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
                                                                                                onclick="set('fileidedit', ${file.id})"><fmt:message
                                                                                                        key="Edit" bundle="${lang}" /></a>
                                                                                </div>
                                                                        <td>
                                                                        <td>
                                                                                <div class="btn-group">
                                                                                        <a data-toggle="modal" role="button" class="btn btn-primary"
                                                                                                href="#DeleteFileModal"
                                                                                                onclick="set('fileiddelete', ${file.id})"><fmt:message
                                                                                                        key="Delete" bundle="${lang}" /></a>
                                                                                </div>
                                                                        </td>
                                                                        <td>
                                                                                <div class="btn-group">
                                                                                        <a data-toggle="modal" role="button" class="btn btn-primary"
                                                                                                href="#MoveFileModal"
                                                                                                onclick="set('fileidmove', ${file.id})"><fmt:message
                                                                                                        key="Move" bundle="${lang}" /></a>
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
                                                <h3 id="MoveFolderModalLabel">
                                                        <fmt:message key="Moving_confirmation" bundle="${lang}" />
                                                </h3>
                                        </div>
                                        <div class="modal-body">
                                                <input type="hidden" id="folderidmove" name="folderidmove">
                                                <p>
                                                        <fmt:message key="Please_select_folder_to_move" bundle="${lang}" />
                                                </p>
                                                <c:forEach items="${folders}" var="folder">
                                                        <c:if test="${folder.id!=getCurFolderId.call()}">
                                                                <input type="radio" name="folderidtarget" value="${folder.id}"> ${folder.name}<br>
                                                        </c:if>
                                                </c:forEach>
                                        </div>
                                        <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        <fmt:message key="Cancel" bundle="${lang}" />
                                                </button>
                                                <button type="submit" class="btn btn-success">
                                                        <fmt:message key="Move" bundle="${lang}" />
                                                </button>
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
                                                <h3 id="MoveFileModalLabel">
                                                        <fmt:message key="Moving_Confirmation" bundle="${lang}" />
                                                </h3>
                                        </div>
                                        <div class="modal-body">
                                                <input type="hidden" id="fileidmove" name="fileidmove">
                                                <p>
                                                        <fmt:message key="Please_select_folder_to_move" bundle="${lang}" />
                                                </p>
                                                <c:forEach items="${folders}" var="folder">
                                                        <c:if test="${folder.id!=getCurFolderId.call()}">
                                                                <input type="radio" name="folderidtarget" value="${folder.id}"> ${folder.name}<br>
                                                        </c:if>
                                                </c:forEach>
                                        </div>
                                        <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        <fmt:message key="Cancel" bundle="${lang}" />
                                                </button>
                                                <button type="submit" class="btn btn-success">
                                                        <fmt:message key="Move" bundle="${lang}" />
                                                </button>
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
                                                <h3 id="DeleteFolderModalLabel">
                                                        <fmt:message key="Delete_Confirmation" bundle="${lang}" />
                                                </h3>
                                        </div>
                                        <div class="modal-body">
                                                <input type="hidden" id="folderiddelete" name="folderid">
                                                <p class="error-text">
                                                        <fmt:message key="Are_you_sure_you_want_to_delete_this_folder"
                                                                bundle="${lang}" />
                                                </p>
                                        </div>
                                        <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        <fmt:message key="Cancel" bundle="${lang}" />
                                                </button>
                                                <button type="submit" name="deletesingle" class="btn btn-danger">
                                                        <fmt:message key="Delete" bundle="${lang}" />
                                                </button>
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
                                                <h3 id="DeleteFileModalLabel">
                                                        <fmt:message key="Delete_Confirmation" bundle="${lang}" />
                                                </h3>
                                        </div>
                                        <div class="modal-body">
                                                <input type="hidden" id="fileiddelete" name="fileid">
                                                <p class="error-text">
                                                        <fmt:message key="Are_you_sure_you_want_to_delete_this_file"
                                                                bundle="${lang}" />
                                                </p>
                                        </div>
                                        <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        <fmt:message key="Cancel" bundle="${lang}" />
                                                </button>
                                                <button type="submit" name="deletesingle" class="btn btn-danger">
                                                        <fmt:message key="Delete" bundle="${lang}" />
                                                </button>
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
                                                <h4 class="modal-title">
                                                        <fmt:message key="Edit" bundle="${lang}" />
                                                </h4>
                                        </div>
                                        <div class="modal-body">
                                                <input type="hidden" id="folderidedit" name="folderid"> <input
                                                        type="text" name="foldername" placeholder="folder name">
                                        </div>
                                        <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        <fmt:message key="Close" bundle="${lang}" />
                                                </button>
                                                <button type="submit" class="btn btn-primary">
                                                        <fmt:message key="Edit" bundle="${lang}" />
                                                </button>
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
                                                <h4 class="modal-title">
                                                        <fmt:message key="Edit" bundle="${lang}" />
                                                </h4>
                                        </div>
                                        <div class="modal-body">
                                                <input type="hidden" id="fileidedit" name="fileid"> <input
                                                        type="text" name="filename" placeholder="filename">
                                        </div>
                                        <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        <fmt:message key="Close" bundle="${lang}" />
                                                </button>
                                                <button type="submit" class="btn btn-primary">
                                                        <fmt:message key="Edit" bundle="${lang}" />
                                                </button>
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
                                                <h4 class="modal-title">
                                                        <fmt:message key="Upload_files" bundle="${lang}" />
                                                </h4>
                                        </div>
                                        <div class="modal-body">
                                                <input type="file" multiple name="fileName">
                                        </div>
                                        <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        <fmt:message key="Close" bundle="${lang}" />
                                                </button>
                                                <button type="submit" class="btn btn-primary">
                                                        <fmt:message key="Upload" bundle="${lang}" />
                                                </button>
                                        </div>
                                </div>
                        </div>
                </div>
        </form>
        <div class="menu">
                <ul id="example1">
                        <li>Root
                        <c:forEach items="${allFolders}" var="folder" >
                                <ul>
                                        <li><c:out value="${folder.name}"/></li>
                                </ul>
                        </c:forEach>
                        </li>
                        
                </ul>
                <script type="text/javascript">
                        make_tree_menu('example1');
                </script>
        </div>
</body>
</html>
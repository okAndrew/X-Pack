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
				return;
			}
		}
		var checkboxes = document.getElementsByName('files');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			if (checkboxes[i].checked === true) {
				document.getElementById('download').removeAttribute('disabled');
				document.getElementById('delete').removeAttribute('disabled');
				return;
			}
		}
		document.getElementById('download').setAttribute('disabled','disabled');
		document.getElementById('delete').setAttribute('disabled','disabled');
	}

	function setSearchedHref() {
		document.getElementById("searchhref").href = "adminsearch?searchtext=" + document.getElementById("searchinput").value;
	}
</script>
</head>
<body>
	<jsp:include page="modeledit.jsp"></jsp:include>
	<jsp:include page="modeldelete.jsp"></jsp:include>

	<form action="adminUsercontroller" method="post" name="controller">
		<div class="container">
			<div class="panel panel-default main">
				<div class="panel-body">
					<nav class="navbar navbar-default controlmenu" role="navigation">
						<div class="collapse navbar-collapse controlmenu">
							<div class="btn-group">
								<a href="adminUserFiles" role="button" class="btn btn-default">
									<fmt:message key="Return_to_userpage" bundle="${lang}" />
								</a>
							</div>
							<div class="btn-group">
								<button type="submit" name="download" class="btn btn-default"
									disabled="disabled" id="download">
									<fmt:message key="Download" bundle="${lang}" />
								</button>
								<button type="submit" name="delete" class="btn btn-default"
									disabled="disabled" id="delete">
									<fmt:message key="Delete" bundle="${lang}" />
								</button>
							</div>
							<div class="btn-toolbar pull-right">
								<div class="input-group" style="width: 300px;">
									<input name="searchtext" type="text" class="form-control"
										id="searchinput"> <span class="input-group-btn">
										<a href="#" id="searchhref" onclick="setSearchedHref()"
										role="button" class="btn btn-default"> <fmt:message
												key="Search" bundle="${lang}" />
									</a>
									</span>
								</div>
							</div>
						</div>
					</nav>
					<table class="table table-condensed">
						<thead>
							<tr>
								<th><input type="checkbox" onClick="toggle(this)" /> <fmt:message
										key="All" bundle="${lang}" /></th>
								<th><fmt:message key="File_name" bundle="${lang}" /></th>
								<th><fmt:message key="Date" bundle="${lang}" /></th>
								<th><fmt:message key="Size" bundle="${lang}" /></th>
								<th><fmt:message key="Type" bundle="${lang}" /></th>
								<th><fmt:message key="Path" bundle="${lang}" /></th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${currentFolder.idUpper!=0}">
								<tr id="${currentFolder.idUpper}">
									<td></td>
									<td colspan="7"><a
										href="adminUserfoldernav?folderid=${currentFolder.idUpper}"
										style="font-size: 20px;"><span
											class="glyphicon glyphicon-chevron-up"> Go to upper</span></a></td>
									<td></td>
								</tr>
							</c:if>
							<c:forEach items="${folders}" var="folder">
								<tr>
									<td><input type="checkbox" name="folders"
										value="${folder.id}" onclick="checkboxesStatus(this)" /></td>
									<td><span class="glyphicon glyphicon-folder-open"></span><a
										href="adminUserfoldernav?folderid=${folder.id}" class=""><b>
												${folder.name}</b></a></td>
									<td><fmt:formatDate value="${folder.date}" /></td>
									<td><c:out value="${folder.size}" /></td>
									<td><c:out value="folder" /></td>
									<td></td>
								</tr>
							</c:forEach>
							<c:forEach items="${files}" var="file">
								<tr>
									<td><label class="checkbox-inline"> <input
											type="checkbox" name="files" value="${file.id}">
									</label></td>
									<td><span class="glyphicon glyphicon-file"></span><a
										href="download?fileid=${file.id}">${file.nameIncome}</a></td>
									<td><fmt:formatDate value="${file.date}" /></td>
									<td><c:out value="${file.size}" /></td>
									<td><c:out value="${file.type}" /> <c:if
											test='${ file.type.equals("IMAGE") }'>
											<div class="btn-group">
												<a data-toggle="modal" role="button" href="#ImageModal"
													onclick="setSRC(${file.id})"> <span
													class="glyphicon glyphicon-play"></span>
												</a>
											</div>
										</c:if> <c:if test='${ file.type.equals("VIDEO") }'>
											<div class="btn-group">
												<a data-toggle="modal" role="button" href="#VideoModal"
													onclick="setVideoSrc(${file.id})"> <span
													class="glyphicon glyphicon-play"></span>
												</a>
											</div>
										</c:if> <c:if test='${ file.type.equals("AUDIO") }'>
											<div class="btn-group">
												<audio controls>
													<source src="download?fileid=${file.id}" type="audio/mpeg">
												</audio>
											</div>
										</c:if></td>
									<td><c:out value="${file.path}" /></td>
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
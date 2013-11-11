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
<link href="res/css/dropzone.css" rel="stylesheet" />
<link rel="stylesheet" href="res/css/minimalist.css">

<link rel="stylesheet" href="res/css/jquery-ui.css" />
<style type="text/css">
img.img {
	max-height: 1000px;
	max-width: 538px;
	margin-bottom: 3px;
}
</style>

<script src="res/js/jquery-1.10.2.min.js"></script>
<script src="res/js/jquery-ui.js"></script>
<script src="res/js/dropzone.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<script src="res/js/utils.js"></script>
<script src="res/js/browser.js"></script>
</head>
<body>
	<jsp:include page="..//menu.jsp"></jsp:include>
	<c:choose>
		<c:when test="${isbanned }">
			<div class="alert alert-danger">
				<fmt:message key="You_are_banned" bundle="${lang}" />
			</div>
		</c:when>
		<c:otherwise>
			<form id="my-awesome-dropzone" action="upload" class="dropzone dropzone-preview">
				<div class="fallback">
					<input name="file" type="file" multiple />
				</div>
			</form>
		</c:otherwise>
	</c:choose>
	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<form action="usercontroller" method="post">
					<nav class="navbar navbar-default controlmenu" role="navigation">
						<div class="collapse navbar-collapse controlmenu">
							<div class="btn-group">
								<a href="#createFolderModal" data-toggle="modal" role="button"
									class="btn btn-default"> <span
									class="glyphicon glyphicon-asterisk"></span> <fmt:message
										key="Create_folder" bundle="${lang}" /></a>
								<button type="submit" name="download" class="btn btn-default"
									disabled="disabled" id="download">
									<span class="glyphicon glyphicon-cloud-download"></span>
									<fmt:message key="Download" bundle="${lang}" />
								</button>
								<button type="submit" name="delete" class="btn btn-default"
									disabled="disabled" id="delete">
									<span class="glyphicon glyphicon-trash"></span>
									<fmt:message key="Delete" bundle="${lang}" />
								</button>

							</div>
							<div class="btn-group select-all" data-toggle="buttons">
								<label class="btn btn-default"><fmt:message key="All"
										bundle="${lang}" />: <input type="checkbox"
									class="select-all-input" onchange="toggle(this)"
									name="selectAll"> <span
									class="glyphicon glyphicon-unchecked"></span> </label>
							</div>
							<div class="btn-toolbar pull-right">
								<div class="input-group" style="width: 300px;">
									<input type="text" onkeyup="searchFiles()" class="form-control"
										id="searchinput" onKeyPress="return disableEnterKey(event)">
									<span class="input-group-addon"> <span
										class="glyphicon glyphicon-search"></span>
									</span>
								</div>
							</div>
						</div>
					</nav>
					<c:if test="${message!=null }">
						<div class="alert alert-warning">
							<p>
								<fmt:message key="${message}" bundle="${lang}" />
							</p>
						</div>
					</c:if>
					<div id="browser">
						<jsp:include page="browser.jsp"></jsp:include>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function loadBrowserContent() {
			$.ajax({
				type : "GET",
				url : 'BrowserContent',
				success : function(data) {
					$("#browser").html(data);
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert('xhr.status ' + xhr.status + '   thrownError:'
							+ thrownError);
				}
			});
		}

		function fixedEncodeURIComponent(str) {
			return encodeURIComponent(str).replace(/[!'()]/g, escape).replace(
					/\*/g, "%2A");
		}

		function searchFiles() {
			var searchText = $("#searchinput").val();
			if (searchText.length == 0) {
				loadBrowserContent();
			} else {
				$.ajax({
					type : "POST",
					url : 'search',
					data : {
						"searchtext" : searchText
					},
					success : function(data) {
						$("#browser").html(data);
					},
					error : function(xhr, ajaxOptions, thrownError) {
						alert('xhr.status ' + xhr.status + '   thrownError:'
								+ thrownError);
					}
				});
			}
		}
		function disableEnterKey(e) {
			var key;
			if (window.event)
				key = window.event.keyCode; //IE
			else
				key = e.which; //firefox
			if (key == 13)
				return false;
			else
				return true;
		}
	</script>
	<script type="text/javascript">
		<fmt:message key="File_is_too_big" var="file" bundle="${lang}"/>
		var file = "${file}";
		<fmt:message key="Max_free_space" var="space" bundle="${lang}"/>
		var space = "${space}";
		var options = {
			url : "upload",
			previewsContainer : "#my-awesome-dropzone",
			addRemoveLinks: true,
			parallelUploads : 1,
			maxFiles : 50,
			maxFilesize : <c:out value="${freeSpace}"/>,
			dictFileTooBig : file + " ({{filesize}}MB). " + space
					+ ": {{maxFilesize}}MB.",
			init : function() {
				this.on("success", function(file) {
					this.removeFile(file);
					loadBrowserContent();
				});
				this.on("maxfilesexceeded", function() {
					alert("No moar files please!");
				});
				this.on('reset', function(file) {
					$('.dz-message').show(200);
				});
				 
			},
			dictRemoveFile: 'Clear',
			clickable: false,
			dictResponseError : "Error uploading. Please try again."
		}
		var bodyDropzoneOptions = options;
		var bodyDropzone = new Dropzone(document.body, bodyDropzoneOptions);
		bodyDropzone.on("drop", function() {
			$('.dz-message').hide(200);
		});
		options.clickable = true;
		Dropzone.options.myAwesomeDropzone = options;
	</script>
	<jsp:include page="modals/modalCreatefolder.jsp"></jsp:include>
	<jsp:include page="modals/modalEdit.jsp"></jsp:include>
	<jsp:include page="modals/modalDelete.jsp"></jsp:include>
	<jsp:include page="modals/modalImage.jsp"></jsp:include>
	<jsp:include page="modals/modalVideo.jsp"></jsp:include>
	<jsp:include page="modals/modalAudio.jsp"></jsp:include>
	<jsp:include page="modals/modellink.jsp"></jsp:include>
</body>
</html>
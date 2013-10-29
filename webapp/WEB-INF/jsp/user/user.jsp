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
<link href="res/css/dropzone/dropzone.css" rel="stylesheet" />
<link rel="stylesheet" href="//releases.flowplayer.org/5.4.3/skin/minimalist.css">

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="res/js/dropzone.min.js"></script>
<script src="res/js/bootstrap.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		Dropzone.options.myAwesomeDropzone = {
			maxFilesize : 1000,
			init : function() {
				this.on("complete", function(file) {
					loadBrowserContent();
				});
			}
		}
	})
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
	function searchFiles() {
		var searchText = $("#searchinput").val();
		if(searchText.length == 0){
			loadBrowserContent();
		}else{
		$.ajax({
			type : "GET",
			url : 'search?searchtext=' + searchText,
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
</script>
</head>
<body>
	<jsp:include page="..//menu.jsp"></jsp:include>
	<jsp:include page="myspace/modelcreatefolder.jsp"></jsp:include>
	<jsp:include page="myspace/modeledit.jsp"></jsp:include>
	<jsp:include page="myspace/modeldelete.jsp"></jsp:include>
	<jsp:include page="myspace/modelimage.jsp"></jsp:include>
	<jsp:include page="myspace/modelvideo.jsp"></jsp:include>

	<c:choose>
		<c:when test="${isbanned }">
			<div class="alert alert-danger">
				<fmt:message key="You_are_banned" bundle="${lang}" />
			</div>
		</c:when>
		<c:otherwise>
			<form id="my-awesome-dropzone" action="upload" class="dropzone">
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
								<button type="button" onclick="loadBrowserContent()"
									name="search" class="btn btn-default" id="search">
									<fmt:message key="Search" bundle="${lang}" />
								</button>
							</div>
							<div class="btn-toolbar pull-right">
								<div class="input-group" style="width: 300px;">
									<input type="text" onkeyup="searchFiles()" class="form-control"
										id="searchinput"> <span class="input-group-btn">
										<button onclick="searchFiles()" type="button"
											class="btn btn-default">
											<fmt:message key="Search" bundle="${lang}" />
										</button>
									</span>
								</div>
							</div>
						</div>
					</nav>
					<div id="browser">
						<jsp:include page="browser.jsp"></jsp:include>
					</div>
				</form>
			</div>
		</div>
	</div>


</body>
</html>
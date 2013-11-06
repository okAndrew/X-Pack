<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DreamHost |Admin User Files</title>
<link href="res/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="res/css/minimalist.css">
<script src="res/js/jquery-1.10.2.min.js"></script>

<script type="text/javascript">
	function fixedEncodeURIComponent(str) {
		return encodeURIComponent(str).replace(/[!'()]/g, escape).replace(
				/\*/g, "%2A");
	}

	function searchFiles() {
		var searchText = $("#searchinput").val();
		$.ajax({
			type : "POST",
			url : 'adminsearch',
			data : {
				"searchtext" : searchText,
			},
			success : function(data) {
				$("#filetable").html(data);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert('xhr.status ' + xhr.status + '   thrownError:'
						+ thrownError);
			}

		});
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

<style type="text/css">
img.img {
	max-height: auto;
	max-width: 538px;
	margin-bottom: 3px;
}
</style>

</head>
<body>
	<jsp:include page="../../../../menu.jsp"></jsp:include>
	<jsp:include page="../adminUserHeader.jsp"></jsp:include>
	<jsp:include page="causeDeletingSendEmailModalPage.jsp"></jsp:include>
	<jsp:include page="../../../../user/modals/modalImage.jsp"></jsp:include>
	<jsp:include page="../../../../user/modals/modalVideo.jsp"></jsp:include>
	<jsp:include page="../../../../user/modals/modalAudio.jsp"></jsp:include>

	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<form action="adminUsercontroller" method="post">
					<nav class="navbar navbar-default controlmenu" role="navigation">
						<div class="collapse navbar-collapse controlmenu">
							<div class="btn-group">

								<button type="submit" name="download" class="btn btn-default"
									disabled="disabled" id="download">
									<fmt:message key="Download" bundle="${lang}" />
								</button>
								<button type="submit" name="causeDelete" class="btn btn-default"
									data-toggle="modal" disabled="disabled" id="delete"
									data-target="#causeDeletingSendEmailModal">
									<fmt:message key="Delete" bundle="${lang}" />
								</button>

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
					<div id="filetable">
						<jsp:include page="tableFiles.jsp"></jsp:include>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
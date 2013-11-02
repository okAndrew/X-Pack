<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DreamHost | Admin User Files</title>

<link href="res/css/bootstrap.css" rel="stylesheet" />
<link href="res/css/style.css" rel="stylesheet" />
<link href="res/css/myspace.css" rel="stylesheet" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="res/js/bootstrap.js"></script>

<script type="text/javascript">
	function searchFiles() {
		$.ajax({
			type : "GET",
			url : 'adminsearch?searchtext=' + $("#searchinput").val(),
			success : function(data) {
				$("#adminbrowser").html(data);
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
</head>
<body>
	<jsp:include page="../../../../menu.jsp"></jsp:include>
	<jsp:include page="../../../../user/myspace/modeldelete.jsp"></jsp:include>
	<jsp:include page="../../../../user/myspace/modelimage.jsp"></jsp:include>
	<jsp:include page="../../../../user/myspace/modelvideo.jsp"></jsp:include>
	<jsp:include page="../../../../user/myspace/modelaudio.jsp"></jsp:include>

	<div class="container">
		<div class="panel panel-default main">
			<div class="panel-body">
				<jsp:include page="../adminUserHeader.jsp"></jsp:include>
				<form action="adminUsercontroller" method="post">
					<nav class="navbar navbar-default controlmenu" role="navigation">
						<div class="collapse navbar-collapse controlmenu">
							<div class="btn-group">

								<button type="submit" name="download" class="btn btn-default"
									disabled="disabled" id="download">
									<fmt:message key="Download" bundle="${lang}" />
								</button>
								<button type="submit" name="cause" class="btn btn-default"
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
					<div id="adminbrowser">
						<jsp:include page="tableFiles.jsp"></jsp:include>
					</div>
					<jsp:include page="causeDeletingSendEmailModalPage.jsp"></jsp:include>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
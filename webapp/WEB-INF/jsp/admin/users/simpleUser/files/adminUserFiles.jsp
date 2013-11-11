<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


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
<form action="adminUsercontroller" method="post">
	<jsp:include page="causeDeletingSendEmailModalPage.jsp"></jsp:include>
	<jsp:include page="../../../../user/modals/modalImage.jsp"></jsp:include>
	<jsp:include page="../../../../user/modals/modalVideo.jsp"></jsp:include>
	<jsp:include page="../../../../user/modals/modalAudio.jsp"></jsp:include>
	<c:if test="${filelist.size() > 0}">
		<div class="panel-body">
			<div class="btn-group">
				<div class="btn-toolbar pull-left">
					<div class="input-group" style="width: 300px;">
						<input type="text" onkeyup="searchFiles()" class="form-control"
							id="searchinput" onKeyPress="return disableEnterKey(event)">
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-search"></span>
						</span>
					</div>
				</div>
				<button type="submit" name="action" value="download"
					class="btn btn-default" disabled="disabled" id="download">
					<fmt:message key="Download" bundle="${lang}" />
				</button>
				<button type="submit" name="causeDelete" class="btn btn-default"
					data-toggle="modal" disabled="disabled" id="delete"
					data-target="#causeDeletingSendEmailModal">
					<fmt:message key="Delete" bundle="${lang}" />
				</button>
			</div>
		</div>
	</c:if>
	<div id="filetable">
		<jsp:include page="tableFiles.jsp"></jsp:include>
	</div>
</form>

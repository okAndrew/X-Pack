<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<ol class="breadcrumb">
	<c:forEach items="${folderpath}" var="folder">
		<li><a href="userfoldernav?folderid=${folder.id}">${folder.name}</a></li>
	</c:forEach>
</ol>

<style>
#gallery {
	float: left;
	width: 100%;
	border: 0px solid gray;
	font-size: 10px;
	border: 0px solid gray;
}

.cell {
	-webkit-border-radius: 5px 5px 5px 5px;
	border-radius: 5px 5px 5px 5px;
	margin: 0px 8px 15px 8px;
	padding: 10px 0px 10px 10px;
	float: left;
	width: 350px;
	border: 1px solid #EEEEEE;
	background: #FBFBF8;
	line-height: 100%;
	height: 95px;
	position: relative;
}

.thumb {
	text-decoration: none;
	float: left;
	margin-right: 6px;
}

.cell-desc {
	height: 90px;
	width: 160px;
	float: left;
	margin-right: 10px;
}

.hover-drop {
	background: #8C97A1;
}
</style>

<div id="gallery">
	<!-- upper -->
	<c:if test="${currentFolder.idUpper!=0}">
		<div class="cell droppable" id="${currentFolder.idUpper}">
			<div class="thumb">
				<a href="userfoldernav?folderid=${currentFolder.idUpper}"
					title="${currentFolder.name}"><img
					src="http://icons.iconarchive.com/icons/icojam/blue-bits/64/arrow-up-icon.png"
					height="70px"> <span class="glyphicon glyphicon-chevron-up"></span></a>
			</div>
			<div class="cell-desc">
				<h4>
					<a href="userfoldernav?folderid=${folder.id}">${folder.name}</a>
				</h4>
				<h6>${folder.date}</h6>
				<h5>${folder.size}</h5>
			</div>
		</div>
	</c:if>
	<!-- folders -->
	<c:forEach items="${folders}" var="folder">
		<div class="cell draggable droppable" name="folder-${folder.id}" id="${folder.id}">
			<div class="thumb">
				<a href="userfoldernav?folderid=${folder.id}" title="${folder.name}"><img
					src="http://www.whatthetech.com/blog/wp-content/uploads/2010/08/leopard-folder.png"
					height="70px"></a>
			</div>
			<div class="cell-desc">
				<h5>
					<a href="userfoldernav?folderid=${folder.id}"
						title="${folder.name}"> <c:set var="formatName"
							value="${folder.name}"></c:set> <c:if
							test="${fn:length(folder.name) > 16}">
							<c:set var="formatName"
								value="${fn:substring(folder.name, 0, 13)}..."></c:set>
						</c:if> <c:out value="${formatName}"></c:out>
					</a> <a data-toggle="modal" role="button" href="#EditModal"
						onclick="set('folderidedit', ${folder.id})"> <span
						class="glyphicon glyphicon-pencil"></span>
					</a>
				</h5>
				<h6>${folder.date}</h6>
				<h6>${folder.size}</h6>
			</div>
			<div class="btn-group">
				<h5>
					<a data-toggle="modal" role="button" href="#DeleteModal"
						onclick="set('folderiddelete', ${folder.id})"> <span
						class="glyphicon glyphicon-trash"></span>
					</a><a data-toggle="modal" href="#EditModal"> <span
						class="glyphicon glyphicon-info-sign"></span>
					</a>
				</h5>
			</div>
		</div>
	</c:forEach>
	<!-- files -->
	<c:forEach items="${files}" var="file">
		<div class="cell draggable" name="file-${file.id}">
			<div class="thumb">
				<c:choose>
					<c:when test="${ file.type.equals('IMAGE') }">
						<a data-toggle="modal" role="button" href="#ImageModal"
							onclick="setSRC(${file.id})"> <span
							class="glyphicon glyphicon-play"></span>
						</a>
					</c:when>
					<c:when test="${ file.type.equals('VIDEO') }">
						<a data-toggle="modal" role="button" href="#videoModal"
							onclick="loadVideoContent(${file.id})"> <span
							class="glyphicon glyphicon-play"></span>
						</a>
					</c:when>
					<c:otherwise>
						<img title="${file.nameIncome}"
							src="http://download.easyicon.net/png/30357/128/" height="70px">
					</c:otherwise>
				</c:choose>
			</div>
			<div class="cell-desc">
				<h5>
					<a href="" title="${file.nameIncome}"> <c:set var="formatName"
							value="${file.nameIncome}"></c:set> <c:if
							test="${fn:length(file.nameIncome) > 16}">
							<c:set var="formatName"
								value="${fn:substring(file.nameIncome, 0, 13)}..."></c:set>
						</c:if> <c:out value="${formatName}"></c:out>
					</a> <a data-toggle="modal" role="button" href="#EditModal"
						onclick="set('folderidedit', ${folder.id})"> <span
						class="glyphicon glyphicon-pencil"></span>
					</a>
				</h5>
				<h6>${file.date}</h6>
				<h6>${file.size}</h6>
			</div>
			<div class="btn-group">
				<h5>
					<a data-toggle="modal" href="#DeleteModal"
						onclick="set('fileiddelete', ${file.id})"> <span
						class="glyphicon glyphicon-trash"></span>
					</a> <a href="download?fileid=${file.id}"> <span
						class="glyphicon glyphicon-download"></span>
					</a> <a data-toggle="modal" href="#EditModal"
						onclick="set('fileiddelete', ${file.id})"> <span
						class="glyphicon glyphicon-info-sign"></span>
					</a>
				</h5>
			</div>
		</div>
	</c:forEach>
</div>

<style type="text/css">
.dropzone {
	margin-left: auto;
	margin-right: auto;
	width: 85%;
	min-height: 150px;
}

.draggable {
	float: left;
}

.droppable {
	float: left;
}
</style>

<script>
$(function() {
	$(".draggable").draggable({
		revert : "invalid",
		scroll: true,
		distance: 20,
		opacity: 0.9,
		start: function(event, ui) {
			$(this).css('z-index', 9999);
		},
		stop:function(event, ui) {
			$(this).css('z-index', 1);
		},
	});

	$(".droppable").droppable({
		drop : function(event, ui) {
			var idmove = ui.draggable.attr('name');
			var idTargetFolder = $(this).attr("id");
			move(idmove, idTargetFolder);
			$(ui.draggable).remove();
		},
    	hoverClass: "hover-drop",
	});
});
function move(moveable, idtargetFolder) {
	$.ajax({
		type : "POST",
		url : 'move',
		data : {
			'moveable' : moveable,
			'idTarget' : idtargetFolder
		},
		success : function(data) {
		},
		error : function(xhr, ajaxOptions, thrownError) {
			alert('xhr.status ' + xhr.status + '   thrownError:'
					+ thrownError);
		}
	});
}

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
		document.getElementById(targetElementId).setAttribute('value', id);
	}
	function getCurFolderId() {
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
		document.getElementById('download')
				.setAttribute('disabled', 'disabled');
		document.getElementById('delete').setAttribute('disabled', 'disabled');
		document.getElementById('move').setAttribute('disabled', 'disabled');
	}

	function setSRC(id) {
		document.getElementById("img").src = "download?fileid=" + id;
	}
</script>
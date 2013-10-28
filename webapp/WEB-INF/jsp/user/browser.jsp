<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

		</tr>
	</thead>
	<tbody>
		<c:if test="${currentFolder.idUpper!=0}">
			<tr class="droppable" id="${currentFolder.idUpper}">
				<td></td>
				<td colspan="7"><a
					href="userfoldernav?folderid=${currentFolder.idUpper}"
					style="font-size: 20px;"><span
						class="glyphicon glyphicon-chevron-up"> Go to upper</span></a></td>
				<td></td>
			</tr>
		</c:if>
		<c:forEach items="${folders}" var="folder">
			<tr class="draggable droppable" id="${folder.id}"
				name="folder-${folder.id}">
				<td><input type="checkbox" name="folders" value="${folder.id}"
					onclick="checkboxesStatus(this)" /></td>
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
			<tr class="draggable" name="file-${file.id}">
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
					</div> <c:if test='${ file.type.equals("IMAGE") }'>
						<div class="btn-group">
							<a data-toggle="modal" role="button" href="#ImageModal"
								onclick="setSRC(${file.id})"> <span
								class="glyphicon glyphicon-play"></span>
							</a>
						</div>
					</c:if>
					<c:if test='${ file.type.equals("VIDEO") }'>
						<div class="btn-group">
							<a data-toggle="modal" role="button" href="#VideoModal"
								onclick="setVideoSrc(${file.id})"> <span
								class="glyphicon glyphicon-play"></span>
							</a>
						</div>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

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
		revert : "invalid"
	});

	$(".droppable").droppable({
		drop : function(event, ui) {
			var idmove = ui.draggable.attr('name');
			var idTargetFolder = $(this).attr("id");
			move(idmove, idTargetFolder);
			$(ui.draggable).remove();
		}
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

	function setVideoSrc(id) {
		document.getElementById("video").src = "download?fileid=" + id;
	}
</script>
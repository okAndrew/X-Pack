<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<ol class="breadcrumb">
	<c:forEach items="${folderpath}" var="folder">
		<li><a href="adminUserfoldernav?folderid=${folder.id}">${folder.name}</a></li>
	</c:forEach>
</ol>

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
				<td><input type="checkbox" name="folders" value="${folder.id}"
					onclick="checkboxesStatus(this)" /></td>
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
		document.getElementById('download')
				.setAttribute('disabled', 'disabled');
		document.getElementById('delete').setAttribute('disabled', 'disabled');
	}

	function setSRC(id) {
		document.getElementById("img").src = "download?fileid=" + id;
	}

	function setVideoSrc(id) {
		document.getElementById("video").src = "download?fileid=" + id;
	}
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table class="table zebra-striped table-hover table-condensed">
	<thead>
		<tr>
			<th><input type="checkbox" onClick="toggle(this)" /> <fmt:message
					key="All" bundle="${lang}" /></th>
			<th><fmt:message key="Path" bundle="${lang}" /></th>
			<th><fmt:message key="File_name" bundle="${lang}" /></th>
			<th><fmt:message key="Date" bundle="${lang}" /></th>
			<th><fmt:message key="Size" bundle="${lang}" /></th>
			<th><fmt:message key="Type" bundle="${lang}" /></th>
		</tr>
	</thead>
	<tbody class="avoid-sort">
		<c:forEach items="${filelist}" var="file">
			<tr>
				<td><label class="checkbox-inline"> <input
						type="checkbox" name="filelist" value="${file.id}">
				</label></td>
				<td>${file.path}</td>
				<td><a href="download?fileid=${file.id}">${file.nameIncome}</a>
					<c:if test='${ file.type.equals("IMAGE") }'>
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
				<td><fmt:formatDate value="${file.date}" /></td>
				<td>${file.size}</td>
				<td>${file.type}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<script>
	function toggle(source) {
		
		var checkboxes = document.getElementsByName('filelist');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
		}
	}

	function set(targetElementId, id) {
		document.getElementById(targetElementId).setAttribute('value', id);
	}
	
	function checkboxesStatus(source) {
		
		var checkboxes = document.getElementsByName('filelist');
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
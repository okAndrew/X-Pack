<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://dreamhost.com/jsp/tags/" prefix="dream"%>

<style type="text/css">
.title-name {
	max-width: 200px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
</style>

<div>
	<c:choose>
		<c:when test="${filelist.size() > 0}">
			<table class="table zebra-striped table-hover table-condensed">
				<thead>
					<tr>
						<th><input type="checkbox" onClick="toggle(this)" /></th>
						<th><fmt:message key="Path" bundle="${lang}" /></th>
						<th><fmt:message key="File_name" bundle="${lang}" /></th>
						<th><fmt:message key="Date" bundle="${lang}" /></th>
						<th><fmt:message key="Size_file" bundle="${lang}" /></th>
						<th><fmt:message key="Type" bundle="${lang}" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${filelist}" var="file">
						<tr>
							<td width="50px"><label class="checkbox-inline"> <input
									type="checkbox" name="files" onchange="checkboxesStatus()"
									value="${file.id}">
							</label></td>
							<td>${file.path}</td>
							<td class="title-name"><a href="download?file=${file.name}">${file.nameIncome}</a>
							<td><fmt:formatDate value="${file.date}" /></td>
							<td><dream:formatSize value="${file.size}" /></td>
							<td>${file.type}</td>
							<td><c:choose>
									<c:when test='${ file.type.equals("IMAGE") }'>
										<a data-toggle="modal" href="#ImageModal"
											onclick="setSRC('${file.name}')"> <span
											class="glyphicon glyphicon-play"></span>
										</a>
									</c:when>
									<c:when test='${ file.type.equals("VIDEO") }'>
										<a data-toggle="modal" href="#videoModal"
											onclick="loadVideoContent('${file.name}')"> <span
											class="glyphicon glyphicon-play"></span>
										</a>
									</c:when>
									<c:when test='${ file.type.equals("AUDIO") }'>
										<a data-toggle="modal" href="#audioModal"
											onclick="loadAudioContent('${file.name}')"> <span
											class="glyphicon glyphicon-play"></span>
										</a>
									</c:when>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:when
			test="${search!=null && search && search_no_result!=null && search_no_result}">
			<div class="well">
				<fmt:message key="Your_search_returned_no_results" bundle="${lang}" />
			</div>
		</c:when>
		<c:otherwise>
			<div class="well">
				<fmt:message key="There_is_no_files" bundle="${lang}" />
			</div>
		</c:otherwise>
	</c:choose>
</div>

<script>
	function toggle(source) {
		var checkboxes = document.getElementsByName('files');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			checkboxes[i].checked = source.checked;
			checkboxesStatus();
		}
	}

	function checkboxesStatus(source) {

		var checkboxes = document.getElementsByName('files');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			if (checkboxes[i].checked === true) {
				$('#delete').prop('disabled', false);
				return;
			}
		}
		$('#delete').prop('disabled', true);
	}

	function setSRC(name) {
		document.getElementById("img").src = "download?file=" + name;
	}
</script>



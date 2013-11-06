<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://dreamhost.com/jsp/tags/" prefix="dream"%>

<div>
	<c:if
		test="${search!=null && search && search_no_result!=null && search_no_result}">
		<p><fmt:message key="Your_search_returned_no_results" bundle="${lang}" /></p>
	</c:if>
	<c:if test="${filelist!=null}">
		<table class="table zebra-striped table-hover table-condensed">
			<thead>
				<tr>
					<th><input type="checkbox" onClick="toggle(this)" /> <fmt:message
							key="All" bundle="${lang}" /></th>
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
						<td><label class="checkbox-inline"> <input
								type="checkbox" name="files" onchange="checkboxesStatus()"
								value="${file.id}">
						</label></td>
						<td>${file.path}</td>
						<td><a href="download?file=${file.name}">${file.nameIncome}</a>
							<div class="cell draggable" name="file-${file.id}">
								<c:choose>
									<c:when test='${ file.type.equals("IMAGE") }'>
										<a data-toggle="modal" role="button" href="#ImageModal"
											onclick="setSRC('${file.name}')"> <span
											class="glyphicon glyphicon-play"></span>
										</a>
									</c:when>
									<c:when test='${ file.type.equals("VIDEO") }'>
										<a data-toggle="modal" role="button" href="#videoModal"
											onclick="loadVideoContent('${file.name}')"> <span
											class="glyphicon glyphicon-play"></span>
										</a>
									</c:when>
									<c:when test='${ file.type.equals("AUDIO") }'>
										<a data-toggle="modal" role="button" href="#audioModal"
											onclick="loadAudioContent('${file.name}')"> <span
											class="glyphicon glyphicon-play"></span>
										</a>
									</c:when>
								</c:choose>
							</div></td>
						<td><fmt:formatDate value="${file.date}" /></td>
						<td>${file.size}</td>
						<td>${file.type}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
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
				$('#download').prop('disabled', false);
				$('#delete').prop('disabled', false);
				return;
			}
		}
		$('#download').prop('disabled', true);
		$('#delete').prop('disabled', true);
	}

	function setSRC(name) {
		document.getElementById("img").src = "download?file=" + name;
	}
</script>



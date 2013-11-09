<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://dreamhost.com/jsp/tags/" prefix="dream"%>

<link type="text/css" href="res/css/browser.css" rel="stylesheet">
<link type="text/css" href="res/css/bootstrap-switch.css"
	rel="stylesheet">
<script type="text/javascript" src="res/js/bootstrap-switch.min.js"></script>

<c:if test="${search==null || !search}">
	<ol class="breadcrumb">
		<c:forEach items="${folderpath}" var="folder">
			<li><a href="userfoldernav?folderid=${folder.id}">${folder.name}</a></li>
		</c:forEach>
	</ol>
</c:if>

<div id="gallery">
	<c:if
		test="${search!=null && search && search_no_result!=null && search_no_result}">
		<div class="alert alert-info">
			<h5>
				<fmt:message key="Your_search_returned_no_results" bundle="${lang}" />
			</h5>
		</div>
	</c:if>
	<!--           upper           -->
	<c:if test="${currentFolder.idUpper!=0 && (search==null || !search)}">
		<div class="cell droppable" id="${currentFolder.idUpper}">
			<div class="thumb">
				<a href="userfoldernav?folderid=${currentFolder.idUpper}"
					title="To up"><img src="res/img/browser/arrow-up-icon.png"
					height="70px"></a>
			</div>
			<div class="cell-desc">
				<h4 class="title-name">${currentFolder.name}</h4>
				<h5>
					<fmt:message key="Total_size" bundle="${lang}" />
					<dream:formatSize value="${currentFolder.size}" />
				</h5>
			</div>
		</div>
	</c:if>
	<!--         folders           -->
	<c:forEach items="${folders}" var="folder">
		<div class="cell draggable droppable" name="folder-${folder.id}"
			id="${folder.id}">
			<div class="thumb">
				<a href="userfoldernav?folderid=${folder.id}" title="${folder.name}"><img
					src="res/img/browser/leopard-folder.png" height="70px"></a>
			</div>
			<div class="cell-desc">
				<h5 class="title-name">
					<a href="userfoldernav?folderid=${folder.id}"
						title="${folder.name}">${folder.name} </a>
				</h5>
				<h5>
					<dream:formatSize value="${folder.size}" />
				</h5>
				<h6>
					<fmt:formatDate type="date" value="${folder.date}" />
				</h6>
			</div>
			<div class="file-buttons">
				<div class="btn-group">
					<h5>
						<a data-toggle="modal" role="button" href="#EditModal"
							onmousedown="set('folderidedit', '${folder.id}')"> <span
							class="glyphicon glyphicon-pencil"></span>
						</a> <a data-toggle="modal" role="button" href="#DeleteModal"
							onclick="set('folderiddelete', ${folder.id})"> <span
							class="glyphicon glyphicon-trash"></span>
						</a>
					</h5>
				</div>
				<div class="btn-group check" data-toggle="buttons">
					<label class="btn btn-default btn-lg check-label"><input
						type="checkbox" class="cell-check" onchange="checkboxesStatus()"
						name="folders" value="${folder.id}"> <span
						class="check-span"></span> </label>
				</div>
			</div>

		</div>
	</c:forEach>
	<!--           files         -->
	<c:forEach items="${files}" var="file">
		<div class="cell draggable" name="file-${file.id}">
			<div class="thumb">
				<c:choose>
					<c:when test="${ file.type.equals('IMAGE') }">
						<a data-toggle="modal" role="button" href="#ImageModal"
							onclick="setSRC('${file.name}')"><img class="trunc"
							title="${file.nameIncome}" src="res/img/browser/image-icon.png">
						</a>
					</c:when>
					<c:when test="${ file.type.equals('VIDEO') }">
						<a data-toggle="modal" role="button" href="#videoModal"
							onclick="loadVideoContent('${file.name}')"><img class="trunc"
							title="${file.nameIncome}" src="res/img/browser/video-icon.png">
						</a>
					</c:when>
					<c:when test="${ file.type.equals('AUDIO') }">
						<a data-toggle="modal" role="button" href="#audioModal"
							onclick="loadAudioContent('${file.name}')"> <img
							class="trunc" title="${file.nameIncome}"
							src="res/img/browser/music-icon.png">
						</a>
					</c:when>
					<c:otherwise>
						<img class="trunc" title="${file.nameIncome}"
							src="res/img/browser/default-icon.png">
					</c:otherwise>
				</c:choose>
			</div>
			<div class="cell-desc">
				<h5 class="title-name">
					<a href="download?file=${file.name}" title="${file.nameIncome}">${file.nameIncome}
					</a>
				</h5>
				<h5>
					<fmt:message key="Size" bundle="${lang}" />
					<dream:formatSize value="${file.size}" />
				</h5>
				<h6>
					<fmt:message key="Created_at" bundle="${lang}" />
					<fmt:formatDate type="date" value="${file.date}" />
				</h6>
			</div>
			<div class="file-buttons">
				<div class="btn-group">
					<h5>
						<a data-toggle="modal" role="button" href="#EditModal"
							onmousedown="set('fileidedit', '${file.id}')"> <span
							class="glyphicon glyphicon-pencil"></span>
						</a> <a data-toggle="modal" href="#DeleteModal"
							onclick="set('fileiddelete', ${file.id})"> <span
							class="glyphicon glyphicon-trash"></span></a>
					</h5>
				</div>
				<div class="btn-group check" data-toggle="buttons">
					<label class="btn btn-default btn-lg check-label"><input
						type="checkbox" class="cell-check" onchange="checkboxesStatus()"
						name="files" value="${file.id}"> <span class="check-span"></span>
					</label>
				</div>
				<div class="make-switch switch-mini" data-on="info"
					data-off="success"
					data-on-label="<fmt:message key='public' bundle='${lang}' />"
					data-off-label="private">
					<c:choose>
						<c:when test="${file.isPublic }">
							<input type="checkbox" onchange="setVisible(this)"
								data-file-id="${file.id}" checked="checked">
						</c:when>
						<c:otherwise>
							<input type="checkbox" onchange="setVisible(this)"
								data-file-id="${file.id}">
						</c:otherwise>
					</c:choose>
				</div>
				<h5>
					<a class="label-file-link" href="#" id="link-button${file.id }"
						onclick="showLink(this)" data-file-id="${file.id}"
						<c:if test="${file.isPublic==false}">
								hidden="hidden"
							</c:if>><span
						class="label label-default">link</span></a>
				</h5>
			</div>

		</div>
	</c:forEach>
</div>
<script type="text/javascript">
$('.label-toggle-switch').on('switch-change', function (e, data) {
    alert(data.value);
});

$(document).ready(function() {
	$(".draggable").draggable(
			{
				revert : "invalid",
				scroll : true,
				distance : 20,
				opacity : 0.9,
				helper : function() {
					var cellCheckBox = $(this).find('input.cell-check');
					if (!cellCheckBox.is(':checked')) {
						cellCheckBox.prop('checked', true);
						var check = $(this).find('.check');
						check.find('span').addClass('glyphicon glyphicon-chevron-down');
						check.find('label').addClass('active');
						buttonsStatus();
					}
					var selected = $('#gallery input.cell-check:checked')
							.parents('.draggable');
					var container = $('<div/>').attr('class', 'dropable');
					container.append(selected.clone()).css('z-index', 9999);
					return container;
				}
			});

	$(".droppable").droppable(
			{
				tolerance : 'pointer',
				drop : function(event, ui) {
					var moveable = [];
					$(ui.helper.children()).each(function() {
						moveable.push($(this).attr('name'));
					});
					var idTargetFolder = $(this).attr("id");
					move(moveable, idTargetFolder);
					$('#gallery input.cell-check:checked')
							.parents('.draggable').each(function() {
								if ($(this).attr('id') != idTargetFolder)
									$(this).remove();
							});
				},
				hoverClass : "hover-drop",
			});
});
$('.check').click(function() {
	var cb = $(this).find('input:checkbox').is(":checked");
	var span = $(this).find('span');
	if (cb) {
		span.removeClass('glyphicon glyphicon-chevron-down');
	} else {
		span.addClass('glyphicon glyphicon-chevron-down');
	}
});
</script>

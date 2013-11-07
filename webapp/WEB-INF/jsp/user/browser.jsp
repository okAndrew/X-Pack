<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://dreamhost.com/jsp/tags/" prefix="dream"%>

<link type="text/css" href="res/css/browser.css" rel="stylesheet">

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
					title="To up"><img
					src="res/img/browser/arrow-up-icon.png"
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
					src="res/img/browser/leopard-folder.png"
					height="70px"></a>
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
			<div class="btn-group check" data-toggle="buttons">
				<label class="btn btn-default"><input type="checkbox"
					class="cell-check" onchange="checkboxesStatus()" name="folders"
					value="${folder.id}"> <span
					class="glyphicon glyphicon-unchecked"></span> </label>
			</div>
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
							title="${file.nameIncome}"
							src="res/img/browser/image-icon.png">
						</a>
					</c:when>
					<c:when test="${ file.type.equals('VIDEO') }">
						<a data-toggle="modal" role="button" href="#videoModal"
							onclick="loadVideoContent('${file.name}')"><img class="trunc"
							title="${file.nameIncome}"
							src="res/img/browser/video-icon.png">
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
				<div class="file-buttons">
					<h5>
						<a href="download?file=${file.name}"> <span
							class="glyphicon glyphicon-download"></span></a>
						<c:choose>
							<c:when test="${file.isPublic }">
								<a href="#" id="link-button${file.id }"
									onclick="showLink(${file.id})"> <span
									class="glyphicon glyphicon-link"></span></a>
							</c:when>
							<c:otherwise>
								<a href="#" id="link-button${file.id }"
									onclick="showLink(${file.id})" class="hidden"> <span
									class="glyphicon glyphicon-link"></span></a>
							</c:otherwise>
						</c:choose>
					</h5>
				</div>
			</div>
			<div class="cell-desc">
				<h5 class="title-name">
					<a title="${file.nameIncome}">${file.nameIncome} </a>
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
			<div class="btn-group check" data-toggle="buttons">
				<label class="btn btn-default"><input type="checkbox"
					class="cell-check" onchange="checkboxesStatus()" name="files"
					value="${file.id}"> <span
					class="glyphicon glyphicon-unchecked"></span> </label>
			</div>
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

			<div class="public">
				<label><fmt:message key="public" bundle="${lang}" /> </label>
				<c:choose>
					<c:when test="${file.isPublic }">
						<input type="checkbox" onchange="setPublic(${file.id})"
							name="publicfiles" value="${file.id}" checked="checked">
					</c:when>
					<c:otherwise>
						<input type="checkbox" onchange="setPublic(${file.id})"
							name="publicfiles" value="${file.id}">
					</c:otherwise>
				</c:choose>
			</div>

		</div>
	</c:forEach>
</div>
<script type="text/javascript" src="res/js/browser.js"> </script>



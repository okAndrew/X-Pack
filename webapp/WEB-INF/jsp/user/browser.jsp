<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://dreamhost.com/jsp/tags/" prefix="dream"%>

<c:if test="${search==null || !search}">
	<ol class="breadcrumb">
		<c:forEach items="${folderpath}" var="folder">
			<li><a href="userfoldernav?folderid=${folder.id}">${folder.name}</a></li>
		</c:forEach>
	</ol>
</c:if>
<style>
#gallery {
	float: left;
	width: 100%;
	height: 100%;
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
	height: 110px;
	position: relative;
}

.thumb {
	text-decoration: none;
	float: left;
	margin-right: 6px;
}

img.trunc {
	height: 70px;
	width: 70px;
}

.cell-desc {
	height: 90px;
	width: 160px;
	float: left;
	margin-left: 10px;
	margin-right: 10px;
}

.hover-drop {
	background: #8C97A1;
}
</style>
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

<div id="gallery">
	<c:if
		test="${search!=null && search && search_no_result!=null && search_no_result}">
		<p>Your search returned no results</p>
	</c:if>
	<!-- upper -->
	<c:if test="${currentFolder.idUpper!=0 && (search==null || !search)}">
		<div class="cell droppable" id="${currentFolder.idUpper}">
			<div class="thumb">
				<a href="userfoldernav?folderid=${currentFolder.idUpper}"
					title="To up"><img
					src="http://icons.iconarchive.com/icons/icojam/blue-bits/64/arrow-up-icon.png"
					height="70px"></a>
			</div>
			<div class="cell-desc">
				<h4>${currentFolder.name}</h4>
				<h5>
					Total size:
					<dream:formatSize value="${currentFolder.size}" />
				</h5>
			</div>
		</div>
	</c:if>
	<!-- folders -->
	<c:forEach items="${folders}" var="folder">
		<div class="cell draggable droppable" name="folder-${folder.id}"
			id="${folder.id}">
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
						onmousedown="set('folderidedit', '${folder.id}')"> <span
						class="glyphicon glyphicon-pencil"></span>
					</a>
				</h5>
				<h5>
					<dream:formatSize value="${folder.size}" />
				</h5>
				<h6>
					<fmt:formatDate type="date" value="${folder.date}" />
				</h6>
			</div>
			<div class="btn-group">
				<h5>
					<a data-toggle="modal" role="button" href="#DeleteModal"
						onclick="set('folderiddelete', ${folder.id})"> <span
						class="glyphicon glyphicon-trash"></span>
					</a>
				</h5>
			</div>
			<div class="btn-group check" data-toggle="buttons">
				<label class="btn btn-default"><input type="checkbox"
					class="cell-check" onchange="checkboxesStatus()" name="folders"
					value="${folder.id}"> <span
					class="glyphicon glyphicon-unchecked"></span> </label>
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
							onclick="setSRC('${file.name}')"><img class="trunc"
							title="${file.nameIncome}"
							src="http://icons.iconarchive.com/icons/treetog/junior/256/document-picture-png-icon.png">
						</a>
					</c:when>
					<c:when test="${ file.type.equals('VIDEO') }">
						<a data-toggle="modal" role="button" href="#videoModal"
							onclick="loadVideoContent('${file.name}')"><img class="trunc"
							title="${file.nameIncome}"
							src="http://www.icon2s.com/wp-content/uploads/2013/01/Blue-File-Video-icon.png">
						</a>
					</c:when>
					<c:when test="${ file.type.equals('AUDIO') }">
						<a data-toggle="modal" role="button" href="#audioModal"
							onclick="loadAudioContent('${file.name}')"> <img
							class="trunc" title="${file.nameIncome}"
							src="https://cdn1.iconfinder.com/data/icons/camill-icons/256/camill_file_mp3.png">
						</a>
					</c:when>
					<c:otherwise>
						<img class="trunc" title="${file.nameIncome}"
							src="http://download.easyicon.net/png/30357/128/">
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
						onmousedown="set('fileidedit', '${file.id}')"> <span
						class="glyphicon glyphicon-pencil"></span>
					</a>
				</h5>
				<h5>
					Size:
					<dream:formatSize value="${file.size}" />
				</h5>
				<h6>
					Created at:
					<fmt:formatDate type="date" value="${file.date}" />
				</h6>

			</div>
			<div class="btn-group">
				<h5>
					<a data-toggle="modal" href="#DeleteModal"
						onclick="set('fileiddelete', ${file.id})"> <span
						class="glyphicon glyphicon-trash"></span>
					</a> <a href="download?file=${file.name}"> <span
						class="glyphicon glyphicon-download"></span>
					</a>
				</h5>
			</div>
			<div class="btn-group check" data-toggle="buttons">
				<label class="btn btn-default"><input type="checkbox"
					class="cell-check" onchange="checkboxesStatus()" name="files"
					value="${file.id}"> <span
					class="glyphicon glyphicon-unchecked"></span> </label>
			</div>
			<div class="public">
				<label>public: </label>
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


<script>
$('.check').click(function(){
	var cb = $(this).find('input:checkbox').is(":checked");
	var span = $(this).find('span');
	if(cb){
		span.removeClass('glyphicon-check');		
		span.addClass('glyphicon-unchecked');
	}else{
		span.addClass('glyphicon-check');
		span.removeClass('glyphicon-unchecked');
	}
});

$(function() {
	$(".draggable").draggable({
		revert : "invalid",
		scroll: true,
		distance: 20,
		opacity: 0.9,
		helper: function(){
			var cellCheckBox = $(this).find('input.cell-check');
			if(!cellCheckBox.is(':checked')){
				cellCheckBox.attr('checked', true);
				cellCheckBox.click();
			}
			var selected = $('#gallery input.cell-check:checked').parents('.draggable');
		    if (selected.length === 0) {
		      	selected = $(this);
		    }
		    var container = $('<div/>').attr('class', 'dropable');
		    container.append(selected.clone()).css('z-index', 9999);
		    return container; 
		}
	});

	$(".droppable").droppable({
		tolerance: 'pointer',
		drop : function(event, ui) {
			var moveable = [];
			$(ui.helper.children()).each(function(){
				moveable.push($(this).attr('name'));
			});
			var idTargetFolder = $(this).attr("id");
			move(moveable, idTargetFolder);
			$('#gallery input.cell-check:checked').parents('.draggable').each(function(){
				if($(this).attr('id') != idTargetFolder)
					$(this).remove();
			});
		},
    	hoverClass: "hover-drop",
	});
});
function move(moveable, idtargetFolder) {
	$.ajax({
		type : "POST",
		url : 'move',
		data : {
			'moveable[]' : moveable,
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
		$('input.cell-check').prop('checked', source.checked);
		if (source.checked) {
			$('.check').find('span').removeClass('glyphicon-unchecked');
			$('.check').find('span').addClass('glyphicon-check');
			$('.check').find('label').addClass('active');
			$('.select-all').find('span').removeClass('glyphicon-unchecked');
			$('.select-all').find('span').addClass('glyphicon-check');
		} else {
			$('.check').find('span').addClass('glyphicon-unchecked');
			$('.check').find('span').removeClass('glyphicon-check');
			$('.check').find('label').removeClass('active');
			$('.select-all').find('span').addClass('glyphicon-unchecked');
			$('.select-all').find('span').removeClass('glyphicon-check');
		}
		buttonsStatus();
	}

	function checkboxesStatus() {
		if ($('input.cell-check:not(:checked)').length === 1) {
			$('input.select-all-input').prop('checked', false);
			$('.select-all').find('span').addClass('glyphicon-unchecked');
			$('.select-all').find('span').removeClass('glyphicon-check');
			$('.select-all').find('label').removeClass('active');
		} else if ($('input.cell-check:not(:checked)').length === 0) {
			$('input.select-all-input').prop('checked', true);
			$('.select-all').find('span').removeClass('glyphicon-unchecked');
			$('.select-all').find('span').addClass('glyphicon-check');
			$('.select-all').find('label').addClass('active');
		}
		buttonsStatus();
	}

	function set(targetElementId, id) {
		$('#'+targetElementId).attr('value', id);
	}
	function getCurFolderId() {
		document.getElementById("folderidmove").getAttribute("value");
	}
	
	function selectAll() {
		$('input.cell-check').attr('checked', true);
		var spans = $('span.glyphicon-unchecked');
		spans.removeClass('glyphicon-unchecked');
		spans.addClass('glyphicon-check');
	}
	
	function buttonsStatus() {
		var checkboxes = document.getElementsByName('folders');
		for ( var i = 0, n = checkboxes.length; i < n; i++) {
			if (checkboxes[i].checked === true) {
				$('#download').prop('disabled', false);
		    	$('#delete').prop('disabled', false);
				return;
			}
		}
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

	function setPublic(id) {
		var publicCheckbox = $("input[name=publicfiles][value=" + id + "]");
		var state = false;
		if (publicCheckbox.is(':checked')) {
			state = true;
		}
		$("#link-button" + id).prop('disabled', !state);
		$.ajax({
			type : "POST",
			url : 'changepublicstate',
			data : {
				'fileId' : id,
				'state' : state
			},
			success: function(data){
				if(data != null && data != ""){
					$("#link input").val(data);
					$('#linkModal').modal('show');
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert('xhr.status ' + xhr.status + '   thrownError:'
						+ thrownError);
			}
		});
	}
</script>



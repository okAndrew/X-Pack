$(function() {
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
						check.find('span').removeClass('glyphicon-unchecked');
						check.find('span').addClass('glyphicon-check');
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
function move(moveable, idtargetFolder) {
	$
			.ajax({
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

$('.check').click(function() {
	var cb = $(this).find('input:checkbox').is(":checked");
	var span = $(this).find('span');
	if (cb) {
		span.removeClass('glyphicon-check');
		span.addClass('glyphicon-unchecked');
	} else {
		span.addClass('glyphicon-check');
		span.removeClass('glyphicon-unchecked');
	}
});
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
	$('#' + targetElementId).attr('value', id);
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

function showLink(id) {
	var state = $("input[name=publicfiles][value=" + id + "]").is(':checked');
	$
			.ajax({
				type : "POST",
				url : 'changepublicstate',
				data : {
					'fileId' : id,
					'state' : state
				},
				success : function(data) {
					if (data != null && data != "") {
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

function setSRC(name) {
	document.getElementById("img").src = "download?file=" + name;
}

function setPublic(id) {
	var state = $("input[name=publicfiles][value=" + id + "]").is(':checked');
	$("#link-button" + id).toggleClass("hidden");
	$
			.ajax({
				type : "POST",
				url : 'changepublicstate',
				data : {
					'fileId' : id,
					'state' : state
				},
				error : function(xhr, ajaxOptions, thrownError) {
					alert('xhr.status ' + xhr.status + '   thrownError:'
							+ thrownError);
				}
			});
}
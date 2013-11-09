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

function toggle(source) {
	$('input.cell-check').prop('checked', source.checked);
	if (source.checked) {
		$('.check').find('span').addClass('glyphicon glyphicon-chevron-down');
		$('.check').find('label').addClass('active');
		$('.select-all').find('span').removeClass('glyphicon-unchecked');
		$('.select-all').find('span').addClass('glyphicon-check');
	} else {
		$('.check').find('span')
				.removeClass('glyphicon glyphicon-chevron-down');
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
	var spans = $('span.check-span');
	spans.addClass('glyphicon glyphicon-chevron-down');
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

function showLink(label) {
	var id = $(label).attr('data-file-id');
	$
			.ajax({
				type : "POST",
				url : 'changepublicstate',
				data : {
					'fileId' : id,
					'state' : 'true'
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

function setVisible(checkbox) {
	var id = $(checkbox).attr('data-file-id');
	var state = $(checkbox).is(':checked');
	if (state == false) {
		$("#link-button" + id).attr('hidden', 'hidden');
	} else {
		$("#link-button" + id).removeAttr('hidden');
	}
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
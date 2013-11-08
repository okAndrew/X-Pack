<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="modal fade" id="audioModal" tabindex="-1" role="dialog"
	aria-labelledby="audioModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
				<div id="audio-container" align="center"></div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script src="res/js/audio.min.js"></script>


<script type="text/javascript">
	function loadAudioContent(name) {
		$.ajax({
			type : "GET",
			url : 'audiocontent?name=' + name,
			success : function(url) {
				$('#audio-container').html(
						$('<audio  id="audio-player" preload="none"></audio>'));
				$("#audio-player").attr('src', url);
				audiojs.createAll();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert('xhr.status ' + xhr.status + '   thrownError:'
						+ thrownError);
			}
		});
		$("#audioModal").modal('show');
	}
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#audioModal').on('hidden.bs.modal', function() {
			$('audio').stop();
			$('audio').remove();
		});
	});
</script>


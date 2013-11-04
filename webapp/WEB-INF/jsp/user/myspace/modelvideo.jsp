<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- Modal -->
<div class="modal fade" id="videoModal" tabindex="-1" role="dialog"
	aria-labelledby="videoModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
				<div id="video-player"></div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script type="text/javascript">
	function loadVideoContent(name) {
		$.ajax({
			type : "GET",
			url : 'videocontent?name=' + name,
			success : function(data) {
				$("#video-player").html(data);
				$("#video-player-inside").flowplayer({
					engine : 'flash'
				});
			},
			error : function(xhr, ajaxOptions, thrownError) {
				alert('xhr.status ' + xhr.status + '   thrownError:'
						+ thrownError);
			}
		});
		$("#videoModal").modal('show');
	}
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#videoModal').on('hidden.bs.modal', function() {
			$("#video-player").html('');
		});
	});
</script>

<script src="//releases.flowplayer.org/5.4.3/flowplayer.min.js"></script>
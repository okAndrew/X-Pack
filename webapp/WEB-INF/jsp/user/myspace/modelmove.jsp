<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<form action="move" method="post">
	<div class="modal fade" id="MoveModal" tabindex="-1" role="dialog"
		aria-labelledby="MoveModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3 id="MoveModalLabel">
						<fmt:message key="Moving_confirmation" bundle="${lang}" />
					</h3>
				</div>
				<div class="modal-body">
					<div id="movecheckboxes">
					</div>
					<input type="hidden" id="folderidmove" name="folderidmove">
					<input type="hidden" id="fileidmove" name="fileidmove">
					<p>
						<fmt:message key="Please_select_folder_to_move" bundle="${lang}" />
					</p>
					<c:forEach items="${folders}" var="folder">
							<input type="radio" name="folderidtarget" value="${folder.id}"> ${folder.name}<br>
					</c:forEach>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<fmt:message key="Cancel" bundle="${lang}" />
					</button>
					<button type="submit" class="btn btn-success" onclick="getCheckboxes()">
						<fmt:message key="Move" bundle="${lang}" />
					</button>
				</div>
			</div>
		</div>
	</div>
</form>
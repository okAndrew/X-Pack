<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<form action="admindelete" method="post">
	<div class="modal fade" id="DeleteModal" tabindex="-1" role="dialog"
		aria-labelledby="DeleteModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3 id="DeleteModalLabel">
						<fmt:message key="Delete_Confirmation" bundle="${lang}" />
					</h3>
				</div>
				<div class="modal-body">
					<input type="hidden" id="folderiddelete" name="folderid"> <input
						type="hidden" id="fileiddelete" name="fileid">
					<p class="error-text">
						<fmt:message key="Are_you_sure_you_want_to_delete"
							bundle="${lang}" />
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<fmt:message key="Cancel" bundle="${lang}" />
					</button>
					<button type="submit" name="deletesingle" class="btn btn-danger">
						<fmt:message key="Delete" bundle="${lang}" />
					</button>
				</div>
			</div>
		</div>
	</div>
</form>
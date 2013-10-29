<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<form action="useredit" method="post">
	<div class="modal fade" id="EditModal" tabindex="-1" role="dialog"
		aria-labelledby="EditModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">
						<fmt:message key="Edit" bundle="${lang}" />
					</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="fileidedit" name="fileid"> <input
						type="hidden" id="folderidedit" name="folderid"> <input
						class="form-control" type="text" name="editname"
						placeholder="name">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<fmt:message key="Close" bundle="${lang}" />
					</button>
					<button type="submit" class="btn btn-primary">
						<fmt:message key="Edit" bundle="${lang}" />
					</button>
				</div>
			</div>
		</div>
	</div>
</form>
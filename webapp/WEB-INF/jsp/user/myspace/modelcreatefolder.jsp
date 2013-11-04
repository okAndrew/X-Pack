<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="modal fade" id="createFolderModal" tabindex="-1" role="dialog" aria-labelledby="createFolderModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title"><fmt:message key="Create_folder" bundle="${lang}" /></h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-info" id="edit-alert">Can only consist
					of symbols ".","(", ")", "-", digits, and letters of the English,
					Ukrainian, Russian or German alphabet</div>
				<form id="form_create_folder" action="createfolder" method="post">
					<div class="form-group">
						<label>Folder name</label> <input type="text" name="foldername"
							placeholder="folder name" class="form-control" value=""
							pattern="[A-Za-zА-Яа-яЁёІіЇїЙйЄє0-9-._\\s\\(\\)]{1,50}$" />
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="Close" bundle="${lang}" /></button>
				<input type="submit" form="form_create_folder" class="btn btn-primary" value="<fmt:message key="Create" bundle="${lang}" />" />
			</div>
		</div>
	</div>
</div>
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
					<div class="alert alert-info" id="edit-alert">
						<fmt:message
							key="Can_only_consist_of_symbols_'.','(',_')',_'-',_digits,_and_letters_of_the_English,_Ukrainian,_Russian"
							bundle="${lang}" />
					</div>
					<input type="hidden" id="fileidedit" name="fileid"> <input
						type="hidden" id="folderidedit" name="folderid"> <input
						class="form-control" type="text" name="editname"
						placeholder="<fmt:message key='Name' bundle='${lang}' />"
						pattern="[A-Za-zА-Яа-яЁёІіЇїЙйЄє0-9-._\s\(\)]{1,50}$">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<fmt:message key="Close" bundle="${lang}" />
					</button>
					<button type="submit" class="btn btn-primary">
						<fmt:message key="Save" bundle="${lang}" />
					</button>
				</div>
			</div>
		</div>
	</div>
</form>
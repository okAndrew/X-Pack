<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="modal fade" id="uploadFormModal" tabindex="-1" role="dialog" aria-labelledby="uploadFormModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">
                   	<fmt:message key="Upload_files" bundle="${lang}" />
				</h4>
			</div>
			<div class="modal-body">
				<form method="post" action="upload" enctype="multipart/form-data" id="form_upload">
					<div class="form-group">
						<label>File input</label>
						<input type="file" multiple name="fileName" />
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="Close" bundle="${lang}" /></button>
				<input type="submit" class="btn btn-primary" form="form_upload" value="<fmt:message key="Upload" bundle="${lang}" />">
			</div>
		</div>
	</div>
</div>   
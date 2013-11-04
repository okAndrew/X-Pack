<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="modal fade" id="linkModal" tabindex="-1" role="dialog"
	aria-labelledby="linkModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="Download_link" bundle="${lang}" />
				</h4>
			</div>
			<div class="modal-body" id="link">
				<input class="form-control" type="text" name="filelink"
					placeholder="Link to download file" onClick="this.select();" />
			</div>
		</div>
	</div>
</div>
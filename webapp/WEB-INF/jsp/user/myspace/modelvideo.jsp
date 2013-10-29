<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="modal fade" id="VideoModal" tabindex="-1" role="dialog"
	aria-labelledby="VideoModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
			<video width="320" height="240" controls>
	  <source src="download?fileid=184" type="video/mp4" id="video">
	</video>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="Close" bundle="${lang}" />
				</button>
			</div>
		</div>
	</div>
</div>

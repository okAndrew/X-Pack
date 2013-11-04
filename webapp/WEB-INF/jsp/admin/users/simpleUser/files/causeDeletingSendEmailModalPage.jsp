<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/jquery-ui.min.js"
	type="text/javascript"></script>
<script type="text/javascript" src="res/js/modal.js"></script>
<script src="res/js/bootstrap.js"></script>
<link href="res/css/style.css" rel="stylesheet" />

<div class="modal fade" id="causeDeletingSendEmailModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title"><fmt:message key="Send_Email_with_cause_of_deleting_files" bundle="${lang}" /></h4>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="navbar navbar-default">
						<div class="navbar-collapse collapse">
							<table class="table">
								<tbody>
									<tr>
										<td><fmt:message key="Cause" bundle="${lang}" /></td>
									</tr>
									<tr>
										<td><textarea class="form-control" rows="5"
												name="message" class="form-control last"
												placeholder="Message"> </textarea></td>
									</tr>
								</tbody>
							</table>
						</div>
						<!--/.nav-collapse -->
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary" name="delete"
					value="sendEmailUsers"><fmt:message key="Submit" bundle="${lang}" /></button>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="Close" bundle="${lang}" />
				</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
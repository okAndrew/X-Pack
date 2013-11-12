<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="res/js/modal.js"></script>
<script src="res/js/bootstrap.js"></script>
<link href="res/css/style.css" rel="stylesheet" />

<div class="modal fade" id="sendEmailModal" tabindex="-1">

	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<fmt:message key="Send_Email" bundle="${lang}" />
				</h4>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="navbar navbar-default">
						<div class="navbar-collapse collapse">
							<table class="table">
								<tbody>
									<tr>
										<td><fmt:message key="Subject" bundle="${lang}" /></td>
										<td><input type="text" name="subject"
											class="form-control midle"
											placeholder=<fmt:message key="Subject" bundle="${lang}" /> /></td>
									</tr>
									<tr>
										<td><fmt:message key="Message" bundle="${lang}" /></td>
										<td><textarea class="form-control" rows="5" name="body"
												class="form-control last"
												placeholder=<fmt:message key="Message" bundle="${lang}" />> </textarea></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<button type="button" class="btn btn-default pull-right"
						data-dismiss="modal">
						<fmt:message key="Close" bundle="${lang}" />
					</button>
					<button type="submit" class="btn btn-primary pull-right"
						name="action" value="sendEmailUsers">
						<fmt:message key="Send" bundle="${lang}" />
					</button>

				</div>
			</div>
		</div>
	</div>
</div>

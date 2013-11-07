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
				<h4 class="modal-title">Send Email</h4>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="navbar navbar-default">
						<div class="navbar-collapse collapse">
							<table class="table">
								<tbody>
									<tr>
										<td>Subject</td>
										<td><input type="text" name="subject"
											class="form-control midle" placeholder="Subject" /></td>
									</tr>
									<tr>
										<td>Message</td>
										<td><textarea class="form-control" rows="5" name="email"
											class="form-control last" placeholder="Message"> </textarea></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary" name="action"
					value="sendEmailUsers">Send</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="Close" bundle="${lang}" />
				</button>
			</div>
		</div>
	</div>
</div>

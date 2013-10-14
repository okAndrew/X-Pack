<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal fade" id="addTariffModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">Add new tariff</h4>
			</div>
			<div class="modal-body">
				<div class="container">
					<!-- Static navbar -->
					<div class="navbar navbar-default">
						<div class="navbar-collapse collapse">
							<form action="addTariff" method="post">
								<div id="errorinfo" class="alert alert-danger"
									style="display: none;">
									<c:if test="${message != null}">
											${message}
									</c:if>
								</div>
								<table class="table">
									<tbody>
										<tr>
											<td>Name</td>
											<td><input type="text" name="name"
												class="form-control first" placeholder="name"
												autofocus="autofocus" /></td>
										</tr>

										<tr>
											<td>Max Capacity</td>
											<td><input type="text" name="maxCapacity"
												class="form-control last" placeholder="Max Capacity" /></td>
										</tr>
									</tbody>
								</table>
								<button type="submit" class="btn btn-primary" name="action" value="addTariff">Add new
									tariff</button>
							</form>
						</div>
						<!--/.nav-collapse -->
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
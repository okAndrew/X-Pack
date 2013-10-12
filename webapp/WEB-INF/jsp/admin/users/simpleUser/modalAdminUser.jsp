<script lang="javascript">
	function getPlaceHolder(id, id2) {
		var placeholder = document.getElementById(id);
		document.getElementById(id2).value = placeholder.value;
		formToSubmit.submit();
	}
</script>


<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">Modal title</h4>
			</div>

			<div class="modal-body">
				<div class="container">
					<!-- Static navbar -->
					<div class="navbar navbar-default">
						<div class="navbar-collapse collapse">
							<form action="updateUser" method="post">
								<table class="table">
									<tbody>
										<tr>
											<td>User id</td>
											<!-- add placeholder!!!!!! -->
											<td><input type="text" name="userIdHolder"
												class="form-control first" value="${user.id}"
												autofocus="autofocus"></td>
										</tr>
										<tr>
											<td>Login</td>
											<td><input type="text" name="userLogin"
												class="form-control first" value="${user.login}"
												autofocus="autofocus"></td>
										</tr>
										<tr>
											<td>Email</td>
											<td><input type="text" name="userEmail"
												class="form-control first" value="${user.email}"
												autofocus="autofocus"></td>
										</tr>
										<tr>
											<td>Tariff</td>
											<td><input type="text" name="userIdTariff"
												class="form-control first" value="${user.idTariff}"
												autofocus="autofocus"></td>
										</tr>
										<tr>
											<td>Capacity</td>
											<td><input type="text" placeHolder="${user.capacity}"
												disabled /></td>
										</tr>
										<tr>
											<td>Token</td>
											<td><input type="text" name="userToken"
												class="form-control first" value="${user.token}"
												autofocus="autofocus"></td>
										</tr>
									</tbody>
								</table>

								<button type="submit" class="btn btn-default">Save
									changes</button>
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


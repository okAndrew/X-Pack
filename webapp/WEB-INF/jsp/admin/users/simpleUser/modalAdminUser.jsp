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
							<table class="table">
								<tbody>
									<tr>
										<td>User id</td>
										<!-- add placeholder!!!!!! -->
										<td><input type="text" id="userIdHolder"
											placeHolder="${user.id}" disabled /></td>
										<td><input type="hidden" name="userIdHidden"
											id="hiddenId" value="${user.id}" /></td>
									</tr>
									<tr>
										<td>Login</td>
										<td><input type="text" id="loginHolder"
											placeHolder="${user.login}"
											onchange="getPlaceHolder('loginHolder', 'hiddenLogin')" /></td>
										<td><input type="hidden" id="hiddenLogin"
											name="userLogin" value="${user.login}"></td>
									</tr>
									<tr>
										<td>Email</td>
										<td><input type="text" id="emailHolder"
											placeHolder="${user.email}"
											onchange="getPlaceHolder('emailHolder', 'hiddenEmail')" /></td>
										<td><input type="hidden" id="hiddenEmail"
											name="userEmail" value="${user.email}"></td>
									</tr>
									<tr>
										<td>Tariff</td>
										<td><input type="text" id="tariffHolder"
											placeHolder="${user.idTariff}"
											onchange="getPlaceHolder('tariffHolder', 'hiddenTariff')" /></td>
										<td><input type="hidden" id="hiddenTariff"
											name="userIdTariff" value="${user.idTariff}"></td>
									</tr>
									<tr>
										<td>Capacity</td>
										<td><input type="text" placeHolder="${user.capacity}"
											disabled /></td>
									</tr>
									<tr>
										<td>Token</td>
										<td><input type="text" id="tokenHolder"
											placeHolder="${user.token}"
											onchange="getPlaceHolder('tokenHolder', 'hiddenToken')" /></td>
										<td><input type="hidden" id="hiddenToken"
											name="userToken" value="${user.token}"></td>
									</tr>

								</tbody>
							</table>
							<form action="updateUser" method="post">
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

